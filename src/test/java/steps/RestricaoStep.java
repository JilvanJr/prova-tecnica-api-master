package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.RestricaoModel;

public class RestricaoStep {

    RestricaoModel restricaoModel = new RestricaoModel();

    @When("faco um GET para {string} passando o {string}")
    public void faco_um_get_para_passando_o(String endpoint, String cpf) {
        restricaoModel.consultandoCpfComRestricao(endpoint, cpf);
    }

    @Then("recebo o {int}")
    public void recebo_o(Integer statusCode) {
        restricaoModel.validandoStatusCode(statusCode);
    }
}
