package hwr.oop.solutions.shape.factory.quadrangles;

class LazySquare implements Square {
    private final double sideLength;

    public LazySquare(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double areaSize() {
        return sideLength * sideLength;
    }

    @Override
    public double perimeterSize() {
        return sideLength * 4;
    }
}
