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
                pessoas.put(pessoa.getMatricula(), pessoa);
            } else {
                throw new RuntimeException("fail: nome inválido, deve conter apenas letras e espaços");
            }
        } else {
            throw new RuntimeException("fail: pessoa nula");
        }
    }

    public void addDisciplinas(int matricula, Disciplina cadeira) {
        if (this.getPessoa(matricula) instanceof Professor) {
            this.getPessoa(matricula).addDisciplinas(cadeira);
        } else {
            throw new RuntimeException("fail: a matricula precisa existir e ser de um professor");
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
            throw new RuntimeException("fail: matrícula inexistente, burro!");
        }

        if (pessoa instanceof Aluno) {
            Aluno aluno = (Aluno) pessoa;

            ArrayList<Disciplina> disciplinasAluno = aluno.getDisciplinas();

            for (Disciplina disciplina : disciplinasAluno) {
                saida += "Disciplina: " + disciplina.getName() +
                        " | Nota1: " + disciplina.getNota1() +
                        " | Nota2: " + disciplina.getNota2() + "\n";
            }

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
                                alunoDisciplina.setNota(novaNota, numeroNota);
                                return;
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

    public void editarAluno(int matricula, String novoNome, String novoEmail, int novaIdade, Turno novoTurno) {
        Pessoa pessoa = this.getPessoa(matricula);

        if (pessoa instanceof Aluno) {
            Aluno aluno = (Aluno) pessoa;
            aluno.setNome(novoNome);
            aluno.setEmail(novoEmail);
            aluno.setIdade(novaIdade);
            aluno.setTurno(novoTurno);
        } else {
            throw new RuntimeException("fail: essa matrícula não existe ou não é de um aluno");
        }
    }

    public void editarProfessor(int matricula, String novoNome, String novoEmail, int novaIdade, Titulo novaTitulacao) {
        Pessoa pessoa = this.getPessoa(matricula);

        if (pessoa instanceof Professor) {
            Professor professor = (Professor) pessoa;
            professor.setNome(novoNome);
            professor.setEmail(novoEmail);
            professor.setIdade(novaIdade);
            professor.setTitulacao(novaTitulacao);
        } else {
            throw new RuntimeException("fail: essa matrícula não é de um professor");
        }
    }

    public void matricularAluno(int matricula, Disciplina cadeira) {
        if (this.getPessoa(matricula) instanceof Aluno) {
            this.getPessoa(matricula).addDisciplinas(cadeira);
        } else {
            throw new RuntimeException("fail: a matricula precisa existir e ser de um aluno");
        }
    }

    public void rmPessoa(int matricula) {
        if (this.getPessoa(matricula) != null)
            pessoas.remove(matricula);
        else {
            throw new RuntimeException("fail: essa matricula é inexistente");
        }

    }

    public Pessoa getPessoa(int matricula) {
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