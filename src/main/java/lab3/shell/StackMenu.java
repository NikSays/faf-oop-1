package lab3.shell;

import lab1.shell.Menu;
import lab3.stack.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StackMenu extends Menu {
    // Need university to save global state on faculty changes
    Stack<String> stack;

    public StackMenu(Scanner scanner, Stack<String> stack, String prompt) {
        this.scanner = scanner;
        this.stack = stack;

        this.menuPrompt = prompt;
        this.options = new ArrayList<>(List.of(
                "push - Push element",
                "pop  - Pop element",
                "peek - Show first without pop",
                "info - Is empty or full",
                "exit - Try a different data structure"));
    }

    protected boolean handleChoice(String input) {
        String[] args = input.split(" ", 2);
        switch (args[0]) {
            case "push": {
                if (args.length != 2) {
                    System.out.println("Enter the string to push");
                    break;
                }
                this.push(args[1]);
                System.out.println("Pushed");
                break;
            }
            case "pop": {
                String item = this.pop();
                System.out.println("Popped: " + item);
                break;
            }
            case "peek": {
                String item = this.peek();
                System.out.println("Peeked: " + item);
                break;
            }
            case "info": {
                this.info();
                break;
            }
            default:
                this.printInvalid();
        }
        return true;
    }

    private void push(String item) {
        this.stack.push(item);
    }

    private String pop() {
        return this.stack.pop();
    }

    private String peek() {
        return this.stack.peek();
    }

    private void info() {
        if (this.stack.isEmpty()) {
            System.out.println("Empty");
        } else if (this.stack.isFull()) {
            System.out.println("Full");
        }
    }
}
