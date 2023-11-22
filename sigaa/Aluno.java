package sigaa;

import java.util.*;

class Aluno extends Pessoa {
    protected Turno turno;

    public Aluno(String nome, int matricula, int idade, Turno turno) {
        super(nome, matricula);
        this.turno = turno;
        this.idade = idade;
        this.disciplinas = new ArrayList<>();
    }

    public float calcularMedia() {
        if (this.getDisciplinas().isEmpty()) {
            return 0; // Ou outro valor padrão se o aluno não estiver matriculado em nenhuma disciplina
        }

        float somaNotas = 0;
        for (Disciplina disciplina : this.getDisciplinas()) {
            somaNotas += disciplina.calcularMedia();
        }

        return somaNotas / this.getDisciplinas().size();
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
