package hwr.oop.lecture.chapter08

// Aufgabe:
//   1. Lege eine eigene Exception-Klasse in diesem Package an.
//      - Sie muss eine Unterklasse von RuntimeException sein (nicht RuntimeException selbst).
//      - Der Klassenname ist frei wählbar — wähle einen aussagekräftigen Namen.
//      - Die Exception soll eine Fehlermeldung tragen, die beschreibt, welche Karte warum ungültig ist.
//
//   2. Implementiere playCard(card: Card):
//      - Ist die Karte spielbar (gleicher Rang ODER gleiche Farbe wie topOfDiscard), tue nichts.
//      - Ist die Karte nicht spielbar, wirf deine custom Exception.
class Game(
	private val hand: List<Card>,
	private val topOfDiscard: Card,
) {
	fun playCard(card: Card) {
		TODO("Implement: throw your custom RuntimeException if card matches neither rank nor suit of topOfDiscard")
	}
}
