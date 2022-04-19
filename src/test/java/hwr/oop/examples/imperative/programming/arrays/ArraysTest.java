package hwr.oop.examples.imperative.programming.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@DisplayName("Arrays, e.g. int[]{...}")
class ArraysTest {

    @Test
    @DisplayName("mutability: i[0] overridden with 42, i[0] is 42")
    void arrayGetElement_SetFirstIntToBeTwo_FirstIntIsTwo() {
        final int[] array = {1};
        array[0] = 42;
        assertThat(array[0]).isEqualTo(42).isNotEqualTo(1);
    }

    @Test
    @DisplayName("[]-selection: i=[42,1337], i[0] is 42 and i[1] is 1337")
    void arrayGetElement_OneAndTwo_OneAtIndexZeroTwoAtIndexOne() {
        int[] array = new int[]{42, 1337};
        int firstElement = array[0];
        int secondElement = array[1];
        assertThat(firstElement).isEqualTo(42);
        assertThat(secondElement).isEqualTo(1337);
    }

    @Test
    @DisplayName("[]-selection: request index 1 from [1], throws Exception")
    void arrayGetElement_One_RequestingIndexOneThrowsException() {
        try {
            int[] array = new int[]{1};
            int meh = array[1];
            fail("should not be accessible -> " + meh);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("length: length of [1], is 1")
    void length_One_IsOne() {
        int[] array = new int[1];
        int length = array.length;
        assertThat(length).isEqualTo(1);
    }

    @Test
    @DisplayName("length: length of new int[]{42} is 1")
    void arrayLength_ArrayWith42InCurlyBrackets_LengthOneFirstElementIs42() {
        int[] array = new int[]{42};
        int length = array.length;
        assertThat(length).isEqualTo(1);
        assertThat(array[0]).isEqualTo(42);
    }

    @Test
    @DisplayName("length: length of new int[42] is 42")
    void arrayLength_ArrayWithLengthOf42_LengthOneFirstElementIsZero() {
        int[] array = new int[42];
        int length = array.length;
        assertThat(length).isEqualTo(42);
        assertThat(array).containsOnly(0);
    }

    @Test
    @DisplayName("array equality: [1] and [1], seem to be equal")
    void arrayEquality_TwoEqualIntegerArrays_AreEqualOnFirstSight() {
        int[] first = {1};
        int[] second = {1};
        assertThat(first).isEqualTo(second);
    }

    @Test
    @DisplayName("#equals: [1] and [1], is false (which is unintuitive)")
    void equals_TwoEqualArrays_IsFalseAndThusDumb() {
        int[] first = {1};
        int[] second = {1};
        boolean plainEquals = first.equals(second);
        assertThat(plainEquals).isFalse();  // duh...
    }

    @Test
    @DisplayName("#hashCode: [1] and [1], is not equal (which is unintuitive)")
    void hashCode_TwoEqualArrays_IsNotEqualAndThusFunky() {
        int[] first = {1};
        int[] second = {1};
        int firstHashCode = first.hashCode();
        int secondHashCode = second.hashCode();
        assertThat(firstHashCode)
                .isNotEqualTo(secondHashCode);  // duh...
    }
}
