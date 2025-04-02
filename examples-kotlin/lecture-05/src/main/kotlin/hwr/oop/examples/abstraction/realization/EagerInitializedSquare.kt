package hwr.oop.examples.abstraction.realization

class EagerInitializedSquare(
  private val sideLength: Double,
) : Square {

  private val perimeter = 4 * sideLength
  private val area = sideLength * sideLength

  override fun areaSize(): Double {
    return area
  }

  override fun perimeterLength(): Double {
    return perimeter
  }

  override fun sideLength(): Double = sideLength

}
