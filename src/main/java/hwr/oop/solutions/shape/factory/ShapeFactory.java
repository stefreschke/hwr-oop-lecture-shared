package hwr.oop.solutions.shape.factory;

import hwr.oop.solutions.shape.factory.angles.Angle;

import java.util.List;

public interface ShapeFactory {
    static ShapeFactory create() {
        return new SimpleShapeFactory();
    }

    <E extends FiniteNumberOfSidesShape> E create(List<Double> sides);

    <E extends FiniteNumberOfSidesShape> E create(List<Double> sides, List<Angle> angles);

}
