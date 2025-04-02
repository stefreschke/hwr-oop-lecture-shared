package hwr.oop.examples.abstraction.realization

interface Rectangle : Quadrangle {

  override fun distinctAngles(): Set<Double> {
    return setOf(90.0)
  }

}