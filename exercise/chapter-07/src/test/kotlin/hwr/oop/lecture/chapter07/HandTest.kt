package hwr.oop.lecture.chapter07

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled("Remove this annotation to activate the exercise")
class HandTest {
 
	@Test
	fun `card matches top of discard pile by rank`() {
		val hand = Hand(listOf(Card(Rank.SEVEN, Suit.CLUBS)))
		val discard = Card(Rank.SEVEN, Suit.HEARTS)
		assertThat(hand.hasPlayableCard(discard)).isTrue()
	}
	
	@Test
	fun `card matches top of discard pile by suit`() {
		val hand = Hand(listOf(Card(Rank.EIGHT, Suit.CLUBS)))
		val discard = Card(Rank.SEVEN, Suit.CLUBS)
		assertThat(hand.hasPlayableCard(discard)).isTrue()
	}
	
	@Test
	fun `card does not match if neither rank nor suit match`() {
		val hand = Hand(listOf(Card(Rank.EIGHT, Suit.CLUBS)))
		val discard = Card(Rank.SEVEN, Suit.HEARTS)
		assertThat(hand.hasPlayableCard(discard)).isFalse()
	}
}
