package hwr.oop.solutions.shape.factory;

public interface Shape {

    double areaSize();

    double perimeterSize();

    interface Comparator extends java.util.Comparator<Shape> {

        static Comparator byAreaSize() {
            return (o1, o2) -> Double.compare(o1.areaSize(), o2.areaSize());
        }

        static Comparator byPerimeterSize() {
            return (o1, o2) -> Double.compare(o1.perimeterSize(), o2.perimeterSize());
        }
    }
}
