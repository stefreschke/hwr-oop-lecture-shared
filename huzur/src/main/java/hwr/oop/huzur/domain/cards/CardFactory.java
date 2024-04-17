package hwr.oop.huzur.domain.cards;

import hwr.oop.huzur.domain.Card;
import hwr.oop.huzur.domain.Color;
import hwr.oop.huzur.domain.Sign;
import java.util.Arrays;
import java.util.stream.Stream;

public class CardFactory {

  public Stream<Card> createAllCards() {
    return Stream.concat(createNormalCards(), createJokers());
  }

  private static Stream<Card> createJokers() {
    return Stream.of(Joker.first(), Joker.second());
  }

  private Stream<Card> createNormalCards() {
    return Arrays.stream(Color.values()).flatMap(this::createCard);
  }

  private Stream<Card> createCard(Color color) {
    return Arrays.stream(Sign.values()).map(sign -> new NormalCard(color, sign));
  }
}
