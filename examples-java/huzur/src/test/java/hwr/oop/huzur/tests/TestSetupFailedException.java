package hwr.oop.huzur.tests;

/**
 * For catch-and-rethrow in test scenario setUps and tearDowns.
 */
public class TestSetupFailedException extends RuntimeException {

  private TestSetupFailedException(Throwable cause) {
    super(cause);
  }

  public static TestSetupFailedException wrapping(Throwable cause) {
    return new TestSetupFailedException(cause);
  }
}
