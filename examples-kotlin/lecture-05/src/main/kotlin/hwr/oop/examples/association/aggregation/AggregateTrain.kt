package hwr.oop.examples.association.aggregation

class AggregateTrain(
  private val wagons: List<AggregatedWagon>,
) {

  fun numberOfWagons(): Int {
    return wagons.size
  }
}