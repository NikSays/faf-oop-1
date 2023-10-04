package lab1.shell;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {
    protected Scanner scanner;

    protected HashMap<String, String> options;

    protected String menuIntro;
    protected String menuPrompt;

    public void run(){
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println(this.menuIntro);
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
        System.out.println("Invalid input. Try again\n");
    }

    protected void printOptions() {
        this.options.entrySet().
        forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));
    }

    // Should return true if main loop should continue
    protected abstract boolean handleChoice(String choice); 
}
