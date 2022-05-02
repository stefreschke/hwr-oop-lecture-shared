package hwr.oop.solutions.tictactoe;

class ArrayTicTacToe {
    private static final int EMPTY_VALUE = 0;
    private static final int PLAYER_A_VALUE = 1;
    private static final int PLAYER_B_VALUE = 2;

    private final int[][] fields;
    private final int[][][] combinations;
    private int lastMove;

    ArrayTicTacToe() {
        this.fields = new int[3][3];
        this.combinations = new int[8][3][2];
        this.lastMove = EMPTY_VALUE;
        initializeWinningCombinations();
    }

    int getValueAt(int x, int y) {
        return fields[x][y];
    }

    void setCross(int x, int y) {
        assertMoveValid(PLAYER_A_VALUE, x, y);
        performMove(PLAYER_A_VALUE, x, y);
    }

    void setCircle(int x, int y) {
        assertMoveValid(PLAYER_B_VALUE, x, y);
        performMove(PLAYER_B_VALUE, x, y);
    }

    boolean isGameOver() {
        for (int[][] combination : combinations) {
            int[] squareValues = getValuesFor(combination);
            boolean gameIsOver = isWinningCombination(squareValues);
            if (gameIsOver) {
                printWinner(squareValues);
                return true;
            }
        }
        return false;
    }

    private void printWinner(int[] values) {
        int winner = values[0];
        System.out.println("Player " + winner + " has won!");
    }

    private int[] getValuesFor(int[][] combination) {
        int[] values = new int[3];
        int i = 0;
        for (int[] coords : combination) {
            int x = coords[0];
            int y = coords[1];
            int value = fields[x][y];
            values[i] = value;
            i++;
        }
        return values;
    }

    private boolean isWinningCombination(int[] values) {
        return values[0] != EMPTY_VALUE && values[0] == values[1] && values[1] == values[2];
    }

    private void performMove(int player, int x, int y) {
        fields[x][y] = player;
        lastMove = player;
    }

    private void assertMoveValid(int player, int x, int y) {
        int square = fields[x][y];
        assertSquareIsStillEmpty(square);
        assertCorrectPlayerIsPlaying(player);
        assertGameNotOver();
    }

    private void assertGameNotOver() {
        if (isGameOver()) {
            throw new RuntimeException("Game is already over!");
        }
    }

    private void assertCorrectPlayerIsPlaying(int player) {
        if (lastMove == player) {
            throw new RuntimeException("Player " + player + " has already played!1Elf!");
        }
    }

    private void assertSquareIsStillEmpty(int square) {
        if (square != EMPTY_VALUE) {
            throw new RuntimeException("Square was already taken by player " + square);
        }
    }

    private void initializeWinningCombinations() {
        int index = 0;
        for (int i = 0; i < 3; i++) {
            int[][] x = {{i, 0}, {i, 1}, {i, 2}};
            int[][] y = {{0, i}, {1, i}, {2, i}};
            combinations[index] = x;
            index++;
            combinations[index] = y;
            index++;
        }
        int[][] d1 = {{0, 0}, {1, 1}, {2, 2}};
        int[][] d2 = {{0, 2}, {1, 1}, {2, 0}};
        combinations[index] = d1;
        index++;
        combinations[index] = d2;
    }


}
