package hwr.oop.examples.polymorphism.subtyp;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SquareTest {
    @Test
    void square_IsPolymorphicType() {
        Shape square = new Square(42);
        assertThat(square)
                .isInstanceOf(Object.class)
                .isInstanceOf(Shape.class)
                .isInstanceOf(Quadrangle.class)
                .isInstanceOf(Rectangle.class)
                .isInstanceOf(Square.class);
    }

    @Test
    void squareIsRectangle_ButRectangleIsNoSquare() {
        Shape square = new Square(42);
        Shape rectangle = new Rectangle(13, 37);
        assertThat(square).isInstanceOf(Rectangle.class);
        assertThat(rectangle).isNotInstanceOf(Square.class);
    }

    @Test
    void square_GetPerimeterLength_IsFourTimesProvidedSize() {
        Shape square = new Square(42);
        double perimeterLength = square.getPerimeterLength();
        assertThat(perimeterLength).isEqualTo(4 * 42);
    }

    @Test
    void square_GetAreaSize_IsSideLengthSquared() {
        Shape square = new Square(42);
        double areaSize = square.getAreaSize();
        assertThat(areaSize).isEqualTo(42 * 42);
    }

    @Test
    void square_GetNumberOfSides_ReturnsFour() {
        Shape square = new Square(42);
        int numberOfSides = square.getNumberOfSides();
        assertThat(numberOfSides).isEqualTo(4);
    }
}
