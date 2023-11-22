package sigaa;

import java.util.*;

class Sistema {
    private Map<Integer, Pessoa> pessoas;

    public Sistema() {
        this.pessoas = new HashMap<Integer, Pessoa>();
    }

    public void addPessoa(Pessoa pessoa) {
        if (pessoa != null) {
            if (pessoa.getNome().matches("[a-zA-Z\\s]+")) {
                int matricula = pessoa.getMatricula();

                if (pessoas.containsKey(matricula)) {
                    throw new RuntimeException("fail: matrícula já existe");
                }
                pessoas.put(matricula, pessoa);
            } else {
                throw new RuntimeException("fail: nome inválido, deve conter apenas letras e espaços");
            }
        } else {
            throw new RuntimeException("fail: pessoa nula");
        }
    }

    public void addDisciplinas(int matricula, Disciplina cadeira) {
        if (this.getPessoa(matricula) instanceof Professor) {
            Professor professor = (Professor) this.getPessoa(matricula);

            for (Disciplina disciplina : professor.getDisciplinas()) {
                if (disciplina.getName().equals(cadeira.getName())) {
                    throw new RuntimeException("fail: disciplina já associada ao professor");
                }
            }

            professor.addDisciplinas(cadeira);
        } else {
            throw new RuntimeException("fail: a matrícula precisa existir e ser de um professor");
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
        Pessoa professor = this.getPessoa(matriculaProf);
        Pessoa aluno = this.getPessoa(matriculaAluno);

        if (professor instanceof Professor) {
            if (aluno instanceof Aluno) {
                for (Disciplina v : professor.getDisciplinas()) {
                    if (v.getName().equals(cadeira.getName())) {
                        for (Disciplina alunoDisciplina : aluno.getDisciplinas()) {
                            if (alunoDisciplina.getName().equals(cadeira.getName())) {
                                if (numeroNota >= 1 && numeroNota <= 2) {
                                    alunoDisciplina.setNota(novaNota, numeroNota);
                                    System.out.println("Nota editada com sucesso!");
                                    return;
                                } else {
                                    throw new RuntimeException("fail: número da nota deve ser 1 ou 2");
                                }
                            }
                        }
                        throw new RuntimeException("fail: disciplina não encontrada para o aluno");
                    }
                }
                throw new RuntimeException("fail: disciplina não encontrada para o professor");
            }
            throw new RuntimeException("fail: essa matricula não é de um aluno");
        }
        throw new RuntimeException("fail: essa matricula não é de um professor");
    }

    public void editarAluno(int matricula, String novoNome, int novaIdade, Turno novoTurno) {
        Pessoa pessoa = this.getPessoa(matricula);

        if (pessoa instanceof Aluno) {
            Aluno aluno = (Aluno) pessoa;
            aluno.setNome(novoNome);
            aluno.setIdade(novaIdade);
            aluno.setTurno(novoTurno);
        } else {
            throw new RuntimeException("fail: essa matrícula não existe ou não é de um aluno");
        }
    }

    public void editarProfessor(int matricula, String novoNome, int novaIdade, Titulo novaTitulacao) {
        Pessoa pessoa = this.getPessoa(matricula);

        if (pessoa instanceof Professor) {
            Professor professor = (Professor) pessoa;
            professor.setNome(novoNome);
            professor.setIdade(novaIdade);
            professor.setTitulacao(novaTitulacao);
        } else {
            throw new RuntimeException("fail: essa matrícula não é de um professor");
        }
    }

    public void matricularAluno(int matricula, Disciplina cadeira) {
        Pessoa pessoa = this.getPessoa(matricula);

        if (pessoa instanceof Aluno) {
            Aluno aluno = (Aluno) pessoa;

            for (Disciplina disciplina : aluno.getDisciplinas()) {
                if (disciplina.getName().equals(cadeira.getName())) {
                    throw new RuntimeException("fail: disciplina já associada ao aluno");
                }
            }

            aluno.addDisciplinas(cadeira);
        } else {
            throw new RuntimeException("fail: a matrícula precisa existir e ser de um aluno");
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
            throw new RuntimeException("Disciplina não encontrada.");
        }
    }

    public Pessoa getPessoa(int matricula) {
        if (pessoas.get(matricula) == null) {
            throw new RuntimeException("fail: essa matricula é inexistente");
        }
        return pessoas.get(matricula);
    }

    public String toString() {
        String s = "- Professores\n";
        for (Pessoa prof : this.pessoas.values()) {
            if (prof instanceof Professor)
                s += prof;
        }

        s += "\n- Alunos\n";
        for (Pessoa aluno : this.pessoas.values()) {
            if (aluno instanceof Aluno)
                s += aluno;
        }
        return s;
    }

}