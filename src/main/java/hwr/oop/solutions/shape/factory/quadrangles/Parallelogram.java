package hwr.oop.solutions.shape.factory.quadrangles;

import hwr.oop.solutions.shape.factory.angles.Angle;

public interface Parallelogram extends Quadrangle {
    static Parallelogram create(double firstSide, double height, Angle angle) {
        return new LazyParallelogram(firstSide, height, angle);
    }

    static Parallelogram create(Angle alpha, double sideA, double sideB) {
        double height = Math.sin(alpha.asRadian()) * sideB;
        return Parallelogram.create(sideA, height, alpha);
    }

}
