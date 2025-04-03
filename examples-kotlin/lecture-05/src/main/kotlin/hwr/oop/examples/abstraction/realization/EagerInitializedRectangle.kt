package hwr.oop.examples.abstraction.realization

class EagerInitializedRectangle(
  firstSide: Double,
  secondSide: Double,
) : Rectangle {

  private val perimeter = 2 * (firstSide + secondSide)
  private val area = firstSide * secondSide
  private val angleSet = setOf(90.0)

  override fun areaSize(): Double {
    return area
  }

  override fun perimeterLength(): Double {
    return perimeter
  }

  override fun distinctAngles(): Set<Double> {
    return angleSet
  }

}
