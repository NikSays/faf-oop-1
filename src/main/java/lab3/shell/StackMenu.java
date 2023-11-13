package lab3.shell;

import lab1.shell.Menu;
import lab3.dataStructures.stack.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StackMenu extends Menu {
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
            case "push":
                if (args.length != 2) {
                    System.out.println("Enter the string to push");
                    break;
                }
                this.push(args[1]);
                break;
            case "pop":
                this.pop();
                break;
            case "peek":
                this.peek();
                break;
            case "info":
                this.info();
                break;
            case "exit":
                return false;
            default:
                this.printInvalid();
        }
        return true;
    }

    private void push(String item) {
        try {
            this.stack.push(item);
            System.out.println("Pushed: " + item);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void pop() {
        try {
            System.out.println("Popped: " + this.stack.pop());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void peek() {
        try {
            System.out.println("Peeked: " + this.stack.peek());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void info() {
        if (this.stack.isEmpty()) {
            System.out.println("Empty");
        } else if (this.stack.isFull()) {
            System.out.println("Full");
        }
    }
}
