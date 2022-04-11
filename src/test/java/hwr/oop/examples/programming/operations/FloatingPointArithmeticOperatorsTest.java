package hwr.oop.examples.programming.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Floating Point Arithmetic Operators (+-*/%)")
class FloatingPointArithmeticOperatorsTest {

    @Test
    @DisplayName("6.0 + 3.0 = 9.0")
    void floatingPointAddition_SixPlusThree_IsNine() {
        double a = 6.0, b = 3.0;
        double result = a + b;
        assertThat(result).isEqualTo(9.0);
    }

    @Test
    @DisplayName("6.0 - 3.0 = 3.0")
    void floatingPointSubtract_SixMinusThree_IsThree() {
        double a = 6.0, b = 3.0;
        double result = a - b;
        assertThat(result).isEqualTo(3.0);
    }

    @Test
    @DisplayName("6.0 * 3.0 = 18.0")
    void floatingPointMultiplication_SixTimesThree_IsEighteen() {
        double a = 6.0, b = 3.0;
        double result = a * b;
        assertThat(result).isEqualTo(18.0);
    }

    @Test
    @DisplayName("6.0 % 3.0 = 0.0")
    void floatingPointModulo_SixModuloThree_IsZero() {
        double a = 6.0, b = 3.0;
        double result = a % b;
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    @DisplayName("6.0 / 4.0 = 1.5")
    void floatingPointDivision_SixDividedByFour_IsOnePointFive() {
        double a = 6.0, c = 4.0;
        double result = a / c;
        assertThat(result).isEqualTo(1.5);
    }
}
