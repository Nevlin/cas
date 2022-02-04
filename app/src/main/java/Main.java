import command.*;
import global.DatabaseConnection;
import menu.Menu;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        setupLogger();

        // Create Menu's
        Menu studentMenu = new Menu("Student");
        studentMenu.addOption("Toevoegen", new AddStudentCommand());
        studentMenu.addOption("Zoeken", new FindStudentCommand());
        studentMenu.addOption("Voornaam Aanpassen", new ChangeStudentNameCommand());
        studentMenu.addOption("Inschrijven Semester", new AddStudentSemesterCommand());
        studentMenu.addOption("Uitschrijven Semester", new DeleteStudentSemesterCommand());

        Menu semesterMenu = new Menu("Semester");
        semesterMenu.addOption("Toevoegen", new AddSemesterCommand());
        semesterMenu.addOption("Zoeken", new FindSemesterCommand());
        semesterMenu.addOption("Inschrijven Student", new AddStudentSemesterCommand());
        semesterMenu.addOption("Uitschrijven Student", new DeleteStudentSemesterCommand());

        Menu moduleMenu = new Menu("Studieonderdeel");
        moduleMenu.addOption("Toevoegen", new AddStudyModuleCommand());
        moduleMenu.addOption("Zoeken", new FindStudyModuleCommand());

        Menu mainMenu = new Menu("Menu", "Afsluiten");
        mainMenu.addOption("Student", studentMenu);
        mainMenu.addOption("Semester", semesterMenu);
        mainMenu.addOption("Studieonderdeel", moduleMenu);

        // Run Menu's
        printTitle();
        mainMenu.run();
        exitApplication();
    }

    public static void setupLogger() {
        Logger logger = Logger.getLogger(Main.class.getName());
        LogManager.getLogManager().reset();
        FileHandler handler = null;
        try {
            handler = new FileHandler("log.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(handler);
        logger.setLevel(Level.FINE);
    }

    public static void exitApplication() {
        System.out.println("Applicatie Afsluiten...");
        DatabaseConnection.closeConnection();
    }

    private static void printTitle() {
        System.out.println();
        System.out.println("     _/_/_/    _/_/      _/_/_/");
        System.out.println("  _/        _/    _/  _/       ");
        System.out.println(" _/        _/_/_/_/    _/_/    ");
        System.out.println("_/        _/    _/        _/   ");
        System.out.println(" _/_/_/  _/    _/  _/_/_/      ");
        System.out.println();
        System.out.println("Cijfer Administratie Systeem");
        System.out.println("(v1.0)");
    }
}
