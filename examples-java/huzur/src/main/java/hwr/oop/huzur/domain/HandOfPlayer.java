package hwr.oop.huzur.domain;

import hwr.oop.huzur.domain.cards.Card;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public final class HandOfPlayer {

  private final Player player;
  private final List<Card> cards;

  public HandOfPlayer(Player player, List<Card> cards) {
    Objects.requireNonNull(player);
    Objects.requireNonNull(cards);
    this.player = player;
    this.cards = cards;
  }

  public Player player() {
    return player;
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

  public Stream<Card> cards() {
    return cards.stream();
  }

  public Stream<Card> notIncluded(List<Card> cards) {
    final var set = new HashSet<>(this.cards);
    return cards.stream().filter(c -> !set.contains(c));
  }

  public Stream<Card> without(List<Card> cards) {
    return this.cards.stream().filter(c -> !cards.contains(c));
  }

}
