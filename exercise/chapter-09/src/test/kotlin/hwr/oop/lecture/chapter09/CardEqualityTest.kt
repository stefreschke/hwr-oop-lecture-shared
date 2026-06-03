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
	
	@Test
	fun `card is not equal to null`() {
		val card = Card(Rank.SEVEN, Suit.CLUBS)
		assertThat(card).isNotEqualTo(null)
	}
	
	@Test
	fun `card is not equal to a String`() {
		val card = Card(Rank.SEVEN, Suit.CLUBS)
		assertThat(card).isNotEqualTo("SEVEN of CLUBS")
	}
	
	@Test
	fun `card is not equal to an Int`() {
		val card = Card(Rank.SEVEN, Suit.CLUBS)
		assertThat(card).isNotEqualTo(7)
	}
	
	@Test
	fun `card is equal to itself`() {
		val card = Card(Rank.SEVEN, Suit.CLUBS)
		assertThat(card).isEqualTo(card)
	}
	
	@Test
	fun `equality is symmetric`() {
		val card1 = Card(Rank.SEVEN, Suit.CLUBS)
		val card2 = Card(Rank.SEVEN, Suit.CLUBS)
		assertThat(card1).isEqualTo(card2)
		assertThat(card2).isEqualTo(card1)
	}
	
	@Test
	fun `cards with different rank have different hash codes`() {
		val card1 = Card(Rank.SEVEN, Suit.CLUBS)
		val card2 = Card(Rank.EIGHT, Suit.CLUBS)
		assertThat(card1.hashCode()).isNotEqualTo(card2.hashCode())
	}
	
	@Test
	fun `cards with different suit have different hash codes`() {
		val card1 = Card(Rank.SEVEN, Suit.CLUBS)
		val card2 = Card(Rank.SEVEN, Suit.HEARTS)
		assertThat(card1.hashCode()).isNotEqualTo(card2.hashCode())
	}
}
