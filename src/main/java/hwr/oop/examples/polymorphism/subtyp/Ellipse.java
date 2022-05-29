package hwr.oop.examples.polymorphism.subtyp;

public interface Ellipse extends Shape, Comparable<Ellipse>{
    @Override
    default int compareTo(Ellipse o) {
        return Double.compare(getAreaSize(), o.getAreaSize());
    }
}
