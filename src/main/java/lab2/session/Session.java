package lab2.session;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;

public class Session implements Serializable {
    public static volatile LocalDateTime lastCommit;
    public static volatile ArrayList<String> committedFilenames;
    private static final String filepath = ".lab2session";

    // ewwww
    public static final ZoneOffset offset = ZoneOffset.systemDefault().getRules().getOffset(Instant.now());

    public static void save() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));

        long lastCommitEpoch = lastCommit.toEpochSecond(offset);
        writer.write(String.format("%d\n", lastCommitEpoch));

        // slash can't be in filename, so can be used as delimiter
        writer.write(String.join("/", committedFilenames));

        writer.close();
    }

    public static void load() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));

        String epochString = reader.readLine();
        long epoch = Long.parseLong(epochString);
        lastCommit = LocalDateTime.ofEpochSecond(epoch, 0, offset);

        String existingFilesStr = reader.readLine();
        committedFilenames = new ArrayList<>(Arrays.asList(existingFilesStr.split("/")));
    }
}
