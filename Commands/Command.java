package Commands;

public abstract class Command {
    private final String name;
    private final String desc;

    public Command(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public abstract void run();

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
