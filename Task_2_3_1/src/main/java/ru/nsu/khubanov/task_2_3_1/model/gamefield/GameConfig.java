package ru.nsu.khubanov.task_2_3_1.model.gamefield;

public class GameConfig {
    public final int width;
    public final int height;
    public final int foodCount;
    public final int winLength;

    public GameConfig(int width, int height, int foodCount, int winLength) {
        this.width = width;
        this.height = height;
        this.foodCount = foodCount;
        this.winLength = winLength;
    }
}
