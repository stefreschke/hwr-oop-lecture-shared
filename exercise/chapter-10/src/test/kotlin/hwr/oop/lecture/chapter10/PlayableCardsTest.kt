package hwr.oop.lecture.chapter10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled("Remove this annotation to activate the exercise")
class PlayableCardsTest {

    private val sevenOfClubs = Card(Rank.SEVEN, Suit.CLUBS)
    private val eightOfClubs = Card(Rank.EIGHT, Suit.CLUBS)
    private val sevenOfHearts = Card(Rank.SEVEN, Suit.HEARTS)
    private val kingOfSpades = Card(Rank.KING, Suit.SPADES)
    private val aceOfDiamonds = Card(Rank.ACE, Suit.DIAMONDS)

    @Test
    fun `playableCards returns cards matching rank of top of discard`() {
        val hand = Hand(listOf(sevenOfClubs, sevenOfHearts, kingOfSpades))
        val topOfDiscard = Card(Rank.SEVEN, Suit.DIAMONDS)
        assertThat(hand.playableCards(topOfDiscard)).containsExactlyInAnyOrder(sevenOfClubs, sevenOfHearts)
    }

    @Test
    fun `playableCards returns cards matching suit of top of discard`() {
        val hand = Hand(listOf(eightOfClubs, sevenOfHearts, kingOfSpades))
        val topOfDiscard = Card(Rank.ACE, Suit.CLUBS)
        assertThat(hand.playableCards(topOfDiscard)).containsExactly(eightOfClubs)
    }

    @Test
    fun `playableCards returns empty list when no card matches`() {
        val hand = Hand(listOf(kingOfSpades, aceOfDiamonds))
        val topOfDiscard = Card(Rank.SEVEN, Suit.CLUBS)
        assertThat(hand.playableCards(topOfDiscard)).isEmpty()
    }

    @Test
    fun `playableCards returns card matching both rank and suit exactly once`() {
        val topOfDiscard = Card(Rank.SEVEN, Suit.CLUBS)
        val hand = Hand(listOf(sevenOfClubs, kingOfSpades))
        assertThat(hand.playableCards(topOfDiscard)).containsExactly(sevenOfClubs)
    }

    @Test
    fun `playableCards returns all cards when every card in hand is playable`() {
        val hand = Hand(listOf(sevenOfClubs, eightOfClubs, sevenOfHearts))
        val topOfDiscard = Card(Rank.NINE, Suit.CLUBS)
        assertThat(hand.playableCards(topOfDiscard)).containsExactlyInAnyOrder(sevenOfClubs, eightOfClubs)
    }

    @Test
    fun `playableCards returns empty list for empty hand`() {
        val hand = Hand(emptyList())
        val topOfDiscard = Card(Rank.SEVEN, Suit.CLUBS)
        assertThat(hand.playableCards(topOfDiscard)).isEmpty()
    }
}
