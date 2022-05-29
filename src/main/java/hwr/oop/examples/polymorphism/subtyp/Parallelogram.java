package hwr.oop.examples.polymorphism.subtyp;

public class Parallelogram implements Quadrangle{

    private final double sideA, sideB, smallAngle;

    public Parallelogram(double sideA, double sideB, double smallAngle) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.smallAngle = smallAngle;
    }

    @Override
    public double[] getDistinctAngles() {
        return new double[]{smallAngle, 180-smallAngle};
    }

    @Override
    public double getPerimeterLength() {
        return 2*(sideA+sideB);
    }

    @Override
    public double getAreaSize() {
        double minSide = Math.min(sideA, sideB);
        double maxSide = Math.max(sideA, sideB);
        return Math.sqrt(Math.pow(maxSide,2)-Math.pow(minSide,2))*minSide;
    }
}
