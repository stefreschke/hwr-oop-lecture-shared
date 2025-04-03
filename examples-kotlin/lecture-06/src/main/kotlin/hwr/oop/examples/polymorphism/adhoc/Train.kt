package hwr.oop.examples.polymorphism.adhoc

class Train(
  private val wagons: List<Wagon>,
) {
  constructor(vararg wagons: Wagon) : this(
    wagons.toList()
  )

//  constructor(wagons: Array<Wagon>): this(
//    wagons.toList()
//  )

  fun numberOfWagons(): Int {
    return wagons.size
  }
}
