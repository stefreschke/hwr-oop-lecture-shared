package hwr.oop.lecture.chapter09

// Aufgabe 4: Implementiere Comparator<Card> — Karten sollen nach Farbe verglichen werden können.
//             Reihenfolge: Karo (DIAMONDS) < Herz (HEARTS) < Pik (SPADES) < Kreuz (CLUBS)
class SuitComparator : Comparator<Card> {
	
	override fun compare(first: Card, other: Card): Int {
		TODO("Implement: compare cards by suit (DIAMONDS < HEARTS < SPADES < CLUBS)")
	}
}
