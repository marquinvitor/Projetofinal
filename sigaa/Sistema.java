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
                if (v.getName().equals(cadeira.getName())) {
                    for (Disciplina alunoDisciplina : this.getPessoa(matriculaaluno).getDisciplinas()) {
                        if (alunoDisciplina.getName().equals(cadeira.getName())) {
                            alunoDisciplina.setNota1(nota);
                        }
                    }
                }
            }
        } else {
            throw new RuntimeException("fail: matrículas incorretas");
        }
    }

    public void adicionarNota2(int matriculadoprof, Disciplina cadeira, int matriculaaluno, float nota) {
        if (this.getPessoa(matriculadoprof) instanceof Professor && this.getPessoa(matriculaaluno) instanceof Aluno) {
            for (Disciplina v : this.getPessoa(matriculadoprof).getDisciplinas()) {
                if (v.getName().equals(cadeira.getName())) {
                    for (Disciplina alunoDisciplina : this.getPessoa(matriculaaluno).getDisciplinas()) {
                        if (alunoDisciplina.getName().equals(cadeira.getName())) {
                            alunoDisciplina.setNota2(nota);
                        }
                    }
                }
            }
        } else {
            throw new RuntimeException("fail: matrículas incorretas");
        }
    }

    public void mostrarNotas(int matricula) {
        String saida = "Notas do aluno: " + this.getPessoa(matricula).getNome() + "\n";

    Pessoa pessoa = this.getPessoa(matricula);
    
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
