package sigaa;
import java.util.*;

public class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sistema sistema = new Sistema();

        while (true) {
            exibirMenu();
            String linha = scanner.nextLine();
            String[] entrada = linha.split(" ");

            try {
                switch (entrada[0]) {
                    case "1":
                        adicionarAluno(scanner, sistema);
                        break;
                    case "2":
                        adicionarProfessor(scanner, sistema);
                        break;
                    case "3":
                        adicionarDisciplina(scanner, sistema);
                        break;
                    case "4":
                        matricularAluno(scanner, sistema);
                        break;
                    case "5":
                        adicionarNota(scanner, sistema, 1);
                        break;
                    case "6":
                        adicionarNota(scanner, sistema, 2);
                        break;
                    case "7":
                        mostrarNotas(scanner, sistema);
                        break;
                    case "8":
                        removerPessoa(scanner, sistema);
                        break;
                    case "9":
                        exibirInformacoes(sistema);
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

    private static void exibirMenu() {
        System.out.println("\n===== Menu =====");
        System.out.println("1. Adicionar Aluno");
        System.out.println("2. Adicionar Professor");
        System.out.println("3. Adicionar Disciplina");
        System.out.println("4. Matricular Aluno");
        System.out.println("5. Adicionar Nota1");
        System.out.println("6. Adicionar Nota2");
        System.out.println("7. Mostrar Notas");
        System.out.println("8. Remover Pessoa");
        System.out.println("9. Exibir Informações");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarAluno(Scanner scanner, Sistema sistema) {
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a matrícula do aluno: ");
        int matricula = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o email do aluno: ");
        String email = scanner.nextLine();
        System.out.print("Digite a idade do aluno: ");
        int idade = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o turno do aluno (MANHA, TARDE, NOITE): ");
        Turno turno = Turno.valueOf(scanner.nextLine().toUpperCase());

        sistema.addPessoa(new Aluno(nome, matricula, email, idade, turno));
        System.out.println("Aluno adicionado com sucesso!");
    }

    private static void adicionarProfessor(Scanner scanner, Sistema sistema) {
        System.out.print("Digite o nome do professor: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a matrícula do professor: ");
        int matricula = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o email do professor: ");
        String email = scanner.nextLine();
        System.out.print("Digite a idade do professor: ");
        int idade = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite a titulação do professor (GRADUACAO, MESTRADO, DOUTORADO): ");
        Titulo titulacao = Titulo.valueOf(scanner.nextLine().toUpperCase());

        sistema.addPessoa(new Professor(nome, matricula, email, idade, titulacao));
        System.out.println("Professor adicionado com sucesso!");
    }

    private static void adicionarDisciplina(Scanner scanner, Sistema sistema) {
        System.out.print("Digite a matrícula do professor que vai ministrar a disciplina: ");
        int matriculaProf = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o nome da disciplina: ");
        String nomeDisciplina = scanner.nextLine();

        sistema.addDisciplinas(matriculaProf, new Disciplina(nomeDisciplina));
        System.out.println("Disciplina adicionada com sucesso!");
    }

    private static void matricularAluno(Scanner scanner, Sistema sistema) {
        System.out.print("Digite a matrícula do aluno: ");
        int matriculaAluno = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o nome da disciplina para matricular o aluno: ");
        String nomeDisciplina = scanner.nextLine();

        sistema.matricularAluno(matriculaAluno, new Disciplina(nomeDisciplina));
        System.out.println("Aluno matriculado na disciplina com sucesso!");
    }

    private static void adicionarNota(Scanner scanner, Sistema sistema, int numeroNota) {
        System.out.print("Digite a matrícula do professor: ");
        int matriculaProf = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o nome da disciplina: ");
        String nomeDisciplina = scanner.nextLine();
        System.out.print("Digite a matrícula do aluno: ");
        int matriculaAluno = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite a nota" + numeroNota + ": ");
        float nota = Float.parseFloat(scanner.nextLine());

        if (numeroNota == 1) {
            sistema.adicionarNota1(matriculaProf, new Disciplina(nomeDisciplina), matriculaAluno, nota);
        } else if (numeroNota == 2) {
            sistema.adicionarNota2(matriculaProf, new Disciplina(nomeDisciplina), matriculaAluno, nota);
        }

        System.out.println("Nota adicionada com sucesso!");
    }

    private static void mostrarNotas(Scanner scanner, Sistema sistema) {
        System.out.print("Digite a matrícula do aluno: ");
        int matriculaAluno = Integer.parseInt(scanner.nextLine());
        sistema.mostrarNotas(matriculaAluno);
    }

    private static void removerPessoa(Scanner scanner, Sistema sistema) {
        System.out.print("Digite a matrícula da pessoa a ser removida: ");
        int matricula = Integer.parseInt(scanner.nextLine());
        sistema.rmPessoa(matricula);
        System.out.println("Pessoa removida com sucesso!");
    }

    private static void exibirInformacoes(Sistema sistema) {
        System.out.println(sistema);
    }
}