package hwr.oop.huzur.domain.layouts;

import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class InitialLayout implements Layout {

  private final Player player;
  private final List<Card> cards;
  private final Map<Card, List<Card>> partnerMap;
  private final int numberOfPlayers;
  private final LayoutType type;
  private final int numberOfCards;

  InitialLayout(int numberOfPlayers, Player player, List<Card> cards) {
    Objects.requireNonNull(player);
    Objects.requireNonNull(cards);

    this.numberOfPlayers = numberOfPlayers;
    this.player = player;
    this.cards = cards;
    this.partnerMap = buildPartnerMap(cards);
    Objects.requireNonNull(partnerMap);

    this.type = LayoutType.of(cards);
    this.numberOfCards = cards.size();
    assertValidNumberOfPairs(cards);
  }

  private void assertValidNumberOfPairs(List<Card> cards) {
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

  @Override
  public boolean isFinished() {
    return false;
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
    return numberOfPlayers == 2;
  }

  @Override
  public Optional<Layout> previous() {
    return Optional.empty();
  }

  @Override
  public Player player() {
    return player;
  }

}
