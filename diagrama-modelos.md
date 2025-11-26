```mermaid
classDiagram

    %% ========== ITEM DE CARDÃPIO ==========

    class ItemCardapio {
        <<abstract>>
        - codigo: String
        - nome: String
        - descricao: String
        - preco: double
        - disponivel: boolean
        + ItemCardapio(codigo: String, nome: String, descricao: String, preco: double)
        + getCategoria()* String
        + calcularPreco(): double
        + getCodigo(): String
        + getNome(): String
        + getDescricao(): String
        + getPreco(): double
        + isDisponivel(): boolean
        + setDisponivel(disponivel: boolean): void
    }

    class Lanche {
        + Lanche(codigo: String, nome: String, descricao: String, preco: double)
        + getCategoria(): String
    }

    class Bebida {
        + Bebida(codigo: String, nome: String, descricao: String, preco: double)
        + getCategoria(): String
    }

    class Acompanhamento {
        + Acompanhamento(codigo: String, nome: String, descricao: String, preco: double)
        + getCategoria(): String
    }

    class Sobremesa {
        + Sobremesa(codigo: String, nome: String, descricao: String, preco: double)
        + getCategoria(): String
    }

    class Combo {
        - itens: List~ItemCardapio~
        - percentualDesconto: double
        + Combo(codigo: String, nome: String, descricao: String, percentualDesconto: double)
        + adicionarItem(item: ItemCardapio): void
        + getCategoria(): String
        + calcularPreco(): double
        + getItens(): List~ItemCardapio~
    }

    ItemCardapio <|-- Lanche
    ItemCardapio <|-- Bebida
    ItemCardapio <|-- Acompanhamento
    ItemCardapio <|-- Sobremesa
    ItemCardapio <|-- Combo

    Combo o-- ItemCardapio


    %% ========== PEDIDO ==========

    class ItemPedido {
        - item: ItemCardapio
        - quantidade: int
        + ItemPedido(item: ItemCardapio, quantidade: int)
        + calcularSubtotal(): double
        + getItem(): ItemCardapio
        + getQuantidade(): int
        + setQuantidade(quantidade: int): void
    }

    class StatusPedido {
        <<enumeration>>
        AGUARDANDO_PAGAMENTO
        PAGO
        EM_PREPARO
        PRONTO
        ENTREGUE
        CANCELADO
    }

    class Pedido {
        - numero: int
        - dataHora: LocalDateTime
        - itens: List~ItemPedido~
        - status: StatusPedido
        - formaPagamento: FormaPagamento
        + Pedido()
        + adicionarItem(item: ItemCardapio, quantidade: int): void
        + removerItem(codigo: String): boolean
        + calcularTotal(): double
        + processarPagamento(formaPagamento: FormaPagamento): boolean
        + avancarStatus(): void
        + getNumero(): int
        + getDataHora(): LocalDateTime
        + getItens(): List~ItemPedido~
        + getStatus(): StatusPedido
        + setStatus(status: StatusPedido): void
        + getFormaPagamento(): FormaPagamento
    }

    Pedido o-- ItemPedido
    Pedido --> StatusPedido
    ItemPedido --> ItemCardapio


    %% ========== PAGAMENTO ==========

    class FormaPagamento {
        <<interface>>
        + processar(valor: double): boolean
        + getTipo(): String
    }

    class PagamentoDinheiro {
        + processar(valor: double): boolean
        + getTipo(): String
    }

    class PagamentoCartao {
        - tipo: String
        + PagamentoCartao(tipo: String)
        + processar(valor: double): boolean
        + getTipo(): String
    }

    class PagamentoPix {
        + processar(valor: double): boolean
        + getTipo(): String
    }

    FormaPagamento <|.. PagamentoDinheiro
    FormaPagamento <|.. PagamentoCartao
    FormaPagamento <|.. PagamentoPix
    Pedido --> FormaPagamento
