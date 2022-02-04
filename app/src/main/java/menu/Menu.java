package menu;

import command.Command;
import global.ScannerGlobal;

import java.util.*;

public class Menu implements Command {

    private String title;
    private LinkedHashMap<String, Command> options = new LinkedHashMap<>();
    private Scanner sc = ScannerGlobal.getScanner();
    private String backOptionName = "Terug";

    public Menu(String title) {
        this.title = title;
    }
    public Menu(String title, String backOptionName) {
        this.title = title;
        this.backOptionName = backOptionName;
    }

    @Override
    public void run() {
        boolean printMenu = true;
        while (true) {

            if (printMenu) printMenu();
            int userInput = getUserInput();
            if (userInput == 0) break;

            if (userInput > 0 && userInput <= options.size()) {
                Command command = getCommandAtIndex(userInput);
                command.run();
                printMenu = true;
            } else {
                System.out.println("Niet beschikbaar. Kies een getal van 0 t/m " + options.size() + ".");
                printMenu = false;
                System.out.print("> ");
            }
        }
    }

    private void printTitle() {
        System.out.println();
        System.out.println("-------------");
        System.out.println("[ " + title + " ]");
    }

    private void printMenu() {
        printTitle();

        int index = 1;
        for (String key : options.keySet()) {
            System.out.println("[" + (index++) + "] " + key);
        }
        System.out.println("[0] " + backOptionName);
        System.out.print("> ");
    }

    public void addOption(String option, Command command) {
        options.put(option, command);
    }

    private Command getCommandAtIndex(int index) {
        Set<String> keySet = options.keySet();
        List<String> listKeys = new ArrayList<>(keySet);
        String key = listKeys.get(index-1);
        return options.get(key);
    }

    private int getUserInput() {
        String input = sc.next() + sc.nextLine();
        if (!input.matches("[0-9]+")) return -1;

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
