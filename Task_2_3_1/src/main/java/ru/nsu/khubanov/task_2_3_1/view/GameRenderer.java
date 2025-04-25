package ru.nsu.khubanov.task_2_3_1.view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.nsu.khubanov.task_2_3_1.model.*;
import ru.nsu.khubanov.task_2_3_1.model.food.*;
import ru.nsu.khubanov.task_2_3_1.model.gamefield.GameField;

import java.util.*;

public class GameRenderer {

    private final Pane pane;
    private final int cellSize;

    /*  ─ состояния прошлого кадра ─  */
    private Set<Cell> prevSnakes = new HashSet<>();
    private Set<Cell> prevFoods  = new HashSet<>();

    public GameRenderer(Pane pane, int cellSize) {
        this.pane = pane;
        this.cellSize = cellSize;
    }

    /** Главная точка входа: отрисовать кадр на основе GameField */
    public void render(GameField field) {
        Set<Cell> snakeCells = collectSnakeCells(field);
        Set<Cell> foodCells  = collectFoodCells(field);

        removeOutdated(snakeCells, foodCells);
        addNewCells(field, snakeCells, foodCells);
        redrawHeads(field);                    // чтобы еда не «просвечивала»

        /* сохранить новое состояние */
        prevSnakes = snakeCells;
        prevFoods  = foodCells;
    }

    /* ─────────────────────────────────────────────────────────── */

    private Set<Cell> collectSnakeCells(GameField f) {
        Set<Cell> set = new HashSet<>();
        for (Snake s : f.getSnakes()) set.addAll(s.getBody());
        return set;
    }

    private Set<Cell> collectFoodCells(GameField f) {
        Set<Cell> set = new HashSet<>();
        f.getFoods().forEach(food -> set.add(food.getPosition()));
        return set;
    }

    private void removeOutdated(Set<Cell> snakes, Set<Cell> foods) {
        Set<Cell> toRemove = new HashSet<>(prevSnakes);
        toRemove.addAll(prevFoods);
        toRemove.removeAll(snakes);
        toRemove.removeAll(foods);

        pane.getChildren().removeIf(node -> {
            if (node instanceof Rectangle r) {
                int x = (int) r.getX() / cellSize;
                int y = (int) r.getY() / cellSize;
                return toRemove.contains(new Cell(x, y));
            }
            return false;
        });
    }

    private void addNewCells(GameField field,
                             Set<Cell> snakes,
                             Set<Cell> foods) {

        Set<Cell> toAdd = new HashSet<>(snakes);
        toAdd.addAll(foods);
        toAdd.removeAll(prevSnakes);
        toAdd.removeAll(prevFoods);

        for (Cell c : toAdd) {
            Rectangle r = new Rectangle(c.getX() * cellSize,
                    c.getY() * cellSize,
                    cellSize, cellSize);

            /* 1) змея? */
            Optional<Snake> s = field.getSnakes().stream()
                    .filter(sn -> sn.getBody().contains(c)).findFirst();
            if (s.isPresent()) {
                r.setFill(s.get() == field.getPlayerSnake() ? Color.GREEN : Color.BLUE);
            } else {         /* 2) иначе это еда */
                Food food = field.getFoods().stream()
                        .filter(f -> f.getPosition().equals(c)).findFirst().orElse(null);
                if (food instanceof Apple)      r.setFill(Color.RED);
                else if (food instanceof Bomb)  r.setFill(Color.BLACK);
                else if (food instanceof SpeedBoost) r.setFill(Color.YELLOW);
            }
            pane.getChildren().add(r);
        }
    }

    private void redrawHeads(GameField field) {
        for (Snake s : field.getSnakes()) {
            Cell h = s.getHead();
            /* убираем всё, что уже лежит в этой клетке */
            pane.getChildren().removeIf(node -> {
                if (node instanceof Rectangle r) {
                    int x = (int) r.getX() / cellSize;
                    int y = (int) r.getY() / cellSize;
                    return x == h.getX() && y == h.getY();
                }
                return false;
            });
            Rectangle headRect = new Rectangle(h.getX() * cellSize,
                    h.getY() * cellSize,
                    cellSize, cellSize);
            headRect.setFill(s == field.getPlayerSnake() ? Color.GREEN : Color.BLUE);
            pane.getChildren().add(headRect);
        }
    }
}
