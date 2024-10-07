import java.util.ArrayList;
import java.util.Scanner;

class Produto {
    private String codigo;
    private String nome;
    private String tamanhoOuPeso;
    private String cor;
    private double valor;
    private int quantidade;

    public Produto(String codigo, String nome, String tamanhoOuPeso, String cor, double valor, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.tamanhoOuPeso = tamanhoOuPeso;
        this.cor = cor;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getTamanhoOuPeso() {
        return tamanhoOuPeso;
    }

    public String getCor() {
        return cor;
    }

    public double getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Código: " + codigo +
                ", Nome: " + nome +
                ", Tamanho/Peso: " + tamanhoOuPeso +
                ", Cor: " + cor +
                ", Valor: R$ " + valor +
                ", Quantidade em Estoque: " + quantidade;
    }
}

public class Main {
    private static final ArrayList<Produto> produtos = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Realizar Venda");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    realizarVenda();
                    break;
                case 4:
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarProduto() {
        System.out.print("Digite o código do produto: ");
        String codigo = scanner.nextLine();
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o tamanho/peso do produto: ");
        String tamanhoOuPeso = scanner.nextLine();
        System.out.print("Digite a cor do produto: ");
        String cor = scanner.nextLine();
        System.out.print("Digite o valor do produto: ");
        double valor = scanner.nextDouble();
        System.out.print("Digite a quantidade em estoque: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        produtos.add(new Produto(codigo, nome, tamanhoOuPeso, cor, valor, quantidade));
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.println("Produtos cadastrados:");
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }
    }

    private static void realizarVenda() {
        System.out.print("Digite o código do produto: ");
        String codigo = scanner.nextLine();
        Produto produto = encontrarProduto(codigo);

        if (produto != null) {
            System.out.print("Digite a quantidade a ser vendida: ");
            int quantidadeVenda = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            if (quantidadeVenda > produto.getQuantidade()) {
                System.out.println("Quantidade insuficiente em estoque.");
                return;
            }

            double valorTotal = produto.getValor() * quantidadeVenda;
            System.out.println("Valor total: R$ " + valorTotal);
            System.out.println("Formas de pagamento: ");
            System.out.println("1. Pix (5% de desconto)");
            System.out.println("2. Espécie (5% de desconto, troco se necessário)");
            System.out.println("3. Transferência (5% de desconto)");
            System.out.println("4. Débito (5% de desconto)");
            System.out.println("5. Crédito (3x sem juros)");
            System.out.print("Escolha a forma de pagamento: ");
            int formaPagamento = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (formaPagamento) {
                case 1: // Pix
                case 3: // Transferência
                case 4: // Débito
                    valorTotal *= 0.95; // Aplica 5% de desconto
                    System.out.println("Valor com desconto: R$ " + valorTotal);
                    break;
                case 2: // Espécie
                    valorTotal *= 0.95; // Aplica 5% de desconto
                    System.out.println("Valor com desconto: R$ " + valorTotal);
                    System.out.print("Digite o valor pago: R$ ");
                    double valorPago = scanner.nextDouble();
                    if (valorPago > valorTotal) {
                        double troco = valorPago - valorTotal;
                        System.out.println("Troco: R$ " + troco);
                    } else {
                        System.out.println("Valor insuficiente.");
                    }
                    break;
                case 5: // Crédito
                    System.out.println("Valor total em 3x sem juros: R$ " + valorTotal);
                    break;
                default:
                    System.out.println("Forma de pagamento inválida.");
                    return;
            }

            // Atualiza a quantidade do produto em estoque
            produto.setQuantidade(produto.getQuantidade() - quantidadeVenda);
            System.out.println("Venda realizada com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static Produto encontrarProduto(String codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo().equalsIgnoreCase(codigo)) {
                return produto;
            }
        }
        return null;
    }
}
