package sigaa;

import java.util.*;

class Aluno extends Pessoa {
    protected Turno turno;

    public Aluno(String nome, int matricula, int idade, Turno turno) {
        super(nome, matricula);
        this.turno = turno;
        if (idade > 90) {
            throw new RuntimeException("fail: idade acima do aceitado pelo sistema");
        } else {
            this.idade = idade;
        }
        this.disciplinas = new ArrayList<>();
    }

    public float calcularMedia() {
        if (this.getDisciplinas().isEmpty()) {
            return 0;
        }

        float somaNotas = 0;
        for (Disciplina disciplina : this.getDisciplinas()) {
            somaNotas += disciplina.calcularMedia();
        }

        return somaNotas / this.getDisciplinas().size();
    }

    public void editarNota(Disciplina disciplina, float novaNota, int numeroNota) {
        for (Disciplina alunoDisciplina : this.disciplinas) {
            if (alunoDisciplina.getName().equals(disciplina.getName())) {
                if (numeroNota == 1) {
                    alunoDisciplina.setNota(novaNota, numeroNota);
                } else if (numeroNota == 2) {
                    alunoDisciplina.setNota(novaNota, numeroNota);
                } else {
                    throw new RuntimeException("fail: número da nota deve ser 1 ou 2");
                }
                return;
            }
        }
        throw new RuntimeException("fail: disciplina não encontrada para o aluno");
    }

    public boolean temDisciplina(Disciplina disciplina) {
        for (Disciplina alunoDisciplina : this.disciplinas) {
            if (alunoDisciplina.getName().equals(disciplina.getName())) {
                return true;
            }
        }
        return false;
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
        return String.format("(Nome: %s | Matricula: %d | Idade: %d | Turno: %s | cadeiras matriculadas: %s)%n",
                getNome(), getMatricula(), getIdade(), getTurno(), getDisciplinas());
    }
}
