package hwr.oop.examples.programming.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Increment/Decrement Operators (++, --)")
class IncrementDecrementTest {

    @Test
    @DisplayName("n++ (n = 1), returns 1, increments n to 2")
    void plusPlus_OneSuffix_VariableIncrementedAfter() {
        int number = 1;
        int suffixResult = number++;
        assertThat(number).isEqualTo(2);
        assertThat(suffixResult).isEqualTo(1);
    }

    @Test
    @DisplayName("++n (n = 1), increments n to 2, returns 2")
    void plusPlus_OnePrefix_VariableIncrementedBefore() {
        int number = 1;
        int prefixResult = ++number;
        assertThat(number).isEqualTo(2);
        assertThat(prefixResult).isEqualTo(2);
    }

    @Test
    @DisplayName("practice: n++/++n (n = 1), increments n to 2")
    void plusPlus_BestPractice_UsedSeparately() {
        int number = 1;
        number++;  // or ++number, does not matter
        assertThat(number).isEqualTo(2);
    }

    @Test
    @DisplayName("n-- (n = 1), returns 1, decrements n to 0")
    void minusMinus_OneSuffix_VariableDecrementedAfter() {
        int number = 1;
        int suffixResult = number--;
        assertThat(number).isEqualTo(0);
        assertThat(suffixResult).isEqualTo(1);
    }

    @Test
    @DisplayName("--n (n = 1), decrements n to 0, returns 0")
    void minusMinus_OnePrefix_VariableDecrementedBefore() {
        int number = 1;
        int prefixResult = --number;
        assertThat(number).isEqualTo(0);
        assertThat(prefixResult).isEqualTo(0);
    }

    @Test
    @DisplayName("practice: n--/--n (n = 1), decrements n to 0")
    void minusMinus_BestPractice_UsedSeparately() {
        int number = 1;
        number--;  // or --number, does not matter
        assertThat(number).isEqualTo(0);
    }
}
