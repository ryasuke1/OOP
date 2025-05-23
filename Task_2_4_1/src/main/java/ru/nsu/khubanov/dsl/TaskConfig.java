package ru.nsu.khubanov.dsl;

public class TaskConfig {
    private final String id;
    private final String name;
    private final int maxScore;
    private final String softDeadline;
    private final String hardDeadline;

    public TaskConfig(String id, String name, int maxScore, String softDeadline, String hardDeadline) {
        this.id = id;
        this.name = name;
        this.maxScore = maxScore;
        this.softDeadline = softDeadline;
        this.hardDeadline = hardDeadline;
    }

    // Геттеры
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public String getSoftDeadline() {
        return softDeadline;
    }

    public String getHardDeadline() {
        return hardDeadline;
    }

    @Override
    public String toString() {
        return id + "(" + name + ", max=" + maxScore + ")";
    }
}
