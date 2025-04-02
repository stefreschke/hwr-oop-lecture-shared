package hwr.oop.examples.classes.comparability;

class Train implements Comparable<Train> {

  private final int numberOfRailCars;
  private int speed;

  public Train(int numberOfRailCars) {
    this.numberOfRailCars = numberOfRailCars;
    this.speed = 0;
  }

  @Override
  public int compareTo(Train o) {
    return Integer.compare(this.speed, o.speed);
  }

  void accelerateTo(int speed) {
    this.speed = speed;
  }

  int getRailCars() {
    return numberOfRailCars;
  }
}
