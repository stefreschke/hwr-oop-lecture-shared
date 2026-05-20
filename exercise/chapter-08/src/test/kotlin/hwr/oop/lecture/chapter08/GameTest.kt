package hwr.oop.lecture.chapter08

import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled("Remove this annotation to activate the exercise")
class GameTest {
	@Test
	fun `playing a card with matching rank does not throw`() {
		val game = Game(
			hand = listOf(Card(Rank.SEVEN, Suit.CLUBS)),
			topOfDiscard = Card(Rank.SEVEN, Suit.HEARTS),
		)
		assertThatCode { game.playCard(Card(Rank.SEVEN, Suit.CLUBS)) }.doesNotThrowAnyException()
	}
	
	@Test
	fun `playing a card with matching suit does not throw`() {
		val game = Game(
			hand = listOf(Card(Rank.EIGHT, Suit.CLUBS)),
			topOfDiscard = Card(Rank.SEVEN, Suit.CLUBS),
		)
		assertThatCode { game.playCard(Card(Rank.EIGHT, Suit.CLUBS)) }.doesNotThrowAnyException()
	}
	
	@Test
	fun `playing a card that matches neither rank nor suit throws a RuntimeException`() {
		val game = Game(
			hand = listOf(Card(Rank.EIGHT, Suit.CLUBS)),
			topOfDiscard = Card(Rank.SEVEN, Suit.HEARTS),
		)
		assertThatThrownBy { game.playCard(Card(Rank.EIGHT, Suit.CLUBS)) }
			.isInstanceOf(RuntimeException::class.java)
	}
	
	@Test
	fun `exception thrown for invalid move is a custom subclass, not RuntimeException itself`() {
		val game = Game(
			hand = listOf(Card(Rank.EIGHT, Suit.CLUBS)),
			topOfDiscard = Card(Rank.SEVEN, Suit.HEARTS),
		)
		assertThatThrownBy { game.playCard(Card(Rank.EIGHT, Suit.CLUBS)) }
			.isNotExactlyInstanceOf(RuntimeException::class.java)
	}
	
	@Test
	fun `exception message contains correct values for eight of clubs vs seven of hearts`() {
		val playedCard = Card(Rank.EIGHT, Suit.CLUBS)
		val topOfDiscard = Card(Rank.SEVEN, Suit.HEARTS)
		val game = Game(hand = listOf(playedCard), topOfDiscard = topOfDiscard)
		assertThatThrownBy { game.playCard(playedCard) }
			.isInstanceOf(RuntimeException::class.java)
			.hasMessageContainingAll(
				playedCard.rank.name,
				playedCard.suit.name,
				topOfDiscard.rank.name,
				topOfDiscard.suit.name,
			)
	}
	
	@Test
	fun `exception message contains correct values for king of diamonds vs ace of spades`() {
		val playedCard = Card(Rank.KING, Suit.DIAMONDS)
		val topOfDiscard = Card(Rank.ACE, Suit.SPADES)
		val game = Game(hand = listOf(playedCard), topOfDiscard = topOfDiscard)
		assertThatThrownBy { game.playCard(playedCard) }
			.isInstanceOf(RuntimeException::class.java)
			.hasMessageContainingAll(
				playedCard.rank.name,
				playedCard.suit.name,
				topOfDiscard.rank.name,
				topOfDiscard.suit.name,
			)
	}
	
	@Test
	fun `exception message contains correct values for jack of hearts vs ten of clubs`() {
		val playedCard = Card(Rank.JACK, Suit.HEARTS)
		val topOfDiscard = Card(Rank.TEN, Suit.CLUBS)
		val game = Game(hand = listOf(playedCard), topOfDiscard = topOfDiscard)
		assertThatThrownBy { game.playCard(playedCard) }
			.isInstanceOf(RuntimeException::class.java)
			.hasMessageContainingAll(
				playedCard.rank.name,
				playedCard.suit.name,
				topOfDiscard.rank.name,
				topOfDiscard.suit.name,
			)
	}
}
