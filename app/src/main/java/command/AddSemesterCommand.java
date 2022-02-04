package command;

import dao.Dao;
import entity.Semester;
import menu.MenuInput;

public class AddSemesterCommand implements Command {

    private Dao dao = new Dao();

    @Override
    public void run() {

        String[] userInputs = new MenuInput("Semester Toevoegen").askUserInputs("ID", "Naam");
        System.out.println();
        int rowsUpdated = 0;

        String id = userInputs[0];

        if (dao.getSemesterById(id) != null) {
            System.out.println("Semster met ID '" + id + "' bestaat al.");
        } else {
            Semester semester = new Semester(id, userInputs[1]);
            rowsUpdated = dao.addSemester(semester);
        }

        System.out.println(rowsUpdated + " Semester(s) toegevoegd.");
    }
}
