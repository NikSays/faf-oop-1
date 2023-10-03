package lab1.log;

public enum Level {
    DEBUG(0),
    INFO(1),
    WARN(2),
    ERROR(3);

    private int level;

    private Level(int level) {
        this.level = level;
    }

    public boolean includes(Level other) {
        return other.level < this.level;
    }
}
