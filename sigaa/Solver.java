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
                    case "10":
                        editarNota(scanner, sistema);
                        break;
                    case "11":
                        editarAluno(scanner, sistema);
                        break;
                    case "12":
                        editarProfessor(scanner, sistema);
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
        System.out.println("10. Editar uma nota");
        System.out.println("11. Editar um aluno");
        System.out.println("12. Editar uma professor");
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

    private static void editarNota(Scanner scanner, Sistema sistema) {
        System.out.print("Digite a matrícula do professor: ");
        int matriculaProf = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o nome da disciplina: ");
        String nomeDisciplina = scanner.nextLine();
        System.out.print("Digite a matrícula do aluno: ");
        int matriculaAluno = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite a nova nota: ");
        float novaNota = Float.parseFloat(scanner.nextLine());
        System.out.print("Digite o número da nota (1 ou 2): ");
        int numeroNota = Integer.parseInt(scanner.nextLine());

        sistema.editarNota(matriculaProf, new Disciplina(nomeDisciplina), matriculaAluno, novaNota, numeroNota);
        System.out.println("Nota editada com sucesso!");
    }

    private static void editarAluno(Scanner scanner, Sistema sistema) {
        System.out.print("Digite a matrícula do aluno: ");
        int matriculaAluno = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o novo nome do aluno: ");
        String novoNome = scanner.nextLine();
        System.out.print("Digite o novo email do aluno: ");
        String novoEmail = scanner.nextLine();
        System.out.print("Digite a nova idade do aluno: ");
        int novaIdade = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o novo turno do aluno (MANHA, TARDE, NOITE): ");
        Turno novoTurno = Turno.valueOf(scanner.nextLine().toUpperCase());

        sistema.editarAluno(matriculaAluno, novoNome, novoEmail, novaIdade, novoTurno);
        System.out.println("Informações do aluno editadas com sucesso!");
    }

    private static void editarProfessor(Scanner scanner, Sistema sistema) {
        System.out.print("Digite a matrícula do professor: ");
        int matriculaProfessor = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o novo nome do professor: ");
        String novoNome = scanner.nextLine();
        System.out.print("Digite o novo email do professor: ");
        String novoEmail = scanner.nextLine();
        System.out.print("Digite a nova idade do professor: ");
        int novaIdade = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite a nova titulação do professor (GRADUACAO, MESTRADO, DOUTORADO): ");
        Titulo novaTitulacao = Titulo.valueOf(scanner.nextLine().toUpperCase());

        sistema.editarProfessor(matriculaProfessor, novoNome, novoEmail, novaIdade, novaTitulacao);
        System.out.println("Informações do professor editadas com sucesso!");
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