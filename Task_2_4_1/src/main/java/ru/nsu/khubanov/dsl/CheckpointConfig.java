package ru.nsu.khubanov.dsl;

public class CheckpointConfig {
    private final String name;
    private final String date;

    public CheckpointConfig(String name, String date) {
        this.name = name;
        this.date = date;
    }

    // ======= Геттеры =======
    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return name + "@" + date;
    }
}
