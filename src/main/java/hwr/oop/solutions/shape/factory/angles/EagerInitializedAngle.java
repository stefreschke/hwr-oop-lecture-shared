package hwr.oop.solutions.shape.factory.angles;

import java.util.Objects;

class EagerInitializedAngle implements Angle {
    private final Double degrees;
    private final Double radians;

    private EagerInitializedAngle(Double degrees, Double radians) {
        this.degrees = degrees;
        this.radians = radians;
    }

    public static Angle ofDegree(double degrees) {
        return new EagerInitializedAngle(degrees, buildRadians(degrees));
    }

    public static Angle ofRadian(double radians) {
        return new EagerInitializedAngle(buildDegrees(radians), radians);
    }

    public static Angle ofOpposite(Angle angle) {
        double degrees = 180 - angle.asDegree();
        return new EagerInitializedAngle(degrees, buildRadians(degrees));
    }

    private static double buildRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    private static double buildDegrees(double radians) {
        return radians * 180 / Math.PI;
    }

    @Override
    public double asRadian() {
        return radians;
    }

    @Override
    public double asDegree() {
        return degrees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Angle)) {
            return false;
        }
        Angle that = (Angle) o;
        return degrees.equals(that.asDegree());
    }

    @Override
    public int hashCode() {
        return Objects.hash(degrees);
    }

    @Override
    public int compareTo(Angle o) {
        return Double.compare(degrees, o.asDegree());
    }

    @Override
    public String toString() {
        return "Angle{" +
                "degrees=" + degrees + "," +
                "radians=" + radians +
                '}';
    }
}
