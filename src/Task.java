import java.time.LocalDateTime;

public class Task {
    private final String name;
    private final String desk;
    private final boolean type; // true -- private , false -- working
    private final LocalDateTime time;
    private final Repeat repeat;
    private final int id;
    private static int counter;

    public Task(String name, String desk, boolean type, LocalDateTime time, Repeat repeat) {
        if (name == null
                || name.isBlank()
                || desk == null
                || desk.isBlank()
                || time == null
                || repeat == null) {
            throw new IllegalArgumentException("Wrong parameters!");
        }
        this.name = name;
        this.desk = desk;
        this.type = type;
        this.time = time;
        this.repeat = repeat;
        id = ++counter;
    }

    public String getName() {
        return name;
    }

    public String getDesk() {
        return desk;
    }

    public boolean isPrivate() {
        return type;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Repeat getRepeat() {
        return repeat;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Задача:{Имя: '%s' Описание: '%s' Личная: '%s' Время: '%s' Повторяемость: '%s'}",
                name, desk, type, time, repeat);
//        return "Task{" +
//                "name='" + name + '\'' +
//                ", desk='" + desk + '\'' +
//                ", type=" + type +
//                ", time=" + time +
//                ", repeat=" + repeat +
//                ", id=" + id +
//                '}';
    }
}
