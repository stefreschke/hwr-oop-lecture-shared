package hwr.oop.huzur.persistence;

import java.util.function.BinaryOperator;

final class SeparatedBy {

  private SeparatedBy() {
    // nothing to do here
  }

  public static BinaryOperator<String> underscore() {
    return (a, b) -> a + "_" + b;
  }

  public static BinaryOperator<String> comma() {
    return (a, b) -> a + "," + b;
  }

  public static BinaryOperator<String> colon() {
    return (a, b) -> a + ":" + b;
  }

}
