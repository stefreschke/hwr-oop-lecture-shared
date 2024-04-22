package hwr.oop.huzur.domain;

import hwr.oop.huzur.domain.cards.Card.Color;
import java.util.List;

public final class FreshGameBuilder {

  private final Color trump;

  public FreshGameBuilder(Color trump) {
    this.trump = trump;
  }

  public Game playedBy(List<Player> players) {
    return new FreshGame(trump, players);
  }
}
