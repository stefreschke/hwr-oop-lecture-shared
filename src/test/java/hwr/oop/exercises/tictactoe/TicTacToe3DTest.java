package hwr.oop.exercises.tictactoe;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TicTacToe3DTest {

    @Test
    void winGame(){
        TicTacToe3D game = new TicTacToe3D();
        game.setCross(0, 0, 0);
        game.setCircle(0, 1, 0);
        game.setCross(1, 0, 0);
        game.setCircle(1, 1, 0);
        game.setCross(2, 0, 0);

        boolean over = game.isGameOver();
        assertThat(over).isTrue();
    }

    @Test
    void notOver(){
        TicTacToe3D game = new TicTacToe3D();
        game.setCross(0, 1,0);
        game.setCircle(0, 0,0);
        game.setCross(1, 1,0);
        game.setCircle(2, 1,0);
        game.setCross(1, 0,0);
        game.setCircle(0, 2,0);
        game.setCross(2, 0,0);
        game.setCircle(1, 2,0);
        game.setCross(2,2,0);

        boolean over = game.isGameOver();
        assertThat(over).isFalse();
    }
}

