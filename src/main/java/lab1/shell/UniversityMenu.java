package lab1.shell;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import lab1.universityStructure.University;

public class UniversityMenu extends Menu {
    private University university;

    public UniversityMenu(Scanner scanner, University university) {
        this.scanner = scanner;
        this.university = university;

        this.menuIntro = "Available options for university:";
        this.menuPrompt = "University";
        this.options = new HashMap<>(Map.of(
            "q", "Quit the program"
        ));
    }
    
    protected boolean handleChoice(String choice) {
        switch (choice) {
            case "q":
            System.out.println("Exiting...");
            return false;

            default:
            this.printInvalid();
        }
        return true;
    }
}
