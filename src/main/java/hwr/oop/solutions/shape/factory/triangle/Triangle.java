package hwr.oop.solutions.shape.factory.triangle;

import hwr.oop.solutions.shape.factory.FiniteNumberOfSidesShape;
import hwr.oop.solutions.shape.factory.angles.Angle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface Triangle extends FiniteNumberOfSidesShape {
    static Triangle create(double sideA, double sideB, double sideC) {
        return new LazyTriangle(sideA, sideB, sideC);
    }

    @Override
    default Set<Angle> distinctAngles() {
        return new HashSet<>(List.of(alpha(), beta(), gamma()));
    }

    @Override
    default double internalAngleSum() {
        return 180.0;
    }

    Angle alpha();

    Angle beta();

    Angle gamma();
}
