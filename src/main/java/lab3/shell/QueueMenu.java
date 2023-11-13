package lab3.shell;

import lab1.shell.Menu;
import lab3.dataStructures.queue.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QueueMenu extends Menu {
    Queue<String> queue;

    public QueueMenu(Scanner scanner, Queue<String> queue, String prompt) {
        this.scanner = scanner;
        this.queue = queue;

        this.menuPrompt = prompt;
        this.options = new ArrayList<>(List.of(
                "enq  - Enqueue element",
                "deq  - Dequeue element",
                "peek - Show first without dequeue",
                "info - Is empty or full",
                "exit - Try a different data structure"));
    }

    protected boolean handleChoice(String input) {
        String[] args = input.split(" ", 2);
        switch (args[0]) {
            case "enq":
                if (args.length != 2) {
                    System.out.println("Enter the string to enqueue");
                    break;
                }
                this.enqueue(args[1]);
                break;
            case "deq":
                this.dequeue();
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

    private void enqueue(String item) {
        try {
            this.queue.enqueue(item);
            System.out.println("Enqueued: " + item);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void dequeue() {
        try {
            System.out.println("Dequeued: " + this.queue.dequeue());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void peek() {
        try {
            System.out.println("Peeked: " + this.queue.peek());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void info() {
        if (this.queue.isEmpty()) {
            System.out.println("Empty");
        } else if (this.queue.isFull()) {
            System.out.println("Full");
        } else {
            System.out.println("Nothing interesting");
        }
    }
}
