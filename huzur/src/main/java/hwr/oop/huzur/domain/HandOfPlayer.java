package hwr.oop.huzur.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public record HandOfPlayer(Player player, List<Card> cards) {

  public HandOfPlayer {
    Objects.requireNonNull(player);
    Objects.requireNonNull(cards);
    if (cards.isEmpty()) {
      throw new IllegalStateException(
          String.format("Player %s has no cards, which means he should have won!", player));
    }
  }

  public boolean containsCards(Card... cards) {
    return containsCards(Arrays.asList(cards));
  }

  public boolean containsCards(List<Card> cards) {
    return new HashSet<>(this.cards).containsAll(cards);
  }

  public int numberOfCards() {
    return cards.size();
  }
}
