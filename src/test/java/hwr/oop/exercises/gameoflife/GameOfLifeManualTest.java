package hwr.oop.exercises.gameoflife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class GameOfLifeManualTest {
    private static int FIELD_SIZE = 10;

    private Game game;
    private Field field;
    private Position[] positions;
    private Cell[] cells;

    @BeforeEach
    void setUp() {
        field = new Field(FIELD_SIZE);
        game = new Game(field);
        initializePositions();
        initializeCells();
    }

    @Test
    @Disabled("manual test")
    void manualTest() throws InterruptedException {
        while(true) {
            String string = buildConsoleOutput();
            game.proceed();
            System.out.println(string);
            Thread.sleep(2000);
        }
    }

    private String buildConsoleOutput() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < cells.length; i++) {
            boolean newRow = i % FIELD_SIZE == 0;
            if (newRow) {
                builder.append('\n');
            }
            Cell cell = cells[i];
            boolean isAlive = cell.isAlive();
            builder.append(isAlive ? 'X' : '-');
        }
        builder.append('\n');
        return builder.toString();
    }

    private void initializeCells() {
        cells = new Cell[FIELD_SIZE * FIELD_SIZE];
        int index = 0;
        for (Position position : positions) {
            Cell cell = field.getCellAt(position);
            cells[index] = cell;
            index++;
        }
    }

    private void initializePositions() {
        positions = new Position[FIELD_SIZE * FIELD_SIZE];
        int index = 0;
        for (int x = 0; x < FIELD_SIZE; x++) {
            for (int y = 0; y < FIELD_SIZE; y++) {
                Position p = new Position(x, y);
                positions[index] = p;
                index++;
            }
        }
    }
}
