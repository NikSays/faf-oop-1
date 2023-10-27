package lab2.shell;


import lab2.fileInfo.FileInfo;
import lab2.fileInfo.ImageInfo;
import lab2.session.Session;
import lab2.fileInfo.TXTInfo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class MonitorMenu extends Menu {
    private final String directory;

    public MonitorMenu(Scanner scanner, String directory) {
        this.menuPrompt = "menu";
        this.scanner = scanner;
        this.directory = directory;
        this.options = new ArrayList<>(List.of(
                "commit      - apply current changes",
                "info <file> - print info about a file",
                "status      - print current changes",
                "quit        - exit the program"));
    }

    protected boolean handleInput(String input) {
        String[] args = input.split(" ");
        switch (args[0]) {
            case "commit":
                this.commit();
                break;
            case "info":
                if (args.length == 2) {
                    File f = new File(this.directory + args[1]);
                    this.printInfo(f);
                } else {
                    this.printInfo();
                }
                break;
            case "status":
                this.printStatus();
                break;
            case "quit":
                System.out.println("Exiting...");
                return false;
            default:
                this.printInvalid();
        }
        return true;
    }

    private void safeSaveState() {
        try {
            Session.save();
        } catch (Exception e) {
            System.out.println("Failed to save state to disk.");
            e.printStackTrace();
        }
    }

    private void commit() {
        ArrayList<String> existingFiles = new ArrayList<>();
        File[] files = new File(this.directory).listFiles();
        if (files == null) {
            System.out.println("Not looking in a directory");
            return;
        }

        for (File file : files) {
            existingFiles.add(file.getName());
        }

        Session.lastCommit = LocalDateTime.now();
        Session.committedFilenames = existingFiles;
        this.safeSaveState();
        System.out.println("Committed changes.");
    }

    private void printInfo() {
        File[] files = new File(this.directory).listFiles();
        if (files == null) {
            System.out.println("Error: " + this.directory + " is not a directory");
            return;
        }

        for (File file : files) {
            this.printInfo(file);
            System.out.println("-------------\n");
        }
    }

    private void printInfo(File file) {
        if (!file.exists()) {
            System.out.println("No such file.");
            return;
        }
        FileInfo fi;
        try {
            fi = this.getInfoByExtension(file);
        } catch (IOException e) {
            System.out.println("Failed to get info.");
            e.printStackTrace();
            return;
        }
        fi.printBasic();
        System.out.println();
        fi.printExtra();
    }

    private FileInfo getInfoByExtension(File file) throws IOException {
        if (file.getName().endsWith(".txt")) {
            return new TXTInfo(file);
        } else if (file.getName().endsWith(".jpeg") ||
                file.getName().endsWith(".jpg") ||
                file.getName().endsWith(".png")) {
            return new ImageInfo(file);
        } else {
            return new FileInfo(file);
        }
    }

    private void printStatus() {
        File[] files = new File(this.directory).listFiles();
        if (files == null) {
            System.out.println("Error: " + this.directory + " is not a directory");
            return;
        }
        System.out.printf("Last commit: %s\n", Session.lastCommit.truncatedTo(ChronoUnit.SECONDS));

        long lastCommitMillis = Session.lastCommit.toInstant(Session.offset).toEpochMilli();
        List<String> newFilenames = Arrays.stream(files).map(File::getName).toList();

        for (File file : files) {
            if (!Session.committedFilenames.contains(file.getName())) {
                System.out.printf("CREATED  - %s\n", file.getName());
            } else if (file.lastModified() > lastCommitMillis) {
                System.out.printf("MODIFIED - %s\n", file.getName());
            }
        }
        for (String name : Session.committedFilenames) {
            if (!newFilenames.contains(name)) {
                System.out.printf("DELETED  - %s\n", name);
            }
        }
    }
}
