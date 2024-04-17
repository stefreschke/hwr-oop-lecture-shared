package hwr.oop.huzur.domain;

import hwr.oop.huzur.domain.cards.CardFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class RandomDeck implements Deck {

  private final List<Card> cards;

  public RandomDeck() {
    final var mutableCards = new ArrayList<>(new CardFactory().createAllCards().toList());
    Collections.shuffle(mutableCards);
    this.cards = Collections.unmodifiableList(mutableCards);
  }

  @Override
  public Stream<Card> cards() {
    return cards.stream();
  }

  @Override
  public DrawFromDeckResult draw() {
    final Card first = cards.getFirst();
    final Deck newDeck = UnshuffledDeck.of(allCardsButTheFirst());
    return new DrawFromDeckResult(newDeck, List.of(first));
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

  private List<Card> allCardsButTheFirst() {
    return allCardsButFirst(1);
  }

  private List<Card> allCardsButFirst(int numberOfCards) {
    return cards.subList(numberOfCards, cards.size());
  }

}
