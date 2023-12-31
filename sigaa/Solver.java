package sigaa;

import java.util.*;

public class Solver {
    private static Scanner scanner = new Scanner(System.in);
    private static Sistema sistema = new Sistema();

    public static void main(String[] args) {

        while (true) {
            Homepage.exibirMenu();
            String linha = scanner.nextLine();
            String[] entrada = linha.split(" ");

            try {
                switch (entrada[0]) {
                    case "1":
                        Homepage.adicionarAluno(scanner, sistema);
                        break;
                    case "2":
                        Homepage.adicionarProfessor(scanner, sistema);
                        break;
                    case "3":
                        Homepage.adicionarDisciplina(scanner, sistema);
                        break;
                    case "4":
                        Homepage.adicionarNota(scanner, sistema, 1);
                        break;
                    case "5":
                        Homepage.adicionarNota(scanner, sistema, 2);
                        break;
                    case "6":
                        Homepage.mostrarNotas(scanner, sistema);
                        break;
                    case "7":
                        Homepage.removerPessoa(scanner, sistema);
                        break;
                    case "8":
                        Homepage.exibirInformacoes(sistema);
                        break;
                    case "9":
                        Homepage.editarNota(scanner, sistema);
                        break;
                    case "10":
                        Homepage.editarAluno(scanner, sistema);
                        break;
                    case "11":
                        Homepage.editarProfessor(scanner, sistema);
                        break;
                    case "12":
                        Homepage.mostrarAlunosDisciplina(scanner, sistema);
                        break;
                    case "13": 
                        Homepage.salvarDados(scanner, sistema);
                        break;
                    case "0":
                        System.out.println("Sistema encerrado.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}