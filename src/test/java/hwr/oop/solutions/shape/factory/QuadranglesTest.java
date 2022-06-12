package hwr.oop.solutions.shape.factory;

import hwr.oop.solutions.shape.factory.angles.Angle;
import hwr.oop.solutions.shape.factory.quadrangles.Parallelogram;
import hwr.oop.solutions.shape.factory.quadrangles.Quadrangle;
import hwr.oop.solutions.shape.factory.quadrangles.Rectangle;
import hwr.oop.solutions.shape.factory.quadrangles.Rhombus;
import hwr.oop.solutions.shape.factory.quadrangles.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class QuadranglesTest {

    private static Stream<Arguments> allQuadranglesInput() {
        return Stream.of(
                Arguments.of(Parallelogram.create(2.0, 1, Angle.ofDegree(30))),
                Arguments.of(Rhombus.create(3.0, 4.0)),
                Arguments.of(Rectangle.create(4.0, 5.0)),
                Arguments.of(Square.create(5.0))
        );
    }

    @ParameterizedTest
    @MethodSource("allQuadranglesInput")
    void internalAngleSum_IsThreeSixty(Quadrangle quadrangle) {
        double internalAngleSum = quadrangle.internalAngleSum();
        assertThat(internalAngleSum).isEqualTo(360.0);
    }

    @ParameterizedTest
    @MethodSource("allQuadranglesInput")
    void numberOfSides_IsOfCourse_Four(Quadrangle quadrangle) {
        int numberOfSides = quadrangle.numberOfSides();
        assertThat(numberOfSides).isEqualTo(4);
    }

    private boolean angleContainedBy(Angle angle, List<Long> angles) {
        return angles.contains(Math.round(angle.asDegree()));
    }

    private boolean angleContainedBy(Angle angle, Long... angles) {
        return angleContainedBy(angle, List.of(angles));
    }

    @Nested
    class SquareTests {
        private Square square;

        @BeforeEach
        void setUp() {
            square = Square.create(5.0);
        }

        @Test
        void areaSize_FiveByFive_IsTwentyFive() {
            double areaSize = square.areaSize();
            assertThat(areaSize).isEqualTo(25);
        }

        @Test
        void perimeterSize_IsTwenty() {
            double perimeterSize = square.perimeterSize();
            assertThat(perimeterSize).isEqualTo(20);
        }

        @Test
        void distinctAngles_ContainsOnlyNinety() {
            Set<Angle> distinct = square.distinctAngles();
            assertThat(distinct).containsOnly(Angle.ofDegree(90.0)).size().isOne();
        }
    }

    @Nested
    class RectanglesTest {
        private Rectangle rect;

        @BeforeEach
        void setUp() {
            rect = Rectangle.create(4.0, 5.0);
        }

        @Test
        void perimeterSize_IsEighteen() {
            double perimeterSize = rect.perimeterSize();
            assertThat(perimeterSize).isEqualTo(18);
        }

        @Test
        void distinctAngles_ContainsOnlyNinety() {
            Set<Angle> distinctAngles = rect.distinctAngles();
            assertThat(distinctAngles).containsOnly(Angle.ofDegree(90.0)).size().isOne();
        }

        @Test
        void areaSize_IsWidthTimesHeight_SoTwenty() {
            double areaSize = rect.areaSize();
            assertThat(areaSize).isEqualTo(20);
        }
    }

    @Nested
    class RhombusTest {
        private Rhombus rhombus;

        @BeforeEach
        void setUp() {
            rhombus = Rhombus.create(3.0, 4.0);
        }

        @Test
        void perimeterSize_IsTen() {
            double perimeterSize = rhombus.perimeterSize();
            assertThat(perimeterSize).isEqualTo(10);
        }

        @Test
        void distinctAngles_IsSeventyFourAndHundredSix() {
            Set<Angle> distinctAngles = rhombus.distinctAngles();
            assertThat(distinctAngles)
                    .allMatch(a -> angleContainedBy(a, 74L, 106L));
        }

        @Test
        void areaSize_IsSix() {
            double areaSize = rhombus.areaSize();
            assertThat(areaSize).isEqualTo(6);
        }

    }

    @Nested
    class ParallelogramTest {
        private Parallelogram parallelogram;

        @BeforeEach
        void setUp() {
            parallelogram = Parallelogram.create(2.0, 1, Angle.ofDegree(30));
        }

        @Test
        void areaSize_IsTwo() {
            double areaSize = parallelogram.areaSize();
            assertThat(areaSize).isEqualTo(2);
        }

        @Test
        void perimeterSize_IsEight() {
            double perimeterSize = parallelogram.perimeterSize();
            assertThat(perimeterSize).isEqualTo(8);
        }

        @Test
        void distinctAngles_ContainsThirtyAndOneFifty() {
            Set<Angle> distinctAngles = parallelogram.distinctAngles();
            assertThat(distinctAngles)
                    .allMatch(a -> angleContainedBy(a, 30L, 150L));
        }
    }
}
