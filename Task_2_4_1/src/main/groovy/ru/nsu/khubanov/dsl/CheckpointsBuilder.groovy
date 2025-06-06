package ru.nsu.khubanov.dsl

class CheckpointsBuilder {
    private final List<CheckpointConfig> checkpoints
    CheckpointsBuilder(List<CheckpointConfig> checkpoints) { this.checkpoints = checkpoints }

    void checkpoint(String name, Closure cl) {
        def cpb = new CheckpointConfigBuilder(name)
        cl.delegate = cpb; cl.resolveStrategy = Closure.DELEGATE_FIRST; cl()
        checkpoints << cpb.build()
    }
}
