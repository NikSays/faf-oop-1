package lab1.log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TXTLogger{
    // Use singleton pattern to have one global logger
    private static TXTLogger singleInstance = null;
    
    public static void init(String filepath, Level level) throws IOException{
        singleInstance = new TXTLogger(filepath, level);
    }

    public static TXTLogger getInstance() throws Exception {
        if (singleInstance == null) {
            throw new Exception("logger not initialized");
        }
        return singleInstance;
    }

    // Class definition
    private final PrintWriter writer;
    private final Level level;

    // TODO custom string
    private TXTLogger(String filepath, Level level) throws IOException{

        FileWriter fileWriter = new FileWriter(filepath);
        this.writer = new PrintWriter(fileWriter);

        this.level = level;
    }

    public void Debug(String... msg) {
        if (this.level.includes(Level.DEBUG)){
            this.writer.print(msg);
        }
    }

    public void Info(String... msg) {
        if (this.level.includes(Level.INFO)){
            this.writer.print(msg);
        }
    }

    public void Warn(String... msg) {
        if (this.level.includes(Level.WARN)){
            this.writer.print(msg);
        }
    }

    public void Error(String... msg) {
        if (this.level.includes(Level.ERROR)){
            this.writer.print(msg);
        }
    }
}
