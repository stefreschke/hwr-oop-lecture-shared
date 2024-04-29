package hwr.oop.examples.imperative.programming;

class ExceptionsExample {

  void throwCheckedException() throws CheckedException {
    throw new CheckedException("This is tracked, notice 'throws' declaration");
  }

  void throwUncheckedException() {
    throw new UncheckedException("This is not tracked!");
  }

  static class CheckedException extends Exception {

    public CheckedException(String message) {
      super(message);
    }
  }

  static class UncheckedException extends RuntimeException {

    public UncheckedException(String message) {
      super(message);
    }
  }
}