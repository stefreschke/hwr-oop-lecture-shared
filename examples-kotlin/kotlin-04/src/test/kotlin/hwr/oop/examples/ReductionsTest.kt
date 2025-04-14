package hwr.oop.examples

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class ReductionsTest : AnnotationSpec() {

  @Test
  fun `1 to 5, first, selects 1`() {
    // given
    val list = listOf(1, 2, 3, 4, 5)
    // when
    val result = list.first()
    // then
    assertThat(result).isEqualTo(1)
  }

  @Test
  fun `1 to 5, first even, selects 2`() {
    // given
    val list = listOf(1, 2, 3, 4, 5)
    // when
    val result = list.first { it % 2 == 0 }
    // then
    assertThat(result).isEqualTo(2)
  }

  @Test
  fun `1 to 5, last, selects 5`() {
    // given
    val list = listOf(1, 2, 3, 4, 5)
    // when
    val result = list.last()
    // then
    assertThat(result).isEqualTo(5)
  }

  @Test
  fun `1 to 5, last even, selects 4`() {
    // given
    val list = listOf(1, 2, 3, 4, 5)
    // when
    val result = list.last { it % 2 == 0 }
    // then
    assertThat(result).isEqualTo(4)
  }

  @Test
  fun `reduce, can be used to sum`() {
    // given
    val listOfNumbers = listOf(1, 2, 5, 6, 8, 9)
    // when
    val sum = listOfNumbers.reduce { a, b -> a + b }
    // then
    assertThat(sum).isEqualTo(31)
  }

  @Test
  fun `sum, is shorter than writing reduce`() {
    // given
    val listOfNumbers = listOf(1, 2, 5, 6, 8, 9)
    // when
    val sum = listOfNumbers.sum()
    // then
    assertThat(sum).isEqualTo(31)
  }
}