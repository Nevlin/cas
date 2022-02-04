package command;

import dao.Dao;
import entity.Student;
import menu.MenuInput;

public class FindStudentCommand implements Command {

    private Dao dao = new Dao();

    @Override
    public void run() {

        String userInput = new MenuInput("Student Zoeken").askUserInputs("ID of Achternaam")[0];
        Student student = null;
        String idOrLastname = "achternaam";

        if (isNumeric(userInput)) {
            try {
                int id = Integer.parseInt(userInput);
                student = dao.getStudentById(id);
                idOrLastname = "ID";
            } catch (NumberFormatException ignore) {}
        } else {
            student = dao.getStudentByLastName(userInput);
        }

        System.out.println();
        if (student == null)
            System.out.println("Geen Student met " + idOrLastname + " '" + userInput + "' gevonden.");
        else
            System.out.println(student);
    }

    private boolean isNumeric(String strNum) {
        return strNum.matches("[0-9]+");
    }
}
