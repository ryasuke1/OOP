package ru.nsu.khubanov.service;

import ru.nsu.khubanov.dsl.TaskConfig;
import ru.nsu.khubanov.service.TestService.TestResult;

public class ScoreService {
    public int calculate(TaskConfig task, boolean compiled,
                         boolean styleOk, TestResult tr) {

        double max = task.getMaxScore();
        double score = 0;

        if (compiled) score += 0.50 * max;
        if (styleOk)  score += 0.25 * max;

        int total = tr.passed + tr.failed;
        score += (total == 0)
                ? 0.25 * max
                : (tr.passed / (double) total) * 0.25 * max;

        return (int) Math.round(score);
    }
}
