package lab1.log;

public interface Logger {
    public void Debug(String... msg);
    public void Info(String... msg);
    public void Warn(String... msg);
    public void Error(String... msg);
}
