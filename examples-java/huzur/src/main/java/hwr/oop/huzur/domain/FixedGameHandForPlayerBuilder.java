package hwr.oop.huzur.domain;

import hwr.oop.huzur.domain.cards.Card;
import java.util.List;

public final class FixedGameHandForPlayerBuilder {

  private final FixedGameBuilder builder;
  private final Player player;

  FixedGameHandForPlayerBuilder(FixedGameBuilder builder, Player player) {
    this.builder = builder;
    this.player = player;
  }

  public FixedGameBuilder hasCards(List<Card> cards) {
    return builder.playerAndCards(player, cards);
  }

}
