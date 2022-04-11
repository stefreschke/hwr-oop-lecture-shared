package hwr.oop.examples.programming.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Brackets () manipulate Operator precedence")
class BracketsTest {
    @Test
    @DisplayName("5 + 2 * 2 = 9, normal precedence")
    void brackets_NotPresent_NormalPrecedenceEvaluation() {
        int number = 5 + 2 * 2;
        assertThat(number).isEqualTo(9);
    }

    @Test
    @DisplayName("(5 + 2) * 2 = 14, brackets first")
    void brackets_Present_AreEvaluatedFirst() {
        int number = (5 + 2) * 2;
        assertThat(number).isEqualTo(14);
    }

    @Test
    @DisplayName("5 * 2 - 4 * 4 = -6, normal precedence")
    void brackets_NotPresentComplexCalculation_NormalPrecedenceEvaluation() {
        int number = 5 * 2 - 4 * 4;
        assertThat(number).isEqualTo(-6);
    }

    @Test
    @DisplayName("(5 * (2 - 4)) * 3 = -30, deepest nested brackets first")
    void brackets_Nested_DeepestIsEvaluatedFirst() {
        int number = (5 * (2 - 4)) * 3;
        assertThat(number).isEqualTo(-30);
    }
}
