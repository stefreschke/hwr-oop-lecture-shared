package hwr.oop.solutions.shape.factory.ellipses;

class LazyCircle implements Circle {
    private final double radius;

    public LazyCircle(double radius) {
        this.radius = radius;
    }

    @Override
    public double areaSize() {
        return Math.PI * radius;
    }

    @Override
    public double perimeterSize() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double getRadius() {
        return radius;
    }
}
