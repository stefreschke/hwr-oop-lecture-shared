package hwr.oop.examples

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class TypeCheckingWithIsTest : AnnotationSpec() {

  @Test
  fun `literal 1, is Int, but no long`() {
    val number: Any = 1
    val numberIsInt = number is Int
    val numberIsLong = number is Long
    assertThat(numberIsInt).isTrue()
    assertThat(numberIsLong).isFalse()
  }

  @Test
  fun `literal 1, is no Long`() {
    val number: Any = 1
    val numberIsNoLong = number !is Long
    assertThat(numberIsNoLong).isTrue()
  }

  @Test
  fun `literal 1L, is Long, but no Int`() {
    val number: Any = 1L
    val numberIsInt = number is Int
    val numberIsLong = number is Long
    assertThat(numberIsInt).isFalse()
    assertThat(numberIsLong).isTrue()
  }


}