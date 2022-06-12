package hwr.oop.solutions.shape.factory;

import hwr.oop.solutions.shape.factory.angles.Angle;
import hwr.oop.solutions.shape.factory.triangle.Triangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class TrianglesTest {

    private Triangle triangle;

    @BeforeEach
    void setUp() {
        triangle = Triangle.create(4.0, 6.0, 6.0);
    }

    @Test
    void angles_CanBeRequestedFromTriangle() {
        Set<Angle> angles = triangle.distinctAngles();
        Angle alpha = triangle.alpha();
        Angle beta = triangle.beta();
        Angle gamma = triangle.gamma();
        assertThat(angles).containsOnly(alpha, beta, gamma).size().isEqualTo(2);
    }

    @Test
    void internalAngleSum_Is180() {
        double internalAngleSum = triangle.internalAngleSum();
        assertThat(internalAngleSum).isEqualTo(180.0);
    }

    @Test
    void perimeterSize_IsSumOfAllSidesSoSixteen() {
        double perimeterSize = triangle.perimeterSize();
        assertThat(perimeterSize).isEqualTo(16);
    }

    @Test
    void numberOfSides_IsThree() {
        int numberOfSides = triangle.numberOfSides();
        assertThat(numberOfSides).isEqualTo(3);
    }

    @Test
    void areaSize_IsSomeWhereBetweenElevenAndTwelve() {
        double areaSize = triangle.areaSize();
        assertThat(areaSize).isGreaterThan(11).isLessThan(12);
    }

}
