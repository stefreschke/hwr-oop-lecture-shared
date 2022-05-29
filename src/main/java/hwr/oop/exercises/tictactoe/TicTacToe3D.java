package hwr.oop.exercises.tictactoe;

import java.util.Arrays;

public class TicTacToe3D {

    static int[][][] board;
    int pointA;
    int pointB;
    public boolean circleSet;
    public boolean crossSet;

    TicTacToe3D() {
        board = new int[3][3][3];
        pointA = 0;
        pointB = 0;
    }

    int getValueAt(int x, int y, int z) {
        return board[x][y][z];
    }

    void setCross(int x, int y, int z) {
        if (!isGameOver() && getValueAt(x, y, z) == 0 && !crossSet) {
            board[x][y][z] = 1;
            crossSet = true;
            circleSet = false;
        } else
            throw new RuntimeException("Illeagl move");
    }

    void setCircle(int x, int y, int z) {
        if (!isGameOver() && getValueAt(x, y, z) == 0 && !circleSet) {
            board[x][y][z] = 2;
            crossSet = false;
            circleSet = true;
        } else
            throw new RuntimeException("Illegal move");
    }

    boolean isfullBoard(){
        for (int[][] array2D: board) {
            for (int[] array1D: array2D) {
                for(int item: array1D) {
                    if (item == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void clearBoard() {
        for (int[][] array2D: board) {
            for (int[] array1D: array2D) {
                Arrays.fill(array1D, 0);
            }
        }
    }

    boolean isGameOver() {
        if (isfullBoard()) {
            return true;
        }
        //REGEL Diagonal 2D
        for(int i = 0; i < board.length; i++){
            for(int l = 0; l == 0; l++){

                if ((board[l][i][l] == board[l+1][i][l+1] && board[l+2][i][l+2] == board[l][i][l])&&(((board[l][i][l])+(board[l+1][i][l+1])+(board[l+2][i][l+2]) == 3))){
                    System.out.println("Spieler 1 hat mit einer 2D Diagonalen gewonnen!");
                    return true;
                }

                if((board[l][i][l] == board[l+1][i][l+1] && board[l+2][i][l+2] == board[l][i][l])&&((board[l][i][l])+(board[l+1][i][l+1])+(board[l+2][i][l+2]) == 6)){
                    System.out.println("Spieler 2 hat mit einer 2D Diagonalen gewonnen!");
                    return true;
                }
            }
        }

        for(int i = 0; i < board.length; i++){
            for(int l = 0; l == 0; l++){

                if((board[l+2][i][l] == board[l+1][i][l+1] && board[l][i][l+2] == board[l+2][i][l])&&(((board[l+2][i][l])+(board[l+1][i][l+1])+(board[l][i][l+2]) == 3))){
                    System.out.println("Spieler 1 hat mit einer 2D Diagonalen gewonnen!");
                    return true;
                }

                if((board[l+2][i][l] == board[l+1][i][l+1] && board[l][i][l+2] == board[l+2][i][l])&&((board[l+2][i][l])+(board[l+1][i][l+1])+(board[l][i][l+2]) == 6)){
                    System.out.println("Spieler 2 hat mit einer 2D Diagonalen gewonnen!");
                    return true;
                }
            }
        }



        //REGEL Diagonal 3D

        for(int i = 0; i == 0; i++){

            if ((board[i][i][i] == board[i+1][i+1][i+1] && board[i][i][i] == board[i+2][i+2][i+2]) && ((board[i][i][i] + board[i+1][i+1][i+1] + board[i+2][i+2][i+2]) == 3)) {
                System.out.println("Spieler 1 hat mit einer 3D Diagonalen gewonnen!");
                return true;
            }

            if ((board[i][i][i] == board[i+1][i+1][i+1] && board[i][i][i] == board[i+2][i+2][i+2]) &&((board[i][i][i])+(board[i+1][i+1][i+1])+(board[i+2][i+2][i+2]) == 6)){
                System.out.println("Spieler 2 hat mit einer 3D Diagonalen gewonnen!");
                return true;
            }

        }

        for(int i = 0; i == 0; i++){

            if((board[i+2][i+2][i] == board[i+1][i+1][i+1] && board[i+2][i+2][i] == board[i][i][i+2])&&((board[i+2][i+2][i])+(board[i+1][i+1][i+1])+(board[i][i][i+2]) == 3)){
                System.out.println("Spieler 1 hat mit einer 3D Diagonalen gewonnen!");
                return true;
            }

            if((board[i+2][i+2][i] == board[i+1][i+1][i+1] && board[i+2][i+2][i] == board[i][i][i+2])&&((board[i+2][i+2][i])+(board[i+1][i+1][i+1])+(board[i][i][i+2]) == 6)){
                System.out.println("Spieler 2 hat mit einer 3D Diagonalen gewonnen!");
                return true;
            }

        }

        //REGEL Horizontal 2D

        for(int i = 0; i < board.length; i++){
            for(int l=0; l < board.length; l++){
                for(int u=0; u == 0; u++){

                    if((board[u][i][l] == board[u+1][i][l] && (board[u][i][l] == board[u+2][i][l])&&(((board[u][i][l])+(board[u+1][i][l])+(board[u+2][i][l]) == 3)))){
                        System.out.println("Spieler 1 hat mit einer 2D Horizontalen gewonnen!");
                        return true;
                    }

                    if(board[u][i][l] == board[u+1][i][l] && (board[u][i][l] == board[u+2][i][l]) && ((board[u][i][l])+(board[u+1][i][l])+(board[u+2][i][l]) == 6)){
                        System.out.println("Spieler 2 hat mit einer 2D Horizontalen gewonnen!");
                        return true;
                    }

                }

            }
        }

        //REGEL Horizontal 3D-------------------------------------

        for(int i = 0; i == 0; i++){
            for(int l=0; l < board.length; l++){
                for (int[][] ints : board) {

                    if ((ints[i][l] == ints[i + 1][l] && ints[i][l] == ints[i + 2][l]) && (ints[i][l]) + (ints[i + 1][l]) + (ints[i + 2][l]) == 3) {
                        System.out.println("Spieler 1 hat mit einer 3D Horizontalen gewonnen!");
                        return true;
                    }

                    if ((ints[i][l] == ints[i + 1][l] && ints[i][l] == ints[i + 2][l]) && (ints[i][l]) + (ints[i + 1][l]) + (ints[i + 2][l]) == 6) {
                        System.out.println("Spieler 2 hat mit einer 3D Horizontalen gewonnen!");
                        return true;
                    }

                }

            }
        }


        //REGEL Vertikal 2D-------------------------------------

        for(int i = 0; i < board.length; i++){
            for (int[][] ints : board) {
                for (int u = 0; u == 0; u++) {

                    if ((ints[i][u] == ints[i][u+1] && ints[i][u] == ints[i][u + 2]) && ((ints[i][u]) + (ints[i][u + 1]) + (ints[i][u + 2]) == 3)) {
                        System.out.println("Spieler 1 hat mit einer 2D Vertikalen gewonnen!");
                        return true;
                    }

                    if ((ints[i][u] == ints[i][u+1] && ints[i][u] == ints[i][u + 2]) && ((ints[i][u]) + (ints[i][u + 1]) + (ints[i][u + 2]) == 6)) {
                        System.out.println("Spieler 2 hat mit einer 2D Vertikalen gewonnen!");
                        return true;
                    }

                }

            }
        }


        //REGEL Vertikal 3D-------------------------------------

        for(int i = 0; i == 0; i++){
            for(int l=0; l < board.length; l++){
                for(int u=0; u == 0; u++){

                    if((board[l][i][u] == board[l][i+1][u+1] && board[l][i+2][u+2] == board[l][i][u]) && ((board[l][i][u])+(board[l][i+1][u+1])+(board[l][i+2][u+2]) == 3)){
                        System.out.println("Spieler 1 hat mit einer 3D Vertikalen gewonnen!");
                        return true;
                    }

                    if((board[l][i][u] == board[l][i+1][u] && board[l][i+2][u] == board[l][i][u]) && (board[u][i][l])+(board[u][i+1][l])+(board[u][i+2][l]) == 6){
                        System.out.println("Spieler 2 hat mit einer 3D Vertikalen gewonnen!");
                        return true;
                    }

                }

            }
        }
        return false;
    }
}
