package hwr.oop.examples.programming.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Integer Arithmetic Operators (+-*/%)")
class IntegerArithmeticOperatorsTest {

    @Test
    @DisplayName("5 + 2 = 7")
    void integerAddition_FivePlusTwo_IsSeven() {
        int a = 5, b = 2;
        int result = a + b;
        assertThat(result).isEqualTo(7);
    }

    @Test
    @DisplayName("5 - 2 = 3")
    void integerSubtraction_FiveMinusTwo_IsThree() {
        int a = 5, b = 2;
        int result = a - b;
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("5 * 2 = 10")
    void integerMultiplication_FiveTimesTwo_IsTen() {
        int a = 5, b = 2;
        int result = a * b;
        assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("5 % 2 = 1")
    void integerModulo_FiveModuloTwo_IsOne() {
        int a = 5, b = 2;
        int result = a % b;
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("5 / 2 = 2")
    void integerDivision_FiveDividedByTwo_IsTwo() {
        int a = 5, b = 2;
        int result = a / b;
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("(double) 5 / 2 = 2")
    void integerDivision_FiveDividedByTwo_IsStillTwo() {
        int a = 5, b = 2;
        double result = a / b;
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("1.0 * 5 / 2 = 2.5")
    void floatingPointDivision_FiveDividedByTwo_IsTwoPointFive() {
        int a = 5, b = 2;
        double result = 1.0 * a / b;  // double * int = double
        assertThat(result).isEqualTo(2.5);
    }
}
