import hwr.oop.examples.abstraction.realization.EagerInitializedRectangle
import hwr.oop.examples.abstraction.realization.EagerInitializedSquare
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class RealizationTest : AnnotationSpec() {

  @Test
  fun `rectangles, 4 sides`() {
    val sut = EagerInitializedRectangle(420.0, 1337.0)
    val sides = sut.numberOfSides()
    assertThat(sides).isEqualTo(4)
  }

  @Test
  fun `rectangles, distinct angles contains only 90deg`() {
    val sut = EagerInitializedRectangle(420.0, 1337.0)
    val angles = sut.distinctAngles()
    assertThat(angles)
      .containsOnlyOnce(90.0)
      .hasSize(1)
  }

  @Test
  fun `rectangles, sides 2 and 3, area 6`() {
    val sut = EagerInitializedRectangle(2.0, 3.0)
    val areaSize = sut.areaSize()
    assertThat(areaSize).isEqualTo(6.0)
  }

  @Test
  fun `rectangles, sides 4 and 5, area 20`() {
    val sut = EagerInitializedRectangle(4.0, 5.0)
    val areaSize = sut.areaSize()
    assertThat(areaSize).isEqualTo(20.0)
  }

  @Test
  fun `rectangles, sides 2 and 3, perimeter 10`() {
    val sut = EagerInitializedRectangle(2.0, 3.0)
    val perimeter = sut.perimeterLength()
    assertThat(perimeter).isEqualTo(10.0)
  }

  @Test
  fun `rectangles, sides 4 and 5, perimeter 18`() {
    val sut = EagerInitializedRectangle(4.0, 5.0)
    val perimeter = sut.perimeterLength()
    assertThat(perimeter).isEqualTo(18.0)
  }

  @Test
  fun `squares, 4 sides`() {
    val sut = EagerInitializedSquare(420.0)
    val sides = sut.numberOfSides()
    assertThat(sides).isEqualTo(4)
  }

  @Test
  fun `squares, distinct angles, contains only 90deg`() {
    val sut = EagerInitializedSquare(420.0)
    val angles = sut.distinctAngles()
    assertThat(angles)
      .containsOnlyOnce(90.0)
      .hasSize(1)
  }

  @Test
  fun `squares, side 2, side length is 2`() {
    val sut = EagerInitializedSquare(2.0)
    val sideLength = sut.sideLength()
    assertThat(sideLength).isEqualTo(2.0)
  }

  @Test
  fun `squares, side 5, side length is 5`() {
    val sut = EagerInitializedSquare(5.0)
    val sideLength = sut.sideLength()
    assertThat(sideLength).isEqualTo(5.0)
  }

  @Test
  fun `squares, side 2, area 4`() {
    val sut = EagerInitializedSquare(2.0)
    val areaSize = sut.areaSize()
    assertThat(areaSize).isEqualTo(4.0)
  }

  @Test
  fun `squares, side 3, area 9`() {
    val sut = EagerInitializedSquare(3.0)
    val areaSize = sut.areaSize()
    assertThat(areaSize).isEqualTo(9.0)
  }

  @Test
  fun `squares, side 2, perimeter 8`() {
    val sut = EagerInitializedSquare(2.0)
    val perimeter = sut.perimeterLength()
    assertThat(perimeter).isEqualTo(8.0)
  }

  @Test
  fun `squares, side 3, perimeter 12`() {
    val sut = EagerInitializedSquare(3.0)
    val perimeter = sut.perimeterLength()
    assertThat(perimeter).isEqualTo(12.0)
  }

  @Test
  fun `4x5 rectangle, 5er square, rectangle is smaller`() {
    val rectangle = EagerInitializedRectangle(4.0, 5.0)
    val square = EagerInitializedSquare(5.0)
    val comparison = rectangle.compareTo(square)
    assertThat(comparison).isNegative().isNotZero()
  }

  @Test
  fun `4x5 rectangle, 5er square, square is bigger`() {
    val rectangle = EagerInitializedRectangle(4.0, 5.0)
    val square = EagerInitializedSquare(5.0)
    val comparison = square.compareTo(rectangle)
    assertThat(comparison).isPositive().isNotZero()
  }

  @Test
  fun `6x5 rectangle, 5er square, rectangle is bigger`() {
    val rectangle = EagerInitializedRectangle(6.0, 5.0)
    val square = EagerInitializedSquare(5.0)
    val comparison = rectangle.compareTo(square)
    assertThat(comparison).isPositive().isNotZero()
  }

  @Test
  fun `6x5 rectangle, 5er square, square is smaller`() {
    val rectangle = EagerInitializedRectangle(6.0, 5.0)
    val square = EagerInitializedSquare(5.0)
    val comparison = square.compareTo(rectangle)
    assertThat(comparison).isNegative().isNotZero()
  }

}