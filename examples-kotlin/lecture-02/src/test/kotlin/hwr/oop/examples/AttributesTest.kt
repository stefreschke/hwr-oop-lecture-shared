package hwr.oop.examples

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class AttributesTest : AnnotationSpec() {

  @Test
  fun `Train with train number 1338, has train number 1338`() {
    val train = TrainWithMultipleProperties(
      trainNumber = 1338,
      isElectrified = true
    )
    val trainNumber = train.trainNumber()
    assertThat(trainNumber).isEqualTo(1338)
  }

  @Test
  fun `Train with train number 420, has train number 420`() {
    val train = TrainWithMultipleProperties(
      trainNumber = 420,
      isElectrified = true
    )
    val trainNumber = train.trainNumber()
    assertThat(trainNumber).isEqualTo(420)
  }

  @Test
  fun `Train electrified, is electrified`() {
    val train = TrainWithMultipleProperties(
      trainNumber = 123,
      isElectrified = true
    )
    val isElectrified = train.isElectrified()
    assertThat(isElectrified).isTrue()
  }

  @Test
  fun `Train not electrified, is not electrified`() {
    val train = TrainWithMultipleProperties(
      trainNumber = 123,
      isElectrified = false
    )
    val isElectrified = train.isElectrified()
    assertThat(isElectrified).isFalse()
  }
}