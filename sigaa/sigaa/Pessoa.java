package sigaa;

import java.util.ArrayList;

abstract class Pessoa {

    protected String nome;
    protected int matricula;
    protected String email;
    protected ArrayList<Disciplina> disciplinas;

    public Pessoa(String nome, int matricula, String email) {
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public int getMatricula() {
        return this.matricula;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return this.disciplinas;
    }

    public void addDisciplinas(Disciplina cadeira) {
        if (cadeira != null) {
            this.disciplinas.add(cadeira);
        } else {
            throw new RuntimeException("fail: cadeira invalida");
        }
    }

    public abstract String toString();

}
