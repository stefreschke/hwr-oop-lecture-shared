package hwr.oop.examples

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TrainIdentityTest {

  @Test
  fun `two objects are never identical`() {
    // given
    val first = Train("RE1", "Berlin", "Magdeburg")
    val second = Train("RE1", "Berlin", "Magdeburg")
    // when
    val identifyResponse = first === second
    // then
    assertThat(identifyResponse).isFalse()
  }

  @Test
  fun `two objects are never identical (AssertJ)`() {
    // given
    val first = Train("RE1", "Berlin", "Magdeburg")
    val second = Train("RE1", "Berlin", "Magdeburg")
    // then
    assertThat(first).isNotSameAs(second)
  }

  @Test
  fun `two objects are never identical (Kotest)`() {
    // given
    val first = Train("RE1", "Berlin", "Magdeburg")
    val second = Train("RE1", "Berlin", "Magdeburg")
    // then
    assertThat(first).isNotSameAs(second)
  }

  @Test
  fun `single objects has identity`() {
    // given
    val first = Train("RE1", "Berlin", "Magdeburg")
    val second = first
    // when
    val identifyResponse = first === second
    // then
    assertThat(identifyResponse).isTrue()
  }

  @Test
  fun `single objects has identity (AssertJ)`() {
    // given
    val first = Train("RE1", "Berlin", "Magdeburg")
    val second = first
    // then
    assertThat(first).isSameAs(second)
  }

  @Test
  fun `single objects has identity (Kotest)`() {
    // given
    val first = Train("RE1", "Berlin", "Magdeburg")
    val second = first
    // then
    assertThat(first).isSameAs(second)
  }

}