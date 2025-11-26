```mermaid
classDiagram
    %% ========== INTERFACE/TELA ==========
    class MainApplication {
        - estabelecimento: Estabelecimento
        + start(stage: Stage): void
        + inicializarSistema(): void
        + carregarPedidos(): void
        + getEstabelecimento(): Estabelecimento
        + main(args: String[]): void
    }
    class TelaInicialController {
        + onComecarPedido(): void
        + onGerenciarPedidos(): void
    }
    class TelaCardapioController {
        - cardapioController: CardapioController
        - pedidoController: PedidoController
        + initialize(): void
        + onFiltrarTodos(): void
        + onFiltrarLanches(): void
        + onFiltrarBebidas(): void
        + onFiltrarAcompanhamentos(): void
        + onFiltrarSobremesas(): void
        + onFiltrarCombos(): void
        + onFinalizarPedido(): void
        + onVoltar(): void
    }
    class TelaResumoController {
        - pedidoController: PedidoController
        + setPedidoController(controller: PedidoController): void
        + onVoltar(): void
        + onRealizarPagamento(): void
    }
    class TelaPagamentoController {
        - pedidoController: PedidoController
        + setPedidoController(controller: PedidoController): void
        + onPagarDinheiro(): void
        + onPagarCartaoDebito(): void
        + onPagarCartaoCredito(): void
        + onPagarPix(): void
        + onVoltar(): void
    }
    class TelaConfirmacaoController {
        + setInformacoes(info: String): void
        + onNovoPedido(): void
        + onVoltar(): void
    }
    class TelaGerenciamentoController {
        - gerenciamentoController: GerenciamentoController
        + initialize(): void
        + onFiltrarTodos(): void
        + onFiltrarPagos(): void
        + onFiltrarEmPreparo(): void
        + onFiltrarProntos(): void
        + onFiltrarEntregues(): void
        + onVoltar(): void
    }
    class TelaDetalhesPedidoController {
        - pedido: Pedido
        - gerenciamentoController: GerenciamentoController
        + setPedido(pedido: Pedido): void
        + onAvancarStatus(): void
        + onVoltar(): void
    }
    class Estabelecimento
    class PersistenciaController
    class CardapioController
    class PedidoController
    class GerenciamentoController
    class Pedido
    
    MainApplication --> Estabelecimento
    MainApplication --> PersistenciaController
    TelaCardapioController --> CardapioController
    TelaCardapioController --> PedidoController
    TelaResumoController --> PedidoController
    TelaPagamentoController --> PedidoController
    TelaGerenciamentoController --> GerenciamentoController
    TelaDetalhesPedidoController --> GerenciamentoController
    TelaDetalhesPedidoController --> Pedido