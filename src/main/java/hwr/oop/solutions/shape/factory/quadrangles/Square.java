package hwr.oop.solutions.shape.factory.quadrangles;

public interface Square extends Rectangle, Rhombus {
    static Square create(double sideLength) {
        return new LazySquare(sideLength);
    }
}
