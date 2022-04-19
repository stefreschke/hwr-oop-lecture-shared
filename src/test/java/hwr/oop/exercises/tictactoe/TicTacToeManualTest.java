package hwr.oop.exercises.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class TicTacToeManualTest {

    TicTacToe game;
    PrintStream out;
    Scanner in;
    Map<Integer, Character> visualCues;
    public int currentTurn = 0;
    public int mode;

    @BeforeEach
    void setUp() {
        out = System.out;
        in = new Scanner(System.in);
        out.println("Pick mode (3/4):");
        mode = in.nextInt();
        game = new TicTacToe(mode);
        initializeVisualCues();
    }

    @Test
    //@Disabled("manual test")
    void playUntilGameOver() {
        while (!game.isGameOver()) {
            playMoveNumber(currentTurn);
            printCurrentStateOfGame();
            currentTurn++;
        }
        out.println("Game over!");
        out.println("--------------");
        game.clearBoard();
        playUntilGameOver();
    }

    void initializeVisualCues() {
        visualCues = new HashMap<>();
        visualCues.put(0, '-');
        visualCues.put(1, 'X');
        visualCues.put(2, 'O');
    }

    void playMoveNumber(int turnNumber) {
        boolean firstPlayer = turnNumber % 2 == 0;
        String help = firstPlayer ? "A" : "B";
        out.println("Now Player " + help + "s turn!");
        out.print("X: ");
        int x = in.nextInt();
        out.print("Y: ");
        int y = in.nextInt();
        out.println();
        performMoveFor(firstPlayer, x, y);
    }

    void performMoveFor(boolean firstPlayer, int x, int y) {
        if (firstPlayer) {
            game.setCross(x, y);
        } else {
            game.setCircle(x, y);
        }
    }

    void printCurrentStateOfGame() {
        for (int y = 0; y < mode; y++) {
            for (int x = 0; x < mode; x++) {
                int field = game.getValueAt(x, y);
                char stuff = visualCues.get(field);
                out.print(stuff);
                out.print(' ');
            }
            out.print('\n');
        }
        out.println();
    }

}
