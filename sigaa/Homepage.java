package sigaa;

import java.util.*;

public class Homepage {

    public static void exibirMenu() {
        System.out.println("\n===== Menu =====");
        System.out.println("1.  Adicionar Aluno");
        System.out.println("2.  Adicionar Professor");
        System.out.println("3.  Adicionar Disciplina");
        System.out.println("4.  Adicionar Nota1");
        System.out.println("5.  Adicionar Nota2");
        System.out.println("6.  Mostrar Notas");
        System.out.println("7.  Remover Pessoa");
        System.out.println("8.  Exibir Informações");
        System.out.println("9.  Editar uma nota");
        System.out.println("10. Editar um aluno");
        System.out.println("11. Editar uma professor");
        System.out.println("12. Mostrar Alunos em uma Disciplina");
        System.out.println("13. Salvar Dados");
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
            System.out.println(e.getMessage() + ", tente novamente.\n");
            adicionarAluno(scanner, sistema);
        }
    }

    public static void adicionarProfessor(Scanner scanner, Sistema sistema) {
        try {
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
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + ", tente novamente.\n");
            adicionarProfessor(scanner, sistema);
        }
    }

    public static void adicionarDisciplina(Scanner scanner, Sistema sistema) {
        try {
            if (sistema.getPessoas().isEmpty()) {
                System.out.println("Não há pessoas cadastradas. Por favor, adicione alunos ou professores para usar essa função");
                return;
            }
            int matricula = obterEntradaNumerica(scanner, "Digite a matrícula: ",
                    "Fail: Matrícula aceita somente números. Tente novamente.");

            System.out.print("Digite o nome da disciplina: ");
            String nomeDisciplina = obterEntradaTexto(scanner, "Digite o nome da disciplina: ",
                    "Fail: Nome da disciplina aceita somente letras. Tente novamente.");

            sistema.addDisciplinas(matricula, new Disciplina(nomeDisciplina));
            System.out.println("Disciplina adicionada com sucesso!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + ", tente novamente.\n");
        }

    }

    public static void adicionarNota(Scanner scanner, Sistema sistema, int numeroNota) {
        try {
           if(!sistema.temAlunos() && !sistema.temProfessor()){
                System.out.println("fail: o sistema precisa ter no minimo um aluno e um professor para essa função.");
                return;
           }
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
            System.out.println(e.getMessage() + ", tente novamente.\n"); 
        }
    }

    public static void editarNota(Scanner scanner, Sistema sistema) {
        try {
            if(!sistema.temAlunos() && !sistema.temProfessor()){
                System.out.println("fail: o sistema precisa ter no minimo um aluno e um professor para essa função.");
                return;
           }
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
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + ", tente novamente.\n");
        }
    }

    public static void editarAluno(Scanner scanner, Sistema sistema) {
        try {
            if(!sistema.temAlunos()){
                System.out.println("fail: o sistema precisa ter no minimo um aluno para essa função.");
                return;
           }
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
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + ", tente novamente.\n");
        }

    }

    public static void editarProfessor(Scanner scanner, Sistema sistema) {
        try {
            if(!sistema.temProfessor()){
                System.out.println("fail: o sistema precisa ter no minimo um professor para essa função.");
                return;
           }
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
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + ", tente novamente.\n");
        }

    }

    public static void mostrarNotas(Scanner scanner, Sistema sistema) {
        try {
           if(!sistema.temAlunos() && !sistema.temProfessor()){
                System.out.println("fail: o sistema precisa ter no minimo um aluno e um professor para essa função.");
                return;
           }
            int matriculaAluno = obterEntradaNumerica(scanner, "Digite a matrícula do aluno: ",
                    "Fail: matricula aceita somente números. Tente novamente.");
            sistema.mostrarNotas(matriculaAluno);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + ", tente novamente.\n");
        }
    }

    public static void removerPessoa(Scanner scanner, Sistema sistema) {
        try {
            if (sistema.getPessoas().isEmpty()) {
                System.out.println("Não há pessoas cadastradas. Por favor, adicione alunos ou professores para usar essa função.");
                return;
            }
            int matricula = obterEntradaNumerica(scanner, "Digite a matrícula da pessoa a ser removida: ",
                    "Fail: matricula aceita somente números. Tente novamente.");
            sistema.rmPessoa(matricula);
            System.out.println("Pessoa removida com sucesso!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + ", tente novamente.\n");
        }

    }

    public static void exibirInformacoes(Sistema sistema) {
        if (sistema.getPessoas().isEmpty()) {
            System.out.println("Não há pessoas cadastradas. Por favor, adicione alunos ou professores para usar essa função.");
            return;
        }
        System.out.println(sistema);
    }

    public static void mostrarAlunosDisciplina(Scanner scanner, Sistema sistema) {
        try {
            if (sistema.getPessoas().isEmpty()) {
                System.out.println("Não há pessoas cadastradas. Por favor, adicione alunos ou professores para usar essa função.");
                return;
            }
            System.out.print("Digite o nome da disciplina: ");
            String nomeDisciplina = obterEntradaTexto(scanner, "Digite o nome da disciplina: ",
                    "Fail: Nome da disciplina aceita somente letras. Tente novamente.");

            sistema.mostrarAlunosDisciplina(nomeDisciplina);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + ", tente novamente.\n");
        }

    }

    public static void salvarDados(Scanner scanner, Sistema sistema) {
        try {
            if (sistema.getPessoas().isEmpty()) {
                System.out.println("Não há pessoas cadastradas. Por favor, adicione alunos ou professores para usar essa função.");
                return;
            }
            System.out.print("Digite o nome do arquivo para salvar os dados: ");
            String nomeArquivo = scanner.nextLine();
            sistema.salvarDadosEmArquivo(nomeArquivo);
            System.out.println("Dados salvos com sucesso!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + ", tente novamente.\n");
        }

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