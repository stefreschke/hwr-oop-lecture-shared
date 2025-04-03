package hwr.oop.examples

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class RangesTest : AnnotationSpec() {

  @Test
  fun `range from 1 to 10, same as list from 1 to 10`() {
    val range: IntRange = 1..10
    val rangeAsList = range.toList()
    assertThat(rangeAsList)
      .containsSequence(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  }

  @Test
  fun `progression from 10 to 1 backwards, same as list from 10 to 1`() {
    val progression = 10 downTo 1
    val progressionAsList = progression.toList()
    assertThat(progressionAsList)
      .containsSequence(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
  }

  @Test
  fun `progression from 1 to 10 with step 2, same as (1, 3, 5, 7, 9)`() {
    val progression = 1..10 step 2
    val progressionAsList = progression.toList()
    assertThat(progressionAsList)
      .containsSequence(1, 3, 5, 7, 9)
  }

  @Test
  fun `backward progression from 10 to 1 with step 2, works perfectly`() {
    val progression = 10 downTo 1 step 2
    val progressionAsList = progression.toList()
    assertThat(progressionAsList)
      .containsSequence(10, 8, 6, 4, 2)
  }
}