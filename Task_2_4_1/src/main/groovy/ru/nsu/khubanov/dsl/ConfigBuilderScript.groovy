package ru.nsu.khubanov.dsl

def call(Closure script) {
    def builder = new ConfigBuilderScript()
    script.delegate = builder
    script.resolveStrategy = Closure.DELEGATE_FIRST
    script()
    return builder.build()
}


class ConfigBuilder {
    List<TaskConfig> tasks = []
    List<GroupConfig> groups = []
    List<AssignmentConfig> assignments = []
    List<CheckpointConfig> checkpoints = []
    Map<String,Object> settings = [:]

    /** секции DSL */
    void tasks(Closure c)       { c.delegate = new TasksBuilder(tasks);       c() }
    void groups(Closure c)      { c.delegate = new GroupsBuilder(groups);      c() }
    void assignments(Closure c) { c.delegate = new AssignmentsBuilder(assignments); c() }
    void checkpoints(Closure c) { c.delegate = new CheckpointsBuilder(checkpoints); c() }
    void settings(Closure c)    { c.delegate = new SettingsBuilder(settings); c() }

    /** импорт другого groovy-скрипта */
    void include(String path) {
        def cfg = new GroovyShell().evaluate(new File(path))
        if (!(cfg instanceof Config)) throw new RuntimeException("include $path must return Config")
        tasks       += cfg.tasks
        groups      += cfg.groups
        assignments += cfg.assignments
        checkpoints += cfg.checkpoints
        settings    << cfg.settings
    }
    Config build() { new Config(tasks, groups, assignments, checkpoints, settings) }
}
