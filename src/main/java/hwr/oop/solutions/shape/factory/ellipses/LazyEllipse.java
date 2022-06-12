package hwr.oop.solutions.shape.factory.ellipses;

class LazyEllipse implements Ellipse {
    private final double height;
    private final double width;

    LazyEllipse(double height, double width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public double areaSize() {
        return .25 * height * width * Math.PI;
    }

    @Override
    public double perimeterSize() {
        double halfHeightSquared = Math.pow(height / 2, 2);
        double halfWidthSquared = Math.pow(width / 2, 2);
        return 2 * Math.PI * Math.sqrt((halfHeightSquared + halfWidthSquared) / 2);
    }

    @Override
    public double height() {
        return height;
    }

    @Override
    public double width() {
        return width;
    }
}
