package lab3.shell;

import lab1.shell.Menu;
import lab3.queue.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QueueMenu extends Menu {
    // Need university to save global state on faculty changes
    Queue<String> queue;

    public QueueMenu(Scanner scanner, Queue<String> queue, String name) {
        this.scanner = scanner;
        this.queue = queue;

        this.menuPrompt = "Faculty " + name;
        this.options = new ArrayList<>(List.of(
                "enq  - enqueue element",
                "deq  - dequeue element",
                "peek - show first without dequeue",
                "info - is empty or full"));
    }

    protected boolean handleChoice(String input) {
        String[] args = input.split(" ", 2);
        switch (args[0]) {
            case "enq": {
                if (args.length != 2) {
                    System.out.println("Enter the string to enqueue");
                    break;
                }
                this.enqueue(args[1]);
                System.out.println("Enqueued");
                break;
            }
            case "deq": {
                String item = this.dequeue();
                System.out.println("Dequeued: " + item);
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

    private void enqueue(String item) {
        this.queue.enqueue(item);
    }

    private String dequeue() {
        return this.queue.dequeue();
    }

    private String peek() {
        return this.queue.peek();
    }

    private void info() {
        if (this.queue.isEmpty()) {
            System.out.println("Empty");
        } else if (this.queue.isFull()) {
            System.out.println("Full");
        }
    }
}
