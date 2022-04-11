package hwr.oop.examples.imperative.programming;

class ExceptionsExample {

    void throwCheckedException() throws Exception {
        throw new Exception("This is tracked, notice 'throws' declaration");
    }

    void throwUncheckedException() {
        throw new RuntimeException("This is not tracked!");
    }
}