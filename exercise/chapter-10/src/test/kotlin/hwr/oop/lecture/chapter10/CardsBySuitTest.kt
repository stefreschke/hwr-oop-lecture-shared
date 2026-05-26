package hwr.oop.lecture.chapter10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled("Remove this annotation to activate the exercise")
class CardsBySuitTest {

    private val sevenOfClubs = Card(Rank.SEVEN, Suit.CLUBS)
    private val eightOfClubs = Card(Rank.EIGHT, Suit.CLUBS)
    private val sevenOfHearts = Card(Rank.SEVEN, Suit.HEARTS)
    private val kingOfSpades = Card(Rank.KING, Suit.SPADES)

    @Test
    fun `cardsBySuit groups cards by their suit`() {
        val hand = Hand(listOf(sevenOfClubs, eightOfClubs, sevenOfHearts, kingOfSpades))
        val result = hand.cardsBySuit()
        assertThat(result[Suit.CLUBS]).containsExactly(sevenOfClubs, eightOfClubs)
        assertThat(result[Suit.HEARTS]).containsExactly(sevenOfHearts)
        assertThat(result[Suit.SPADES]).containsExactly(kingOfSpades)
        assertThat(result).doesNotContainKey(Suit.DIAMONDS)
    }

    @Test
    fun `cardsBySuit returns empty map for empty hand`() {
        val hand = Hand(emptyList())
        assertThat(hand.cardsBySuit()).isEmpty()
    }

    @Test
    fun `cardsBySuit returns single entry when all cards share the same suit`() {
        val hand = Hand(listOf(sevenOfClubs, eightOfClubs))
        val result = hand.cardsBySuit()
        assertThat(result).containsOnlyKeys(Suit.CLUBS)
        assertThat(result[Suit.CLUBS]).containsExactly(sevenOfClubs, eightOfClubs)
    }

    @Test
    fun `cardsBySuit map has one entry per distinct suit in hand`() {
        val hand = Hand(listOf(sevenOfClubs, eightOfClubs, sevenOfHearts, kingOfSpades))
        assertThat(hand.cardsBySuit()).hasSize(3)
    }
}
