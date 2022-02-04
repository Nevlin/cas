package menu;

import global.ScannerGlobal;

import java.util.Scanner;

public class MenuInput {

    private Scanner sc = ScannerGlobal.getScanner();
    private String title = null;

    public MenuInput() {}

    public MenuInput(String title) {
        this.title = title;
    }

    public String[] askUserInputs(String... labels) {

        System.out.println();
        if (title != null) printTitle();

        String[] userInputs = new String[labels.length];

        for (int i = 0; i < labels.length; i++) {
            System.out.print(labels[i] + ": ");
            userInputs[i] = sc.next() + sc.nextLine();
        }
        return userInputs;
    }

    private void printTitle() {
        System.out.println("-------------");
        System.out.println("[ " + title + " ]");
    }
}
