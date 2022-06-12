package hwr.oop.solutions.gameoflife;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Field {
    private final Map<Position, Cell> map;

    Field(int size) {
        this.map = new HashMap<>();
        initialCellSetup(size);
        setUpCellNeighbours();
    }

    Collection<Cell> allCells() {
        return map.values();
    }

    Cell getCellAt(Position position) {
        return map.get(position);
    }

    private void initialCellSetup(int size) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Position position = new Position(x, y);
                Cell cell = new Cell(position);
                map.put(position, cell);
            }
        }
    }

    private void setUpCellNeighbours() {
        Map<Position, Collection<Position>> neighbours = buildNeighbourPositions();
        for (Map.Entry<Position, Collection<Position>> e : neighbours.entrySet()) {
            Position key = e.getKey();
            Cell home = map.get(key);
            Collection<Position> value = e.getValue();
            Collection<Cell> nn = new ArrayList<>();
            for (Position position : value) {
                Cell n = map.get(position);
                nn.add(n);
            }
            home.setNeighbours(nn);
        }
    }

    private Map<Position, Collection<Position>> buildNeighbourPositions() {
        Map<Position, Collection<Position>> neighbours = new HashMap<>();
        Set<Position> positions = map.keySet();
        for (Position position : positions) {
            Collection<Position> nextOnes = new ArrayList<>();
            neighbours.put(position, nextOnes);
            for (Position other : positions) {
                if (position.isNextTo(other)) {
                    nextOnes.add(other);
                }
            }
        }
        return neighbours;
    }
}
