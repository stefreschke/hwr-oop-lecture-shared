package hwr.oop.examples.programming.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Bitwise Operators (>>, <<, &, |, ^)")
class BitwiseOperatorsTest {

    @Test
    @DisplayName("4 >> 1 = 2")
    void rightShift_FourByOne_IsTwo() {
        int rightShiftResult = 4 >> 1;
        assertThat(rightShiftResult).isEqualTo(2);
    }

    @Test
    @DisplayName("4 << 1 = 8")
    void leftShift_FourByOne_IsEight() {
        int leftShiftResult = 4 << 1;
        assertThat(leftShiftResult).isEqualTo(8);
    }

    @Test
    @DisplayName("4 & 15 = 4")
    void bitwiseAnd_FourAndFifteen_IsFour() {
        int andResult = 4 & 15;
        assertThat(andResult).isEqualTo(4);
    }

    @Test
    @DisplayName("4 | 15 = 15")
    void bitwiseOr_FourOrFifteen_IsFifteen() {
        int orResult = 4 | 15;
        assertThat(orResult).isEqualTo(15);
    }

    @Test
    @DisplayName("4 ^ 15 = 11")
    void bitwiseXor_FourXorFifteen_IsEleven() {
        int xorResult = 4 ^ 15;
        assertThat(xorResult).isEqualTo(11);
    }

}
