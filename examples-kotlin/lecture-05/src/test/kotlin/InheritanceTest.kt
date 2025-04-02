import hwr.oop.examples.abstraction.inheritance.RectangleAsOpenClass
import hwr.oop.examples.abstraction.inheritance.SquareInheritingFromRectangle
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class InheritanceTest : AnnotationSpec() {

  @Test
  fun `squares are also rectangles`() {
    // given
    val square = SquareInheritingFromRectangle(1.0)
    // then
    assertThat(square)
      .isInstanceOf(RectangleAsOpenClass::class.java)
  }

  @Test
  fun `rectangles are no squares`() {
    // given
    val rectangle = RectangleAsOpenClass(1.0, 2.0)
    // then
    assertThat(rectangle)
      .isInstanceOf(SquareInheritingFromRectangle::class.java)
  }

  @Test
  fun `squares, side length 2, perimeter 8`() {
    // given
    val square = SquareInheritingFromRectangle(2.0)
    // when
    val perimeter = square.perimeterLength()
    // then
    assertThat(perimeter).isEqualTo(8.0)
  }

  @Test
  fun `squares, side length 3, perimeter 12`() {
    // given
    val square = SquareInheritingFromRectangle(3.0)
    // when
    val perimeter = square.perimeterLength()
    // then
    assertThat(perimeter).isEqualTo(12.0)
  }

  @Test
  fun `squares, side length 2, area 4`() {
    // given
    val square = SquareInheritingFromRectangle(2.0)
    // when
    val area = square.areaSize()
    // then
    assertThat(area).isEqualTo(4.0)
  }

  @Test
  fun `squares, side length 3, area 9`() {
    // given
    val square = SquareInheritingFromRectangle(3.0)
    // when
    val area = square.areaSize()
    // then
    assertThat(area).isEqualTo(9.0)
  }


}