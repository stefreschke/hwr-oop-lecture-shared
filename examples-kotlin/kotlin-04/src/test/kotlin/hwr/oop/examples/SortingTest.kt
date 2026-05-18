package hwr.oop.examples

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class SortingTest : AnnotationSpec() {

  @Test
  fun `sorting`() {
    // given
    val unsorted = listOf(3, 1, 4, 1, 5, 9, 2, 6)
    // when
    val result = unsorted.sorted()
    // then
    assertThat(result).isSorted
  }
}