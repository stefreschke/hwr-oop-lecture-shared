package hwr.oop.huzur.domain.cards;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Decks are <i>immutable</i>. If you {@link #draw(int)} cards, the drawn cards and the updated Deck
 * is returned as part of {@link DrawFromDeckResult}.
 */
public interface Deck {

  static Deck random() {
    return new RandomDeck();
  }

  static Deck unshuffled(List<Card> cards) {
    return new UnshuffledDeck(cards);
  }

  static Deck unshuffled(Card... cards) {
    return unshuffled(Arrays.asList(cards));
  }

  static Deck empty() {
    return new UnshuffledDeck(Collections.emptyList());
  }

  boolean isEmpty();

  int remainingCards();

  Stream<Card> peek();

  DrawFromDeckResult draw();

  DrawFromDeckResult draw(int numberOfCards);

  class DrawFromEmptyDeckException extends RuntimeException {

    public DrawFromEmptyDeckException() {
      super("can not draw from empty deck");
    }
  }

  class DrawMoreCardsThanInDeckException extends RuntimeException {

    public DrawMoreCardsThanInDeckException(int drawnCards, int remainingCards) {
      super(buildMessage(drawnCards, remainingCards));
    }

    private static String buildMessage(int drawnCards, int remainingCards) {
      return String.format("Tried to draw %d cards from deck,"
          + " but there were only %d cards remaining", drawnCards, remainingCards);
    }
  }

  /**
   * Result of drawing from an immutable {@link Deck} using {@link Deck#draw(int)}.
   */
  final class DrawFromDeckResult {

    private final Deck deck;
    private final List<Card> cards;

    DrawFromDeckResult(Deck deck, List<Card> cards) {
      Objects.requireNonNull(deck);
      Objects.requireNonNull(cards);
      this.deck = deck;
      this.cards = List.copyOf(cards);
    }

    public Deck deck() {
      return deck;
    }

    public Stream<Card> cards() {
      return cards.stream();
    }
  }
}
