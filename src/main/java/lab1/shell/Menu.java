package lab1.shell;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu {
    protected Scanner scanner;

    protected ArrayList<String> options;

    protected String menuPrompt;

    public void run(){
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println("\nAvailable options:");
            this.printOptions();
            this.printPrompt(menuPrompt);
            String choice = this.scanner.nextLine();
            continueLoop = this.handleChoice(choice);
        }
    }

    protected void printPrompt(String prompt) {
        System.out.print(prompt + "> ");
    }

    protected void printInvalid() {
        System.out.println("Invalid input. Try again");
    }

    // TODO?
    protected void printOptions() {
        this.options.
        forEach(entry -> System.out.println(entry));
    }

    // Should return true if main loop should continue
    protected abstract boolean handleChoice(String choice); 
}
