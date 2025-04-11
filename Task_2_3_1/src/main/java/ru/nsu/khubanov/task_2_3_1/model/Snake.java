package ru.nsu.khubanov.task_2_3_1.model;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    protected final LinkedList<Cell> body = new LinkedList<>();
    protected Direction direction = Direction.RIGHT;

    public Snake(int startX, int startY) {
        body.add(new Cell(startX, startY));
    }

    public void setDirection(Direction newDir) {
        if ((this.direction == Direction.UP && newDir != Direction.DOWN) ||
                (this.direction == Direction.DOWN && newDir != Direction.UP) ||
                (this.direction == Direction.LEFT && newDir != Direction.RIGHT) ||
                (this.direction == Direction.RIGHT && newDir != Direction.LEFT)) {
            this.direction = newDir;
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public List<Cell> getBody() {
        return body;
    }

    public Cell getHead() {
        return body.getLast();
    }

    public Cell nextHead(int width, int height) {
        Cell head = getHead();
        int x = (head.getX() + direction.getDx() + width) % width;
        int y = (head.getY() + direction.getDy() + height) % height;
        return new Cell(x, y);
    }

    private int shrink = 0;

    public void setShrink(int amount) {
        shrink = amount;
    }

    public void move(boolean grow, int width, int height) {
        body.addLast(nextHead( width ,height));

        if (shrink > 0) {
            body.removeFirst(); // первый хвост
            if (body.size() > 1) {
                body.removeFirst(); // второй хвост, если есть
            }
            shrink--;
        } else if (!grow) {
            body.removeFirst();
        }
    }


    public boolean checkCollision(Cell c) {
        return body.contains(c);
    }
}
