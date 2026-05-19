package hwr.oop.huzur.domain.cards;

import java.util.stream.Stream;

public final class CardFactory {

  private static Stream<Card> createJokers() {
    return Stream.of(Joker.first(), Joker.second());
  }

  public Stream<Card> createAllCards() {
    return Stream.concat(createNormalCards(), createJokers());
  }

  private Stream<Card> createNormalCards() {
    return Card.Color.all().stream().flatMap(this::createCardsOfColor);
  }

  private Stream<Card> createCardsOfColor(Card.Color color) {
    return Card.Sign.all().stream().map(sign -> new NormalCard(color, sign));
  }
}
