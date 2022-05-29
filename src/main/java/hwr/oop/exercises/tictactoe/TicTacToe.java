package hwr.oop.exercises.tictactoe;

import java.util.Arrays;

class TicTacToe {

    static int[][] board;
    public boolean circleSet;
    public boolean crossSet;
    int pointA;
    int pointB;
    int mode;

    TicTacToe(int mode) {
        board = new int[mode][mode];
        pointB = 0;
        pointA = 0;
        this.mode = mode;
    }

    int getValueAt(int x, int y) {
        return board[x][y];
    }

    void setCross(int x, int y) {
        check(x, y, crossSet);
        board[x][y] = 1;
        crossSet = true;
        circleSet = false;
    }

    private void check(int x, int y, boolean set) {
        boolean moveIsValid = isGameOver() || getValueAt(x, y) != 0 || set;
        if (moveIsValid) {
            throw new RuntimeException("You made an illegal move!");
        }
    }

    void setCircle(int x, int y) {
        check(x, y, circleSet);
        board[x][y] = 2;
        crossSet = false;
        circleSet = true;

    }

    boolean isfullBoard() {
        for (int[] ints : board) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean isGameOver() {
        if (isfullBoard()) {
            return true;
        }
        // vertical, horizontal wins
        int sum = 0;
        int sum1 = 0;
        if (mode == 3){
            for (int i = 0; i < 3; i++) {
                if ((board[i][0] == board[i][1]) && (board[i][0] == board[i][2]))
                    sum = board[i][0] + board[i][1] + board[i][2];
                else if ((board[0][i] == board[1][i]) && (board[0][i] == board[2][i]))
                    sum1 = board[0][i] + board[1][i] + board[2][i];

                if (checkWin(sum, sum1, mode)) return true;
            }

            // diagonal wins
            if (board[0][0] == board[1][1] && board[0][0] == board[2][2])
                sum  = board[0][0] + board[1][1] + board[2][2];
            else if (board[0][2] == board[1][1] && board[0][2] == board[2][0])
                sum1 = board[0][2] + board[1][1] + board[2][0];

            return checkWin(sum, sum1, mode);

        } else if (mode == 4) {
            for (int i = 0; i < 4; i++) {
                if ((board[i][0] == board[i][1]) && (board[i][0] == board[i][2]) && (board[i][0] == board[i][3]))
                    sum = board[i][0] + board[i][1] + board[i][2] + board[i][3];
                else if ((board[0][i] == board[1][i]) && (board[0][i] == board[2][i]) && (board[0][i] == board[3][i]))
                    sum1 = board[0][i] + board[1][i] + board[2][i] + board[3][i];

                if (checkWin(sum, sum1, mode)) return true;
            }

            // diagonal wins
            if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == board[3][3])
                sum = board[0][0] + board[1][1] + board[2][2] + board[3][3];
            else if (board[0][3] == board[1][2] && board[2][1] == board[3][0] && board[1][2] == board[2][1])
                sum1 = board[0][3] + board[1][2] + board[2][1] + board[3][0];

            return checkWin(sum, sum1, mode);
        }
        return false;
    }

    public boolean checkWin(int sum, int sum1, int mode){
        if ((sum == mode) || (sum1 == mode)) {
            pointA++;
            System.out.println("Player A won and now has " + pointA + " point(s). Player B has " + pointB + " point(s).");
            return true;
        } else if ((sum == mode*2) || (sum1 == mode*2)) {
            pointB++;
            System.out.println("Player B won and now has " + pointB + " point(s). Player A has " + pointA + " point(s).");
            return true;
        }
        return false;
    }
    public void clearBoard() {
        for (int[] ints : board) {
            Arrays.fill(ints, 0);
        }
    }

}