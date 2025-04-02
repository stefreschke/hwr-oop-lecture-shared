package hwr.oop.examples

class ByWagonComparator : Comparator<Train> {

  override fun compare(first: Train, second: Train): Int {
    return first.numberOfWagons.compareTo(second.numberOfWagons)
  }

}