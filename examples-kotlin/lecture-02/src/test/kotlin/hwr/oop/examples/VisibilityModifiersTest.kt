package hwr.oop.examples

import hwr.oop.examples.visibilitymodifiers.RegionalTrain
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class VisibilityModifiersTest {
  @Test
  fun `Regional Train can start Engine in super class`() {
    val regionalTrain = RegionalTrain()
    regionalTrain.accelerateTo(100)
    assertThat(regionalTrain.isEngineStarted()).isTrue()
  }
}