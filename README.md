# Sistema de Quiosque de Autoatendimento

## Requisitos

### Software Necessário

- Java JDK versão 17 ou superior
- JavaFX versão 17 ou superior
- Maven versão 3.6 ou superior (para build)
- VS Code com extensões:
   - Extension Pack for Java (Microsoft)
   - Maven for Java (Microsoft)

### Verificar Instalação

```bash
# Verificar Java
java -version

# Verificar Maven
mvn -version
```

---

## Instalação

### 1. Clonar o Repositório

```bash
git clone https://github.com/bernardody/quiosque.git
cd projeto-final
```

### 2. Configurar Dependências

O projeto utiliza Maven para gerenciar dependências. O arquivo `pom.xml` já contém todas as configurações necessárias.

### 3. Build do Projeto

```bash
mvn clean install
```

---

## Execução

### Opção 1: Executar via VS Code (Recomendado)

1. **Abrir o Projeto**:
   - File → Open Folder → Selecione a pasta do projeto
   - Aguarde o VS Code detectar o projeto Maven (canto inferior direito)

2. **Sincronizar Dependências**:
   - Abra o Explorer (Ctrl+Shift+E)
   - Localize o ícone Maven na barra lateral esquerda
   - Clique em "Reload Projects" (ícone de refresh)

3. **Executar a Aplicação**:
   - Abra `MainApplication.java` (em `src/main/java/br/feevale/projetofinal/`)
   - Clique em "Run" acima do método `main()`
   - OU pressione `F5`
   - OU clique com botão direito → "Run Java"

### Opção 2: Executar via Terminal Integrado

```bash
# Abrir terminal no VS Code (Ctrl+`)
mvn clean javafx:run
```

## Testes

O sistema possui duas áreas principais que devem ser testadas:

#### 1. Fluxo do Cliente (Realizar Pedidos)
- **Adicionar produtos ao carrinho**: Teste diferentes categorias (Lanches, Bebidas, Acompanhamentos, Combos)
- **Gerenciar carrinho**: Adicione múltiplas quantidades do mesmo item, remova produtos
- **Calcular totais**: Verifique se os preços e descontos (combos têm 15% off) estão corretos
- **Finalizar pedido**: Teste as 4 formas de pagamento (Dinheiro, Débito, Crédito, PIX)
- **Validações**: Tente finalizar com carrinho vazio

#### 2. Gerenciamento de Pedidos (Área do Atendente)
- **Visualizar pedidos**: Verifique se todos os pedidos aparecem corretamente
- **Filtrar por status**: Teste os filtros (Todos, Pagos, Em Preparo, Prontos, Entregues)
- **Avançar status**: Mova pedidos através do fluxo: PAGO → EM PREPARO → PRONTO → ENTREGUE
- **Persistência**: Feche e reabra a aplicação para verificar se os dados foram salvos no arquivo `pedidos.dat`

### Cenários de Teste Sugeridos

**Cenário Básico**: Crie um pedido simples (1 lanche + 1 bebida), finalize com PIX, depois acesse o gerenciamento e avance o pedido até ENTREGUE.

**Cenário Combo**: Adicione um combo ao carrinho e verifique se o desconto de 15% foi aplicado corretamente.

**Cenário Múltiplos Pedidos**: Crie 3-4 pedidos com diferentes status e teste os filtros no gerenciamento.