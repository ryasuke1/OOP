package ru.nsu.khubanov.dsl

class GroupConfigBuilder {
    String name
    List<StudentConfig> students = []

    GroupConfigBuilder(String name) { this.name = name }

    void student(String nick, Closure cl) {
        def sb = new StudentConfigBuilder(nick)
        cl.delegate = sb; cl.resolveStrategy = Closure.DELEGATE_FIRST; cl()
        students << sb.build()
    }

    GroupConfig build() {
        return new GroupConfig(name, students)
    }
}
