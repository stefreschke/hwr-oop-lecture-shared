package hwr.oop.examples.abstraction.inheritance

import hwr.oop.examples.abstraction.realization.Rectangle

open class RectangleAsOpenClass(
  private val height: Double,
  private val width: Double,
) : Rectangle {

  override fun areaSize(): Double {
    return height * width
  }

  override fun perimeterLength(): Double {
    return 2 * (height + width)
  }

}
