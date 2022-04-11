package hwr.oop.examples.imperative.programming;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VariablesTest {
    @Test
    void variable_IntVariable_CanBeCreated() {
        int intVar = 1;
        assertThat(intVar).isEqualTo(1);
    }

    @Test
    void nonFinalVariable_IntVariable_CanBeReassigned() {
        int intVar = 1;
        intVar = 2;
        assertThat(intVar).isEqualTo(2);
    }

    @Test
    void finalVariable_IntVariable_CantBeReassigned() {
        final int finalIntVar = 1;
        // finalIntVar = 2;  // compiler does not like this
        assertThat(finalIntVar).isNotEqualTo(2);
    }

    @Test
    void variable_StringVariable_CanBeCreated() {
        String stringVar = "42";
        assertThat(stringVar).isEqualTo("42").isNotEqualTo("1337");
    }

    @Test
    void nonFinalVariable_StringVariable_CanBeReassigned() {
        String stringVar = "42";
        stringVar = "1337";
        assertThat(stringVar).isEqualTo("1337");
    }

    @Test
    void finalVariable_StringVariable_CantBeReassigned() {
        final String stringVar = "42";
        // stringVar = "1337";  // compiler does not like this
        assertThat(stringVar).isNotEqualTo("1337");
    }
}
