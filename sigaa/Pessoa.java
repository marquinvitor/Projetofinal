package sigaa;

import java.util.*;

abstract class Pessoa {

    protected String nome;
    protected int matricula;
    protected String email;
    protected ArrayList<Disciplina> disciplinas = new ArrayList<>();
    protected int idade;

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

    public void setNome(String nome) {
        if (nome.matches("[a-zA-Z\\s]+")) {
            this.nome = nome;
        } else {
            throw new RuntimeException("fail: nome inválido, deve conter apenas letras e espaços");
        }
    }

    public void setMatricula(int matricula) {
        if(!String.valueOf(matricula).matches("\\d+")){
            throw new RuntimeException("fail: a matricula deve conter apenas numeros");
        }
        this.matricula = matricula;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdade(int idade) {
        this.idade = idade;
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
