package ru.nsu.khubanov.dsl;

import java.util.List;
import java.util.Map;

public class Config {
    private final List<TaskConfig> tasks;
    private final List<GroupConfig> groups;
    private final List<AssignmentConfig> assignments;
    private final List<CheckpointConfig> checkpoints;
    private final Map<String,Object> settings;

    public Config(List<TaskConfig> tasks,
                  List<GroupConfig> groups,
                  List<AssignmentConfig> assignments,
                  List<CheckpointConfig> checkpoints,
                  Map<String,Object> settings) {
        this.tasks = tasks;
        this.groups = groups;
        this.assignments = assignments;
        this.checkpoints = checkpoints;
        this.settings = settings;
    }

    // ======= Геттеры =======
    public List<TaskConfig> getTasks() {
        return tasks;
    }

    public List<GroupConfig> getGroups() {
        return groups;
    }

    public List<AssignmentConfig> getAssignments() {
        return assignments;
    }

    public List<CheckpointConfig> getCheckpoints() {
        return checkpoints;
    }

    public Map<String,Object> getSettings() {
        return settings;
    }

    @Override
    public String toString() {
        return "Config{" +
                "tasks=" + tasks +
                ", groups=" + groups +
                ", assignments=" + assignments +
                ", checkpoints=" + checkpoints +
                ", settings=" + settings +
                '}';
    }
}
