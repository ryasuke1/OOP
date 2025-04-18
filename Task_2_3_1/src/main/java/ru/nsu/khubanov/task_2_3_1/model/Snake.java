package ru.nsu.khubanov.task_2_3_1.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Snake {
    protected final LinkedList<Cell> body = new LinkedList<>();
    protected final String name;
    protected Direction direction = Direction.RIGHT;

    public Snake(int startX, int startY,String name) {
        body.add(new Cell(startX, startY));
        this.name = name;
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

    private boolean botWin = false;

    public void setBotWin(boolean value) {
        this.botWin = value;
    }

    public boolean isBotWin() {
        return botWin;
    }

    private final Queue<Direction> directionQueue = new LinkedList<>();

    public void queueDirection(Direction newDir) {
        if (directionQueue.isEmpty()) {
            directionQueue.add(newDir);
        } else {
            Direction last = ((LinkedList<Direction>) directionQueue).getLast();
            if (!newDir.isOpposite(last)) {
                directionQueue.add(newDir);
            }
        }
    }

    public void applyNextDirection() {
        if (!directionQueue.isEmpty()) {
            Direction next = directionQueue.poll();
            if (!next.isOpposite(this.direction)) {
                this.direction = next;
            }
        }
    }
    public String getName() {
        return name;
    }
    
}
