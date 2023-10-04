package lab1.log;

public enum Level {
    DEBUG(0, "DEBUG"),
    INFO(1, "INFO"),
    WARN(2, "WARN"),
    ERROR(3, "ERROR");

    private final int level;
    private final String name;

    Level(int level, String name) {
        this.level = level;
        this.name = name;
    }

    public boolean includes(Level other) {
        return other.level >= this.level;
    }

    public String toString() {
        return this.name;
    }
}
