package lab2.watcher;

import lab2.session.Session;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Watcher implements Runnable{

    ArrayList<String> createdNoCommit;
    ArrayList<String> deletedNoCommit;
    LocalDateTime lastRun;

    // ew
    private final String directory = "./resources/lab2/";


    public Watcher() {
        this.createdNoCommit = new ArrayList<>();
        this.deletedNoCommit = new ArrayList<>();
        this.lastRun = LocalDateTime.now();

    }
    @Override
    public void run() {
        LocalDateTime runTime = LocalDateTime.now();
        long lastRunMillis = this.lastRun.toInstant(Session.offset).toEpochMilli();

        File[] currentFiles = new File(this.directory).listFiles();
        if (currentFiles == null) {
            System.out.println("Not looking in a directory");
            return;
        }
        List<String> currentFilenames = Arrays.stream(currentFiles).map(File::getName).toList();

        for (File file : currentFiles) {
            if (!Session.committedFilenames.contains(file.getName()) && !this.createdNoCommit.contains(file.getName())) {
                this.fileCreated(file.getName());
            } else if (file.lastModified() > lastRunMillis) {
                System.out.printf("MODIFIED - %s\n", file.getName());
            }
        }

        ArrayList<String> allFilenames = new ArrayList<>(Session.committedFilenames);
        allFilenames.addAll(this.createdNoCommit);

        for (String name : allFilenames) {
            if (!currentFilenames.contains(name) && !this.deletedNoCommit.contains(name)) {
                this.fileDeleted(name);
            }
        }
        this.lastRun = runTime;
    }

    private void fileCreated(String name) {
        System.out.printf("CREATED  - %s\n", name);
        this.createdNoCommit.add(name);
        this.deletedNoCommit.remove(name);
    }

    private void fileDeleted(String name) {
        System.out.printf("DELETED  - %s\n", name);
        this.deletedNoCommit.add(name);
        this.createdNoCommit.remove(name);
    }
}
