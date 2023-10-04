package lab1.shell;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu {
    protected Scanner scanner;

    protected ArrayList<String> options;

    protected String menuPrompt;

    public void run() {
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println("Available options:");
            this.printOptions();
            System.out.println();
            this.printPrompt(this.menuPrompt);
            String choice = this.scanner.nextLine();
            System.out.println();
            continueLoop = this.handleChoice(choice);

            System.out.println();
            if (continueLoop) {
                System.out.print("Press ENTER to continue...");
                this.scanner.nextLine();
            }
        }
    }

    protected void printPrompt(String prompt) {
        System.out.print(prompt + "> ");
    }

    protected void printInvalid() {
        System.out.println();
        System.out.println("Invalid input. Try again");
    }

    protected void printOptions() {
        this.options.forEach(System.out::println);
    }

    // Should return true if main loop should continue
    protected abstract boolean handleChoice(String choice);
}
