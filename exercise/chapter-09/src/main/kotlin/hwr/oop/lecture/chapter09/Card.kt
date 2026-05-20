package hwr.oop.lecture.chapter09

// Aufgabe 1: Implementiere equals() und hashCode() so, dass die Tests in CardEqualityTest grün werden.
//            Zwei Karten sind gleich, wenn Rang UND Farbe übereinstimmen.
//
// Aufgabe 2: Ersetze diese class durch eine data class.
//            Stelle sicher, dass alle Tests in CardEqualityTest danach noch grün sind.
class Card(val rank: Rank, val suit: Suit) {
	
	// Aufgabe 1
	override fun equals(other: Any?): Boolean {
		TODO("Implement: two cards with same rank and suit must be equal")
	}
	
	// Aufgabe 1
	override fun hashCode(): Int {
		TODO("Implement: equal cards must have the same hash code")
	}
}
