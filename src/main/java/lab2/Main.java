package lab2;



import lab2.session.Session;
import lab2.shell.MonitorMenu;
import lab2.watcher.Watcher;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String directory = "./resources/lab2";
        try {
            Session.load();
        } catch (IOException e) {
            Session.lastCommit = LocalDateTime.now();
            Session.committedFilenames = new ArrayList<>();
        }

        new Watcher(directory).start();

        MonitorMenu shell = new MonitorMenu(
                new Scanner(System.in),
                directory
        );

        shell.run();
    }
}
