package hwr.oop.huzur.tests;

import static hwr.oop.huzur.tests.Utils.allCardsOfColor;
import static hwr.oop.huzur.tests.Utils.allCardsOfSign;
import static org.assertj.core.api.Assertions.assertThat;

import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.Card.Sign;
import hwr.oop.huzur.domain.cards.NormalCard;
import org.junit.jupiter.api.Test;

class TestUtilsHuzurTest {

  @Test
  @TestSetupTest
  void allOfColor_ReturnsAllCardsOfAGivenColor() {
    final var all = Color.all();
    assertThat(all).hasSize(4);
    all.forEach(color -> {
      final var cards = allCardsOfColor(color);
      assertThat(cards).contains(
          new NormalCard(color, Sign.SEVEN),
          new NormalCard(color, Sign.EIGHT),
          new NormalCard(color, Sign.NINE),
          new NormalCard(color, Sign.TEN),
          new NormalCard(color, Sign.JACK),
          new NormalCard(color, Sign.QUEEN),
          new NormalCard(color, Sign.KING),
          new NormalCard(color, Sign.THREE),
          new NormalCard(color, Sign.TWO),
          new NormalCard(color, Sign.ACE)
      );
    });
  }

  @Test
  @TestSetupTest
  void allOfSign_ReturnsAllCardsOfAGivenColor() {
    final var all = Sign.all();
    assertThat(all).hasSize(10);
    all.forEach(sign -> {
      final var cards = allCardsOfSign(sign);
      assertThat(cards).contains(
          new NormalCard(Color.CLUBS, sign),
          new NormalCard(Color.DIAMONDS, sign),
          new NormalCard(Color.SPADES, sign),
          new NormalCard(Color.HEARTS, sign)
      );
    });
  }

}
