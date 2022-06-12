package hwr.oop.solutions.shape.factory;

import hwr.oop.solutions.shape.factory.ellipses.Circle;
import hwr.oop.solutions.shape.factory.ellipses.Ellipse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class EllipsesTest {

    @Test
    void ellipseIsNotNull() {
        Shape ellipse = Ellipse.create(2, 4);
        assertThat(ellipse).isNotNull();
    }

    @Test
    void ellipseCreation() {
        Ellipse ellipse = Ellipse.create(2, 4);
        double areaSize = 2 * Math.PI;
        double perimeterSize = 9.93;
        double height = ellipse.height();
        double width = ellipse.width();

        assertThat(height).isEqualTo(2.0);
        assertThat(width).isEqualTo(4.0);
        assertThat(ellipse.areaSize()).isEqualTo(areaSize);
        assertThat(ellipse.perimeterSize()).isGreaterThan(perimeterSize);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 2.0, 3.0})
    void circleCreation(double inputRadius) {
        Circle circle = Circle.create(inputRadius);

        double radius = circle.getRadius();
        double areaSize = circle.areaSize();
        double perimeter = circle.perimeterSize();
        double height = circle.height();
        double width = circle.width();

        assertThat(radius).isEqualTo(inputRadius);
        assertThat(areaSize).isEqualTo(Math.PI * inputRadius);
        assertThat(perimeter).isEqualTo(2 * Math.PI * inputRadius);
        assertThat(height).isEqualTo(inputRadius);
        assertThat(width).isEqualTo(inputRadius);
    }
}
