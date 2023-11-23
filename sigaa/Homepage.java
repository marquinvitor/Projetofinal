package sigaa;

import java.util.*;

public class Homepage {

    public static void exibirMenu() {
        System.out.println("\n===== Menu =====");
        System.out.println("1.  Adicionar Aluno");
        System.out.println("2.  Adicionar Professor");
        System.out.println("3.  Adicionar Disciplina");
        System.out.println("4.  Matricular Aluno");
        System.out.println("5.  Adicionar Nota1");
        System.out.println("6.  Adicionar Nota2");
        System.out.println("7.  Mostrar Notas");
        System.out.println("8.  Remover Pessoa");
        System.out.println("9.  Exibir Informações");
        System.out.println("10. Editar uma nota");
        System.out.println("11. Editar um aluno");
        System.out.println("12. Editar uma professor");
        System.out.println("13. Mostrar Alunos na Disciplina");
        System.out.println("0.  Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void adicionarAluno(Scanner scanner, Sistema sistema) {
        try {
            System.out.print("Digite o nome do aluno: ");
            String nome = obterEntradaTexto(scanner, "Digite o nome do aluno: ",
                    "Fail: Nome aceita somente letras. Tente novamente.");
    
            int matricula = obterEntradaNumerica(scanner, "Digite a matrícula do aluno: ",
                    "Fail: Matrícula aceita somente números. Tente novamente.");
    
            int idade = obterEntradaNumerica(scanner, "Digite a idade do aluno: ",
                    "Fail: Idade aceita somente números. Tente novamente.");
    
            Turno turno = obterEntradaTurno(scanner, "Digite o turno do aluno (MANHA, TARDE, NOITE): ",
                    "Fail: Turno inválido. Tente novamente.");
    
            sistema.addPessoa(new Aluno(nome, matricula, idade, turno));
            System.out.println("Aluno adicionado com sucesso!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
}

    public static void adicionarProfessor(Scanner scanner, Sistema sistema) {
        System.out.print("Digite o nome do professor: ");
        String nome = obterEntradaTexto(scanner, "Digite o nome do professor: ",
                "Fail: Nome aceita somente letras. Tente novamente.");

        int matricula = obterEntradaNumerica(scanner, "Digite a matrícula do professor: ",
                "Fail: Matrícula aceita somente números. Tente novamente.");

        int idade = obterEntradaNumerica(scanner, "Digite a idade do professor: ",
                "Fail: Idade aceita somente números. Tente novamente.");

        Titulo titulacao = obterEntradaTitulacao(scanner,
                "Digite a titulação do professor (GRADUACAO, MESTRADO, DOUTORADO): ",
                "Fail: Turno inválido. Tente novamente.");

        sistema.addPessoa(new Professor(nome, matricula, idade, titulacao));
        System.out.println("Professor adicionado com sucesso!");
    }

    public static void adicionarDisciplina(Scanner scanner, Sistema sistema) {

        int matriculaProf = obterEntradaNumerica(scanner, "Digite a matrícula do professor: ",
                "Fail: Matrícula aceita somente números. Tente novamente.");

        System.out.print("Digite o nome da disciplina: ");
        String nomeDisciplina = obterEntradaTexto(scanner, "Digite o nome da disciplina: ",
                "Fail: Nome da disciplina aceita somente letras. Tente novamente.");

        sistema.addDisciplinas(matriculaProf, new Disciplina(nomeDisciplina));
        System.out.println("Disciplina adicionada com sucesso!");
    }

    public static void matricularAluno(Scanner scanner, Sistema sistema) {
        int matriculaAluno = obterEntradaNumerica(scanner, "Digite a matrícula do aluno: ",
                "Fail: Matrícula aceita somente números. Tente novamente.");

        System.out.print("Digite o nome da disciplina para matricular o aluno: ");
        String nomeDisciplina = obterEntradaTexto(scanner, "Digite o nome da disciplina: ",
                "Fail: Nome da disciplina aceita somente letras. Tente novamente.");

        sistema.matricularAluno(matriculaAluno, new Disciplina(nomeDisciplina));
        System.out.println("Aluno matriculado na disciplina com sucesso!");
    }

    public static void adicionarNota(Scanner scanner, Sistema sistema, int numeroNota) {
        try {
            int matriculaProf = obterEntradaNumerica(scanner, "Digite a matrícula do professor: ",
                "Fail: Matrícula aceita somente números. Tente novamente.");

            System.out.print("Digite o nome da disciplina: ");
            String nomeDisciplina = obterEntradaTexto(scanner, "Digite o nome da disciplina: ",
                    "Fail: Nome da disciplina aceita somente letras. Tente novamente.");

            int matriculaAluno = obterEntradaNumerica(scanner, "Digite a matrícula do aluno: ",
                    "Fail: Matrícula aceita somente números. Tente novamente.");

            float nota = obterEntradaNumericaFloat(scanner, "Digite a nota" + numeroNota + ": ",
                    "Fail: Nota aceita somente números. Tente novamente.");

            sistema.adicionarNota(matriculaProf, new Disciplina(nomeDisciplina), matriculaAluno, nota, numeroNota);
            System.out.println("Nota adicionada com sucesso!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            adicionarNota(scanner, sistema, numeroNota);
        }
    }

    public static void editarNota(Scanner scanner, Sistema sistema) {
        int matriculaProf = obterEntradaNumerica(scanner, "Digite a matrícula do professor: ",
                "Fail: Matrícula aceita somente números. Tente novamente.");

        System.out.print("Digite o nome da disciplina: ");
        String nomeDisciplina = obterEntradaTexto(scanner, "Digite o nome da disciplina: ",
                "Fail: Nome da disciplina aceita somente letras. Tente novamente.");

        int matriculaAluno = obterEntradaNumerica(scanner, "Digite a matrícula do aluno: ",
                "Fail: Matrícula aceita somente números. Tente novamente.");

        float novaNota = obterEntradaNumericaFloat(scanner, "Digite a nova nota: ",
                "Fail: Nota aceita somente números. Tente novamente.");

        int numeroNota = obterEntradaNumerica(scanner, "Digite o número da nota (1 ou 2): ",
                "Fail: Número da nota aceita somente números. Tente novamente.");

        sistema.editarNota(matriculaProf, new Disciplina(nomeDisciplina), matriculaAluno, novaNota, numeroNota);
        System.out.println("Nota editada com sucesso!");
    }

    public static void editarAluno(Scanner scanner, Sistema sistema) {
        int matriculaAluno = obterEntradaNumerica(scanner, "Digite a matrícula do aluno: ",
                "Fail: Matrícula aceita somente números. Tente novamente.");

        System.out.print("Digite o novo nome do aluno: ");
        String novoNome = obterEntradaTexto(scanner, "Digite o novo nome do aluno: ",
                "Fail: Nome aceita somente letras. Tente novamente.");

        int novaIdade = obterEntradaNumerica(scanner, "Digite a nova idade do aluno: ",
                "Fail: Idade aceita somente números. Tente novamente.");

        Turno novoTurno = obterEntradaTurno(scanner, "Digite o novo turno do aluno (MANHA, TARDE, NOITE): ",
                "Fail: Turno inválido. Tente novamente.");

        sistema.editarAluno(matriculaAluno, novoNome, novaIdade, novoTurno, scanner);
        System.out.println("Informações do aluno editadas com sucesso!");
    }

    public static void editarProfessor(Scanner scanner, Sistema sistema) {
        int matriculaProfessor = obterEntradaNumerica(scanner, "Digite a matrícula do professor: ",
                "Fail: Matrícula aceita somente números. Tente novamente.");

        System.out.print("Digite o novo nome do professor: ");
        String novoNome = obterEntradaTexto(scanner, "Digite o novo nome do professor: ",
                "Fail: Nome aceita somente letras. Tente novamente.");

        int novaIdade = obterEntradaNumerica(scanner, "Digite a nova idade do professor: ",
                "Fail: Idade aceita somente números. Tente novamente.");

        Titulo novaTitulacao = obterEntradaTitulacao(scanner,
                "Digite a nova titulação do professor (GRADUACAO, MESTRADO, DOUTORADO): ",
                "Fail: Titulação inválida. Tente novamente.");

        sistema.editarProfessor(matriculaProfessor, novoNome, novaIdade, novaTitulacao, scanner);
        System.out.println("Informações do professor editadas com sucesso!");
    }

    public static void mostrarNotas(Scanner scanner, Sistema sistema) {
        int matriculaAluno = obterEntradaNumerica(scanner, "Digite a matrícula do aluno: ",
                "Fail: matricula aceita somente números. Tente novamente.");
        sistema.mostrarNotas(matriculaAluno);
    }

    public static void removerPessoa(Scanner scanner, Sistema sistema) {
        int matricula = obterEntradaNumerica(scanner, "Digite a matrícula da pessoa a ser removida: ",
                "Fail: matricula aceita somente números. Tente novamente.");
        sistema.rmPessoa(matricula);
        System.out.println("Pessoa removida com sucesso!");
    }

    public static void exibirInformacoes(Sistema sistema) {
        System.out.println(sistema);
    }

    public static void mostrarAlunosDisciplina(Scanner scanner, Sistema sistema) {
        System.out.print("Digite o nome da disciplina: ");
        String nomeDisciplina = obterEntradaTexto(scanner, "Digite o nome da disciplina: ",
                "Fail: Nome da disciplina aceita somente letras. Tente novamente.");

        sistema.mostrarAlunosDisciplina(nomeDisciplina);
    }

    private static boolean verificarNome(String nome) {
        try {
            Integer.parseInt(nome);
            return false;
        }

        catch (Exception e) {
            return true;
        }
    }

    private static String obterEntradaTexto(Scanner scanner, String mensagemPrompt, String mensagemErro) {
        while (true) {
            String entrada = scanner.nextLine();
            if (verificarNome(entrada) && entrada != null) {
                return entrada;
            } else {
                System.out.println(mensagemErro);
                System.out.print(mensagemPrompt);
            }
        }
    }

    private static int obterEntradaNumerica(Scanner scanner, String mensagemPrompt, String mensagemErro) {
        while (true) {
            try {
                System.out.print(mensagemPrompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(mensagemErro);
            }
        }
    }

    private static Turno obterEntradaTurno(Scanner scanner, String mensagemPrompt, String mensagemErro) {
        while (true) {
            try {
                System.out.print(mensagemPrompt);
                return Turno.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println(mensagemErro);
            }
        }
    }

    private static Titulo obterEntradaTitulacao(Scanner scanner, String mensagemPrompt, String mensagemErro) {
        while (true) {
            try {
                System.out.print(mensagemPrompt);
                return Titulo.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println(mensagemErro);
            }
        }
    }

    private static float obterEntradaNumericaFloat(Scanner scanner, String mensagemPrompt, String mensagemErro) {
        while (true) {
            try {
                System.out.print(mensagemPrompt);
                return Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(mensagemErro);
            }
        }
    }

}