package sigaa;

import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class Sistema {

    private Map<Integer, Pessoa> pessoas;

    public Sistema() {
        this.pessoas = new TreeMap<>();
    }

    public void addPessoa(Pessoa pessoa) {

        if (pessoa == null) {
            throw new RuntimeException("fail: pessoa nula");
        }

        if (pessoa.getNome().matches("[a-zA-Z\\s]+")) {
            int matricula = pessoa.getMatricula();

            if (pessoas.containsKey(matricula)) {
                throw new RuntimeException("fail: matrícula já existe");
            }
            pessoas.put(matricula, pessoa);
        }
    }

    public void addDisciplinas(int matricula, Disciplina cadeira) {

        Pessoa pessoa = this.getPessoa(matricula);

        if (pessoa instanceof Professor) {
            Professor professor = (Professor) pessoa;
            if (professor.temDisciplina(cadeira)) {
                throw new RuntimeException("fail: disciplina já associada ao Professor");
            }
            professor.addDisciplinas(cadeira);
        } else if (pessoa instanceof Aluno) {
            Aluno aluno = (Aluno) pessoa;
            if (aluno.temDisciplina(cadeira)) {
                throw new RuntimeException("fail: disciplina já associada ao Aluno");
            }
            aluno.addDisciplinas(cadeira);
        } else {
            throw new RuntimeException("fail: matrícula invalida");
        }
    }

    public void adicionarNota(int matriculadoprof, Disciplina cadeira, int matriculaaluno, float nota, int numNota) {
        Pessoa professor, aluno;

        try {
            professor = this.getPessoa(matriculadoprof);
            aluno = this.getPessoa(matriculaaluno);
        } catch (RuntimeException e) {
            throw new RuntimeException("fail: matricula inexistente");
        }

        if (!(professor instanceof Professor)) {
            throw new RuntimeException("fail: essa matricula não é de um professor");
        }

        if (!(aluno instanceof Aluno)) {
            throw new RuntimeException("fail: essa matricula não é de um aluno");
        }

        for (Disciplina v : professor.getDisciplinas()) {
            if (v.getName().equals(cadeira.getName())) {
                for (Disciplina alunoDisciplina : aluno.getDisciplinas()) {
                    if (alunoDisciplina.getName().equals(cadeira.getName())) {
                        alunoDisciplina.setNota(nota, numNota);
                        return;
                    }
                }
                throw new RuntimeException("fail: disciplina não encontrada para o aluno");
            }
        }

        throw new RuntimeException("fail: disciplina não encontrada para o professor");
    }

    public void mostrarNotas(int matricula) {
        String saida;
        Pessoa pessoa;

        try {
            saida = "Notas do aluno: " + this.getPessoa(matricula).getNome() + "\n";
            pessoa = this.getPessoa(matricula);
        } catch (RuntimeException e) {
            throw new RuntimeException("fail: matrícula inexistente");
        }

        if (pessoa instanceof Aluno) {
            Aluno aluno = (Aluno) pessoa;

            ArrayList<Disciplina> disciplinasAluno = aluno.getDisciplinas();

            for (Disciplina disciplina : disciplinasAluno) {
                saida += "\nDisciplina: " + disciplina.getName() +
                        " | Nota1: " + disciplina.getNota1() +
                        " | Nota2: " + disciplina.getNota2() +
                        " | Média: " + disciplina.calcularMedia() +
                        " | Status: " + (disciplina.calcularMedia() >= 7 ? "Aprovado | " : "Reprovado | ");
            }

            saida += "\nMédia Geral: " + aluno.calcularMedia();
        } else {
            throw new RuntimeException("fail: a matrícula precisa ser de um aluno");
        }

        System.out.println(saida);
    }

    public void editarNota(int matriculaProf, Disciplina cadeira, int matriculaAluno, float novaNota, int numeroNota) {
        Pessoa pessoaProf = this.getPessoa(matriculaProf);
        Pessoa pessoaAluno = this.getPessoa(matriculaAluno);

        if (!(pessoaProf instanceof Professor)) {
            throw new RuntimeException("fail: essa matrícula não é de um professor");
        }

        Professor professor = (Professor) pessoaProf;
        Aluno aluno = (Aluno) pessoaAluno;

        if (!professor.temDisciplina(cadeira)) {
            throw new RuntimeException("fail: disciplina não encontrada para o professor");
        }

        if (!aluno.temDisciplina(cadeira)) {
            throw new RuntimeException("fail: disciplina não encontrada para o aluno");
        }

        if (numeroNota < 1 || numeroNota > 2) {
            throw new RuntimeException("fail: número da nota deve ser 1 ou 2");
        }

        aluno.editarNota(cadeira, novaNota, numeroNota);
        System.out.println("Nota editada com sucesso!");
    }

    public void editarAluno(int matricula, String novoNome, int novaIdade, Turno novoTurno, Scanner scanner) {
        Pessoa pessoa = getPessoa(matricula);

        if (pessoa instanceof Aluno) {
            Aluno aluno = (Aluno) pessoa;

            aluno.setNome(novoNome);
            aluno.setIdade(novaIdade);
            aluno.setTurno(novoTurno);

            System.out.println("Disciplinas do Aluno:");
            mostrarDisciplinas(aluno);

            System.out.print("Deseja editar disciplinas do aluno? (S/N): ");
            String resposta = obterEntradaSN(scanner);

            if (resposta.equalsIgnoreCase("S")) {
                editarDisciplinas(aluno, scanner);
            }
        } else {
            throw new RuntimeException("fail: essa matrícula não existe ou não é de um aluno");
        }

    }

    public void editarProfessor(int matricula, String novoNome, int novaIdade, Titulo novaTitulacao, Scanner scanner) {
        Pessoa pessoa = getPessoa(matricula);

        if (pessoa instanceof Professor) {
            Professor professor = (Professor) pessoa;

            professor.setNome(novoNome);
            professor.setIdade(novaIdade);
            professor.setTitulacao(novaTitulacao);

            System.out.println("Disciplinas do Professor:");
            mostrarDisciplinas(professor);

            System.out.print("Deseja editar disciplinas do professor? (S/N): ");
            String resposta = obterEntradaSN(scanner);

            if (resposta.equalsIgnoreCase("S")) {
                editarDisciplinas(professor, scanner);
            }
        } else {
            throw new RuntimeException("fail: essa matrícula não é de um professor");
        }
    }

    private void editarDisciplinas(Pessoa pessoa, Scanner scanner) {
        System.out.print("Digite o nome da disciplina que deseja editar: ");
        String nomeDisciplina = scanner.nextLine();

        Disciplina disciplina = encontrarDisciplina(pessoa, nomeDisciplina);

        if (disciplina != null) {
            System.out.println("1. Remover Disciplina");
            System.out.print("Escolha uma opção: ");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    pessoa.removerDisciplina(disciplina);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } else {
            System.out.println("Disciplina não encontrada para a pessoa.");
        }
    }

    public void rmPessoa(int matricula) {
        if (this.getPessoa(matricula) != null)
            pessoas.remove(matricula);
        else {
            throw new RuntimeException("fail: essa matricula é inexistente");
        }

    }

    public void mostrarAlunosDisciplina(String nomeDisciplina) {
        System.out.println("\n===== Alunos na Disciplina " + nomeDisciplina + " =====");
        boolean disciplinaEncontrada = false;

        for (Pessoa pessoa : pessoas.values()) {
            if (pessoa instanceof Aluno) {
                Aluno aluno = (Aluno) pessoa;
                ArrayList<Disciplina> disciplinasAluno = aluno.getDisciplinas();

                for (Disciplina disciplina : disciplinasAluno) {
                    if (disciplina.getName().equals(nomeDisciplina)) {
                        disciplinaEncontrada = true;
                        float media = disciplina.calcularMedia();
                        String status = (media >= 7) ? "Aprovado" : "Reprovado";

                        System.out.println("Nome: " + aluno.getNome() +
                                " | Matrícula: " + aluno.getMatricula() +
                                " | Nota1: " + disciplina.getNota1() +
                                " | Nota2: " + disciplina.getNota2() +
                                " | Média: " + media +
                                " | Status: " + status);
                    }
                }
            }
        }

        if (!disciplinaEncontrada) {
            throw new RuntimeException("Disciplina não encontrada");
        }
    }

    public Pessoa getPessoa(int matricula) {
        if (pessoas.get(matricula) == null) {
            throw new RuntimeException("fail: essa matricula é inexistente");
        }
        return pessoas.get(matricula);
    }

    public Collection<Pessoa> getPessoas() {
        return pessoas.values();
    }

    public boolean temAlunos() {
        for (Pessoa pessoa : this.pessoas.values()) {
            if (pessoa instanceof Aluno) {
                return true;
            }
        }
        return false;

    }

    public boolean temProfessor() {
        for (Pessoa pessoa : this.pessoas.values()) {
            if (pessoa instanceof Professor) {
                return true;
            }
        }
        return false;
    }

    private Disciplina encontrarDisciplina(Pessoa pessoa, String nomeDisciplina) {
        for (Disciplina disciplina : pessoa.getDisciplinas()) {
            if (disciplina.getName().equalsIgnoreCase(nomeDisciplina)) {
                return disciplina;
            }
        }
        return null;
    }

    private void mostrarDisciplinas(Pessoa pessoa) {
        for (Disciplina disciplina : pessoa.getDisciplinas()) {
            System.out.println("- " + disciplina.getName());
        }
    }

    public void salvarDadosEmArquivo(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            StringBuilder s = new StringBuilder("- Professores\n");
            for (Pessoa prof : pessoas.values()) {
                if (prof instanceof Professor)
                    s.append(prof);
            }

            s.append("\n- Alunos\n");
            for (Pessoa aluno : pessoas.values()) {
                if (aluno instanceof Aluno) {
                    Aluno a = (Aluno) aluno;
                    s.append(a);

                    ArrayList<Disciplina> disciplinasAluno = a.getDisciplinas();
                    for (Disciplina disciplina : disciplinasAluno) {
                        s.append("  - Disciplina: ").append(disciplina.getName())
                                .append(" | Nota1: ").append(disciplina.getNota1())
                                .append(" | Nota2: ").append(disciplina.getNota2()).append("\n");
                    }
                }
            }
            writer.write(s.toString());
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados no arquivo: " + e.getMessage());
        }
    }

    private String obterEntradaSN(Scanner scanner) {
        while (true) {
            String entrada = scanner.nextLine().toUpperCase();
            if (entrada.equals("S") || entrada.equals("N")) {
                return entrada;
            } else {
                System.out.println("Opção inválida. Digite 'S' para Sim ou 'N' para Não.");
                System.out.print("Escolha uma opção: ");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("- Professores\n");
        for (Pessoa prof : pessoas.values()) {
            if (prof instanceof Professor)
                s.append(prof);
        }

        s.append("\n- Alunos\n");
        for (Pessoa aluno : pessoas.values()) {
            if (aluno instanceof Aluno)
                s.append(aluno);
        }

        return s.toString();

    }
}