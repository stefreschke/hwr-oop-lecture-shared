package hwr.oop.solutions.shape.factory.ellipses;

public interface Circle extends Ellipse {
    static Circle create(double radius) {
        return new LazyCircle(radius);
    }

    @Override
    default double height() {
        return getRadius();
    }

    @Override
    default double width() {
        return getRadius();
    }

    double getRadius();

}
