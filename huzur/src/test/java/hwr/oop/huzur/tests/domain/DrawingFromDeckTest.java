package hwr.oop.huzur.tests.domain;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.CardConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DrawingFromDeckTest {

  private Player alpha;
  private Player beta;
  private Player gamma;
  private CardConverter converter;

  @BeforeEach
  void setUp() {
    alpha = Player.id("alpha");
    beta = Player.id("beta");
    gamma = Player.id("gamma");
    converter = new CardConverter();
  }

  @Test
  void threeLayout_DrawsThreeCards_AgainSevenCardsInHand() {
    final var fixture = fixture();
    final var firstThreeCardsInDeck = converter.parseCards("C7,C8,C9");
    final var result = fixture.play(alpha, converter.parseCards("H7,S7,D9"));
    assertSoftly(softly -> {
      softly.assertThat(result.handOf(alpha).containsCards(firstThreeCardsInDeck)).isTrue();
      softly.assertThat(result.remainingDeck()).doesNotContainAnyElementsOf(firstThreeCardsInDeck);
      softly.assertThat(result.numberOfRemainingCards()).isEqualTo(7);
    });
  }

  @Test
  void fiveLayout_DrawsFiveCards_AgainSevenCardsInHand() {
    final var fixture = fixture();
    final var firstFiveCardsInDeck = converter.parseCards("C7,C8,C9,CT,CJ");
    final var result = fixture.play(alpha, converter.parseCards("H7,S7,D9,HT,ST"));
    assertSoftly(softly -> {
      softly.assertThat(result.handOf(alpha).containsCards(firstFiveCardsInDeck)).isTrue();
      softly.assertThat(result.remainingDeck()).doesNotContainAnyElementsOf(firstFiveCardsInDeck);
      softly.assertThat(result.numberOfRemainingCards()).isEqualTo(5);
    });
  }

  @Test
  void fiveLayout_BetaAnswers_BothDraw5Cards() {
    final var fixture = fixture();
    final var firstFiveCardsInDeck = converter.parseCards("C7,C8,C9,CT,CJ");
    final var secondFiveCardsInDeck = converter.parseCards("CQ,CK,C3,C2,CA");
    final var result = fixture
        .play(alpha, converter.parseCards("H7,S7,D9,HT,ST"))
        .play(beta, converter.parseCards("H3,S3,D3,HK,SK"));
    assertSoftly(softly -> {
      softly.assertThat(result.handOf(alpha).containsCards(firstFiveCardsInDeck)).isTrue();
      softly.assertThat(result.handOf(beta).containsCards(secondFiveCardsInDeck)).isTrue();
      softly.assertThat(result.remainingDeck())
          .doesNotContainAnyElementsOf(firstFiveCardsInDeck)
          .doesNotContainAnyElementsOf(secondFiveCardsInDeck);
      softly.assertThat(result.numberOfRemainingCards()).isEqualTo(0);
    });
  }

  @Test
  void fiveLayout_BetaAndGammaAnswer_AlphaBetaDraw_GammaTwoCardsLeft() {
    final var fixture = fixture();
    final var firstFiveCardsInDeck = converter.parseCards("C7,C8,C9,CT,CJ");
    final var secondFiveCardsInDeck = converter.parseCards("CQ,CK,C3,C2,CA");
    final var result = fixture
        .play(alpha, converter.parseCards("H7,S7,D9,HT,ST"))
        .play(beta, converter.parseCards("H3,S3,D3,HK,SK"))
        .play(gamma, converter.parseCards("HA,SA,DA,J1,J2"));
    assertSoftly(softly -> {
      softly.assertThat(result.handOf(alpha).containsCards(firstFiveCardsInDeck)).isTrue();
      softly.assertThat(result.handOf(beta).containsCards(secondFiveCardsInDeck)).isTrue();
      softly.assertThat(result.handOf(gamma).containsCards(firstFiveCardsInDeck)).isFalse();
      softly.assertThat(result.handOf(gamma).containsCards(secondFiveCardsInDeck)).isFalse();
      softly.assertThat(result.handOf(gamma).numberOfCards()).isEqualTo(2);
      softly.assertThat(result.remainingDeck())
          .doesNotContainAnyElementsOf(firstFiveCardsInDeck)
          .doesNotContainAnyElementsOf(secondFiveCardsInDeck);
      softly.assertThat(result.numberOfRemainingCards()).isEqualTo(0);
    });
  }

  private Game fixture() {
    return Game.newBuilder()
        .id("1337")
        .playerOrder(alpha, beta, gamma)
        .player(alpha).hasCards(converter.parseCards("H7,S7,D9,HT,ST,DJ,H8"))
        .player(beta).hasCards(converter.parseCards("H3,S3,D3,HK,SK,DK,S8"))
        .player(gamma).hasCards(converter.parseCards("HA,SA,DA,J1,J2,H9,S9"))
        .trump(Color.CLUBS)
        .turn(alpha)
        .deck(converter.parseCards("C7,C8,C9,CT,CJ,CQ,CK,C3,C2,CA"))
        .build();
  }
}
