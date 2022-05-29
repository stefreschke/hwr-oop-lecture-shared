package hwr.oop.examples.polymorphism.subtyp;

public class Trapez implements Quadrangle{
    @Override
    public double[] getDistinctAngles() {
        return new double[0];
    }

    @Override
    public double getPerimeterLength() {
        return 0;
    }

    @Override
    public double getAreaSize() {
        return 0;
    }
}
