package lab1.log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TXTLogger implements Logger {
    private PrintWriter writer;
    private Level level;

    // TODO custom string
    public TXTLogger(String filepath, Level level) throws IOException{
        FileWriter fileWriter = new FileWriter(filepath);
        this.writer = new PrintWriter(fileWriter);

        this.level = level;
    }

    @Override
    public void Debug(String... msg) {
        if (this.level.includes(Level.DEBUG)){
            this.writer.print(msg);
        }
    }

    @Override
    public void Info(String... msg) {
        if (this.level.includes(Level.INFO)){
            this.writer.print(msg);
        }
    }

    @Override
    public void Warn(String... msg) {
        if (this.level.includes(Level.WARN)){
            this.writer.print(msg);
        }
    }

    @Override
    public void Error(String... msg) {
        if (this.level.includes(Level.ERROR)){
            this.writer.print(msg);
        }
    }
}
