package hwr.oop.examples.classes.comparability;

import java.util.Comparator;

class RailCarComparator implements Comparator<Train> {

  @Override
  public int compare(Train o1, Train o2) {
    return Integer.compare(o1.getRailCars(), o2.getRailCars());
  }
}
