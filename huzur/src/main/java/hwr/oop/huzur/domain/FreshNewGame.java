package hwr.oop.huzur.domain;

import hwr.oop.huzur.domain.Deck.DrawFromDeckResult;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FreshNewGame implements Game {

  private static final int NUMBER_OF_CARDS_PER_PLAYER = 7;
  private final Color trump;
  private final List<Player> players;
  private final Deck deck;
  private final Map<Player, List<Card>> handCards;

  public FreshNewGame(Color trump, List<Player> players) {
    if (players.size() < 2) {
      throw new IllegalArgumentException("Game requires at least two players");
    }
    this.trump = trump;
    this.players = players;
    final var randomDeck = new RandomDeck();
    final var drawResult = randomDeck.draw(players.size() * NUMBER_OF_CARDS_PER_PLAYER);
    this.deck = drawResult.deck();
    this.handCards = buildHandCardsMap(players, drawResult);
  }

  @Override
  public Color trump() {
    return trump;
  }


  @Override
  public Stream<Player> players() {
    return players.stream();
  }

  @Override
  public HandOfPlayer handOf(Player player) {
    if (!handCards.containsKey(player)) {
      final var message = String.format("Player %s does not play in hand", player);
      throw new IllegalArgumentException(message);
    }
    return new HandOfPlayer(player, handCards.get(player));
  }

  @Override
  public Stream<Card> remainingDeck() {
    return deck.cards();
  }

  @Override
  public int numberOfRemainingCards() {
    return deck.remainingCards();
  }

  @Override
  public Player turn() {
    return null;
  }

  @Override
  public Game play(Player player, List<Card> cards) {
    return null;
  }

  @Override
  public Optional<Layout> currentLayout() {
    return Optional.empty();
  }

  @Override
  public Game pickup(Player player) {
    return null;
  }

  private Map<Player, List<Card>> buildHandCardsMap(List<Player> players,
      DrawFromDeckResult drawResult) {
    final Map<Player, List<Card>> freshMap = new HashMap<>();
    IntStream.range(0, players.size()).forEach(i -> {
      final var player = players.get(i);
      final var cards = drawResult.cards()
          .subList(i * NUMBER_OF_CARDS_PER_PLAYER, i * NUMBER_OF_CARDS_PER_PLAYER + 7);
      freshMap.put(player, Collections.unmodifiableList(cards));
    });
    return freshMap;
  }
}
