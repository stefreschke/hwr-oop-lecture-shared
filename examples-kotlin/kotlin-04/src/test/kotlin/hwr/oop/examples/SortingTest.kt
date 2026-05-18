package hwr.oop.examples

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SortingTest {

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