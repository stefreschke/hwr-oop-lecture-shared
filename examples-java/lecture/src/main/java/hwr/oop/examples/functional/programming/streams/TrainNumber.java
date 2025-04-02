package hwr.oop.examples.functional.programming.streams;

record TrainNumber(
    String value
) {

  TrainNumber {
    try {
      Long.parseLong(value);
    } catch (NumberFormatException e) {
      throw new InvalidTrainNumberException(value);
    }
  }

  public static TrainNumber of(String value) {
    return new TrainNumber(value);
  }

  public boolean isOver9000() {
    return Long.parseLong(value) > 9000L;
  }

  static class InvalidTrainNumberException extends RuntimeException {

    public InvalidTrainNumberException(String trainNumber) {
      super("Invalid train number: " + trainNumber);
    }
  }
}
