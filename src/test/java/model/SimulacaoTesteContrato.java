package model;
import factory.SimulacaoDataFactory;
import io.restassured.http.ContentType;
import org.junit.Test;
import pojo.Simulacao;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static util.Auxiliar.carregarUrl;
import io.restassured.response.ValidatableResponse;

public class SimulacaoTesteContrato {

    static Simulacao adicionarSimulacao;
    private static ValidatableResponse response;

    @Test
    public void adicionandoSimulacaoComSucesso() throws IOException {
        carregarUrl();
        adicionarSimulacao = SimulacaoDataFactory.criarSimulacaoValida();

        response =
                given()
                    .body(adicionarSimulacao)
                    .contentType(ContentType.JSON)
                .when()
                    .post("/v1/simulacoes/")
                .then()
                        .body(matchesJsonSchemaInClasspath("schemas/simulacoes.json"));
    }
}
