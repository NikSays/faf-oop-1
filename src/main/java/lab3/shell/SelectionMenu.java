package lab3.shell;

import lab1.shell.Menu;
import lab3.dataStructures.queue.QueueArrayDown;
import lab3.dataStructures.queue.QueueArrayUp;
import lab3.dataStructures.queue.QueueLinkedList;
import lab3.dataStructures.stack.StackArrayDown;
import lab3.dataStructures.stack.StackArrayUp;
import lab3.dataStructures.stack.StackLinkedList;

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
                new QueueMenu(this.scanner, new QueueArrayUp<>(), "Queue Array Up").run();
            case "qd":
                new QueueMenu(this.scanner, new QueueArrayDown<>(), "Queue Array Down").run();
            case "ql":
                new QueueMenu(this.scanner, new QueueLinkedList<>(), "Queue Linked List").run();
            case "su":
                new StackMenu(this.scanner, new StackArrayUp<>(), "Stack Array Up").run();
            case "sd":
                new StackMenu(this.scanner, new StackArrayDown<>(), "Stack Array Down").run();
            case "sl":
                new StackMenu(this.scanner, new StackLinkedList<>(), "Stack Linked List").run();
            default:
                this.printInvalid();
        }
        return true;
    }
}