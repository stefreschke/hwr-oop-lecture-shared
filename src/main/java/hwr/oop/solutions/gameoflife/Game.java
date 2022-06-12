package hwr.oop.solutions.gameoflife;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class Game {
    private final Field field;

    public Game(Field field) {

        this.field = field;
    }

    void proceed() {
        Collection<Cell> cells = field.allCells();
        Map<Cell, Boolean> futureState = new HashMap<>();
        for (Cell cell : cells) {
            boolean willBeAlive = cell.isAliveNextGeneration();
            futureState.put(cell, willBeAlive);
        }
        for (Map.Entry<Cell, Boolean> entry : futureState.entrySet()) {
            Cell cell = entry.getKey();
            Boolean cellIsAlive = entry.getValue();
            if (Boolean.TRUE.equals(cellIsAlive)) {
                cell.markAlive();
            } else {
                cell.markDead();
            }
        }
    }
}
