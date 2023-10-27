package lab2.watcher;

import java.io.File;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

public class Watcher extends Thread {
    private final String directory;

    public Watcher(String directory) {
        this.directory = directory;
    }

    public void run() {
        File file = new File(this.directory);

        if (!file.isDirectory()) {
            System.out.println("Error: " + this.directory + " is not a directory");
            return;
        }

        FileSystem fs = file.toPath().getFileSystem();
        try (WatchService service = fs.newWatchService()) {
            file.toPath().register(service, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
            String lastCreated = "";
            WatchKey key = null;
            do {
                key = service.take();

                WatchEvent.Kind<?> kind = null;
                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                    kind = watchEvent.kind();
                    String triggeredBy = ((WatchEvent<Path>) watchEvent).context().toString();
                    if (triggeredBy.startsWith(".") || triggeredBy.endsWith("~")) {
                        continue;
                    }
                    if (ENTRY_CREATE == kind) {
                        lastCreated = triggeredBy;
                        System.out.println("CREATED  - " + triggeredBy);
                    } else if (ENTRY_MODIFY == kind) {
                        if (triggeredBy.equals(lastCreated)) {
                            lastCreated = "";
                        } else {
                            System.out.println("MODIFIED - " + triggeredBy);
                        }
                    } else if (ENTRY_DELETE == kind) {
                        System.out.println("DELETED  - " + triggeredBy);
                    }
                }
            } while (key.reset());

        } catch (Exception e) {
            System.out.println("Error watching directory");
            e.printStackTrace();
        }
    }
}
