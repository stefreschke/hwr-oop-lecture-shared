package hwr.oop.examples.polymorphism.adhoc

class NaiveTrain(
  private val wagons: List<Wagon>,
) {

  constructor() : this(emptyList())

  constructor(wagon: Wagon) : this(
    listOf(wagon)
  )

  constructor(first: Wagon, second: Wagon) : this(
    listOf(first, second)
  )

  constructor(first: Wagon, second: Wagon, third: Wagon) : this(
    listOf(first, second, third)
  )

  fun numberOfWagons(): Int {
    return wagons.size
  }
}
