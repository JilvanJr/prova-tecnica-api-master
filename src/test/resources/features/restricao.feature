@regressivo

Feature: Gerenciamento de cpf com/sem restricao

  @cpfComRestricao
  Scenario Outline: Consultando cpf com restricao
    When faco um GET para "<endpoint>" passando o "<cpf>"
    Then recebo o <statusCode>

    Examples:
      | endpoint        | cpf         | statusCode |
      | /v1/restricoes/ | novoCPF     | 204        |
      | /v1/restricoes/ | 97093236014 | 200        |