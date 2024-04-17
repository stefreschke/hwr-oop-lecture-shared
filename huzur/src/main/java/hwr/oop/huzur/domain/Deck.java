package hwr.oop.huzur.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public interface Deck {

  Stream<Card> cards();

  DrawFromDeckResult draw();

  DrawFromDeckResult draw(int numberOfCards);

  int remainingCards();

  boolean isEmpty();

  class DrawFromEmptyDeckException extends RuntimeException {

    public DrawFromEmptyDeckException() {
      super("can not draw from empty deck");
    }
  }

  class DrawMoreCardsThanInDeckException extends RuntimeException {

    public DrawMoreCardsThanInDeckException(int drawnCards, int remainingCards) {
      super(String.format("Tried to draw %d cards from deck, but there were only %d cards remaining",
          drawnCards, remainingCards));
    }
  }

  record DrawFromDeckResult(Deck deck, List<Card> cards) {

    public DrawFromDeckResult {
      Objects.requireNonNull(deck);
      Objects.requireNonNull(cards);
    }
  }
}
