package hwr.oop.examples.imperative.programming.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Comparison Operators (==, !=, >, <, >=, <=)")
class ComparisonOperatorsTest {

    @Test
    @DisplayName("1 < 2 is true")
    void lessThan_OneLessThanTwo_IsTrue() {
        boolean ltResult = 1 < 2;
        assertThat(ltResult).isTrue();
    }

    @Test
    @DisplayName("1 > 2 is false")
    void greaterThan_OneGreaterThanTwo_IsFalse() {
        boolean gtResult = 1 > 2;
        assertThat(gtResult).isFalse();
    }

    @Test
    @DisplayName("1 <= 2 is true")
    void lessThanOrEqual_OneLessThanOrEqualToTwo_IsTrue() {
        boolean ltoeResult = 1 <= 2;
        assertThat(ltoeResult).isTrue();
    }

    @Test
    @DisplayName("1 >= 2 is false")
    void greaterThanOrEqual_OneGreaterThanOrEqualToTwo_IsFalse() {
        boolean gtoeResult = 1 >= 2;
        assertThat(gtoeResult).isFalse();
    }

}
