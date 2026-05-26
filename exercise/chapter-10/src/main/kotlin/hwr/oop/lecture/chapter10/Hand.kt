package hwr.oop.lecture.chapter10

// Aufgabe 1 (PlayableCardsTest): Implementiere playableCards(topOfDiscard: Card): List<Card>
//   Gib alle Handkarten zurück, die gegen die oberste Karte des Ablagestapels gespielt werden dürfen.
//   Spielbar: gleicher Rang ODER gleiche Farbe wie topOfDiscard.
//
// Aufgabe 2 (MappingTest): Implementiere ranks(): List<Rank> und suits(): List<Suit>
//   Gib eine Liste aller Rangwerte bzw. Farbwerte der Handkarten zurück (Reihenfolge beibehalten).
//
// Aufgabe 3 (SortedByRankTest): Implementiere sortedByRank(): List<Card>
//   Gib die Handkarten aufsteigend nach Rang sortiert zurück (7 zuerst, Ass zuletzt).
//
// Aufgabe 4 (GameTest): siehe Game.kt
//
// Aufgabe 5 (CardsBySuitTest): Implementiere cardsBySuit(): Map<Suit, List<Card>>
//   Gruppiere die Handkarten nach Farbe.
class Hand(val cards: List<Card>) {
    // Aufgabe 1 — see PlayableCardsTest
    fun playableCards(topOfDiscard: Card): List<Card> {
        TODO("PlayableCardsTest: filter cards matching topOfDiscard by rank or suit")
    }

    // Aufgabe 2 — see MappingTest
    fun ranks(): List<Rank> {
        TODO("MappingTest: map each card to its rank")
    }

    fun suits(): List<Suit> {
        TODO("MappingTest: map each card to its suit")
    }

    // Aufgabe 3 — see SortedByRankTest
    fun sortedByRank(): List<Card> {
        TODO("SortedByRankTest: sort cards ascending by rank")
    }

    // Aufgabe 5 — see CardsBySuitTest
    fun cardsBySuit(): Map<Suit, List<Card>> {
        TODO("CardsBySuitTest: group cards by suit")
    }
}
