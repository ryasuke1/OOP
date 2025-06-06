package ru.nsu.khubanov.dsl;

import java.util.List;

public class GroupConfig {
    private final String name;
    private final List<StudentConfig> students;

    public GroupConfig(String name, List<StudentConfig> students) {
        this.name = name;
        this.students = students;
    }

    // ======= Геттеры =======
    public String getName() {
        return name;
    }

    public List<StudentConfig> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return name + students;
    }
}
