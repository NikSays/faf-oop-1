package lab3.shell;

import lab1.shell.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SelectionMenu extends Menu {
    public SelectionMenu(Scanner scanner) {
        this.scanner = scanner;

        this.menuPrompt = "Lab3";
        this.options = new ArrayList<>(List.of(
                "qu - Queue, array up",
                "qd - Queue, array down",
                "ql - Queue, linked list",
                "su - Stack, array up",
                "sd - Stack, array down",
                "sl - Stack, linked list"));
    }

    protected boolean handleChoice(String choice) {
        switch (choice) {
            case "qu":
            case "qd":
            case "ql":
            case "su":
            case "sd":
            case "sl":
            default:
                this.printInvalid();
        }
        return true;
    }
}