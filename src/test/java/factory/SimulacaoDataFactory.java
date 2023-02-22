package factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.SimulacaoModel;
import pojo.Simulacao;

import java.io.FileInputStream;
import java.io.IOException;

import static util.Auxiliar.gerarNovoCPF;

public class SimulacaoDataFactory {

    public static Simulacao criarSimulacao() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Simulacao simulacao = objectMapper.readValue(new FileInputStream("src/test/resources/request/simulacoes.json"), Simulacao.class);
        return simulacao;
    }

    public static Simulacao criarSimulacaoValida() throws IOException {
        Simulacao simulacaoValida = criarSimulacao();
        simulacaoValida.setCpf(gerarNovoCPF());
        return simulacaoValida;
    }


    public static Simulacao simulacaoComCpfRepetido() throws IOException {
        Simulacao simulacaoCpfRepetido = criarSimulacao();
        simulacaoCpfRepetido.setCpf(SimulacaoModel.retornaCpfDaSimulacaoCadastrada());
        return simulacaoCpfRepetido;
    }

    public static Simulacao simulacaoComCpfNull() throws IOException {
        Simulacao simulacaoCpfNull = criarSimulacao();
        simulacaoCpfNull.setCpf(null);
        return simulacaoCpfNull;
    }

    public static Simulacao simulacaoComEmailInvalidoSemArroba() throws IOException {
        Simulacao simulacaoComEmailInvalido = criarSimulacao();
        simulacaoComEmailInvalido.setCpf(gerarNovoCPF());
        simulacaoComEmailInvalido.setEmail("email.com.br");
        return simulacaoComEmailInvalido;
    }

    public static Simulacao simulacaoComEmailNull() throws IOException {
        Simulacao simulacaoComEmailInvalido = criarSimulacao();
        simulacaoComEmailInvalido.setCpf(gerarNovoCPF());
        simulacaoComEmailInvalido.setEmail(null);
        return simulacaoComEmailInvalido;
    }

    public static Simulacao simulacaoComEmailInvalidoSemPonto() throws IOException {
        Simulacao simulacaoComEmailInvalido = criarSimulacao();
        simulacaoComEmailInvalido.setCpf(gerarNovoCPF());
        simulacaoComEmailInvalido.setEmail("email@combr");
        return simulacaoComEmailInvalido;
    }

    public static Simulacao simulacaoComValorMaior40K() throws IOException {
        Simulacao simulacaoValorMaior40m = criarSimulacao();
        simulacaoValorMaior40m.setCpf(gerarNovoCPF());
        simulacaoValorMaior40m.setValor(40001);
        return simulacaoValorMaior40m;
    }

    public static Simulacao simulacaoComParcelaInvalida() throws IOException {
        Simulacao simulacaoParcelaInvalida = criarSimulacao();
        simulacaoParcelaInvalida.setCpf(gerarNovoCPF());
        simulacaoParcelaInvalida.setParcelas(1);
        return simulacaoParcelaInvalida;
    }

    public static Simulacao alterarSimulacao() throws IOException {
        Simulacao alterarSimulacao = new Simulacao();

        String cpf = SimulacaoModel.retornaCpfDaSimulacaoCadastrada();
        alterarSimulacao.setCpf(cpf);
        alterarSimulacao.setNome("Alterando Simulacao");
        alterarSimulacao.setEmail("simulacao@alterada.com");
        alterarSimulacao.setValor(1300);
        alterarSimulacao.setParcelas(5);
        alterarSimulacao.setSeguro(false);
        return alterarSimulacao;
    }
}
