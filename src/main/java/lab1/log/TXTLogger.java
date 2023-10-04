package lab1.log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class TXTLogger {
    // Use singleton pattern to have one global logger
    private static TXTLogger singleInstance = null;

    public static void init(String filepath, Level level) throws IOException {
        singleInstance = new TXTLogger(filepath, level);
    }

    public static TXTLogger get() throws java.lang.Error {
        if (singleInstance == null) {
            throw new java.lang.Error("logger not initialized");
        }
        return singleInstance;
    }

    // Class definition
    private final PrintWriter writer;
    private final Level level;

    // TODO custom string
    private TXTLogger(String filepath, Level level) throws IOException {

        FileWriter fileWriter = new FileWriter(filepath, true);
        this.writer = new PrintWriter(fileWriter);

        this.level = level;
    }

    private void print(Level level, String msg) {
        String formatted = String.format(
                "%s [%5s] %s",
                LocalDateTime.now(),
                level, msg);
        this.writer.println(formatted);
        this.writer.flush();
    }

    public void Debug(String msg) {
        if (this.level.includes(Level.DEBUG)) {
            this.print(Level.DEBUG, msg);
        }
    }

    public void Info(String msg) {
        if (this.level.includes(Level.INFO)) {
            this.print(Level.INFO, msg);
        }
    }

    public void Warn(String msg) {
        if (this.level.includes(Level.WARN)) {
            this.print(Level.WARN, msg);
        }
    }

    public void Error(String msg) {
        if (this.level.includes(Level.ERROR)) {
            this.print(Level.ERROR, msg);
        }
    }
}
