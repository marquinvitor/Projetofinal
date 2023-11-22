package sigaa;

public class Disciplina {
    protected String nome;
    protected float nota1;
    protected float nota2;

    public Disciplina(String nome) {
        this.nome = nome;
        this.nota1 = 0;
        this.nota2 = 0;
    }

    public String getName() {
        return this.nome;
    }

    public float getNota1() {
        return this.nota1;
    }

    public float getNota2() {
        return this.nota2;
    }

    public void setNota(float nota, int numNota) {
        if (numNota == 1) {
            nota1 = nota;
        }

        if (numNota == 2) {
            nota2 = nota;
        }
    }

    public String toString() {
        return this.nome;
    }

}
