package hwr.oop.examples

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class TypeCheckingWithIsTest : AnnotationSpec() {

  @Test
  fun `literal 1, is Int, but no long`() {
    val number: Any = 1
    val isInt = number is Int
    val isLong = number is Long
    assertThat(isInt).isTrue()
    assertThat(isLong).isFalse()
  }

  @Test
  fun `literal 1, is no Long`() {
    val number: Any = 1
    val isNoLong = number !is Long
    assertThat(isNoLong).isTrue()
  }

  @Test
  fun `literal 1L, is Long, but no Int`() {
    val number: Any = 1L
    val isInt = number is Int
    val isLong = number is Long
    assertThat(isInt).isFalse()
    assertThat(isLong).isTrue()
  }



}