package hwr.oop.solutions.shape.factory;

import hwr.oop.solutions.shape.factory.angles.Angle;

import java.util.List;

public class CannotCreateShapeException extends RuntimeException {
    private final List<Double> sides;
    private final List<Angle> angles;

    CannotCreateShapeException(List<Double> sides, List<Angle> angles) {
        this.sides = sides;
        this.angles = angles;
    }

    @Override
    public String getMessage() {
        return "Cannot create shape of sides={" + sides + "}, angles={" + angles + "}";
    }
}
