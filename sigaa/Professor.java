package sigaa;

import java.util.*;

class Professor extends Pessoa {
    protected Titulo titulacao;

    public Professor(String nome, int matricula, String email, int idade, Titulo titulacao) {
        super(nome, matricula, email);
        this.idade = idade;
        this.titulacao = titulacao;
        this.disciplinas = new ArrayList<>();

    }

    public Titulo getTitulacao() {
        return this.titulacao;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setDisciplina(Disciplina cadeira) {
        this.disciplinas.add(cadeira);
    }

    public void setTitulacao(Titulo titulo) {
        this.titulacao = titulo;
    }

    @Override
    public String toString() {
        return "(Nome: " + this.getNome() + " | Matricula: " + this.getMatricula() + " | Idade: " + this.getIdade()
                + " | Titulo: " + this.getTitulacao() + " | cadeiras: " + this.getDisciplinas() + ")\n";
    }
}
