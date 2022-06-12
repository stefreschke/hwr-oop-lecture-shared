package hwr.oop.solutions.shape.factory.quadrangles;

import hwr.oop.solutions.shape.factory.angles.Angle;

import java.util.Set;

class LazyParallelogram implements Parallelogram {
    private final double secondSize;
    private final double height;
    private final Angle angle;
    private final double firstSide;

    public LazyParallelogram(double firstSide, double height, Angle angle) {
        this.height = height;
        this.angle = angle;
        this.firstSide = firstSide;
        secondSize = height / Math.sin(angle.asRadian());
    }

    @Override
    public double areaSize() {
        return firstSide * height;
    }

    @Override
    public double perimeterSize() {
        return 2 * firstSide + 2 * secondSize;
    }

    @Override
    public Set<Angle> distinctAngles() {
        return Set.of(angle, Angle.oppositeTo(angle));
    }
}
