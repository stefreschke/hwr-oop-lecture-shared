package hwr.oop.huzur.domain.layouts;

import hwr.oop.huzur.domain.GameCardContext;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class AnswerLayout implements Layout {

  private final List<Card> cards;
  private final List<Card> hiddenCards;
  private final int numberOfPlayers;
  private final int numberOfCards;
  private final Player startingPlayer;
  private final Layout previous;
  private final Player player;

  AnswerLayout(Layout previous, GameCardContext context, Player player, List<Card> cards) {
    Objects.requireNonNull(previous);
    Objects.requireNonNull(context);
    Objects.requireNonNull(player);

    this.cards = cards;
    this.numberOfPlayers = previous.numberOfPlayers();
    this.numberOfCards = previous.numberOfCards();
    this.startingPlayer = previous.startingPlayer();
    this.hiddenCards = previous.allCards().toList();
    this.previous = previous;
    this.player = player;

    assertCorrectNumberOfCards();
    assertCardsStrongEnough(previous, context.strenghtComparator());
  }

  private void assertCorrectNumberOfCards() {
    final int cardNumber = cards.size();
    if (cardNumber < numberOfCards) {
      throw new IllegalArgumentException(
          String.format("not enough cards, required %d, got %d", numberOfCards, cardNumber)
      );
    }
    if (cardNumber > numberOfCards) {
      throw new IllegalArgumentException(
          String.format("too many cards, required %d, got %d", numberOfCards, cardNumber)
      );
    }
  }

  private void assertCardsStrongEnough(
      Layout previous, Comparator<Card> comparator
  ) {
    final var strengthMap = buildStrengthMap(cards, previous, comparator);
    final var taken = new ArrayList<Card>();
    strengthMap.forEach((card, strongerCards) -> {
      final var remainingVs = strongerCards.stream().filter(v -> !taken.contains(v)).toList();
      if (remainingVs.isEmpty()) {
        final var notUsefulCards = cards.stream().filter(c -> !taken.contains(c)).toList();
        throw new IllegalArgumentException(
            String.format("not strong enough cards,"
                + " can't use any of %s on %s", notUsefulCards, card)
        );
      } else {
        final var firstValue = remainingVs.getFirst();
        taken.add(firstValue);
      }
    });
  }

  private Map<Card, List<Card>> buildStrengthMap(
      List<Card> cards, Layout previous, Comparator<Card> comparator
  ) {
    final var previousCards = previous.cards().toList();
    return previousCards.stream().collect(Collectors.toMap(
        Function.identity(),
        p -> cards.stream()
            .filter(c -> comparator.compare(p, c) < 0)
            .sorted(comparator)
            .toList()
    ));
  }

  @Override
  public Player startingPlayer() {
    return startingPlayer;
  }

  @Override
  public Stream<Card> cards() {
    return cards.stream();
  }

  @Override
  public Stream<Card> hiddenCards() {
    return hiddenCards.stream();
  }

  @Override
  public boolean isFinished() {
    final var expectedNumberOfCards = (long) cards.size() * numberOfPlayers;
    final var accumulatedCards = Stream.concat(cards(), hiddenCards()).count();
    return accumulatedCards == expectedNumberOfCards;
  }

  @Override
  public int numberOfPlayers() {
    return numberOfPlayers;
  }

  @Override
  public int numberOfCards() {
    return numberOfCards;
  }

  @Override
  public boolean finishedOnPlay() {
    return allCards().count() + numberOfCards == (long) numberOfCards * numberOfPlayers;
  }

  @Override
  public Optional<Layout> previous() {
    return Optional.ofNullable(previous);
  }

  @Override
  public Player player() {
    return player;
  }
}
