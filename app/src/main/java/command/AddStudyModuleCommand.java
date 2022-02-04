package command;

import dao.Dao;
import entity.StudyModule;
import menu.MenuInput;

public class AddStudyModuleCommand implements Command {

    private Dao dao = new Dao();

    @Override
    public void run() {

        String[] userInputs = new MenuInput("Studieonderdeel Toevoegen").askUserInputs("ID", "Naam");
        System.out.println();
        int rowsUpdated = 0;

        String id = userInputs[0];

        if (dao.getStudyModuleById(id) != null) {
            System.out.println("Studieonderdeel met ID '" + id + "' bestaat al.");
        } else {
            StudyModule module = new StudyModule(id, userInputs[1]);
            rowsUpdated = dao.addStudyModule(module);
        }

        System.out.println(rowsUpdated + " Studieonderdeel(en) toegevoegd.");
    }
}
