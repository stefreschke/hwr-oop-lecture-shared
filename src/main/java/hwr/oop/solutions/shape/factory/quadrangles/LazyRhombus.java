package hwr.oop.solutions.shape.factory.quadrangles;

import hwr.oop.solutions.shape.factory.angles.Angle;

import java.util.Set;

class LazyRhombus implements Rhombus {
    private final double sideLength;
    private final double firstDiagonal;
    private final double secondDiagonal;

    public LazyRhombus(double firstDiagonal, double secondDiagonal) {
        this.firstDiagonal = firstDiagonal;
        this.secondDiagonal = secondDiagonal;
        sideLength = Math.sqrt(
                Math.pow(firstDiagonal / 2, 2) +
                        Math.pow(secondDiagonal / 2, 2)
        );
    }

    @Override
    public double areaSize() {
        return firstDiagonal * secondDiagonal / 2;
    }

    @Override
    public double perimeterSize() {
        return sideLength * 4;
    }

    @Override
    public Set<Angle> distinctAngles() {
        double newAngle = Math.acos(firstDiagonal / sideLength * 0.5);
        Angle angle = Angle.ofRadians(newAngle * 2);
        return Set.of(angle, Angle.oppositeTo(angle));
    }
}
