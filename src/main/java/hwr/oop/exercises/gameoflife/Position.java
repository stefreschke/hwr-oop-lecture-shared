package hwr.oop.exercises.gameoflife;

public class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isNextTo(Position neighbour) {
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                if(this.x == neighbour.x + i && this.y == neighbour.y + j)
                    return true;
            }
        } return false;
    }
}
