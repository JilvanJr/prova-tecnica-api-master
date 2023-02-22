package model;

import factory.SimulacaoDataFactory;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import pojo.Simulacao;

import java.io.IOException;

import static factory.SimulacaoDataFactory.*;
import static io.restassured.RestAssured.*;
import static util.Auxiliar.*;

public class SimulacaoModel {

    private static ValidatableResponse response;
    static Simulacao adicionarSimulacao;

    // POST ---- /api/v1/simulacoes
    public void adicionandoSimulacaoComSucesso(String endpoint) throws IOException {
        carregarUrl();
        adicionarSimulacao = SimulacaoDataFactory.criarSimulacaoValida();

        response =
                given()
                    .body(adicionarSimulacao)
                    .contentType(ContentType.JSON)
                .when()
                    .post(endpoint)
                .then();
    }

    public void adicionandoSimulacaoComErroNoCpf(String condicao, String endpoint) throws IOException {
        carregarUrl();
        adicionarSimulacao = SimulacaoDataFactory.simulacaoComCpfRepetido();

        if (condicao.contains("AddComCpfNull")) {
            adicionarSimulacao = SimulacaoDataFactory.simulacaoComCpfNull();
        }

        response =
                given()
                    .body(adicionarSimulacao)
                    .contentType(ContentType.JSON)
                .when()
                    .post(endpoint)
                .then();
    }

    public void adicionandoSimulacaoComErroNoEmail(String condicao, String endpoint) throws IOException {
        carregarUrl();
        adicionarSimulacao = SimulacaoDataFactory.simulacaoComEmailInvalidoSemArroba();

        if (condicao.contains("EmailSem.")) {
            adicionarSimulacao = SimulacaoDataFactory.simulacaoComEmailInvalidoSemPonto();
        } else if (condicao.contains("EmailNull")) {
            adicionarSimulacao = SimulacaoDataFactory.simulacaoComEmailNull();
        }

        response =
                given()
                    .body(adicionarSimulacao)
                    .contentType(ContentType.JSON)
                .when()
                    .post(endpoint)
                .then();
    }

    public void adicionandoSimulacaoComValorInvalido(String condicao, String endpoint) throws IOException {
        carregarUrl();
        adicionarSimulacao = SimulacaoDataFactory.simulacaoComValorMaior40K();

        response =
                given()
                    .body(adicionarSimulacao)
                    .contentType(ContentType.JSON)
                .when()
                    .post(endpoint)
                .then();
    }

    public void adicionandoSimulacaoComParcelaInvalido(String condicao, String endpoint) throws IOException {
        carregarUrl();
        adicionarSimulacao = SimulacaoDataFactory.simulacaoComParcelaInvalida();

        response =
                given()
                    .body(adicionarSimulacao)
                    .contentType(ContentType.JSON)
                .when()
                    .post(endpoint)
                .then();
    }

    // PUT ---- /api/v1/simulacoes/{cpf}
    public void alterandoSimulacaoComSucesso(String condicao, String endpoint) throws IOException {
        carregarUrl();
        String getCpf = SimulacaoModel.retornaCpfDaSimulacaoCadastrada();

        response =
                given()
                    .body(alterarSimulacao())
                    .contentType(ContentType.JSON)
                .when()
                    .put(endpoint + getCpf)
                .then();
    }

    public void alterandoSimulacaoComErroNoEmail(String condicao, String endpoint) throws IOException {
        carregarUrl();
        String getCpf = SimulacaoModel.retornaCpfDaSimulacaoCadastrada();
        adicionarSimulacao = SimulacaoDataFactory.simulacaoComEmailInvalidoSemArroba();

        if (condicao.contains("EmailSem.")) {
            adicionarSimulacao = SimulacaoDataFactory.simulacaoComEmailInvalidoSemPonto();
        }

        response =
                given()
                    .body(adicionarSimulacao)
                    .contentType(ContentType.JSON)
                .when()
                    .put(endpoint + getCpf)
                .then();
    }

    public void alterandoSimulacaoComErroNoCpf(String condicao, String endpoint) throws IOException {
        carregarUrl();
        SimulacaoModel.retornaCpfDaSimulacaoCadastrada();
        String getCpf = response.extract().jsonPath().getString("[1].cpf");
        adicionarSimulacao = SimulacaoDataFactory.simulacaoComCpfRepetido();

        if (condicao.contains("cpfSemSimulacao")) {
            getCpf = gerarNovoCPF();
        }

        response =
                given()
                    .body(adicionarSimulacao)
                    .contentType(ContentType.JSON)
                .when()
                    .put(endpoint + getCpf)
                .then();
    }

    public void alterandoSimulacaoCom1Parcela(String condicao, String endpoint) throws IOException {
        carregarUrl();
        String getCpf = SimulacaoModel.retornaCpfDaSimulacaoCadastrada();
        adicionarSimulacao = SimulacaoDataFactory.simulacaoComParcelaInvalida();

        response =
                given()
                    .body(adicionarSimulacao)
                    .contentType(ContentType.JSON)
                .when()
                    .put(endpoint + getCpf)
                .then();
    }

    public static String retornaCpfDaSimulacaoCadastrada() throws IOException {
        adicionarSimulacao = SimulacaoDataFactory.criarSimulacao();

        response = get("/v1/simulacoes").then();

        return response.extract().jsonPath().getString("[0].cpf");
    }

    public void validandoStatusCode(Integer statusCode) {
        response.assertThat().statusCode(statusCode);
    }

    public void validandoStatusCodeComErro(Integer statusCode) {
        response.assertThat().statusCode(statusCode);
    }

    // GET ---- /api/v1/simulacoes
    public void consultarTodasSimulacoes(String endpoint) {
        carregarUrl();
        response = get(endpoint).then();
    }

    // GET ---- /api/v1/simulacoes/{cpf}
    public void consultarSimulacaoPorCpf(String endpoint) throws IOException {
        carregarUrl();
        String getCpf = SimulacaoModel.retornaCpfDaSimulacaoCadastrada();
        adicionarSimulacao = SimulacaoDataFactory.simulacaoComParcelaInvalida();

        response = get(endpoint + getCpf).then();
    }

    public void consultarCpfSemSimulacao(String endpoint) {
        carregarUrl();
        response = get(endpoint + gerarNovoCPF()).then();
    }

    // DELETE ---- /api/v1/simulacoes/{id}
    public void deletarSimulacao(String endpoint) throws IOException {
        carregarUrl();
        SimulacaoModel.retornaCpfDaSimulacaoCadastrada();
        String getId = response.extract().jsonPath().getString("[0].id");

        response = delete(endpoint + getId).then();
    }
}
