package command;

import dao.Dao;
import entity.Student;
import menu.MenuInput;

public class AddStudentCommand implements Command {

    private Dao dao = new Dao();

    @Override
    public void run() {

        String[] userInputs = new MenuInput("Student Toevoegen").askUserInputs("ID", "Voornaam", "Achternaam");
        System.out.println();
        int rowsUpdated = 0;

        if (isNumeric(userInputs[0])) {
            int id = Integer.parseInt(userInputs[0]);

            if (dao.getStudentById(id) != null) {
                System.out.println("Student met ID '" + id + "' bestaat al.");
            } else {
                Student student = new Student(id, userInputs[1], userInputs[2]);
                rowsUpdated = dao.addStudent(student);
            }
        } else {
            System.out.println("Student ID mag alleen nummers bevatten.");
        }

        System.out.println(rowsUpdated + " Student(en) toegevoegd.");
    }

    private boolean isNumeric(String strNum) {
        return strNum.matches("[0-9]+");
    }
}
