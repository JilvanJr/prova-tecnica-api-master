package model;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.when;
import static org.junit.Assert.assertTrue;
import static util.Auxiliar.*;

public class RestricaoModel {

    private ValidatableResponse response;
    private String getBody;

    public void consultandoCpfComRestricao(String endpoint, String cpf) {
        carregarUrl();
        if (cpf.contains("novoCPF")) {
            cpf = gerarNovoCPF();
        }

        response =
                when()
                        .get(endpoint + cpf)
                        .then();

        this.getBody = response.extract().body().asString();
    }

    public void validandoStatusCode(Integer statusCode) {
        response.assertThat().statusCode(statusCode);
        if (statusCode == 200) {
            assertTrue(getBody.contains("tem problema"));
        }
    }
}
