package hwr.oop.lecture.chapter10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled("Remove this annotation to activate the exercise")
class SortedByRankTest {

    private val sevenOfClubs = Card(Rank.SEVEN, Suit.CLUBS)
    private val queenOfHearts = Card(Rank.QUEEN, Suit.HEARTS)
    private val kingOfSpades = Card(Rank.KING, Suit.SPADES)

    @Test
    fun `sortedByRank returns cards in ascending rank order`() {
        val hand = Hand(listOf(kingOfSpades, sevenOfClubs, queenOfHearts))
        assertThat(hand.sortedByRank()).containsExactly(sevenOfClubs, queenOfHearts, kingOfSpades)
    }

    @Test
    fun `sortedByRank with already sorted hand preserves order`() {
        val hand = Hand(listOf(sevenOfClubs, queenOfHearts, kingOfSpades))
        assertThat(hand.sortedByRank()).containsExactly(sevenOfClubs, queenOfHearts, kingOfSpades)
    }

    @Test
    fun `sortedByRank does not mutate the original hand`() {
        val originalOrder = listOf(kingOfSpades, sevenOfClubs, queenOfHearts)
        val hand = Hand(originalOrder)
        hand.sortedByRank()
        assertThat(hand.cards).containsExactly(kingOfSpades, sevenOfClubs, queenOfHearts)
    }

    @Test
    fun `sortedByRank returns empty list for empty hand`() {
        val hand = Hand(emptyList())
        assertThat(hand.sortedByRank()).isEmpty()
    }
}
