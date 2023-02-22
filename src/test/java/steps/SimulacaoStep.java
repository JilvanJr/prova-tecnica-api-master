package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.SimulacaoModel;

import java.io.IOException;

public class SimulacaoStep {

    SimulacaoModel simulacaoModel = new SimulacaoModel();

    @When("faco um POST para o {string} passando condicao de sucesso")
    public void faco_um_post_para_o_passando_condicao_de_sucesso(String endpoint) throws IOException {
        simulacaoModel.adicionandoSimulacaoComSucesso(endpoint);
    }

    @Then("recebo o {int} de sucesso")
    public void recebo_o_da_inclusao_de_sucesso(Integer statusCode) {
        simulacaoModel.validandoStatusCode(statusCode);
    }

    @When("faco um POST para {string} passando a condicao {string} do cpf no body")
    public void faco_um_post_para_passando_a_condicao_do_cpf_no_body(String condicao, String endpoint) throws IOException {
        simulacaoModel.adicionandoSimulacaoComErroNoCpf(endpoint, condicao);
    }

    @Then("recebo o {int} de erro")
    public void recebo_o_de_erro(Integer statusCode) {
        simulacaoModel.validandoStatusCodeComErro(statusCode);
    }

    @When("faco um POST para {string} passando a condicao {string} do email no body")
    public void faco_um_post_para_passando_a_condicao_do_email_no_body(String condicao, String endpoint) throws IOException {
        simulacaoModel.adicionandoSimulacaoComErroNoEmail(endpoint, condicao);
    }

    @When("faco um POST para {string} passando a condicao {string} do valor no body")
    public void faco_um_post_para_passando_a_condicao_do_valor_no_body(String condicao, String endpoint) throws IOException {
        simulacaoModel.adicionandoSimulacaoComValorInvalido(endpoint, condicao);
    }


    @When("faco um POST para {string} passando a condicao {string} da parcela no body")
    public void faco_um_post_para_passando_a_condicao_da_parcela_no_body(String condicao, String endpoint) throws IOException {
        simulacaoModel.adicionandoSimulacaoComParcelaInvalido(endpoint, condicao);
    }

    @When("faco um PUT para {string} passando a condicao {string} no body")
    public void faco_um_put_para_passando_a_condicao_no_body(String condicao, String endpoint) throws IOException {
        simulacaoModel.alterandoSimulacaoComSucesso(endpoint, condicao);
    }

    @When("faco um PUT para {string} passando a condicao do email {string} no body")
    public void faco_um_put_para_passando_a_condicao_do_email_no_body(String condicao, String endpoint) throws IOException {
        simulacaoModel.alterandoSimulacaoComErroNoEmail(endpoint, condicao);
    }

    @When("faco um PUT para {string} passando a condicao do cpf {string} no body")
    public void faco_um_put_para_passando_a_condicao_do_cpf_no_body(String condicao, String endpoint) throws IOException {
        simulacaoModel.alterandoSimulacaoComErroNoCpf(endpoint, condicao);
    }

    @When("faco um PUT para {string} passando a condicao do parcela {string} no body")
    public void faco_um_put_para_passando_a_condicao_do_parcela_no_body(String condicao, String endpoint) throws IOException {
        simulacaoModel.alterandoSimulacaoCom1Parcela(endpoint, condicao);
    }

    @When("faco um GET no {string}")
    public void faco_um_get_no(String endpoint) {
        simulacaoModel.consultarTodasSimulacoes(endpoint);
    }

    @When("faco um GET no {string} passando o cpf")
    public void faco_um_get_no_passando_o_cpf(String endpoint) throws IOException {
        simulacaoModel.consultarSimulacaoPorCpf(endpoint);
    }

    @When("faco um GET no {string} passando um cpf  sem simulacao")
    public void faco_um_get_no_passando_um_cpf_sem_simulacao(String endpoint) {
        simulacaoModel.consultarCpfSemSimulacao(endpoint);
    }

    @When("faco um DELETE no {string} passando o id")
    public void faco_um_delete_no_passando_o_id(String endpoint) throws IOException {
        simulacaoModel.deletarSimulacao(endpoint);
    }
}
