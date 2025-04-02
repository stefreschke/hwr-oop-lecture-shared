package hwr.oop.examples.abstraction.realization

interface Quadrangle : Shape, Comparable<Quadrangle> {

  override fun numberOfSides(): Int = 4

  fun distinctAngles(): Set<Double>

  override fun compareTo(other: Quadrangle): Int {
    return areaSize().compareTo(other.areaSize())
  }

}
