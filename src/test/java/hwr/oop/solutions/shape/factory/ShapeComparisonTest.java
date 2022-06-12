package hwr.oop.solutions.shape.factory;

import hwr.oop.solutions.shape.factory.quadrangles.Rectangle;
import hwr.oop.solutions.shape.factory.quadrangles.Square;
import hwr.oop.solutions.shape.factory.triangle.Triangle;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

class ShapeComparisonTest {

    @Test
    void byAreaSize_MediumSquareBiggerThanThinLongRectangle() {
        // given
        Shape.Comparator comparator = Shape.Comparator.byAreaSize();
        Shape first = Square.create(10);
        Shape second = Rectangle.create(.1, 400);
        // when
        Set<Shape> set = new TreeSet<>(comparator);
        set.add(first);
        set.add(second);
        // then
        assertThat(set).containsSequence(second, first);
    }

    @Test
    void byPerimeterSize_ThinLongRectangleBiggerThanMediumSquare() {
        // given
        Shape.Comparator comparator = Shape.Comparator.byPerimeterSize();
        Shape first = Rectangle.create(.1, 400);
        Shape second = Square.create(10);
        // when
        Set<Shape> set = new TreeSet<>(comparator);
        set.add(first);
        set.add(second);
        // then
        assertThat(set).containsSequence(second, first);
    }

    @Test
    void byInternalAngleSum_TriangleSmallerThanRectangle() {
        // given
        FiniteNumberOfSides.Comparator comparator =
                FiniteNumberOfSides.Comparator.byInternalAngleSum();
        FiniteNumberOfSides first = Rectangle.create(10, 10);
        FiniteNumberOfSides second = Triangle.create(1, 2, 2);
        // when
        Set<FiniteNumberOfSides> set = new TreeSet<>(comparator);
        set.add(first);
        set.add(second);
        // then
        assertThat(set).containsSequence(second, first);
    }

    @Test
    void byNumberOfDistinctAngles_RectangleSmallerThanTriangle() {
        // given
        FiniteNumberOfSides.Comparator comparator =
                FiniteNumberOfSides.Comparator.byNumberOfDistinctAngles();
        FiniteNumberOfSides first = Triangle.create(1, 2, 2);
        FiniteNumberOfSides second = Rectangle.create(10, 10);
        // when
        Set<FiniteNumberOfSides> set = new TreeSet<>(comparator);
        set.add(first);
        set.add(second);
        // then
        assertThat(set).containsSequence(second, first);
    }
}
