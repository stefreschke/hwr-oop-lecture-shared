package hwr.oop.solutions.shape.factory.quadrangles;

import hwr.oop.solutions.shape.factory.angles.Angle;

public interface Rhombus extends Parallelogram {
    static Rhombus create(double firstDiagonal, double secondDiagonal) {
        return new LazyRhombus(firstDiagonal, secondDiagonal);
    }

    static Rhombus create(double sideLength, Angle firstAngle) {
        Angle secondAngle = Angle.oppositeTo(firstAngle);
        double squared = sideLength * sideLength;
        double firstDiagonal = Math.sqrt(squared + squared
                - 2 * squared * Math.cos(firstAngle.asRadian()));
        double secondDiagonal = Math.sqrt(squared + squared
                - 2 * sideLength * sideLength * Math.cos(secondAngle.asRadian()));
        return create(firstDiagonal, secondDiagonal);
    }

}
