package hwr.oop.examples.classes.methods;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ToStringTest {

    private Train train;

    @BeforeEach
    void setUp() {
        train = new Train(3);
    }

    @Test
    void toString_TrainSpeed1337_Contains1337() {
        train.accelerateTo(1337);
        String toStringResult = train.toString();
        assertThat(toStringResult).contains("1337");
    }

    @Test
    void toString_TrainSpeed42_Contains42() {
        train.accelerateTo(42);
        String toStringResult = train.toString();
        assertThat(toStringResult).contains("42");
    }

    @Test
    void toString_ThreeRailCars_ContainsThree() {
        String toStringResult = train.toString();
        assertThat(toStringResult).contains("3");
    }
}
