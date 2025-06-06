package ru.nsu.khubanov.dsl

class GroupsBuilder {
    private final List<GroupConfig> groups
    GroupsBuilder(List<GroupConfig> groups) { this.groups = groups }

    void group(String name, Closure cl) {
        def gb = new GroupConfigBuilder(name)
        cl.delegate = gb; cl.resolveStrategy = Closure.DELEGATE_FIRST; cl()
        groups << gb.build()
    }
}
