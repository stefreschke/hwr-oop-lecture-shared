package hwr.oop.solutions.gameoflife;

class Position {
    private final int x;
    private final int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean isNextTo(Position otherPosition) {
        int horizontalDistance = otherPosition.x - x;
        int verticalDistance = otherPosition.y - y;
        boolean isCellItself = horizontalDistance == 0 && verticalDistance == 0;
        boolean isNextToX = horizontalDistance >= -1 && horizontalDistance <= 1;
        boolean isNextToY = (verticalDistance >= -1 && verticalDistance <= 1);
        return !isCellItself && isNextToX && isNextToY;
    }

    @Override
    public boolean equals(Object o) {
        Position position = (Position) o;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
