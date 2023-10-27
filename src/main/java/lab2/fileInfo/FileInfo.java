package lab2.fileInfo;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;

public class FileInfo {
    protected final File file;

    public FileInfo(File file) throws IOException {
        this.file = file;
    }

    public void printBasic() {
        String[] nameParts = this.file.getName().split("\\.");
        String name, ext;
        if (nameParts.length == 1) {
            name = nameParts[0];
            ext = "None";
        } else {
            name = String.join(".", Arrays.copyOfRange(nameParts, 0, nameParts.length - 1));
            ext = nameParts[nameParts.length - 1].toUpperCase();
        }

        System.out.printf("Filename:  %s\n", name);
        System.out.printf("Extension: %s\n", ext);
        System.out.printf("Modified:  %s\n", Instant.ofEpochMilli(this.file.lastModified()));
    }

    public void printExtra() {
        System.out.println("No extra data to print");
    }
}
