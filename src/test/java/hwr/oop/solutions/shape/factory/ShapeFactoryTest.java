package hwr.oop.solutions.shape.factory;

import hwr.oop.solutions.shape.factory.angles.Angle;
import hwr.oop.solutions.shape.factory.quadrangles.Parallelogram;
import hwr.oop.solutions.shape.factory.quadrangles.Rectangle;
import hwr.oop.solutions.shape.factory.quadrangles.Rhombus;
import hwr.oop.solutions.shape.factory.quadrangles.Square;
import hwr.oop.solutions.shape.factory.triangle.Triangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

class ShapeFactoryTest {

    private ShapeFactory factory;

    @BeforeEach
    void setUp() {
        factory = ShapeFactory.create();
    }

    @Test
    void create_ProvidedThreeSides_CreatesTriangle() {
        List<Double> sides = List.of(1.0, 2.0, 3.0);
        // when
        Triangle shape = factory.create(sides);
        int numberOfSides = shape.numberOfSides();
        // then
        assertThat(numberOfSides).isEqualTo(3);
    }

    @Test
    void create_ProvidedFourIdenticalSides_CreatesSquare() {
        List<Double> sides = List.of(1.0, 1.0, 1.0, 1.0);
        List<Angle> angles = List.of(Angle.ofDegree(90));
        assertThatNoException().isThrownBy(() -> {
            Square shape = factory.create(sides, angles);
        });
    }

    @Test
    void create_ProvidedFourSidesWithTwoDistinctValues_CreatesRectangle() {
        List<Double> sides = List.of(1.0, 3.0, 1.0, 3.0);
        List<Angle> angles = List.of(Angle.ofDegree(90));
        assertThatNoException().isThrownBy(() -> {
            Rectangle shape = factory.create(sides, angles);
        });
    }

    @Test
    void create_ProvidedSingleSideWithIdenticalSides_CreatesRhombus() {
        List<Double> sides = List.of(1.0, 1.0, 1.0, 1.0);
        List<Angle> angles = List.of(Angle.ofDegree(60), Angle.ofDegree(120));
        assertThatNoException().isThrownBy(() -> {
            Rhombus shape = factory.create(sides, angles);
        });
    }

    @Test
    void create_ProvidedFourSidesWithTwoDistinctValuesAndAngle_CreatesParallelogram() {
        List<Double> sides = List.of(1.0, 3.0, 1.0, 3.0);
        List<Angle> angles = List.of(
                Angle.ofDegree(60), Angle.ofDegree(120), Angle.ofDegree(60), Angle.ofDegree(120)
        );
        assertThatNoException().isThrownBy(() -> {
            Parallelogram shape = factory.create(sides, angles);
        });
    }

    @Test
    void create_ProvidedTwoSides_CannotCreateShapeAndThrowsException() {
        List<Double> sides = List.of(1.0, 2.0);
        assertThatExceptionOfType(AdditionalInformationRequiredException.class)
                .isThrownBy(() -> factory.create(sides))
                .withMessageContaining("2 sides");
    }

    @Test
    void create_ProvidedFourSides_CannotCreateShapeBecauseAngleIsRequiredAndThrowsException() {
        List<Double> sides = List.of(1.0, 2.0, 3.0, 4.0);
        assertThatExceptionOfType(AdditionalInformationRequiredException.class)
                .isThrownBy(() -> factory.create(sides))
                .withMessageContaining("angle");
    }

    @Test
    void createWithAngles_ProvidedTwoSides_CannotCreateShapeAndThrowsException() {
        List<Double> sides = List.of(1.0, 2.0);
        List<Angle> angles = List.of(Angle.ofDegree(360));
        assertThatExceptionOfType(AdditionalInformationRequiredException.class)
                .isThrownBy(() -> factory.create(sides, angles))
                .withMessageContaining("2 sides");
    }

    @Test
    void createWithAngles_Trapezoid_IsNotSupportedThusExceptionIsThrown() {
        List<Double> sides = List.of(4.0, 6.0, 4.0, 10.0);
        List<Angle> angles = List.of(Angle.ofDegree(360));
        assertThatExceptionOfType(CannotCreateShapeException.class)
                .isThrownBy(() -> factory.create(sides, angles))
                .withMessageContaining("sides=")
                .withMessageContaining("angles=");
    }

    @Test
    void createWithAngles_Pentagon_CannotBeCreatedThrowsException() {
        List<Double> sides = List.of(4.0, 6.0, 4.0, 10.0, 5.0);
        List<Angle> angles = List.of(Angle.ofDegree(360));
        assertThatExceptionOfType(CannotCreateShapeException.class)
                .isThrownBy(() -> factory.create(sides, angles))
                .withMessageContaining("sides=")
                .withMessageContaining("angles=");
    }
}
