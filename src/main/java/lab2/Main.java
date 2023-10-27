package lab2;



import lab2.session.Session;
import lab2.shell.MonitorMenu;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Session.load();
        } catch (IOException e) {
            Session.lastCommit = LocalDateTime.now();
            Session.existingFiles = new ArrayList<>();
        }
        MonitorMenu shell = new MonitorMenu(
                new Scanner(System.in)
        );

        shell.run();
    }
}
