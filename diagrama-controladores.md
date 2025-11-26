```mermaid
classDiagram
    %% ========== APLICAÇÃO/CONTROLADOR ==========
    class CardapioController {
        - estabelecimento: Estabelecimento
        + CardapioController(estabelecimento: Estabelecimento)
        + getItensDisponiveis(): List~ItemCardapio~
        + getItensPorCategoria(categoria: String): List~ItemCardapio~
    }
    class PedidoController {
        - estabelecimento: Estabelecimento
        - pedidoAtual: Pedido
        + PedidoController(estabelecimento: Estabelecimento)
        + iniciarNovoPedido(): void
        + adicionarItem(codigo: String, quantidade: int): boolean
        + removerItem(codigo: String): boolean
        + getItensPedidoAtual(): List~ItemPedido~
        + getValorTotal(): double
        + finalizarPedido(formaPagamento: FormaPagamento): boolean
        + getPedidoAtual(): Pedido
    }
    class GerenciamentoController {
        - estabelecimento: Estabelecimento
        + GerenciamentoController(estabelecimento: Estabelecimento)
        + getTodosPedidos(): List~Pedido~
        + getPedidosPorStatus(status: StatusPedido): List~Pedido~
        + avancarStatusPedido(numeroPedido: int): void
    }
    class PersistenciaController {
        + salvarPedidos(pedidos: List~Pedido~): void
        + carregarPedidos(): List~Pedido~
    }
    class Estabelecimento {
        - nome: String
        - cardapio: List~ItemCardapio~
        - pedidos: List~Pedido~
        + Estabelecimento(nome: String)
        + adicionarItemCardapio(item: ItemCardapio): void
        + buscarItemPorCodigo(codigo: String): ItemCardapio
        + getItensDisponiveis(): List~ItemCardapio~
        + getItensPorCategoria(categoria: String): List~ItemCardapio~
        + criarNovoPedido(): Pedido
        + registrarPedido(pedido: Pedido): void
        + buscarPedidoPorNumero(numero: int): Pedido
        + getTodosPedidos(): List~Pedido~
        + getPedidosPorStatus(status: StatusPedido): List~Pedido~
        + getNome(): String
        + getCardapio(): List~ItemCardapio~
    }
    class ItemCardapio {
        <<abstract>>
    }
    class Pedido {
    }
    class StatusPedido {
        <<enumeration>>
    }
    CardapioController --> Estabelecimento
    PedidoController --> Estabelecimento
    PedidoController --> Pedido
    GerenciamentoController --> Estabelecimento
    PersistenciaController --> Pedido
    
    Estabelecimento o-- ItemCardapio
    Estabelecimento o-- Pedido