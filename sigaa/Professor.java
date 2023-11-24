package sigaa;

import java.util.*;

class Professor extends Pessoa {
    protected Titulo titulacao;

    public Professor(String nome, int matricula, int idade, Titulo titulacao) {
        super(nome, matricula);
        if (idade > 90 || idade < 18) {
            throw new RuntimeException("fail: idade não aceita pelo sistema");
        } else {
            this.idade = idade;
        }
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
        return String.format("(Nome: %s | Matricula: %d | Idade: %d | Titulo: %s | cadeiras: %s)%n",
                getNome(), getMatricula(), getIdade(), getTitulacao(), getDisciplinas());
    }
}
