package ru.nsu.khubanov.dsl

class AssignmentsBuilder {
    private final List<AssignmentConfig> assignments
    AssignmentsBuilder(List<AssignmentConfig> assignments) { this.assignments = assignments }

    void assign(String taskId, String studentNick) {
        assignments << new AssignmentConfig(taskId, studentNick)
    }
}
