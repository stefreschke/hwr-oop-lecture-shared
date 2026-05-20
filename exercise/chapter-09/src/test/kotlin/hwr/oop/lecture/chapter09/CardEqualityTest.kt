package hwr.oop.lecture.chapter09

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled("Remove this annotation to activate the exercise")
class CardEqualityTest {
	
	@Test
	fun `two cards with same rank and suit are equal`() {
		val card1 = Card(Rank.SEVEN, Suit.CLUBS)
		val card2 = Card(Rank.SEVEN, Suit.CLUBS)
		assertThat(card1).isEqualTo(card2)
	}
	
	@Test
	fun `two equal cards have the same hash code`() {
		val card1 = Card(Rank.SEVEN, Suit.CLUBS)
		val card2 = Card(Rank.SEVEN, Suit.CLUBS)
		assertThat(card1).hasSameHashCodeAs(card2)
	}
	
	@Test
	fun `two cards with different rank are not equal`() {
		val card1 = Card(Rank.SEVEN, Suit.CLUBS)
		val card2 = Card(Rank.EIGHT, Suit.CLUBS)
		assertThat(card1).isNotEqualTo(card2)
	}
	
	@Test
	fun `two cards with different suit are not equal`() {
		val card1 = Card(Rank.SEVEN, Suit.CLUBS)
		val card2 = Card(Rank.SEVEN, Suit.HEARTS)
		assertThat(card1).isNotEqualTo(card2)
	}
}
