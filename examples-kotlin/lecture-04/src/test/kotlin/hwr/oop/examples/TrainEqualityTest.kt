package hwr.oop.examples

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldHaveSameHashCodeAs
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import org.assertj.core.api.Assertions.assertThat

class TrainEqualityTest : AnnotationSpec() {

  @Test
  fun `equal REs, are not always identical`() {
    // given
    val first = Train("RE1", "Berlin", "Magdeburg")
    val second = Train("RE1", "Berlin", "Magdeburg")
    // when
    val identifyResponse = first === second
    // then
    assertThat(identifyResponse).isFalse()
  }

  @Test
  fun `equal REs, are not always identical (with specific AssertJ assertion)`() {
    // given
    val first = Train("RE1", "Berlin", "Magdeburg")
    val second = Train("RE1", "Berlin", "Magdeburg")
    // then
    assertThat(first).isNotSameAs(second)
  }

  @Test
  fun `equal REs, are not always identical (with Kotest assertion)`() {
    // given
    val first = Train("RE1", "Berlin", "Magdeburg")
    val second = Train("RE1", "Berlin", "Magdeburg")
    // then
    first shouldNotBeSameInstanceAs second
  }

  @Test
  fun `equal REs (but not identical), are equal`() {
    // given
    val first = Train("RE1", "Berlin", "Magdeburg")
    val second = Train("RE1", "Berlin", "Magdeburg")
    // when
    val equalsResponse = first == second
    val directlyCallingEquals = first.equals(second)
    // then
    assertThat(equalsResponse).isTrue()
    assertThat(equalsResponse).isEqualTo(directlyCallingEquals)
  }

  @Test
  fun `equal REs (but not identical), have same hashCode`() {
    // given
    val first = Train("RE1", "Berlin", "Magdeburg")
    val second = Train("RE1", "Berlin", "Magdeburg")
    // when
    val firstHash = first.hashCode()
    val secondHash = second.hashCode()
    // then
    assertThat(firstHash).isEqualTo(secondHash)
  }

  @Test
  fun `equal REs (but not identical), are equal (with specific AssertJ assertion)`() {
    // given
    val first = Train("RE1", "Berlin", "Magdeburg")
    val second = Train("RE1", "Berlin", "Magdeburg")
    // then
    assertThat(first).isEqualTo(second)
  }

  @Test
  fun `equal REs (but not identical), have same hashCode (with specific AssertJ assertion)`() {
    // given
    val first = Train("RE1", "Berlin", "Magdeburg")
    val second = Train("RE1", "Berlin", "Magdeburg")
    // then
    assertThat(first).hasSameHashCodeAs(second)
  }

  @Test
  fun `equal REs (but not identical), are equal (with Kotest assertion)`() {
    // given
    val first = Train("RE1", "Berlin", "Magdeburg")
    val second = Train("RE1", "Berlin", "Magdeburg")
    // then
    first shouldBe second
  }

  @Test
  fun `equal REs (but not identical), have same hashCode (with Kotest assertion)`() {
    // given
    val first = Train("RE1", "Berlin", "Magdeburg")
    val second = Train("RE1", "Berlin", "Magdeburg")
    // then
    first shouldHaveSameHashCodeAs second
  }

  @Test
  fun `different REs, not equal`() {
    // given
    val first = Train("RE1", "Berlin", "Magdeburg")
    val second = Train("RE8", "Berlin", "Wismar")
    // when
    val equalsResponse = first == second
    val directlyCallingEquals = first.equals(second)
    // then
    assertThat(equalsResponse).isFalse()
    assertThat(equalsResponse).isEqualTo(directlyCallingEquals)
  }

  @Test
  fun `different REs, not equal (with Kotest assertion)`() {
    // given
    val first = Train("RE1", "Berlin", "Magdeburg")
    val second = Train("RE8", "Berlin", "Wismar")
    // then
    first shouldNotBe second
  }

  @Test
  fun `different REs, not equal (with specific AssertJ assertion)`() {
    // given
    val first = Train("RE1", "Berlin", "Magdeburg")
    val second = Train("RE8", "Berlin", "Wismar")
    // then
    assertThat(first).isNotEqualTo(second)
  }
}