package command;

import dao.Dao;
import entity.StudyModule;
import menu.MenuInput;

public class FindStudyModuleCommand implements Command {

    private Dao dao = new Dao();

    @Override
    public void run() {

        String userInput = new MenuInput("Studieonderdeel Zoeken").askUserInputs("Naam")[0];
        StudyModule module = dao.getStudyModuleByName(userInput);

        System.out.println();
        if (module == null)
            System.out.println("Geen Studieonderdeel met de naam '" + userInput + "' gevonden.");
        else
            System.out.println(module);
    }
}
