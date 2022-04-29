package hwr.oop.solutions.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

class TicTacToeTest {

    private ArrayTicTacToe game;

    @BeforeEach
    void setUp() {
        game = new ArrayTicTacToe();
    }

    @Test
    void newGame_NoTicksYet_AllFieldsAreZero() {
        int[] squares = new int[9];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int targetIndex = y * 3 + x;
                squares[targetIndex] = game.getValueAt(x, y);
            }
        }
        assertThat(squares).containsOnly(0);
    }

    @Test
    void setCross_FirstPlayerTicksCentralSquare_CentralSquareTicked() {
        game.setCross(1, 1);
        int value = game.getValueAt(1, 1);
        assertThat(value).isEqualTo(1);
    }

    @Test
    void setCross_SameSquare_IsNotAllowed() {
        game.setCross(1, 1);
        game.setCircle(2, 2);
        try {
            game.setCross(1, 1);
            fail("no error if (1,1) is ticked twice!");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    void setCross_TwiceInARow_IsNotAllowed() {
        game.setCross(0, 0);
        try {
            game.setCross(0, 1);
            fail("no error, player A ticked twice in a row!");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    void setCircle_FirstPlayerToedCentralSquare_CentralSquareToed() {
        game.setCross(0, 0);
        game.setCircle(1, 1);
        int value = game.getValueAt(1, 1);
        assertThat(value).isEqualTo(2);
    }

    @Test
    void setCircle_SameSquare_IsNotAllowed() {
        game.setCross(0, 0);
        game.setCircle(1, 1);
        game.setCross(2, 2);
        try {
            game.setCircle(1, 1);
            fail("no error if (1,1) is toed twice!");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    void setCircle_TwiceInARow_IsNotAllowed() {
        game.setCross(2, 2);
        game.setCircle(0, 0);
        try {
            game.setCircle(0, 1);
            fail("no error, player B toed twice in a row!");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    void isGameOver_NewGame_GameIsNotOver() {
        boolean over = game.isGameOver();
        assertThat(over).isFalse();
    }

    @Test
    void isGameOver_FirstPlayerCompletesThree_GameIsOver() {
        // given
        game.setCross(0, 0);
        game.setCircle(0, 1);
        game.setCross(1, 0);
        game.setCircle(1, 1);
        game.setCross(2, 0);
        // when
        boolean over = game.isGameOver();
        // then
        assertThat(over).isTrue();
    }

    @Test
    void isGameOver_FirstPlayerDoesNotWinAfterThreeTicsNotInARow_GameIsNotOver() {
        // given: a NOT finished Game
        game.setCross(0, 0);
        game.setCircle(0, 1);
        game.setCross(1, 0);
        game.setCircle(1, 1);
        game.setCross(0, 2);

        // when
        boolean over = game.isGameOver();
        // then
        assertThat(over).isFalse();
    }

    @Test
    void setCross_finishedGame_PlayingAFinishedGameIsNotAllowed() {
        // given: a finished Game
        game.setCross(0, 0);
        game.setCircle(0, 1);
        game.setCross(1, 0);
        game.setCircle(1, 1);
        game.setCross(2, 0);

        // when
        try {
            game.setCircle(0, 2);
            fail("no error, played on after three in a row");
        } catch (RuntimeException e) {
            // then
            e.printStackTrace();
        }
    }

}
