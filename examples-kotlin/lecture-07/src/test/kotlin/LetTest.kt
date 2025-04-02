import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class LetTest : AnnotationSpec() {

  @Test
  fun `1? + 2? = 3?`() {
    val first: Int? = 1
    val second: Int? = 2
    val sum = sut_sum(first, second)
    sum shouldBe 3
    sum shouldNotBe null
  }
}

private fun sut_sum(first: Int?, second: Int?): Int? {
  return second?.let { first?.plus(it) }
}