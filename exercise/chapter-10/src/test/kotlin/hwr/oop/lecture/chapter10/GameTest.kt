package hwr.oop.lecture.chapter10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled("Remove this annotation to activate the exercise")
class GameTest {

    // Aufgabe 4: flatMap
    @Test
    fun `allCards returns all cards from all player hands`() {
        val cardA = Card(Rank.ACE, Suit.CLUBS)
        val cardB = Card(Rank.KING, Suit.HEARTS)
        val cardC = Card(Rank.SEVEN, Suit.DIAMONDS)
        val playerAlice = Player("Alice", Hand(listOf(cardA, cardB)))
        val playerBob = Player("Bob", Hand(listOf(cardC)))
        val game = Game(listOf(playerAlice, playerBob))
        assertThat(game.allCards()).containsExactly(cardA, cardB, cardC)
    }

    @Test
    fun `allCards returns empty list when all hands are empty`() {
        val playerAlice = Player("Alice", Hand(emptyList()))
        val playerBob = Player("Bob", Hand(emptyList()))
        val game = Game(listOf(playerAlice, playerBob))
        assertThat(game.allCards()).isEmpty()
    }

    @Test
    fun `allCards returns empty list when there are no players`() {
        val game = Game(emptyList())
        assertThat(game.allCards()).isEmpty()
    }

    @Test
    fun `allCards returns cards from three players in player order`() {
        val cardA = Card(Rank.ACE, Suit.CLUBS)
        val cardB = Card(Rank.KING, Suit.HEARTS)
        val cardC = Card(Rank.QUEEN, Suit.SPADES)
        val game = Game(listOf(
            Player("Alice", Hand(listOf(cardA))),
            Player("Bob", Hand(listOf(cardB))),
            Player("Carol", Hand(listOf(cardC)))
        ))
        assertThat(game.allCards()).containsExactly(cardA, cardB, cardC)
    }

    @Test
    fun `allCards skips empty hands without losing cards from other players`() {
        val cardA = Card(Rank.ACE, Suit.CLUBS)
        val cardB = Card(Rank.SEVEN, Suit.DIAMONDS)
        val game = Game(listOf(
            Player("Alice", Hand(listOf(cardA))),
            Player("Bob", Hand(emptyList())),
            Player("Carol", Hand(listOf(cardB)))
        ))
        assertThat(game.allCards()).containsExactly(cardA, cardB)
    }
}
