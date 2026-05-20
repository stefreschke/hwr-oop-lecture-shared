package hwr.oop.lecture.chapter07

class Hand(private val cards: List<Card>) {
	
	// Aufgabe 1: Implementiere hasPlayableCard(topOfDiscard: Card): Boolean
	//   Eine Karte ist spielbar, wenn sie mit der obersten Karte des Ablagestapels
	//   den gleichen Kartenwert (Rank) ODER die gleiche Kartenfarbe (Suit) teilt.
	fun hasPlayableCard(topOfDiscard: Card): Boolean {
		TODO("Implement: return true if any card in hand matches topOfDiscard by rank or suit")
	}
}
