package hwr.oop.examples

import hwr.oop.examples.data_classes.TrainAsDataClass
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class TrainDataClassTest : AnnotationSpec() {

  @Test
  fun `equal trains, not identical, are equal, same hashCode`() {
    // given
    val first = TrainAsDataClass("RE1", "Berlin", "Magdeburg")
    val second = TrainAsDataClass("RE1", "Berlin", "Magdeburg")
    // then
    assertThat(first).isNotSameAs(second)
    assertThat(first).isEqualTo(second)
    assertThat(first).hasSameHashCodeAs(second)
  }

  @Test
  fun `non-equal trains, not identical, not equal, different hashCode`() {
    // given
    val first = TrainAsDataClass("RE1", "Berlin", "Magdeburg")
    val second = TrainAsDataClass("RE2", "Berlin", "Wismar")
    // then
    assertThat(first).isNotSameAs(second)
    assertThat(first).isNotEqualTo(second)
    assertThat(first.hashCode()).isNotEqualTo(second.hashCode())
  }

}