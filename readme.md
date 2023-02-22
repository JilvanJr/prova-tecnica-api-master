# Prova técnica API

## Como executar os testes

Na classe `CucumberRunner` a tag `@regressivo` executa todos os testes. <br/>
Caso queira executar um teste em específico, é só trocar para a tag desejada.

## Teste de contrato

A classe `SimulacaoTesteContrato` valida que a resposta do endpoint `POST /api/v1/simulacoes` está condizente com o contrato. 
Ou seja, dado que executo um POST neste endpoint e, retorna uma resposta diferente do esperado, então esse teste ira falhar, mostrando o erro. 

## Cenários de teste

### Restrições `GET /api/v1/restricoes/{cpf}`

| Tag                | Cenário                             | StatusCode esperado |
|--------------------|-------------------------------------|---------------------|
| @cpfComRestricao   | Consultando cpf com restricao       | 200                 |
| @cpfComRestricao   | Consultando cpf novo, sem restrição | 204                 |

### Criar uma simulação `POST /api/v1/simulacoes`

| Tag                                 | Cenário                                                                                                                                                         | StatusCode esperado |
|-------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------|
| @adicionarSimulacaoComSucesso       | Adicionando simulação com sucesso                                                                                                                               | 201                 |
| @adicionarSimulacaoComErroNoCpf     | Adicionando simulação informando um CPF ja existente. <br/>Adicionando simulação informando um CPF null                                    | 400                 |
| @adicionarSimulacaoComErroNoEmail   | Adicionando simulação informando um email sem '@'. <br/> Adicionando simulação informando um email sem '.' <br/> Adicionando simulação informando um email null | 400                 |
| @adicionarSimulacaoComErroNoValor   | Adicionando simulação informando valor maior que 48.000                                                                                                         | 400                 |
| @adicionarSimulacaoComErroNaParcela | Adicionando simulação informando parcela de valor 1                                                                                                             | 400                 |

### Alterar uma simulação `PUT /api/v1/simulacoes/{cpf}`

| Tag                               | Cenário                                                                                              | StatusCode esperado    |
|-----------------------------------|------------------------------------------------------------------------------------------------------|------------------------|
| @alterarSimulacaoComSucesso       | Alterando simulação com sucesso                                                                      | 200                    |
| @alterarSimulacaoComErroNoCpf     | Alterando simulação informando um cpf já cadastrado                                                  | 400                    |
| @alterarSimulacaoComErroNoCpf     | Alterando simulação informando um cpf que não possui simulação                                       | 404                    |
| @alterarSimulacaoComErroNoEmail   | Alterando simulação informando um email sem '@' <br/> Alterando simulação informando um emailsem '.' | 400                    |
| @alterarSimulacaoComErroNaParcela | Alterando simulação informando parcela de valor 1                                                    | 400                    |

### Consultar todas a simulações cadastradas `GET /api/v1/simulacoes`

| Tag                               | Cenário                    | StatusCode esperado |
|-----------------------------------|----------------------------|---------------------|
| @consultarTodasSimulacoes         | Consultar todas simulações | 200                 |

### Consultar uma simulação pelo CPF `GET /api/v1/simulacoes/{cpf}`

| Tag                         | Cenário                                | StatusCode esperado |
|-----------------------------|----------------------------------------|---------------------|
| @consultarSimulacaoPorCpf   | Consultar simulação por cpf            | 200                 |
| @consultarCpfSemSimulacao   | Consultar cpf que não possui simulação | 404                 |

### Remover uma simulação `DELETE /api/v1/simulacoes/{id}`

| Tag                | Cenário                    | StatusCode esperado |
|--------------------|----------------------------|---------------------|
| @deletarSimulacao  | Deletar simulacao por id   | 200                 |

____________

## Bugs encontrado durante os testes

### Criar uma simulação `POST /api/v1/simulacoes`

| Esperado                                                                                     | O que ocorreu                                                           | 
|----------------------------------------------------------------------------------------------|-------------------------------------------------------------------------|
| Uma simulação para um mesmo CPF retorna um HTTP Status 409 com a mensagem "CPF já existente" | retornou HTTP Status 400                                                |
| texto informando o CPF não no formato 999.999.999-99                                         | Permitiu criar simulação passando cpf com formatação 000.000.000-00     | 
| Campo CPF obrigatório                                                                        | Permitiu criar simulação passando cpf vazio                             | 
| Campo Nome obrigatório                                                                       | Permitiu criar simulação passando nome vazio                            | 
| valor da simulação que deve ser igual ou maior que R$ 1.000                                  | Permitiu criar simulação passando valor menor que 1000                  | 
| número de parcelas para pagamento deve ser menor ou igual a 48                               | Permitiu criar simulação passando parcela maior que 48                  |

### Alterar uma simulação `PUT /api/v1/simulacoes/{cpf}`

| Esperado                                                       | O que ocorreu                                                                               | 
|----------------------------------------------------------------|---------------------------------------------------------------------------------------------|
| Campo CPF obrigatório                                          | Permitiu alterar passando um CPF vazio                                                      |
| Campo CPF obrigatório                                          | Permitiu alterar passando CPF null, retornou 200. Porém o CPF não foi alterado no body      |
| texto informando o CPF não no formato 999.999.999-99           | Permitiu alterar passando CPF com formatação 000.000.000-00                                 |
| Campo Nome obrigatório                                         | Permitiu alterar passando Nome null, retornou 200. Porém o nome não foi alterado no body    |
| Campo Nome obrigatório                                         | Permitiu alterar passando Nome vazio                                                        |
| Campo Email obrigatório                                        | Permitiu alterar passando Email vazio, retornou 200. Porém o Email não foi alterado no body |
| Campo Email obrigatório                                        | Permitiu alterar passando Email null, retornou 200. Porém o Email não foi alterado no body  |
| Campo Valor obrigatório                                        | Permitiu alterar passando Valor vazio, retornou 200. Porém o Valor retornou 10,00 no body   |
| Campo Valor obrigatório                                        | Permitiu alterar passando Valor null, retornou 200. Porém o Valor retornou 10,00 no body    |
| Campo Valor obrigatório                                        | Passando qualquer valor, retorna 200. Porém o valor no body sempre apresenta 10,00          |
| número de parcelas para pagamento deve ser menor ou igual a 48 | Permitiu alterar passando parcela maior que 48                                              |

### Remover uma simulação `DELETE /api/v1/simulacoes/{id}`

| Esperado                                                                                                         | O que ocorreu            | 
|------------------------------------------------------------------------------------------------------------------|--------------------------|
| Retorna o HTTP Status 204 se simulação for removida com sucesso                                                  | retornou HTTP Status 200 |
| Retorna o HTTP Status 404 com a mensagem "Simulação não encontrada" se não existir a simulação pelo ID informado | retornou HTTP Status 200 | 

___________

## Dependências
| Dependências          |                                                                                  |
|-----------------------|----------------------------------------------------------------------------------|
| Rest assured          | [https://mvnrepository.com/artifact/io.rest-assured/rest-assured/3.1.1]          |
| Cucumber JUnit        | [https://mvnrepository.com/artifact/io.cucumber/cucumber-junit/6.8.2]            |
| Cucumber Java         | [https://mvnrepository.com/artifact/io.cucumber/cucumber-java/6.8.2]             |
| Owner                 | [https://mvnrepository.com/artifact/org.aeonbits.owner/owner/1.0.12]             |
| Json Schema Validator | [https://mvnrepository.com/artifact/io.rest-assured/json-schema-validator/3.1.1] |


