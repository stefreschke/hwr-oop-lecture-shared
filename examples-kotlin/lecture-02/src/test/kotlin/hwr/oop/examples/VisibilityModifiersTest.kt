package hwr.oop.examples

import hwr.oop.examples.visibilitymodifiers.RegionalTrain
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class VisibilityModifiersTest : AnnotationSpec() {
  @Test
  fun `Regional Train can start Engine in super class`() {
    val regionalTrain = RegionalTrain()
    regionalTrain.accelerateTo(100)
    assertThat(regionalTrain.isEngineStarted()).isTrue()
  }
}