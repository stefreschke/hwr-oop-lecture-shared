package hwr.oop.huzur.tests.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.Card.Sign;
import hwr.oop.huzur.domain.cards.CardConverter;
import hwr.oop.huzur.domain.cards.Joker;
import hwr.oop.huzur.domain.cards.NormalCard;
import hwr.oop.huzur.tests.ErrorHandlingTag;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class CardsColorsSignsAndJokersTest {

  @Test
  void firstAndSecondJoker_HaveNoColor() {
    final var jokers = Stream.of(Joker.first(), Joker.second());
    assertSoftly(softly -> jokers.forEach(joker -> {
      softly.assertThat(joker.hasColor(Color.CLUBS)).isFalse();
      softly.assertThat(joker.hasColor(Color.HEARTS)).isFalse();
      softly.assertThat(joker.hasColor(Color.SPADES)).isFalse();
      softly.assertThat(joker.hasColor(Color.DIAMONDS)).isFalse();
    }));
  }

  @Test
  void firstAndSecondJoker_HaveNoSign() {
    final var jokers = Stream.of(Joker.first(), Joker.second());
    assertSoftly(softly -> jokers.forEach(joker -> {
      softly.assertThat(joker.hasSign(Sign.SEVEN)).isFalse();
      softly.assertThat(joker.hasSign(Sign.EIGHT)).isFalse();
      softly.assertThat(joker.hasSign(Sign.NINE)).isFalse();
      softly.assertThat(joker.hasSign(Sign.TEN)).isFalse();
      softly.assertThat(joker.hasSign(Sign.JACK)).isFalse();
      softly.assertThat(joker.hasSign(Sign.QUEEN)).isFalse();
      softly.assertThat(joker.hasSign(Sign.KING)).isFalse();
      softly.assertThat(joker.hasSign(Sign.THREE)).isFalse();
      softly.assertThat(joker.hasSign(Sign.TWO)).isFalse();
      softly.assertThat(joker.hasSign(Sign.ACE)).isFalse();
    }));
  }

  @Test
  void firstAndSecondJoker_DoNotHaveSameColor() {
    final var first = Joker.first();
    final var second = Joker.second();
    assertSoftly(softly -> {
      softly.assertThat(first.sameColorAs(second)).isFalse();
      softly.assertThat(second.sameColorAs(first)).isFalse();
    });
  }

  @Test
  void sevenOfSpades_HasColorSpades() {
    final var sevenOfSpades = new NormalCard(Color.SPADES, Sign.SEVEN);
    assertSoftly(softly -> {
      softly.assertThat(sevenOfSpades.hasColor(Color.SPADES)).isTrue();
      softly.assertThat(sevenOfSpades.hasColor(Color.CLUBS)).isFalse();
      softly.assertThat(sevenOfSpades.hasColor(Color.HEARTS)).isFalse();
      softly.assertThat(sevenOfSpades.hasColor(Color.DIAMONDS)).isFalse();
    });
  }

  @Test
  void sevenOfDiamonds_HasColorSpades() {
    final var sevenOfDiamonds = new NormalCard(Color.DIAMONDS, Sign.SEVEN);
    assertSoftly(softly -> {
      softly.assertThat(sevenOfDiamonds.hasColor(Color.DIAMONDS)).isTrue();
      softly.assertThat(sevenOfDiamonds.hasColor(Color.SPADES)).isFalse();
      softly.assertThat(sevenOfDiamonds.hasColor(Color.CLUBS)).isFalse();
      softly.assertThat(sevenOfDiamonds.hasColor(Color.HEARTS)).isFalse();
    });
  }

  @Test
  void aceOfSpades_IsOfSignAce() {
    final var aceOfSpades = new NormalCard(Color.SPADES, Sign.ACE);
    assertSoftly(softly -> {
      softly.assertThat(aceOfSpades.hasSign(Sign.ACE)).isTrue();
      softly.assertThat(aceOfSpades.hasSign(Sign.TWO)).isFalse();
      softly.assertThat(aceOfSpades.hasSign(Sign.SEVEN)).isFalse();
    });
  }

  @Test
  void kingOfSpades_IsOfSignKing() {
    final var kingOfSpades = new NormalCard(Color.SPADES, Sign.KING);
    assertSoftly(softly -> {
      softly.assertThat(kingOfSpades.hasSign(Sign.KING)).isTrue();
      softly.assertThat(kingOfSpades.hasSign(Sign.THREE)).isFalse();
      softly.assertThat(kingOfSpades.hasSign(Sign.QUEEN)).isFalse();
    });
  }

  @Test
  @ErrorHandlingTag
  void unknownColor_Exception() {
    final var converter = new CardConverter();
    assertThatThrownBy(() -> converter.convertColor("T"))
        .hasMessageContainingAll("Cannot convert String to Color", "expected [CDHS]", "got T");
  }
}
