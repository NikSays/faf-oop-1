package lab3;

import lab3.shell.SelectionMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new SelectionMenu(new Scanner(System.in)).run();
    }
}