package hwr.oop.examples.imperative.programming;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class ExceptionsTest {

    @Test
    void exceptions_DivisionByZero_ThrowsException() {
        try {
            int that = 42 / 0;
            fail("should not be able to reach -> " + that);
        } catch (Exception e) {  // catch all exceptions
            e.printStackTrace();
        }
    }

    @Test
    void exceptions_DivisionByZero_CanAssertOnTypes() {
        try {
            int that = 42 / 0;
            fail("should not be able to reach -> " + that);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class);
            assertThat(e).isInstanceOf(ArithmeticException.class);
        }
    }

    @Test
    void exceptions_DivisionByZero_CatchOnlySpecificException() {
        try {
            int that = 42 / 0;
            fail("should not be able to reach -> " + that);
        } catch (ArithmeticException e) {  // catch only this exception
            e.printStackTrace();
        }
    }

    @Test
    void exceptions_MultipleExceptions_SeparateCatchBlocksForEach() {
        int a[] = new int[5];
        try {
            a[5] = 30 / 0;
            fail("unreachable for two reasons");
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }

    @Test
    void exceptions_MultipleExceptions_CollapsedBlocksPossible() {
        int a[] = new int[5];
        try {
            a[5] = 30 / 0;
            fail("unreachable for two reasons");
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException e) {
            e.printStackTrace();
        }
    }

    @Test
    void divisionByZero_tryCatchFinally_finallyExecutedIfCatch() {
        boolean flag = false;
        try {
            int that = 42 / 0;
            fail("should not be able to reach -> " + that);
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } finally {
            flag = true;
        }
        assertThat(flag).isTrue();
    }

    @Test
    void divisionByZero_tryCatchFinally_finallyAlwaysExecuted() {
        boolean flag = false;
        try {
            // no exception
            int iNoBreakStuff = 42 & 42;
        } catch (ArithmeticException e) {
            fail("should not be thrown -> " + e);
        } finally {
            flag = true;
        }
        assertThat(flag).isTrue();
    }

    @Test
    void exceptionExample_ThrowsCheckedException_CanBeCaught() {
        ExceptionsExample example = new ExceptionsExample();
        fail("Test not yet implemented");
    }

    @Test
    void exceptionExample_ThrowsUncheckedException_CanBeCaught() {
        ExceptionsExample example = new ExceptionsExample();
        fail("Test not yet implemented");
    }

}
