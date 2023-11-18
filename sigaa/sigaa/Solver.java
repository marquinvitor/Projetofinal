package sigaa;
import java.util.*;

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
                    sistema.addDisciplinas(Integer.parseInt(ui[1]), new Disciplina(ui[2]));
                } else if (ui[0].equals("show")) {
                    System.out.println(sistema);
                } else if (ui[0].equals("remover")) {
                    sistema.rmPessoa(Integer.parseInt(ui[1]));
                }
                else if (ui[0].equals("addnota")) {
                    sistema.adicionarNota1(Integer.parseInt(ui[1]), new Disciplina(ui[2]), Integer.parseInt(ui[3]), Float.parseFloat(ui[4]));
                }
                else if (ui[0].equals("matricular")) {
                    sistema.matricularAluno(Integer.parseInt(ui[1]), new Disciplina(ui[2]));
                }
                else if (ui[0].equals("mostrarnota")) {
                    sistema.mostrarNotas(Integer.parseInt(ui[1]));
                }

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }

        }

    }
}