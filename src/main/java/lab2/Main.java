package lab2;



import lab2.session.Session;
import lab2.shell.MonitorMenu;
import lab2.watcher.Watcher;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        String directory = "./resources/lab2";
        try {
            Session.load();
        } catch (IOException e) {
            Session.lastCommit = LocalDateTime.now();
            Session.committedFilenames = new ArrayList<>();
        }


        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new Watcher(), 1, 1, TimeUnit.SECONDS);

        MonitorMenu shell = new MonitorMenu(
                new Scanner(System.in),
                directory
        );

        shell.run();
    }
}
