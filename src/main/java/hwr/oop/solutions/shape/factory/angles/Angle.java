package hwr.oop.solutions.shape.factory.angles;

public interface Angle extends Comparable<Angle> {
    static Angle ofDegree(double degrees) {
        return EagerInitializedAngle.ofDegree(degrees);
    }

    static Angle ofRadians(double radians) {
        return EagerInitializedAngle.ofRadian(radians);
    }

    static Angle oppositeTo(Angle angle) { return EagerInitializedAngle.ofOpposite(angle); }

    double asRadian();

    double asDegree();

}
