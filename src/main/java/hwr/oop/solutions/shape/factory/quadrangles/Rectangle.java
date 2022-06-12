package hwr.oop.solutions.shape.factory.quadrangles;

import hwr.oop.solutions.shape.factory.angles.Angle;

import java.util.Set;

public interface Rectangle extends Parallelogram {
    static Rectangle create(double height, double width) {
        return new LazyRectangle(height, width);
    }

    @Override
    default Set<Angle> distinctAngles() {
        return Set.of(Angle.ofDegree(90));
    }

}
