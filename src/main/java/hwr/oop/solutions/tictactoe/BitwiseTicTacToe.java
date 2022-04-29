package hwr.oop.solutions.tictactoe;

class BitwiseTicTacToe {
    private static final int EMPTY_VALUE = 0;
    private static final int PLAYER_A_VALUE = 1;
    private static final int PLAYER_B_VALUE = 2;

    private final short rowMask = 73;
    private final short columnMask = 7;
    private final short firstDiagonalMask = 273;
    private final short secondDiagonalMask = 84;

    private short fieldA;
    private short fieldB;
    private int lastPlayer;

    BitwiseTicTacToe() {
        lastPlayer = EMPTY_VALUE;
        fieldA = 0;
        fieldB = 0;
    }

    int getValueAt(int x, int y) {
        int mask = buildMask(x, y);
        boolean usedByA = matches(fieldA, mask);
        if (usedByA) {
            return PLAYER_A_VALUE;
        }
        boolean usedByB = matches(fieldB, mask);
        if (usedByB) {
            return PLAYER_B_VALUE;
        }
        return EMPTY_VALUE;
    }

    void setCross(int x, int y) {
        fieldA = makeMoveAt(x, y, PLAYER_A_VALUE, fieldA);
        lastPlayer = PLAYER_A_VALUE;
    }

    void setCircle(int x, int y) {
        fieldB = makeMoveAt(x, y, PLAYER_B_VALUE, fieldB);
        lastPlayer = PLAYER_B_VALUE;
    }

    boolean isGameOver() {
        return columnsWon() || rowsWon() || diagonalsWon();
    }

    boolean matches(int field, int mask) {
        return (field & mask) == mask;
    }

    private short makeMoveAt(int x, int y, int playerValue, short field) {
        assertMoveValid(x, y, playerValue);
        int mask = buildMask(x, y);
        field |= mask;
        // need to return field, because call-by-value
        return field;
    }

    private int buildMask(int x, int y) {
        int offset = offsetFor(x, y);
        return 1 << offset;
    }

    private void assertMoveValid(int x, int y, int playerValue) {
        assertGameNotOver();
        assertSquareStillEmpty(x, y);
        assertLastPlayerCorrect(playerValue);
    }

    private void assertGameNotOver() {
        if (isGameOver()) {
            throw new RuntimeException("Game already over!");
        }
    }

    private void assertLastPlayerCorrect(int playerValue) {
        if (lastPlayer == playerValue) {
            throw new RuntimeException("Last player not " + playerValue + " but " + lastPlayer);
        }
    }

    private void assertSquareStillEmpty(int x, int y) {
        if (getValueAt(x, y) != EMPTY_VALUE) {
            throw new RuntimeException("foo");
        }
    }

    private boolean diagonalsWon() {
        return matches(fieldA, firstDiagonalMask) || matches(fieldB, firstDiagonalMask) ||
                matches(fieldA, secondDiagonalMask) || matches(fieldB, secondDiagonalMask);
    }

    private boolean rowsWon() {
        for (int i = 0; i < 3; i++) {
            int bitMask = columnMask << i * 3;
            if (matches(fieldA, bitMask) || matches(fieldB, bitMask)) {
                return true;
            }
        }
        return false;
    }

    private boolean columnsWon() {
        for (int i = 0; i < 3; i++) {
            int bitMask = rowMask << i;
            if (matches(fieldA, bitMask) || matches(fieldB, bitMask)) {
                return true;
            }
        }
        return false;
    }

    private int offsetFor(int x, int y) {
        return y * 3 + x;
    }

}
