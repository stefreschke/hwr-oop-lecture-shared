package hwr.oop.exercises.gameoflife;

public class Cell {
    public Cell(int x, int y) {
        position = new Position(x,y);
    }
    private Position position;

    public Cell[] neighbours;

    private boolean isAlive;

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isDead() {
        return !isAlive;
    }

    public void markAlive() {
        isAlive = true;
    }

    public void markDead() {
        isAlive = false;
    }

    public Cell[] getNeighbours() {
        return new Cell[0];
    }

    public Position getPosition() {
        return position;
    }

    public boolean isAliveNextGeneration() {
        return false;
    }
}
