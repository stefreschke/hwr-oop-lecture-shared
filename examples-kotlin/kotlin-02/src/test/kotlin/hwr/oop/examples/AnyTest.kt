package hwr.oop.examples

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class AnyTest : AnnotationSpec() {

  @Test
  fun `any Any has the toString method`() {
    // given
    val any: Any = 1
    // when
    val result = any.toString()
    // then
    assertThat(result).isEqualTo("1")
  }

  @Test
  fun `any Any has the equals method`() {
    // given
    val any: Any = 1
    val other: Any = 2
    // when
    val result = any.equals(other)
    val equivalent = any == other  // == operator calls equals method
    // then
    assertThat(result)
      .isEqualTo(equivalent)
      .isFalse
  }

  @Test
  fun `any Any has the hashCode method`() {
    // given
    val any: Any = "some string"
    // when
    val result = any.hashCode()
    // then
    assertThat(result).isNotZero
  }
}
