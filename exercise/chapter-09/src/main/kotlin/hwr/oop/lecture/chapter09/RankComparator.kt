package hwr.oop.lecture.chapter09

// Aufgabe 3: Implementiere Comparator<Card> — Karten sollen nach Rang verglichen werden können.
//             Reihenfolge: 7 < 8 < 9 < 10 < Bube < Dame < König < Ass
class RankComparator : Comparator<Card> {
	
	override fun compare(first: Card, other: Card): Int {
		TODO("Implement: compare cards by rank (SEVEN < EIGHT < ... < ACE)")
	}
}
