package hwr.oop.solutions.gameoflife;

import java.util.ArrayList;
import java.util.Collection;

class Cell {
    private final Position position;
    private final Collection<Cell> neighbours;
    private boolean alive;

    Cell(Position position) {
        this.position = position;
        this.neighbours = new ArrayList<>();
        this.alive = false;
    }

    public void markAlive() {
        this.alive = true;
    }

    public boolean isAliveNextGeneration() {
        int aliveNeighbours = 0;
        for (Cell neighbour : neighbours) {
            if (neighbour.isAlive()) {
                aliveNeighbours++;
            }
        }
        if (alive) {
            return aliveNeighbours >= 2 && aliveNeighbours <= 3;
        } else {
            return aliveNeighbours == 3;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isDead() {
        return !alive;
    }

    public void markDead() {
        this.alive = false;
    }

    Position getPosition() {
        return position;
    }

    Cell[] getNeighbours() {
        return neighbours.toArray(new Cell[0]);
    }

    void setNeighbours(Collection<Cell> cells) {
        this.neighbours.addAll(cells);
    }
}
