import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar o nome do aluno
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();

        // Solicitar as notas do aluno
        System.out.print("Digite a primeira nota: ");
        double nota1 = scanner.nextDouble();

        System.out.print("Digite a segunda nota: ");
        double nota2 = scanner.nextDouble();

        System.out.print("Digite a terceira nota: ");
        double nota3 = scanner.nextDouble();

        // Calcular a média das notas
        double media = (nota1 + nota2 + nota3) / 3;

        // Determinar a situação acadêmica
        String situacao;
        if (media >= 7) {
            situacao = "Aprovado";
        } else if (media < 4) {
            situacao = "Reprovado";
        } else {
            situacao = "Final";
        }

        // Exibir o resultado
        System.out.println("Aluno: " + nome);
        System.out.println("Média: " + media);
        System.out.println("Situação: " + situacao);

        scanner.close();
    }
}
