package hwr.oop.huzur.domain;

import hwr.oop.huzur.domain.cards.Card;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.Deck;
import hwr.oop.huzur.domain.layouts.Layout;
import hwr.oop.huzur.domain.layouts.LayoutType;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

final class FixedGame implements Game {

  private final Color trump;
  private final List<Player> players;
  private final Map<Player, List<Card>> handCards;
  private final Deck deck;
  private final Player turn;
  private final Layout layout;
  private final GameId gameId;

  FixedGame(
      GameId gameId,
      Color trump,
      List<Player> players,
      Map<Player, List<Card>> handCards,
      Deck deck,
      Player turn,
      Layout layout
  ) {
    Objects.requireNonNull(gameId);
    Objects.requireNonNull(trump);
    Objects.requireNonNull(handCards);
    Objects.requireNonNull(deck);
    Objects.requireNonNull(turn);
    assertNoDuplicateCards(handCards, deck);

    this.gameId = gameId;
    this.trump = trump;
    this.players = players;
    this.handCards = Collections.unmodifiableMap(handCards);
    this.deck = deck;
    this.turn = turn;
    this.layout = layout;
  }

  private List<Card> listDuplicateUsingCollectionsFrequency(List<Card> list) {
    return list.stream().filter(i -> Collections.frequency(list, i) > 1).distinct().toList();
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
    return deck.peek();
  }

  @Override
  public int numberOfRemainingCards() {
    return deck.remainingCards();
  }

  @Override
  public GameId id() {
    return gameId;
  }

  @Override
  public Player turn() {
    return turn;
  }

  @Override
  public Game play(Player player, List<Card> cards) {
    assertCorrectPlayer(player);
    final var handOfPlayer = handOf(player);
    assertNoUnContainedCards(cards, handOfPlayer);
    assertAllowedNumberOfCards(cards);
    final var newLayout = createLayoutBasedOnOldOne(player, cards);
    final var remainingPlayerCards = handOfPlayer.without(cards).toList();
    return updatedGameUsing(player, remainingPlayerCards, newLayout);
  }

  @Override
  public Game pickup(Player player) {
    if (!player.equals(turn)) {
      throw new WrongPlayerException(
          String.format("Not player %s's turn, player %s is the next player",
              player, turn));
    }
    if (layout == null) {
      throw new CannotPickupException("Cannot pickup, no layout present");
    }
    final var newCards = Stream.concat(handCards.get(player).stream(), layout.allCards()).toList();
    return copy().noLayout().turn(nextPlayer()).player(player).hasCards(newCards).build();
  }

  @Override
  public boolean gameIsOver() {
    return !listOfWinners().isEmpty();
  }

  @Override
  public Optional<Player> winner() {
    final var winners = listOfWinners();
    if (winners.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(winners.getFirst());
    }
  }

  @Override
  public Optional<Layout> currentLayout() {
    return Optional.ofNullable(layout);
  }

  @Override
  public Color trump() {
    return trump;
  }

  private void assertNoDuplicateCards(Map<Player, List<Card>> handCards, Deck deck) {
    final var allCards = Stream.concat(
        handCards.values().stream().flatMap(Collection::stream),
        deck.peek()
    ).toList();
    final var duplicates = listDuplicateUsingCollectionsFrequency(allCards);
    if (!duplicates.isEmpty()) {
      throw new IllegalArgumentException("Found duplicate cards: " + duplicates);
    }
  }

  private Player nextPlayer() {
    final int indexOfCurrentPlayer = players.indexOf(turn);
    final int indexOfNextPlayer = (indexOfCurrentPlayer + 1) % players.size();
    return players.get(indexOfNextPlayer);
  }

  private HandOfPlayer smallestHand() {
    return players.stream()
        .map(this::handOf)
        .reduce((a, b) -> a.numberOfCards() > b.numberOfCards() ? b : a)
        .orElseThrow();
  }

  private void assertAllowedNumberOfCards(List<Card> cards) {
    if (layout == null) {
      final var numberOfCards = cards.size();
      final var smallHand = smallestHand();
      final var numberOfCardsInSmallestHand = smallHand.numberOfCards();
      final var possibleLayouts = LayoutType.available(deck, numberOfCardsInSmallestHand)
          .map(LayoutType::numberOfCards)
          .sorted().toList();
      final var isAllowedLayout = possibleLayouts.stream()
          .anyMatch(l -> l >= numberOfCards);
      if (!isAllowedLayout) {
        final var smallHandPlayer = smallHand.player();
        throw new IllegalArgumentException(
            String.format(
                "too many cards, player %s has %d cards remaining, allowed layouts are %s",
                smallHandPlayer, numberOfCardsInSmallestHand, possibleLayouts)
        );
      }
    }
  }

  private void assertNoUnContainedCards(List<Card> cards, HandOfPlayer handOfPlayer) {
    final var unContainedCards = handOfPlayer.notIncluded(cards).toList();
    if (!unContainedCards.isEmpty()) {
      throw new IllegalArgumentException("Invalid layout, cards not in hand: " + unContainedCards);
    }
  }

  private Game updatedGameUsing(Player player, List<Card> remainingPlayerCards, Layout newLayout) {
    final var builder = copyWithUpdatedDeckAndHand(player, remainingPlayerCards);
    if (newLayout.isFinished()) {
      return builder.noLayout().turn(player).build();
    } else {
      return builder.layout(newLayout).turn(nextPlayer()).build();
    }
  }

  private FixedGameBuilder copyWithUpdatedDeckAndHand(Player player,
      List<Card> remainingPlayerCards) {
    final var builder = copy();
    final var drawTarget = calculateDrawTarget(remainingPlayerCards);
    final var needToDrawCards = drawTarget != 0;
    if (needToDrawCards) {
      final var result = deck.draw(drawTarget);
      final var newHandCards = Stream.concat(
          remainingPlayerCards.stream(),
          result.cards()
      ).toList();
      return builder.player(player).hasCards(newHandCards).deck(result.deck());
    } else {
      return builder.player(player).hasCards(remainingPlayerCards);
    }
  }

  private int calculateDrawTarget(List<Card> remainingPlayerCards) {
    final var cardsToDraw = 7 - remainingPlayerCards.size();
    return Math.min(numberOfRemainingCards(), cardsToDraw);
  }

  private void assertCorrectPlayer(Player player) {
    if (!player.equals(turn)) {
      throw new IllegalArgumentException(
          String.format("%s played out of turn, next player is: %s", player, turn));
    }
  }

  private Layout createLayoutBasedOnOldOne(Player player, List<Card> cards) {
    if (layout == null) {
      return Layout.initial(players.size(), player, cards);
    } else {
      return Layout.answering(layout).with(player, cards).given(this);
    }
  }

  private List<Player> listOfWinners() {
    return handCards.entrySet().stream()
        .filter(e -> e.getValue().isEmpty())
        .map(Entry::getKey)
        .toList();
  }

  private FixedGameBuilder copy() {
    return Game.newBuilder()
        .id(gameId)
        .trump(trump)
        .deck(deck.peek())
        .playerOrder(players)
        .playerAndCards(handCards);
  }

}
