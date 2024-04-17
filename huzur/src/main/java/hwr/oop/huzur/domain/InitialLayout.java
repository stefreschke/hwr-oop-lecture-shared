package hwr.oop.huzur.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class InitialLayout implements Layout {

  private final Player player;
  private final List<Card> cards;
  private final Map<Card, List<Card>> partnerMap;

  InitialLayout(Player player, List<Card> cards) {
    Objects.requireNonNull(player);
    Objects.requireNonNull(cards);

    this.player = player;
    this.cards = cards;
    this.partnerMap = buildPartnerMap(cards);
    Objects.requireNonNull(partnerMap);

    assertValidNumberOfPairs(cards);
  }

  private void assertValidNumberOfPairs(List<Card> cards) {
    final var type = LayoutType.of(cards);
    final var accountedFor = new ArrayList<Card>();
    partnerMap.forEach((key, values) -> {
      final var match = values.stream()
          .filter(c -> !accountedFor.contains(c))
          .findAny();
      if (match.isPresent()) {
        accountedFor.add(key);
        accountedFor.add(match.get());
      }
    });
    final int numberOfPairs = accountedFor.size() / 2;
    if (numberOfPairs < type.pairsRequired()) {
      throw new IllegalArgumentException(
          String.format("Not enough pairs to play %d cards, got %d pairs,"
                  + " expected at least %d pairs in %s", cards.size(), numberOfPairs,
              type.pairsRequired(), cards)
      );
    }
  }

  private Map<Card, List<Card>> buildPartnerMap(List<Card> cards) {
    return Collections.unmodifiableMap(cards.stream().collect(Collectors.toMap(
        Function.identity(),
        c -> cards.stream()
            .filter(o -> o.sameSignAs(c))
            .filter(i -> !i.equals(c))
            .toList()
    )));
  }

  @Override
  public Player startingPlayer() {
    return player;
  }

  @Override
  public Stream<Card> cards() {
    return cards.stream();
  }

  @Override
  public Stream<Card> hiddenCards() {
    return Stream.empty();
  }

  private enum LayoutType {
    SINGLE(0), THREE(1), FIVE(2);

    private final int pairsRequired;

    LayoutType(int pairsRequired) {
      this.pairsRequired = pairsRequired;
    }

    private int pairsRequired() {
      return pairsRequired;
    }

    private static LayoutType of(int numberOfCards) {
      return switch (numberOfCards) {
        case 1 -> SINGLE;
        case 3 -> THREE;
        case 5 -> FIVE;
        default -> throw new IllegalArgumentException(
            String.format("Invalid number of cards, got %d cards, but expected any of {1, 3, 5}",
                numberOfCards));
      };
    }

    private static LayoutType of(List<Card> cards) {
      return of(cards.size());
    }
  }
}
