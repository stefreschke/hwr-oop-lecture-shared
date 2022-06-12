package hwr.oop.solutions.shape.factory.ellipses;

import hwr.oop.solutions.shape.factory.Shape;

public interface Ellipse extends Shape {
    static Ellipse create(double height, double width) {
        return new LazyEllipse(height, width);
    }

    double height();

    double width();
}
