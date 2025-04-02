package hwr.oop.examples.association.composition

class CompositionTrain(numberOfWagons: Int) {

  val wagons: List<ComposedWagon> = List(numberOfWagons) { ComposedWagon() }

  fun numberOfWagons(): Int {
    return wagons.size
  }

}