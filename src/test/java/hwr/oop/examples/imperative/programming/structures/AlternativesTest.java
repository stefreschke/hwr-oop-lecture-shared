package hwr.oop.examples.imperative.programming.structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class AlternativesTest {

    @Test
    void if_ConditionFalse_ThenBlockNotExecuted() {
        boolean condition = false;
        if (condition) {
            fail();
        }
    }

    @Test
    void if_ConditionTrue_ThenBlockIsExecuted() {
        boolean condition = true;
        if (condition) {
            return;  // void return, abort test without failure
        }
        fail();
    }

    @Test
    void if_ConditionTrueThenBlockOnlyOneStatement_BracketsNotNecessary() {
        boolean condition = true;
        if (condition)
            return;  // no brackets, next statement not part of then-block
        fail();
    }

    @Test
    void ifElse_ConditionTrue_OnlyThenBlockExecuted() {
        boolean condition = true;
        if (condition) {
            // no fail
        } else {
            fail();
        }
    }

    @Test
    void ifElse_ConditionTrueElseBlockOnlyOneStatement_BracketsNotNecessary() {
        boolean condition = true;
        if (condition) {
            // no fail
        } else
            fail();  // no brackets, next statement not part of else-block
    }

    @Test
    void ifElse_ConditionFalse_OnlyElseBlockExecuted() {
        boolean condition = false;
        if (condition) {
            fail();
        } else {
            // no fail
        }
    }

    @Test
    void ifElseIfElse_ElseIfConditionIsTrue_OnlyElseIfThenExecuted() {
        int number = 2;
        if (number == 1) {
            fail();
        } else if (number == 2) {
            // no fail
        } else {
            fail();
        }
    }
}
