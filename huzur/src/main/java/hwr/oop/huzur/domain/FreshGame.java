package hwr.oop.huzur.domain;

import hwr.oop.huzur.domain.cards.Card;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.Deck;
import hwr.oop.huzur.domain.cards.Deck.DrawFromDeckResult;
import hwr.oop.huzur.domain.layouts.Layout;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

final class FreshGame implements Game {

  private static final int NUMBER_OF_CARDS_PER_PLAYER = 7;
  private final Game wrapped;

  FreshGame(Color trump, List<Player> players) {
    if (players.size() < 2) {
      throw new IllegalArgumentException("Game requires at least two players");
    }
    wrapped = createNewGame(trump, players);
  }

  private Game createNewGame(Color trump, List<Player> players) {
    final var newID = UUID.randomUUID();
    final var drawResult = Deck.random().draw(players.size() * NUMBER_OF_CARDS_PER_PLAYER);
    final var deck = drawResult.deck();
    final var handCards = buildHandCardsMap(players, drawResult);
    final var builder = Game.newBuilder()
        .id(newID.toString())
        .noLayout()
        .deck(deck)
        .turn(players.getFirst())
        .trump(trump)
        .playerOrder(players);
    handCards.forEach((p, cs) -> builder.player(p).hasCards(cs));
    return builder.build();
  }

  private Map<Player, List<Card>> buildHandCardsMap(List<Player> players,
      DrawFromDeckResult drawResult) {
    final Map<Player, List<Card>> freshMap = new HashMap<>();
    IntStream.range(0, players.size()).forEach(i -> {
      final var player = players.get(i);
      final var cards = drawResult.cards().toList()
          .subList(i * NUMBER_OF_CARDS_PER_PLAYER, i * NUMBER_OF_CARDS_PER_PLAYER + 7);
      freshMap.put(player, Collections.unmodifiableList(cards));
    });
    return freshMap;
  }

  @Override
  public Color trump() {
    return wrapped.trump();
  }

  @Override
  public Stream<Player> players() {
    return wrapped.players();
  }

  @Override
  public HandOfPlayer handOf(Player player) {
    return wrapped.handOf(player);
  }

  @Override
  public Stream<Card> remainingDeck() {
    return wrapped.remainingDeck();
  }

  @Override
  public int numberOfRemainingCards() {
    return wrapped.numberOfRemainingCards();
  }

  @Override
  public GameId id() {
    return wrapped.id();
  }

  @Override
  public Player turn() {
    return wrapped.turn();
  }

  @Override
  public Optional<Layout> currentLayout() {
    return wrapped.currentLayout();
  }

  @Override
  public Game play(Player player, List<Card> cards) {
    return wrapped.play(player, cards);
  }

  @Override
  public Game pickup(Player player) {
    return wrapped.pickup(player);
  }

  @Override
  public boolean gameIsOver() {
    return wrapped.gameIsOver();
  }

  @Override
  public Optional<Player> winner() {
    return wrapped.winner();
  }
}
