package sigaa;

import java.util.*;

class Sistema {
    private Map<Integer, Pessoa> pessoas;

    public Sistema() {
        this.pessoas = new HashMap<Integer, Pessoa>();
    }

    public void addPessoa(Pessoa pessoa) {
        pessoas.put(pessoa.getMatricula(), pessoa);
    }

    public void addDisciplinas(int matricula, Disciplina cadeira) {
        if (this.getPessoa(matricula) instanceof Professor) {
            this.getPessoa(matricula).addDisciplinas(cadeira);
        } else {
            throw new RuntimeException("fail: a matricula precisa ser de um professor");
        }
    }

    public void adicionarNota1(int matriculadoprof, Disciplina cadeira, int matriculaaluno, float nota) {
        if (this.getPessoa(matriculadoprof) instanceof Professor && this.getPessoa(matriculaaluno) instanceof Aluno) {
            for (Disciplina v : this.getPessoa(matriculadoprof).getDisciplinas()) {
                if (v == cadeira) {
                    for (Disciplina x : this.getPessoa(matriculaaluno).getDisciplinas()) {
                        if (x == cadeira) {
                            x.setNota1(nota);
                        }
                    }
                }
            }
        }else{throw new RuntimeException("fail: matriculas erradas");}
    }

    public void matricularAluno(int matricula, Disciplina cadeira) {
        if (this.getPessoa(matricula) instanceof Aluno) {
            this.getPessoa(matricula).addDisciplinas(cadeira);
        } else {
            throw new RuntimeException("fail: a matricula precisa ser de um aluno");
        }
    }

    public void rmPessoa(int matricula) {
        pessoas.remove(matricula);
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
