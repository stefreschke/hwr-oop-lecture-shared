package hwr.oop.lecture.chapter09

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

// Hinweis: Aufgaben 1 und 2 (CardEqualityTest) müssen zuerst abgeschlossen sein,
// da einige dieser Tests equals() für Listenvergleiche voraussetzen.
@Disabled("Remove this annotation to activate the exercise")
class CardComparabilityTest {

// Aufgabe 3: RankComparator
	
	@Test
	fun `rank comparator, card with lower rank compares less than card with higher rank`() {
		val seven = Card(Rank.SEVEN, Suit.CLUBS)
		val ace = Card(Rank.ACE, Suit.CLUBS)
		val sut = RankComparator()
		assertThat(sut.compare(seven, ace)).isNegative()
	}
	
	@Test
	fun `rank comparator, cards with same rank compare as equal`() {
		val card1 = Card(Rank.QUEEN, Suit.CLUBS)
		val card2 = Card(Rank.QUEEN, Suit.HEARTS)
		val sut = RankComparator()
		assertThat(sut.compare(card1, card2)).isZero()
	}

// Aufgabe 4: SuitComparator
	
	@Test
	fun `suit comparator, diamonds is less than clubs`() {
		val diamonds = Card(Rank.SEVEN, Suit.DIAMONDS)
		val clubs = Card(Rank.SEVEN, Suit.CLUBS)
		val sut = SuitComparator()
		assertThat(sut.compare(diamonds, clubs)).isNegative()
	}
	
	@Test
	fun `suit comparator, clubs is greater than spades`() {
		val clubs = Card(Rank.SEVEN, Suit.CLUBS)
		val spades = Card(Rank.SEVEN, Suit.SPADES)
		val sut = SuitComparator()
		assertThat(sut.compare(clubs, spades)).isPositive()
	}

// Aufgabe 5: RankThenSuitComparator (kombiniert RankComparator + SuitComparator)
	
	@Test
	fun `rank-then-suit comparator, lower rank sorts first regardless of suit`() {
		val sevenOfClubs = Card(Rank.SEVEN, Suit.CLUBS)
		val aceOfDiamonds = Card(Rank.ACE, Suit.DIAMONDS)
		val sut = RankThenSuitComparator()
		assertThat(sut.compare(sevenOfClubs, aceOfDiamonds)).isNegative()
	}
	
	@Test
	fun `rank-then-suit comparator, same rank, diamonds sorts before clubs`() {
		val sevenOfDiamonds = Card(Rank.SEVEN, Suit.DIAMONDS)
		val sevenOfClubs = Card(Rank.SEVEN, Suit.CLUBS)
		val sut = RankThenSuitComparator()
		assertThat(sut.compare(sevenOfDiamonds, sevenOfClubs)).isNegative()
	}
	
	@Test
	fun `rank-then-suit comparator, sorts subset of cards in expected order`() {
		val cards = listOf(
			Card(Rank.SEVEN, Suit.CLUBS),
			Card(Rank.SEVEN, Suit.SPADES),
			Card(Rank.SEVEN, Suit.HEARTS),
			Card(Rank.SEVEN, Suit.DIAMONDS),
			Card(Rank.ACE, Suit.CLUBS),
			Card(Rank.ACE, Suit.DIAMONDS),
		)
		val sorted = cards.sortedWith(RankThenSuitComparator())
		assertThat(sorted).containsExactly(
			Card(Rank.SEVEN, Suit.DIAMONDS),
			Card(Rank.SEVEN, Suit.HEARTS),
			Card(Rank.SEVEN, Suit.SPADES),
			Card(Rank.SEVEN, Suit.CLUBS),
			Card(Rank.ACE, Suit.DIAMONDS),
			Card(Rank.ACE, Suit.CLUBS),
		)
	}
}
