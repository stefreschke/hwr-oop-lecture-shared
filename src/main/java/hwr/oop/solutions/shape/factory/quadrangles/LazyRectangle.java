package hwr.oop.solutions.shape.factory.quadrangles;

class LazyRectangle implements Rectangle {
    private final double width;
    private final double height;

    public LazyRectangle(double height, double width) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double areaSize() {
        return width * height;
    }

    @Override
    public double perimeterSize() {
        return 2 * width + 2 * height;
    }
}
