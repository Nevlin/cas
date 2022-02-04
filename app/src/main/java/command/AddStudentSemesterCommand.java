package command;

import dao.Dao;
import entity.Semester;
import entity.Student;
import menu.MenuInput;

public class AddStudentSemesterCommand implements Command {

    private Dao dao = new Dao();

    @Override
    public void run() {

        String[] userInputs = new MenuInput("Student Inschrijven Semester").askUserInputs("Student ID", "Semester ID");
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

        rowsUpdated = dao.addStudentSemester(student, semester);
        if (rowsUpdated == 0) {
            System.out.println("Student staat al ingeschreven.");
            return;
        }

        System.out.println(rowsUpdated + " Student(en) ingeschreven.");
    }

    private boolean isNumeric(String strNum) {
        return strNum.matches("[0-9]+");
    }
}
