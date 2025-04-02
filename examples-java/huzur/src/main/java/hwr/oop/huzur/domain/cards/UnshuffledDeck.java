package hwr.oop.huzur.domain.cards;

import java.util.List;
import java.util.stream.Stream;

final class UnshuffledDeck implements Deck {

  private final List<Card> cards;

  static Deck of(List<Card> cards) {
    return new UnshuffledDeck(cards);
  }

  UnshuffledDeck(List<Card> cards) {
    this.cards = cards;
  }

  @Override
  public Stream<Card> peek() {
    return cards.stream();
  }

  @Override
  public DrawFromDeckResult draw() {
    if (isEmpty()) {
      throw new Deck.DrawFromEmptyDeckException();
    } else {
      final var first = cards.getFirst();
      final var allButFirstCard = cards.subList(1, cards.size());
      final var newDeck = UnshuffledDeck.of(allButFirstCard);
      return new DrawFromDeckResult(newDeck, List.of(first));
    }
  }

  @Override
  public DrawFromDeckResult draw(int numberOfCards) {
    if (numberOfCards > remainingCards()) {
      throw new DrawMoreCardsThanInDeckException(numberOfCards, cards.size());
    }
    final var drawnCards = cards.subList(0, numberOfCards);
    final var remaining = allCardsButFirst(numberOfCards);
    final var newDeck = UnshuffledDeck.of(remaining);
    return new DrawFromDeckResult(newDeck, drawnCards);
  }

  @Override
  public int remainingCards() {
    return cards.size();
  }

  @Override
  public boolean isEmpty() {
    return cards.isEmpty();
  }

  private List<Card> allCardsButFirst(int numberOfCards) {
    return cards.subList(numberOfCards, cards.size());
  }
}
