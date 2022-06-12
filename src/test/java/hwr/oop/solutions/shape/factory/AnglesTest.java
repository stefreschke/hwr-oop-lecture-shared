package hwr.oop.solutions.shape.factory;

import hwr.oop.solutions.shape.factory.angles.Angle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AnglesTest {

    private Angle alpha;
    private Angle beta;
    private Angle gamma;

    @BeforeEach
    void setUp() {
        alpha = Angle.ofDegree(10);
        beta = Angle.ofDegree(20);
        gamma = Angle.ofDegree(30);
    }

    @Test
    void conversion_WorksSeamless() {
        // given
        Angle degree = Angle.ofDegree(90);
        Angle radian = Angle.ofRadians(Math.PI / 2);
        // when
        double degToRad = degree.asRadian();
        double radToDeg = radian.asDegree();
        // then
        assertThat(degToRad).isEqualTo(Math.PI / 2);
        assertThat(radToDeg).isEqualTo(90);
    }

    @Test
    void ofRadiant_CanCreateAnglesFromRadianValues() {
        // given
        Angle angle = Angle.ofRadians(Math.PI);
        Angle ninetyDegrees = Angle.ofDegree(90);
        // when, then
        assertThat(angle.asDegree()).isEqualTo(180);
        assertThat(ninetyDegrees.asRadian()).isEqualTo(Math.PI / 2);
    }

    @Test
    void anglesCanBeKeysInMaps() {
        // given
        Map<Angle, Integer> map = new HashMap<>();
        map.put(alpha, 1);
        map.put(beta, 2);
        map.put(gamma, 3);
        // when, then
        assertThat(map.get(alpha)).isEqualTo(1);
        assertThat(map.get(beta)).isEqualTo(2);
        assertThat(map.get(gamma)).isEqualTo(3);
    }

    @Test
    void compareTo_ThreeDifferentAngles_CanBeOrderedBySize() {
        assertThat(alpha).isLessThan(beta).isLessThan(gamma);
        assertThat(beta).isGreaterThan(alpha).isLessThan(gamma);
        assertThat(gamma).isGreaterThan(alpha).isGreaterThan(beta);
    }

    @Test
    void equals_ThreeDifferentAngles_AllAreNotEqual() {
        assertThat(alpha).isNotEqualTo(beta);
        assertThat(beta).isNotEqualTo(gamma);
        assertThat(alpha).isNotEqualTo(gamma);
    }

    @Test
    void equals_NonAngleObjectAndNull_IsNotEqual() {
        assertThat(alpha).isNotEqualTo(new Object());
        assertThat(alpha).isNotEqualTo(null);
    }

    @Test
    void toString_ProvidesInformationAboutDegreesAndRadians() {
        // when
        String toString = alpha.toString();
        // then
        double radians = 10 * Math.PI / 180;
        String radiansString = Double.toString(radians);
        assertThat(toString)
                .containsSequence("radians=", radiansString)
                .containsSequence("degrees=", "10");
    }
}
