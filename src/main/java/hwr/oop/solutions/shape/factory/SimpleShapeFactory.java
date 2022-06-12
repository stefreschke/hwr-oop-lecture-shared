package hwr.oop.solutions.shape.factory;

import hwr.oop.solutions.shape.factory.angles.Angle;
import hwr.oop.solutions.shape.factory.quadrangles.Parallelogram;
import hwr.oop.solutions.shape.factory.quadrangles.Rectangle;
import hwr.oop.solutions.shape.factory.quadrangles.Rhombus;
import hwr.oop.solutions.shape.factory.quadrangles.Square;
import hwr.oop.solutions.shape.factory.triangle.Triangle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SimpleShapeFactory implements ShapeFactory {
    @Override
    public <E extends FiniteNumberOfSidesShape> E create(List<Double> sides) {
        return (E) createWithoutCast(sides);
    }

    @Override
    public <E extends FiniteNumberOfSidesShape> E create(List<Double> sides, List<Angle> angles) {
        return (E) createWithoutCast(sides, angles);
    }

    private FiniteNumberOfSidesShape createWithoutCast(List<Double> sides) {
        assertSideNumberIsNotTooSmall(sides);
        assertSideNumberDoesNotAlsoRequireAnAngle(sides);
        return Triangle.create(sides.get(0), sides.get(1), sides.get(2));
    }

    private void assertSideNumberDoesNotAlsoRequireAnAngle(List<Double> sides) {
        int sideNumber = sides.size();
        if (sideNumber > 3) {
            throw new AdditionalInformationRequiredException(
                    "Need at least one angle for beyond triangle shapes");
        }
    }

    private FiniteNumberOfSidesShape createWithoutCast(List<Double> sides, List<Angle> angles) {
        assertSideNumberIsNotTooSmall(sides);
        assertShapeWillBeQuadrangleOrLess(sides, angles);

        List<Double> relistSides = distinctOf(sides);
        List<Angle> relistAngles = distinctOf(angles);

        Optional<FiniteNumberOfSidesShape> optional = createQuadrangle(relistSides, relistAngles);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new CannotCreateShapeException(sides, angles);
    }

    private Optional<FiniteNumberOfSidesShape> createQuadrangle(List<Double> relistSides,
                                                                List<Angle> relistAngles) {
        int numberOfDistinctSides = relistSides.size();
        if (numberOfDistinctSides == 1) {
            return Optional.of(createQuadrangleWithIdenticalSides(relistSides, relistAngles));
        } else if (numberOfDistinctSides == 2) {
            return Optional.of(createQuadrangleWithTwoSetsOfSides(relistSides, relistAngles));
        } else {
            return Optional.empty();
        }
    }

    private FiniteNumberOfSidesShape createQuadrangleWithTwoSetsOfSides(List<Double> distinctSides,
                                                                        List<Angle> distinctAngles) {
        int distinctAngleNumber = distinctAngles.size();
        if (distinctAngleNumber == 1) {
            return Rectangle.create(
                    distinctSides.get(0), distinctSides.get(1)
            );
        } else {
            return Parallelogram.create(
                    distinctAngles.get(0), distinctSides.get(0), distinctSides.get(1)
            );
        }
    }

    private FiniteNumberOfSidesShape createQuadrangleWithIdenticalSides(List<Double> relistSides,
                                                                        List<Angle> relistAngles) {
        int distinctAngleNumber = relistAngles.size();
        if (distinctAngleNumber == 1) {
            return Square.create(relistSides.get(0));
        } else {
            return Rhombus.create(relistSides.get(0), relistAngles.get(0));
        }
    }

    private void assertShapeWillBeQuadrangleOrLess(List<Double> sides, List<Angle> angles) {
        int sideNumber = sides.size();
        if (sideNumber > 4) {
            throw new CannotCreateShapeException(sides, angles);
        }
    }

    private void assertSideNumberIsNotTooSmall(List<Double> sides) {
        int sideNumber = sides.size();
        if (sideNumber < 3) {
            throw new AdditionalInformationRequiredException("More than 2 sides are required");
        }
    }

    private <E> List<E> distinctOf(List<E> stuff) {
        Set<E> distinctSides = new HashSet<>(stuff);
        return new ArrayList<>(distinctSides);
    }
}
