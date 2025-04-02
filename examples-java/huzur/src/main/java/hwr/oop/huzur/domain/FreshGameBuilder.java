package hwr.oop.huzur.domain;

import hwr.oop.huzur.domain.cards.Card.Color;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public final class FreshGameBuilder {

  private final Color trump;
  private final String id;

  public FreshGameBuilder(Color trump) {
    this(UUID.randomUUID().toString(), trump);
  }

  public FreshGameBuilder(String id, Color trump) {
    this.id = id;
    this.trump = trump;
  }

  public Game playedBy(List<Player> players) {
    return new FreshGame(id, trump, players);
  }

  public Game playedBy(Player... players) {
    return playedBy(Arrays.asList(players));
  }
}
