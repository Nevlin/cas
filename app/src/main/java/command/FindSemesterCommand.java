package command;

import dao.Dao;
import entity.Semester;
import menu.MenuInput;

public class FindSemesterCommand implements Command {

    private Dao dao = new Dao();

    @Override
    public void run() {

        String userInput = new MenuInput("Semester Zoeken").askUserInputs("Naam")[0];
        Semester semester = dao.getSemesterByName(userInput);

        System.out.println();
        if (semester == null)
            System.out.println("Geen Semester met de naam '" + userInput + "' gevonden.");
        else
            System.out.println(semester);
    }
}
