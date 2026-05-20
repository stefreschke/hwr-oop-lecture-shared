package hwr.oop.lecture.chapter09

// Aufgabe 5: Kombiniere RankComparator und SuitComparator zu einem neuen Comparator<Card>.
//             Sortiere zuerst nach Rang; bei gleichem Rang entscheidet die Farbe.
//             Erwartetes Ergebnis (Ausschnitt): K7, H7, P7, Kr7, K8, H8, ..., KrA
//             (K=Karo, H=Herz, P=Pik, Kr=Kreuz; 7–10, B=Bube, D=Dame, Kg=König, A=Ass)
class RankThenSuitComparator : Comparator<Card> {
	
	override fun compare(first: Card, other: Card): Int {
		TODO("Implement: delegate to RankComparator, fall back to SuitComparator when ranks are equal")
	}
}
