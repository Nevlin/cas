package command;

import dao.Dao;
import entity.Student;
import menu.MenuInput;

public class ChangeStudentNameCommand implements Command {

    private Dao dao = new Dao();

    @Override
    public void run() {

        String userInput = new MenuInput("Voornaam Student Aanpassen").askUserInputs("ID")[0];
        int rowsUpdated = 0;

        System.out.println();
        if (isNumeric(userInput)) {
            int id = Integer.parseInt(userInput);

            Student student = dao.getStudentById(id);
            if (student != null) {

                System.out.println(student);
                String userInput2 = new MenuInput().askUserInputs("Nieuwe Voornaam")[0];
                student.setFirstName(userInput2);
                rowsUpdated = dao.updateStudent(student);

            } else {
                System.out.println("Student met ID '" + userInput + "' bestaat niet.");
            }
        } else {
            System.out.println("Student ID mag alleen nummers bevatten.");
        }

        System.out.println(rowsUpdated + " Student(en) Aangepast.");
    }

    private boolean isNumeric(String strNum) {
        return strNum.matches("[0-9]+");
    }
}
