@regressivo

Feature: Gerenciamento de simulacoes

  @adicionarSimulacaoComSucesso
  Scenario Outline: Adicionando simulacao com sucesso
    When faco um POST para o "<endpoint>" passando condicao de sucesso
    Then recebo o <statusCode> de sucesso

    Examples:
      | endpoint        | statusCode |
      | /v1/simulacoes/ | 201        |

  @adicionarSimulacaoComErroNoCpf
  Scenario Outline: "<ct>" - Simulacao com erro no CPF
    When faco um POST para "<endpoint>" passando a condicao "<ct>" do cpf no body
    Then recebo o <statusCode> de erro

    Examples:
      | ct             | endpoint        | statusCode |
      | AddComMesmoCPF | /v1/simulacoes/ | 400        |
      | AddComCpfNull  | /v1/simulacoes/ | 400        |

  @adicionarSimulacaoComErroNoEmail
  Scenario Outline: "<ct>" - Simulacao com erro no Email
    When faco um POST para "<endpoint>" passando a condicao "<ct>" do email no body
    Then recebo o <statusCode> de erro

    Examples:
      | ct        | endpoint        | statusCode |
      | EmailSem@ | /v1/simulacoes/ | 400        |
      | EmailSem. | /v1/simulacoes/ | 400        |
      | EmailNull | /v1/simulacoes/ | 400        |


  @adicionarSimulacaoComErroNoValor
  Scenario Outline: "<ct>" - Simulacao com erro no Valor
    When faco um POST para "<endpoint>" passando a condicao "<ct>" do valor no body
    Then recebo o <statusCode> de erro

    Examples:
      | ct       | endpoint        | statusCode |
      | Maior40k | /v1/simulacoes/ | 400        |

  @adicionarSimulacaoComErroNaParcela
  Scenario Outline: "<ct>" - Simulacao com erro na Parcela
    When faco um POST para "<endpoint>" passando a condicao "<ct>" da parcela no body
    Then recebo o <statusCode> de erro

    Examples:
      | ct       | endpoint        | statusCode |
      | Parcela1 | /v1/simulacoes/ | 400        |

  @alterarSimulacaoComSucesso
  Scenario Outline: "<ct>" - Alterar simulacao com sucesso
    When faco um PUT para "<endpoint>" passando a condicao "<ct>" no body
    Then recebo o <statusCode> de sucesso

    Examples:
      | ct      | endpoint        | statusCode |
      | Alterar | /v1/simulacoes/ | 200        |

  @alterarSimulacaoComErroNoCpf
  Scenario Outline: "<ct>" - Alterar simulacao erro no cpf
    When faco um PUT para "<endpoint>" passando a condicao do cpf "<ct>" no body
    Then recebo o <statusCode> de sucesso

    Examples:
      | ct              | endpoint        | statusCode |
      | cpfDuplicado    | /v1/simulacoes/ | 400        |
      | cpfSemSimulacao | /v1/simulacoes/ | 404        |

  @alterarSimulacaoComErroNoEmail
  Scenario Outline: "<ct>" - Alterar simulacao com erro no email
    When faco um PUT para "<endpoint>" passando a condicao do email "<ct>" no body
    Then recebo o <statusCode> de sucesso

    Examples:
      | ct        | endpoint        | statusCode |
      | EmailSem@ | /v1/simulacoes/ | 400        |
      | EmailSem. | /v1/simulacoes/ | 400        |

  @alterarSimulacaoComErroNaParcela
  Scenario Outline: "<ct>" - Alterar simulacao com erro na parcela
    When faco um PUT para "<endpoint>" passando a condicao do parcela "<ct>" no body
    Then recebo o <statusCode> de sucesso

    Examples:
      | ct       | endpoint        | statusCode |
      | Parcela1 | /v1/simulacoes/ | 400        |

  @consultarTodasSimulacoes
  Scenario Outline: Consultar todas simulacoes
    When faco um GET no "<endpoint>"
    Then recebo o <statusCode> de sucesso

    Examples:
      | endpoint        | statusCode |
      | /v1/simulacoes/ | 200        |

  @consultarSimulacaoPorCpf
  Scenario Outline: Consultar simulacao por cpf
    When faco um GET no "<endpoint>" passando o cpf
    Then recebo o <statusCode> de sucesso

    Examples:
      | endpoint        | statusCode |
      | /v1/simulacoes/ | 200        |

  @consultarCpfSemSimulacao
  Scenario Outline: Consultar cpf sem simulacao
    When faco um GET no "<endpoint>" passando um cpf  sem simulacao
    Then recebo o <statusCode> de erro

    Examples:
      | endpoint        | statusCode |
      | /v1/simulacoes/ | 404        |

  @deletarSimulacao
  Scenario Outline: Deletar simulacao por id
    When faco um DELETE no "<endpoint>" passando o id
    Then recebo o <statusCode> de sucesso

    Examples:
      | endpoint        | statusCode |
      | /v1/simulacoes/ | 200        |

