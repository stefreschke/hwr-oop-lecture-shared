package hwr.oop.huzur.domain;

import hwr.oop.huzur.domain.cards.Card;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.Deck;
import hwr.oop.huzur.domain.layouts.Layout;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public final class FixedGameBuilder {

  private final Map<Player, List<Card>> map;
  private GameId gameId;
  private Color trump;
  private Deck deck;
  private Player turn;
  private Layout layout;
  private List<Player> players;

  FixedGameBuilder() {
    this.map = new HashMap<>();
  }

  public FixedGameBuilder id(GameId gameId) {
    this.gameId = gameId;
    return this;
  }

  public FixedGameBuilder id(String gameId) {
    this.gameId = GameId.of(gameId);
    return this;
  }

  public FixedGameBuilder trump(Color color) {
    this.trump = color;
    return this;
  }

  public FixedGameBuilder deck(List<Card> cards) {
    this.deck = Deck.unshuffled(cards);
    return this;
  }

  public FixedGameBuilder deck(Stream<Card> cards) {
    return deck(cards.toList());
  }

  public FixedGameBuilder deck(Deck deck) {
    this.deck = deck;
    return this;
  }

  public FixedGameBuilder emptyDeck() {
    this.deck = Deck.empty();
    return this;
  }

  public FixedGameHandForPlayerBuilder player(Player player) {
    return new FixedGameHandForPlayerBuilder(this, player);
  }

  public FixedGameBuilder playerOrder(List<Player> players) {
    this.players = players;
    return this;
  }

  public FixedGameBuilder playerOrder(Player... players) {
    return playerOrder(Arrays.asList(players));
  }

  FixedGameBuilder playerAndCards(Player player, List<Card> cards) {
    map.put(player, cards);
    return this;
  }

  public FixedGameBuilder playerAndCards(Map<Player, List<Card>> handCards) {
    map.clear();
    map.putAll(handCards);
    return this;
  }

  public FixedGameBuilder turn(Player player) {
    this.turn = player;
    return this;
  }

  public FixedGameBuilder noLayout() {
    this.layout = null;
    return this;
  }

  public FixedGameBuilder layout(Layout layout) {
    this.layout = layout;
    return this;
  }

  public Game build() {
    return new FixedGame(gameId, trump, players, map, deck, turn, layout);
  }
}
