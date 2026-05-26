package hwr.oop.lecture.chapter10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled("Remove this annotation to activate the exercise")
class MappingTest {

    private val sevenOfClubs = Card(Rank.SEVEN, Suit.CLUBS)
    private val queenOfHearts = Card(Rank.QUEEN, Suit.HEARTS)
    private val kingOfSpades = Card(Rank.KING, Suit.SPADES)

    @Test
    fun `ranks returns all ranks of cards in hand`() {
        val hand = Hand(listOf(sevenOfClubs, queenOfHearts, kingOfSpades))
        assertThat(hand.ranks()).containsExactly(Rank.SEVEN, Rank.QUEEN, Rank.KING)
    }

    @Test
    fun `ranks returns empty list for empty hand`() {
        val hand = Hand(emptyList())
        assertThat(hand.ranks()).isEmpty()
    }

    @Test
    fun `ranks includes duplicate ranks when hand has two cards of same rank`() {
        val sevenOfSpades = Card(Rank.SEVEN, Suit.SPADES)
        val hand = Hand(listOf(sevenOfClubs, sevenOfSpades, kingOfSpades))
        assertThat(hand.ranks()).containsExactly(Rank.SEVEN, Rank.SEVEN, Rank.KING)
    }

    @Test
    fun `ranks has same number of elements as cards in hand`() {
        val hand = Hand(listOf(sevenOfClubs, queenOfHearts, kingOfSpades))
        assertThat(hand.ranks()).hasSameSizeAs(hand.cards)
    }

    @Test
    fun `suits returns all suits of cards in hand`() {
        val hand = Hand(listOf(sevenOfClubs, queenOfHearts, kingOfSpades))
        assertThat(hand.suits()).containsExactly(Suit.CLUBS, Suit.HEARTS, Suit.SPADES)
    }

    @Test
    fun `suits returns empty list for empty hand`() {
        val hand = Hand(emptyList())
        assertThat(hand.suits()).isEmpty()
    }

    @Test
    fun `suits includes duplicate suits when hand has two cards of same suit`() {
        val eightOfClubs = Card(Rank.EIGHT, Suit.CLUBS)
        val hand = Hand(listOf(sevenOfClubs, eightOfClubs, kingOfSpades))
        assertThat(hand.suits()).containsExactly(Suit.CLUBS, Suit.CLUBS, Suit.SPADES)
    }

    @Test
    fun `suits has same number of elements as cards in hand`() {
        val hand = Hand(listOf(sevenOfClubs, queenOfHearts, kingOfSpades))
        assertThat(hand.suits()).hasSameSizeAs(hand.cards)
    }
}
