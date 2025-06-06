package ru.nsu.khubanov.dsl;

public class AssignmentConfig {
    private final String taskId;
    private final String studentNick;

    public AssignmentConfig(String taskId, String studentNick) {
        this.taskId = taskId;
        this.studentNick = studentNick;
    }

    // ======= Геттеры =======
    public String getTaskId() {
        return taskId;
    }

    public String getStudentNick() {
        return studentNick;
    }

    @Override
    public String toString() {
        return taskId + " -> " + studentNick;
    }
}
