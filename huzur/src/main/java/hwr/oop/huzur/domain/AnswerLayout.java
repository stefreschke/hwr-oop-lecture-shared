package hwr.oop.huzur.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class AnswerLayout implements Layout {

  private final Layout previous;
  private final List<Card> cards;

  AnswerLayout(Layout previous, Comparator<Card> comparator, Player player, List<Card> cards) {
    Objects.requireNonNull(previous);
    Objects.requireNonNull(comparator);
    Objects.requireNonNull(player);

    this.previous = previous;
    this.cards = cards;

    assertValidCards(cards, previous, comparator);
  }

  private void assertValidCards(List<Card> cards, Layout previous, Comparator<Card> comparator) {
    final var previousCards = previous.cards().toList();
    final Map<Card, List<Card>> strengthMap = previousCards.stream().collect(Collectors.toMap(
        Function.identity(),
        p -> cards.stream()
            .filter(c -> comparator.compare(p, c) < 0)
            .sorted(comparator)
            .toList()
    ));
    final var taken = new ArrayList<Card>();
    strengthMap.forEach((k, vs) -> {
      final var remainingVs = vs.stream().filter(v -> !taken.contains(v)).toList();
      if (remainingVs.isEmpty()) {
        final var notUsefulCards = cards.stream().filter(c -> !taken.contains(c)).toList();
        throw new IllegalArgumentException(
            String.format("not strong enough cards,"
                + " can't use any of %s on %s", notUsefulCards, k)
        );
      } else {
        final var firstValue = remainingVs.getFirst();
        taken.add(firstValue);
      }
    });
  }

  @Override
  public Player startingPlayer() {
    return previous.startingPlayer();
  }

  @Override
  public Stream<Card> cards() {
    return cards.stream();
  }

  @Override
  public Stream<Card> hiddenCards() {
    return Stream.concat(previous.hiddenCards(), previous.cards());
  }
}
