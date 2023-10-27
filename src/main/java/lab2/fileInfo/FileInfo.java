package lab2.fileInfo;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

public class FileInfo {
    protected final File file;

    public FileInfo(File file) {
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

        ZoneOffset offset = ZoneOffset.systemDefault().getRules().getOffset(Instant.now());
        System.out.printf("Filename:  %s\n", name);
        System.out.printf("Extension: %s\n", ext);
        System.out.printf("Modified:  %s\n", LocalDateTime.ofEpochSecond(this.file.lastModified() / 1000, 0, offset));
    }

    public void printExtra() {
        System.out.println("No extra data to print");
    }
}
