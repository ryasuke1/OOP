package ru.nsu.khubanov.dsl

class CheckpointConfigBuilder {
    String name, date

    CheckpointConfigBuilder(String name) { this.name = name }

    void date(String d) { this.date = d }

    CheckpointConfig build() {
        return new CheckpointConfig(name, date)
    }
}
