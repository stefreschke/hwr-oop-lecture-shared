package hwr.oop.huzur.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class FixedHandGame implements Game {

  private final Color trump;
  private final List<Player> players;
  private final Map<Player, List<Card>> handCards;
  private final Deck deck;
  private final Player turn;
  private final Layout layout;

  private FixedHandGame(
      Color trump,
      List<Player> players,
      Map<Player, List<Card>> handCards,
      Deck deck,
      Player turn,
      Layout layout
  ) {
    Objects.requireNonNull(trump);
    Objects.requireNonNull(handCards);
    Objects.requireNonNull(deck);
    Objects.requireNonNull(turn);
    assertNoDuplicateCards(handCards, deck);

    this.trump = trump;
    this.players = players;
    this.handCards = Collections.unmodifiableMap(handCards);
    this.deck = deck;
    this.turn = turn;
    this.layout = layout;
  }

  private Player nextPlayer() {
    final int indexOfCurrentPlayer = players.indexOf(turn);
    final int indexOfNextPlayer = (indexOfCurrentPlayer + 1) % players.size();
    return players.get(indexOfNextPlayer);
  }

  private void assertNoDuplicateCards(Map<Player, List<Card>> handCards, Deck deck) {
    final var allCards = Stream.concat(
        handCards.values().stream().flatMap(Collection::stream), deck.cards()).toList();
    final var duplicates = listDuplicateUsingCollectionsFrequency(allCards);
    if (!duplicates.isEmpty()) {
      throw new IllegalArgumentException("Found duplicate cards: " + duplicates);
    }
  }

  public static FixedHandBuilder newBuilder() {
    return new FixedHandBuilder();
  }

  private List<Card> listDuplicateUsingCollectionsFrequency(List<Card> list) {
    return list.stream()
        .filter(i -> Collections.frequency(list, i) > 1)
        .distinct()
        .toList();
  }

  @Override
  public Stream<Player> players() {
    return handCards.keySet().stream();
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
    return turn;
  }

  @Override
  public Game play(Player player, List<Card> cards) {
    final var cardsOfPlayer = handCards.get(player);
    final var unContainedCards = cards.stream()
        .filter(c -> !cardsOfPlayer.contains(c))
        .toList();
    if (unContainedCards.isEmpty()) {
      final var newLayout = createNewLayout(player, cards);
      final var remainingPlayerCards = cardsOfPlayer.stream()
          .filter(c -> !cards.contains(c))
          .toList();
      final var nextPlayer = nextPlayer();
      return copy()
          .layout(newLayout)
          .player(player).hasCards(remainingPlayerCards)
          .turn(nextPlayer)
          .build();
    } else {
      throw new IllegalArgumentException("Invalid layout, cards not in hand: " + unContainedCards);
    }
  }

  @Override
  public Game pickup(Player player) {
    final var newCards = Stream.concat(
        handCards.get(player).stream(),
        layout.allCards()
    ).toList();
    return copy()
        .noLayout()
        .turn(nextPlayer())
        .player(player).hasCards(newCards)
        .build();
  }

  private Layout createNewLayout(Player player, List<Card> cards) {
    if (layout != null) {
      return Layout.on(this.layout, strenghtComparator(), player, cards);
    } else {
      return Layout.of(player, cards);
    }
  }

  @Override
  public Optional<Layout> currentLayout() {
    return Optional.ofNullable(layout);
  }

  private FixedHandBuilder copy() {
    return newBuilder()
        .trump(trump)
        .deck(deck.cards())
        .playerOrder(players)
        .playerAndCards(handCards);
  }

  @Override
  public Color trump() {
    return trump;
  }

  public static class FixedHandBuilder {

    private final HashMap<Player, List<Card>> map;
    private Color trump;
    private Deck deck;
    private Player turn;
    private Layout layout;
    private List<Player> players;

    private FixedHandBuilder() {
      this.map = new HashMap<>();
    }

    public FixedHandBuilder trump(Color color) {
      this.trump = color;
      return this;
    }

    public FixedHandBuilder deck(List<Card> cards) {
      this.deck = UnshuffledDeck.of(cards);
      return this;
    }

    public FixedHandBuilder deck(Stream<Card> cards) {
      return deck(cards.toList());
    }

    public FixedHandBuilderWithPlayer player(Player player) {
      return new FixedHandBuilderWithPlayer(this, player);
    }

    public FixedHandBuilder playerOrder(List<Player> players) {
      this.players = players;
      return this;
    }

    public FixedHandBuilder playerOrder(Player... players) {
      return playerOrder(Arrays.asList(players));
    }

    private FixedHandBuilder playerAndCards(Player player, List<Card> cards) {
      map.put(player, cards);
      return this;
    }

    public FixedHandBuilder playerAndCards(Map<Player, List<Card>> handCards) {
      map.clear();
      map.putAll(handCards);
      return this;
    }

    public FixedHandBuilder turn(Player player) {
      this.turn = player;
      return this;
    }

    public FixedHandBuilder noLayout() {
      this.layout = null;
      return this;
    }

    public FixedHandBuilder layout(Layout layout) {
      this.layout = layout;
      return this;
    }

    public Game build() {
      return new FixedHandGame(trump, players, map, deck, turn, layout);
    }
  }

  public static class FixedHandBuilderWithPlayer {

    private final FixedHandBuilder builder;
    private final Player player;

    private FixedHandBuilderWithPlayer(FixedHandBuilder builder, Player player) {
      this.builder = builder;
      this.player = player;
    }

    public FixedHandBuilder hasCards(List<Card> cards) {
      return builder.playerAndCards(player, cards);
    }
  }
}
