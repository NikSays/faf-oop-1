package lab1;

import java.io.IOException;
import java.util.Scanner;

import lab1.log.Level;
import lab1.log.TXTLogger;
import lab1.session.BinaryFileManager;
import lab1.shell.UniversityMenu;
import lab1.universityStructure.University;

public class Main {
    public static void main(String[] args) throws IOException {
        // Initialize dependencies
        TXTLogger.init("./log.txt", Level.DEBUG);
        BinaryFileManager stateManager = new BinaryFileManager("./.session");

        UniversityMenu shell = new UniversityMenu(
            new Scanner(System.in),
            new University(stateManager)
        );

        shell.run();
    }
}
