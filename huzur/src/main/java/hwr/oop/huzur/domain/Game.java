package hwr.oop.huzur.domain;

import hwr.oop.huzur.domain.cards.Card;
import hwr.oop.huzur.domain.layouts.Layout;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface Game extends GameCardContext {

  static FixedGameBuilder newBuilder() {
    return new FixedGameBuilder();
  }

  static FreshGameBuilder fresh(Card.Color trump) {
    return new FreshGameBuilder(trump);
  }

  static FreshGameBuilder fresh(String id, Card.Color trump) {
    return new FreshGameBuilder(id, trump);
  }

  GameId id();

  Player turn();

  Stream<Player> players();

  HandOfPlayer handOf(Player player);

  int numberOfRemainingCards();

  Stream<Card> remainingDeck();

  Optional<Layout> currentLayout();

  boolean gameIsOver();

  Optional<Player> winner();

  Game play(Player player, List<Card> cards);

  Game pickup(Player player);

  class CannotPickupException extends RuntimeException {

    public CannotPickupException(String message) {
      super(message);
    }
  }

  class WrongPlayerException extends RuntimeException {

    public WrongPlayerException(String message) {
      super(message);
    }
  }
}
