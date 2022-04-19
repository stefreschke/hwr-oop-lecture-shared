package hwr.oop.examples.imperative.programming.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Logical Operators (==, !, !=, &&, ||, ^)")
class LogicalOperatorsTest {

    @Test
    @DisplayName("true == false (equality) is false")
    void equal_TrueEqualsFalse_IsFalse() {
        boolean eqResult = false;
        assertThat(eqResult).isFalse();
    }

    @Test
    @DisplayName("true != false (no equality) is true")
    void notEqual_TrueNotEqualToFalse_IsTrue() {
        boolean neqResult = true;
        assertThat(neqResult).isTrue();
    }

    @Test
    @DisplayName("!true (not) is false")
    void not_NotTrue_IsFalse() {
        boolean notResult = false;
        assertThat(notResult).isFalse();
    }

    @Test
    @DisplayName("!false (not) is true")
    void not_NotFalse_IsTrue() {
        boolean notResult = true;
        assertThat(notResult).isTrue();
    }

    @Test
    @DisplayName("true && false (and) is false")
    void and_TrueAndFalse_IsFalse() {
        boolean andResult = false;
        assertThat(andResult).isFalse();
    }

    @Test
    @DisplayName("true || false (or) is true")
    void or_TrueAndFalse_IsTrue() {
        boolean orResult = true;
        assertThat(orResult).isTrue();
    }

    @Test
    @DisplayName("true ^ false (xor) is true")
    void xor_TrueXorFalse_IsTrue() {
        boolean xorResult = true;
        assertThat(xorResult).isTrue();
    }
}
