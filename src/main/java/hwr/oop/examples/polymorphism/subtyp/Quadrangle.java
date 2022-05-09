package hwr.oop.examples.polymorphism.subtyp;

public interface Quadrangle extends Shape, Comparable<Quadrangle> {
    @Override
    default int getNumberOfSides() { return 4; }

    double[] getDistinctAngles();

    @Override
    default int compareTo(Quadrangle o) {
        return Double.compare(getAreaSize(), o.getAreaSize());
    }
}
