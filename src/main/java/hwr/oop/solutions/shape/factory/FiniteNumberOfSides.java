package hwr.oop.solutions.shape.factory;

import hwr.oop.solutions.shape.factory.angles.Angle;

import java.util.Set;
import java.util.function.Function;

public interface FiniteNumberOfSides {
    int numberOfSides();

    Set<Angle> distinctAngles();

    double internalAngleSum();

    interface Comparator extends java.util.Comparator<FiniteNumberOfSides> {

        static Comparator byInternalAngleSum() {
            return (o1, o2) -> Double.compare(o1.internalAngleSum(), o2.internalAngleSum());
        }

        static Comparator byNumberOfDistinctAngles() {
            Function<FiniteNumberOfSides, Integer> query = o -> o.distinctAngles().size();
            return (o1, o2) -> Integer.compare(query.apply(o1), query.apply(o2));
        }
    }
}
