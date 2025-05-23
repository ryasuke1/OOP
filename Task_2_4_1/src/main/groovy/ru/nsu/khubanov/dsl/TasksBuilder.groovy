package ru.nsu.khubanov.dsl

class TasksBuilder {
    private final List<TaskConfig> tasks
    TasksBuilder(List<TaskConfig> tasks) { this.tasks = tasks }

    void task(String id, Closure cl) {
        def tb = new TaskConfigBuilder(id)
        cl.delegate = tb; cl.resolveStrategy = Closure.DELEGATE_FIRST; cl()
        tasks << tb.build()
    }
}
