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

    public void setNota1(float nota) {
        this.nota1 = nota;
    }

    public void setNota2(float nota) {
        this.nota2 = nota;
    }

    public String toString() {
        return this.nome;
    }

}
