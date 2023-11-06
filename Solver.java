import java.util.*;

enum Turno {
    MANHA,
    TARDE,
    NOITE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

enum Titulo {
    graduacao,
    mestrado,
    doutorado;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

abstract class Pessoa {

    protected String nome;
    protected int matricula;
    protected String email;

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

    public abstract boolean isAluno();

    public abstract boolean isProf();

    public abstract String toString();

}

class Aluno extends Pessoa {
    protected Turno turno;
    protected int idade;
    protected double nota1;
    protected double nota2;

    public Aluno(String nome, int matricula, String email, int idade ,Turno turno) {
        super(nome, matricula, email);
        this.turno = turno;
        this.nota1 = 0.00;
        this.nota2 = 0.00;
        this.idade = idade;
    }

    public Turno getTurno() {
        return this.turno;
    }

    public int getIdade() {
        return this.idade;
    }

    public double getNota1() {
        return this.nota1;
    }

    public double getNota2() {
        return this.nota2;
    }

    public void setNota1(double nota1) {
        if (nota1 >= 0 && nota1 <= 10) {
            this.nota1 = nota1;
        } else {
            throw new RuntimeException("fail: primeira nota invalida");
        }
    }

    public void setNota2(double nota2) {
        if (nota2 >= 0 && nota2 <= 10) {
            this.nota2 = nota2;
        } else {
            throw new RuntimeException("fail: segunda nota invalida");
        }
    }

    @Override
    public boolean isAluno() {
        return true;
    }

    @Override
    public boolean isProf() {
        return false;
    }

    @Override
    public String toString() {
        return "(Nome: " + this.getNome() + " | Matricula: " + this.getMatricula() + " | Idade: " + this.getIdade()
                + " | Turno: " + this.getTurno() + " | nota1: " + this.getNota1() + " | nota2: " + this.getNota2()+ ")\n";
    }
}

class Professor extends Pessoa {
    protected Titulo titulacao;
    protected int idade;
    protected ArrayList<String> disciplinas;

    public Professor(String nome, int matricula, String email, int idade, Titulo titulacao) {
        super(nome, matricula, email);
        this.idade = idade;
        this.titulacao = titulacao;
        this.disciplinas =  new ArrayList<>();

    }

    public void addDisciplinas(String cadeira) {
        if (cadeira != null) {
            this.disciplinas.add(cadeira);
        } else {
            throw new RuntimeException("fail: cadeira invalida");
        }
    }

    public void addNotas(Aluno aluno, double nota1, double nota2) {
        if (aluno != null) {
            aluno.setNota1(nota1);
            aluno.setNota2(nota2);
        }else{
            throw new RuntimeException("fail: aluno null");
        }
    }

    public Titulo getTitulacao() {
        return this.titulacao;
    }

    public int getIdade() {
        return this.idade;
    }

    public List<String> getDisciplinas() {
        return this.disciplinas;
    }

    @Override
    public boolean isAluno() {
        return false;
    }

    @Override
    public boolean isProf() {
        return true;
    }

    @Override
    public String toString() {
        return "(Nome: " + this.getNome() + " | Matricula: " + this.getMatricula() + " | Idade: " + this.getIdade()
                + " | Titulo " + this.getTitulacao() + " | cadeiras: " + this.getDisciplinas() + ")\n";
    }
}

class Sistema {
    private Map< Integer, Aluno> alunos;
    private Map< Integer, Professor> professores;

    public Sistema(){
        this.alunos = new HashMap<Integer, Aluno>();
        this.professores = new HashMap<Integer, Professor>();
    }

    public void addProfessor(String nome, int matricula, String email, int idade, Titulo titulacao){
        Professor prof = new Professor(nome, matricula, email, idade, titulacao);
        this.professores.put(matricula, prof);
    }

    public void addAluno(String nome, int matricula, String email, int idade, Turno turno){
        Aluno aluno = new Aluno(nome, matricula, email, idade, turno);
        this.alunos.put(matricula, aluno);
    }

    public String toString (){
        String s = "- Professores\n";
        for (Professor prof : this.professores.values()) {
            s += prof;
        }
        s += "\n- Alunos\n";
        for (Aluno aluno : this.alunos.values()) {
            s += aluno;
        }
        return s;
    }

}

public class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sistema sistema = new Sistema();

    try{
        sistema.addAluno("marquin", 15, "marquin@gmail.com", 18, Turno.MANHA);
        sistema.addAluno("xte", 14, "xte@gmail.com", 18, Turno.MANHA);
        sistema.addAluno("henrq", 13, "henrq@gmail.com", 18, Turno.MANHA);
        sistema.addAluno("mikas", 12, "mikas@gmail.com", 18, Turno.MANHA);
        sistema.addProfessor("Rubens", 01, "rubensfc@gmail.com", 55, Titulo.doutorado);
        sistema.addProfessor("Elvis", 02, "elvisfc@gmail.com", 40, Titulo.doutorado);
        sistema.addProfessor("Jefersson", 03, "jeferssonfc@gmail.com", 30, Titulo.doutorado);
        sistema.addProfessor("Pauline", 04, "paulinefc@gmail.com", 39, Titulo.doutorado);

        
        
        System.out.println(sistema);

            

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }

        }
    }

