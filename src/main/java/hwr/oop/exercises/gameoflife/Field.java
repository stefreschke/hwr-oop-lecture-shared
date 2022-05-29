package hwr.oop.exercises.gameoflife;

public class Field {
    private Cell[][] cells;

    public Field(int fieldSize) {
        cells = new Cell[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++){
            for (int j = 0; j < fieldSize; j++){
                cells[i][j] = new Cell(i,j);
            }
        }
    }

    public Cell[] setCellNeigbour(){

        return new Cell[0];
    }

    public Cell getCellAt(Position position) {
        return cells[position.x][position.y];
    }
}
