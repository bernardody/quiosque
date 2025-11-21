```mermaid
classDiagram

    %% ========== ITEM DE CARDÁPIO ==========

    class ItemCardapio {
        <<abstract>>
        -String codigo
        -String nome
        -String descricao
        -double preco
        -boolean disponivel
        +ItemCardapio(codigo, nome, descricao, preco)
        +getCategoria()* String
        +calcularPreco() double
        +getCodigo() String
        +getNome() String
        +getDescricao() String
        +getPreco() double
        +isDisponivel() boolean
        +setDisponivel(boolean) void
    }

    class Lanche {
        +Lanche(codigo, nome, descricao, preco)
        +getCategoria() String
    }

    class Bebida {
        +Bebida(codigo, nome, descricao, preco)
        +getCategoria() String
    }

    class Acompanhamento {
        +Acompanhamento(codigo, nome, descricao, preco)
        +getCategoria() String
    }

    class Sobremesa {
        +Sobremesa(codigo, nome, descricao, preco)
        +getCategoria() String
    }

    class Combo {
        -List~ItemCardapio~ itens
        -double percentualDesconto
        +Combo(codigo, nome, descricao, percentualDesconto)
        +adicionarItem(ItemCardapio) void
        +getCategoria() String
        +calcularPreco() double
        +getItens() List~ItemCardapio~
    }

    ItemCardapio <|-- Lanche
    ItemCardapio <|-- Bebida
    ItemCardapio <|-- Acompanhamento
    ItemCardapio <|-- Sobremesa
    ItemCardapio <|-- Combo

    Combo o-- ItemCardapio


    %% ========== PEDIDO ==========

    class ItemPedido {
        -ItemCardapio item
        -int quantidade
        +ItemPedido(item, quantidade)
        +calcularSubtotal() double
        +getItem() ItemCardapio
        +getQuantidade() int
        +setQuantidade(int) void
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
        -int numero
        -LocalDateTime dataHora
        -List~ItemPedido~ itens
        -StatusPedido status
        -FormaPagamento formaPagamento
        +Pedido()
        +adicionarItem(ItemCardapio, int) void
        +removerItem(String) boolean
        +calcularTotal() double
        +processarPagamento(FormaPagamento) boolean
        +avancarStatus() void
        +getNumero() int
        +getDataHora() LocalDateTime
        +getItens() List~ItemPedido~
        +getStatus() StatusPedido
        +setStatus(StatusPedido) void
        +getFormaPagamento() FormaPagamento
    }

    Pedido o-- ItemPedido
    Pedido --> StatusPedido
    ItemPedido --> ItemCardapio


    %% ========== PAGAMENTO ==========

    class FormaPagamento {
        <<interface>>
        +processar(double) boolean
        +getTipo() String
    }

    class PagamentoDinheiro {
        +processar(double) boolean
        +getTipo() String
    }

    class PagamentoCartao {
        -String tipo
        +PagamentoCartao(tipo)
        +processar(double) boolean
        +getTipo() String
    }

    class PagamentoPix {
        +processar(double) boolean
        +getTipo() String
    }

    FormaPagamento <|.. PagamentoDinheiro
    FormaPagamento <|.. PagamentoCartao
    FormaPagamento <|.. PagamentoPix
    Pedido --> FormaPagamento

