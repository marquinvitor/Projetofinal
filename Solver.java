package sigaa;
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
    GRADUACAO,
    MESTRADO,
    DOUTORADO;

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

    public abstract void addDisciplinas(String cadeira);

    public abstract String toString();

}

class Aluno extends Pessoa {
    protected Turno turno;
    protected int idade;
    protected double nota1;
    protected double nota2;
    protected ArrayList<String> disciplinas;

    public Aluno(String nome, int matricula, String email, int idade, Turno turno) {
        super(nome, matricula, email);
        this.turno = turno;
        this.nota1 = 0.00;
        this.nota2 = 0.00;
        this.idade = idade;
    }

    @Override
    public void addDisciplinas(String cadeira) {
        if (cadeira != null) {
            this.disciplinas.add(cadeira);
        } else {
            throw new RuntimeException("fail: cadeira invalida");
        }
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
    public String toString() {
        return "(Nome: " + this.getNome() + " | Matricula: " + this.getMatricula() + " | Idade: " + this.getIdade()
                + " | Turno: " + this.getTurno() + " | nota1: " + this.getNota1() + " | nota2: " + this.getNota2()
                + ")\n";
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
        this.disciplinas = new ArrayList<>();

    }

    public void addNotas(Aluno aluno, double nota1, double nota2) {
        if (aluno != null) {
            aluno.setNota1(nota1);
            aluno.setNota2(nota2);
        } else {
            throw new RuntimeException("fail: aluno null");
        }
    }

    @Override
    public void addDisciplinas(String cadeira) {
        if (cadeira != null) {
            this.disciplinas.add(cadeira);
        } else {
            throw new RuntimeException("fail: cadeira invalida");
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

    public void setDisciplina(String cadeira) {
        this.disciplinas.add(cadeira);
    }

    @Override
    public String toString() {
        return "(Nome: " + this.getNome() + " | Matricula: " + this.getMatricula() + " | Idade: " + this.getIdade()
                + " | Titulo " + this.getTitulacao() + " | cadeiras: " + this.getDisciplinas() + ")\n";
    }
}

class Sistema {
    private Map<Integer, Pessoa> pessoas;

    public Sistema() {
        this.pessoas = new HashMap<Integer, Pessoa>();
    }

    public void addPessoa(Pessoa pessoa) {
        pessoas.put(pessoa.getMatricula(), pessoa);
    }

    public void addDisciplinas(int matricula, String cadeira) {
        if (this.getPessoa(matricula) instanceof Professor) {
            this.getPessoa(matricula).addDisciplinas(cadeira);
        } else {
            throw new RuntimeException("fail: a matricula precisa ser de um professor");
        }
    }

    public void rmPessoa(int matricula) {
        pessoas.remove(matricula);
    }

    public Pessoa getPessoa(int matricula) {
        return pessoas.get(matricula);
    }

    public String toString() {
        String s = "- Professores\n";
        for (Pessoa prof : this.pessoas.values()) {
            if (prof instanceof Professor)
                s += prof;
        }

        s += "\n- Alunos\n";
        for (Pessoa aluno : this.pessoas.values()) {
            if (aluno instanceof Aluno)
                s += aluno;
        }
        return s;
    }

}

public class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sistema sistema = new Sistema();
        while (true) {

            try {
                String line = scanner.nextLine();
                System.out.println("$" + line);
                String ui[] = line.split(" ");

                if (ui[0].equals("end")) {
                    break;
                } else if (ui[0].equals("addaluno")) {
                    sistema.addPessoa(new Aluno(ui[1], Integer.parseInt(ui[2]), ui[3], Integer.parseInt(ui[4]),
                            Turno.valueOf(ui[5].toUpperCase())));
                } else if (ui[0].equals("addprofessor")) {
                    sistema.addPessoa(new Professor(ui[1], Integer.parseInt(ui[2]), ui[3], Integer.parseInt(ui[4]),
                            Titulo.valueOf(ui[5].toUpperCase())));
                } else if (ui[0].equals("addcadeira")) {
                    sistema.addDisciplinas(Integer.parseInt(ui[1]), ui[2]);
                } else if (ui[0].equals("show")) {
                    System.out.println(sistema);
                } else if (ui[0].equals("remover")) {
                    sistema.rmPessoa(Integer.parseInt(ui[1]));
                }

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }

        }

    }
}
