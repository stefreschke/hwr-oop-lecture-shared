package hwr.oop.examples

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class QueriesTest : AnnotationSpec() {
  @Test
  fun `any, true if at least one element satisfies the predicate`() {
    // given
    val listOfNumbers = listOf(1, 2, 5, 6, 8, 9)
    // when
    val someElementsGreaterThan5 = listOfNumbers.any { it > 5 }
    // then
    assertThat(someElementsGreaterThan5).isTrue()
  }

  @Test
  fun `all, true if all elements satisfy predicate`() {
    // given
    val listOfNumbers = listOf(1, 2, 5, 6, 8, 9)
    // when
    val someElementsGreaterThan5 = listOfNumbers.all { it > 5 }
    // then
    assertThat(someElementsGreaterThan5).isFalse()
  }
}