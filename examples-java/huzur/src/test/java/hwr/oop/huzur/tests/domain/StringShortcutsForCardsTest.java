package hwr.oop.huzur.tests.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.Card.Sign;
import hwr.oop.huzur.domain.cards.CardConverter;
import hwr.oop.huzur.domain.cards.Joker;
import hwr.oop.huzur.domain.cards.NormalCard;
import hwr.oop.huzur.tests.ErrorHandlingTag;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringShortcutsForCardsTest {

  private CardConverter converter;

  @BeforeEach
  void setUp() {
    converter = new CardConverter();
  }

  @ParameterizedTest
  @CsvSource(delimiter = ',', value = {
      "C,CLUBS",
      "H,HEARTS",
      "D,DIAMONDS",
      "S,SPADES",
  })
  void convertColors(String string, Color color) {
    final var cardString = string + "A";
    final var card = converter.convert(cardString);
    final var soft = new SoftAssertions();
    soft.assertThat(card.hasColor(color)).isTrue();
    soft.assertThat(card.hasSign(Sign.ACE)).isTrue();
    soft.assertAll();
  }

  @ParameterizedTest
  @CsvSource(delimiter = ',', value = {
      "7,SEVEN",
      "8,EIGHT",
      "9,NINE",
      "T,TEN",
      "J,JACK",
      "Q,QUEEN",
      "K,KING",
      "3,THREE",
      "2,TWO",
      "A,ACE",
  })
  void convertSigns(String string, Sign sign) {
    final var cardString = "H" + string;
    final var card = converter.convert(cardString);
    final var soft = new SoftAssertions();
    soft.assertThat(card.hasColor(Color.HEARTS)).isTrue();
    soft.assertThat(card.hasSign(sign)).isTrue();
    soft.assertAll();
  }

  @Test
  void convert_J1_FirstJoker() {
    final var card = converter.convert("J1");
    final var soft = new SoftAssertions();
    soft.assertThat(card).isEqualTo(Joker.first());
    soft.assertThat(card).isNotEqualTo(Joker.second());
    soft.assertAll();
  }

  @Test
  void convert_J2_SecondJoker() {
    final var card = converter.convert("J2");
    final var soft = new SoftAssertions();
    soft.assertThat(card).isEqualTo(Joker.second());
    soft.assertThat(card).isNotEqualTo(Joker.first());
    soft.assertAll();
  }

  @Test
  void multipleConversions_ReturnListOfCards() {
    final var cards = converter.parseCards("J1,HA,S7,D9,CJ,J2");
    assertThat(cards).hasSize(6).containsSequence(
        Joker.first(), new NormalCard(Color.HEARTS, Sign.ACE),
        new NormalCard(Color.SPADES, Sign.SEVEN), new NormalCard(Color.DIAMONDS, Sign.NINE),
        new NormalCard(Color.CLUBS, Sign.JACK), Joker.second()
    );
  }

  @Test
  @ErrorHandlingTag
  void convert_ThreeSignString_Exception() {
    assertThatThrownBy(() -> converter.convert("AHH"))
        .hasMessageContainingAll("AHH", "expected two", "got");
  }

  @Test
  @ErrorHandlingTag
  void convert_SingleSignString_Exception() {
    assertThatThrownBy(() -> converter.convert("A"))
        .hasMessageContainingAll("A", "expected two", "got");
  }

  @Test
  @ErrorHandlingTag
  void convert_ColorL_UnknownColor_Exception() {
    assertThatThrownBy(() -> converter.convert("LA"))
        .hasMessageContainingAll("L", "Cannot convert");
  }

  @Test
  @ErrorHandlingTag
  void convert_SignL_UnknownSign_Exception() {
    assertThatThrownBy(() -> converter.convert("DL"))
        .hasMessageContainingAll("L", "Cannot convert");
  }
}
