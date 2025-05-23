package ru.nsu.khubanov.dsl

class TaskConfigBuilder {
    String id, name
    int maxScore
    String softDeadline, hardDeadline

    TaskConfigBuilder(String id) { this.id = id }

    void name(String n)             { this.name = n }
    void maxScore(int s)            { this.maxScore = s }
    void softDeadline(String date)  { this.softDeadline = date }
    void hardDeadline(String date)  { this.hardDeadline = date }

    TaskConfig build() {
        return new TaskConfig(id, name, maxScore, softDeadline, hardDeadline)
    }
}
