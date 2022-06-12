package hwr.oop.solutions.shape.factory.triangle;

import hwr.oop.solutions.shape.factory.angles.Angle;

class LazyTriangle implements Triangle {

    private final double sideA;
    private final double sideB;
    private final double sideC;

    public LazyTriangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    @Override
    public int numberOfSides() {
        return 3;
    }

    @Override
    public Angle alpha() {
        return enclosedAngle(sideC, sideB, sideA);
    }

    @Override
    public Angle beta() {
        return enclosedAngle(sideA, sideC, sideB);
    }

    @Override
    public Angle gamma() {
        return enclosedAngle(sideB, sideA, sideC);
    }

    @Override
    public double areaSize() {
        return 0.5 * sideC * Math.sin(beta().asRadian()) * sideA;
    }

    @Override
    public double perimeterSize() {
        return sideA + sideB + sideC;
    }

    private Angle enclosedAngle(double sideA, double sideB, double sideC) {
        double angleRadian = calculateEnclosedAngleRadian(sideA, sideB, sideC);
        return Angle.ofRadians(angleRadian);
    }

    private double calculateEnclosedAngleRadian(double left, double right, double opposite) {
        return Math.acos((left * left + right * right - opposite * opposite) / (2 * left * right));
    }
}
