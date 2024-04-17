package hwr.oop.huzur.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface Game extends GameCardContext {


  Stream<Player> players();

  HandOfPlayer handOf(Player player);

  Stream<Card> remainingDeck();

  int numberOfRemainingCards();

  Player turn();

  Game play(Player player, List<Card> cards);

  Game pickup(Player player);

  Optional<Layout> currentLayout();
}
