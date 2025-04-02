package hwr.oop.examples.functional.programming.streams;

record WagonNumber(
    String value
) {

  public static WagonNumber of(String value) {
    return new WagonNumber(value);
  }
}
