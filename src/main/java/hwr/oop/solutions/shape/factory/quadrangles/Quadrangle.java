package hwr.oop.solutions.shape.factory.quadrangles;

import hwr.oop.solutions.shape.factory.FiniteNumberOfSidesShape;

public interface Quadrangle extends FiniteNumberOfSidesShape {
    double areaSize();

    double perimeterSize();

    default int numberOfSides() {
        return 4;
    }

    @Override
    default double internalAngleSum() {
        return 360.0;
    }

}
