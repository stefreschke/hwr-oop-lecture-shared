package hwr.oop.exercises.gameoflife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CellLifeCycleTest {

    private Field gameField;
    private Position centerPosition;
    private Cell centerCell;

    @BeforeEach
    void setUp() {
        gameField = new Field(10);
        centerPosition = new Position(5, 5);
        centerCell = gameField.getCellAt(centerPosition);
    }

    private void markCellsAsAlive(Position[] positions) {
        for (Position position : positions) {
            Cell cell = gameField.getCellAt(position);
            cell.markAlive();
        }
    }

    @Nested
    class DeadCellTest {
        @Test
        void isAliveNextGeneration_deadCellGetsThreeLivingNeighbours_cellBecomesAlive() {
            // given
            markCellsAsAlive(new Position[]{
                    new Position(4, 4),
                    new Position(4, 5),
                    new Position(4, 6)
            });
            // when
            boolean willBeAlive = centerCell.isAliveNextGeneration();
            // then
            assertThat(willBeAlive).isTrue();
        }

        @Test
        void isAliveNextGeneration_deadCellGetsTwoLivingNeighbours_cellRemainsDead() {
            // given
            markCellsAsAlive(new Position[]{
                    new Position(4, 4),
                    new Position(4, 5)
            });
            // when
            boolean willBeAlive = centerCell.isAliveNextGeneration();
            // then
            assertThat(willBeAlive).isFalse();
        }

        @Test
        void isAliveNextGeneration_deadCellGetsFourLivingNeighbours_cellRemainsDead() {
            // given
            markCellsAsAlive(new Position[]{
                    new Position(4, 4),
                    new Position(4, 5),
                    new Position(4, 6),
                    new Position(5, 4)
            });
            // when
            boolean willBeAlive = centerCell.isAliveNextGeneration();
            // then
            assertThat(willBeAlive).isFalse();
        }
    }

    @Nested
    class AliveCellTest {
        @BeforeEach
        void setUp() {
            centerCell.markAlive();
        }

        @Test
        void isAliveNextGeneration_aliveCellGetsThreeLivingNeighbours_cellRemainsAlive() {
            // given
            markCellsAsAlive(new Position[]{
                    new Position(4, 4),
                    new Position(4, 5),
                    new Position(4, 6)
            });
            // when
            boolean willBeAlive = centerCell.isAliveNextGeneration();
            // then
            assertThat(willBeAlive).isTrue();
        }

        @Test
        void isAliveNextGeneration_aliveCellGetsTwoLivingNeighbours_cellRemainsAlive() {
            // given
            markCellsAsAlive(new Position[]{
                    new Position(4, 4),
                    new Position(4, 5)
            });
            // when
            boolean willBeAlive = centerCell.isAliveNextGeneration();
            // then
            assertThat(willBeAlive).isTrue();
        }

        @Test
        void isAliveNextGeneration_aliveCellGetsOneLivingNeighbour_cellBecomesDead() {
            // given
            markCellsAsAlive(new Position[]{
                    new Position(4, 5)
            });
            // when
            boolean willBeAlive = centerCell.isAliveNextGeneration();
            // then
            assertThat(willBeAlive).isFalse();
        }

        @Test
        void isAliveNextGeneration_aliveCellGetsFourLivingNeighbours_cellBecomesDead() {
            // given
            markCellsAsAlive(new Position[]{
                    new Position(4, 4),
                    new Position(4, 5),
                    new Position(4, 6),
                    new Position(5, 4)
            });
            // when
            boolean willBeAlive = centerCell.isAliveNextGeneration();
            // then
            assertThat(willBeAlive).isFalse();
        }
    }
}
