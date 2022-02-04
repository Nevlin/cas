package command;

import dao.Dao;
import entity.Semester;
import entity.Student;
import menu.MenuInput;

public class DeleteStudentSemesterCommand implements Command {

    private Dao dao = new Dao();

    @Override
    public void run() {

        String[] userInputs = new MenuInput("Student Uitschrijven Semester").askUserInputs("Student ID", "Semester ID");
        int rowsUpdated;
        Student student;
        System.out.println();

        if (isNumeric(userInputs[0])) {
            int id = Integer.parseInt(userInputs[0]);
            student = dao.getStudentById(id);

            if (student == null) {
                System.out.println("Geen Student met ID '" + userInputs[0] + "' gevonden.");
                return;
            }

        } else {
            System.out.println("Student ID mag alleen nummers bevatten.");
            return;
        }

        Semester semester = dao.getSemesterById(userInputs[1]);
        if (semester == null) {
            System.out.println("Geen Semester met ID '" + userInputs[1] + "' gevonden.");
            return;
        }

        rowsUpdated = dao.deleteStudentSemester(student, semester);
        if (rowsUpdated == 0) {
            System.out.println("Student staat niet ingeschreven in dit Semester.");
        }

        System.out.println(rowsUpdated + " Student(en) uitgeschreven.");
    }

    private boolean isNumeric(String strNum) {
        return strNum.matches("[0-9]+");
    }
}
