package sigaa;

import java.util.*;

class Aluno extends Pessoa {
    protected Turno turno;

    public Aluno(String nome, int matricula, String email, int idade, Turno turno) {
        super(nome, matricula, email);
        this.turno = turno;
        this.idade = idade;
        this.disciplinas = new ArrayList<>();
    }

    public Turno getTurno() {
        return this.turno;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "(Nome: " + this.getNome() + " | Matricula: " + this.getMatricula() + " | Idade: " + this.getIdade()
                + " | Turno: " + this.getTurno() + " | cadeiras matriculadas: " + this.getDisciplinas() + ")\n";
    }
}
