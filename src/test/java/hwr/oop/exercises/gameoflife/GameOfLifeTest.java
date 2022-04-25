package hwr.oop.exercises.gameoflife;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameOfLifeTest {

    @Test
    void positionIsNextTo_positionWithEightNeighbourPositions_AllNeighbourPositionsAreNextToPosition() {

        // given
        Position cellPosition = new Position(5, 5);
        Position[] neighbours = new Position[8];
        int index = 0;
        for (int x = 4; x <= 6; x++) {
            for (int y = 4; y <= 6; y++) {
                boolean isCellItself = x == 5 && y == 5;
                if (!isCellItself) {
                    Position p = new Position(x, y);
                    neighbours[index] = p;
                    index++;
                }
            }
        }

        for (Position neighbour : neighbours) {
            // when
            boolean neighbourIsNextToPosition = cellPosition.isNextTo(neighbour);
            // then
            assertThat(neighbourIsNextToPosition).isTrue();
        }
    }

    @Test
    void getNeighbours_cellAtCenterPosition_cellHasEightNeighboursWhichAreNextToCenterPosition() {

        // given
        Field gameField = new Field(10);
        Position centerPosition = new Position(5, 5);
        Cell cell = gameField.getCellAt(centerPosition);
        // when
        Cell[] neighbours = cell.getNeighbours();
        // then
        assertThat(neighbours).hasSize(8);
        for (Cell neighbour : neighbours) {
            assertThat(neighbour.getPosition().isNextTo(centerPosition)).isTrue();
        }
    }

    @Test
    void gameProceed_singleAliveCell_cellIsDead() {
        // given
        Field field = new Field(10);
        Game game = new Game(field);
        Position cellPosition = new Position(5, 5);
        Cell cell = field.getCellAt(cellPosition);
        cell.markAlive();
        // when
        game.proceed();
        // then
        assertThat(cell.isAlive()).isFalse();
    }

    @Test
    void gameProceed_threeAliveDiagonalCellsAreAlive_cellsBlinkDuringFourGameSteps() {
        // given
        Field field = new Field(10);
        Game game = new Game(field);
        Cell cell_4_5 = field.getCellAt(new Position(4, 5));
        Cell cell_5_5 = field.getCellAt(new Position(5, 5));
        Cell cell_6_5 = field.getCellAt(new Position(6, 5));
        Cell cell_5_4 = field.getCellAt(new Position(5, 4));
        Cell cell_5_6 = field.getCellAt(new Position(5, 6));

        // interactions
        cell_4_5.markAlive();
        cell_5_5.markAlive();
        cell_6_5.markAlive();

        // first step
        game.proceed();
        assertThat(new Cell[]{cell_5_4, cell_5_5, cell_5_6}).allMatch(Cell::isAlive);
        assertThat(new Cell[]{cell_4_5, cell_6_5}).allMatch(Cell::isDead);

        // second step
        game.proceed();
        assertThat(new Cell[]{cell_4_5, cell_5_5, cell_6_5}).allMatch(Cell::isAlive);
        assertThat(new Cell[]{cell_5_4, cell_5_6}).allMatch(Cell::isDead);

        // third step
        game.proceed();
        assertThat(new Cell[]{cell_5_4, cell_5_5, cell_5_6}).allMatch(Cell::isAlive);
        assertThat(new Cell[]{cell_4_5, cell_6_5}).allMatch(Cell::isDead);

        // fourth step
        game.proceed();
        assertThat(new Cell[]{cell_4_5, cell_5_5, cell_6_5}).allMatch(Cell::isAlive);
        assertThat(new Cell[]{cell_5_4, cell_5_6}).allMatch(Cell::isDead);
    }

}
