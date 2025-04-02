package hwr.oop.huzur.domain.layouts;

import hwr.oop.huzur.domain.cards.Card;
import hwr.oop.huzur.domain.cards.Deck;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum LayoutType {
  SINGLE(1, 0, false),
  THREE(3, 1, false),
  FIVE(5, 2, false),
  SEVEN(7, 3, true);

  private final int pairsRequired;
  private final int numberOfCards;
  private final boolean onEmptyDeck;

  LayoutType(int numberOfCards, int pairsRequired, boolean onlyOnEmptyDeck) {
    this.numberOfCards = numberOfCards;
    this.pairsRequired = pairsRequired;
    this.onEmptyDeck = onlyOnEmptyDeck;
  }

  public static Stream<LayoutType> available(Deck deck, int numberOfCards) {
    return Arrays.stream(values())
        .filter(v -> v.numberOfCards() <= numberOfCards)
        .filter(v -> deck.isEmpty() || !v.onlyOnEmptyDeck());
  }

  public static LayoutType of(int numberOfCards) {
    return Arrays.stream(values())
        .filter(v -> v.numberOfCards() == numberOfCards)
        .findFirst().orElseThrow(() -> new IllegalArgumentException(
            String.format(
                "Invalid number of cards, got %d cards, but expected any of {1, 3, 5}",
                numberOfCards)
        ));
  }

  public static LayoutType of(List<Card> cards) {
    return of(cards.size());
  }

  public int pairsRequired() {
    return pairsRequired;
  }

  public int numberOfCards() {
    return numberOfCards;
  }

  public boolean onlyOnEmptyDeck() {
    return onEmptyDeck;
  }
}
