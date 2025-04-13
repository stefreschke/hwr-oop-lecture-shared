package hwr.oop.examples

import io.kotest.assertions.fail
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThatThrownBy

class NothingTest : AnnotationSpec() {

  @Test
  fun `any expression can be stored to variable typed as Nothing`() {
    assertThatThrownBy {
      val throwResult: Nothing = throw Exception("test")
    }.hasMessageContaining("test")
  }

  @Test
  fun `kotest's fail assertion returns Nothing`() {
    assertThatThrownBy {
      val fail: Nothing = fail("test")
      // returns Nothing, because "fail always fails"
    }.hasMessageContaining("test")
  }

}