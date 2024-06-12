package hwr.oop.huzur.tests.domain;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.CardConverter;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GamesWithFourPlayersTest {

  private CardConverter converter;
  private Game fixture;

  @BeforeEach
  void setUp() {
    converter = new CardConverter();
    fixture = fixture();
  }

  @Test
  void alphaBetaGammaPlay_DeltaCanFinish() {
    final var alpha = Player.id("alpha");
    final var beta = Player.id("beta");
    final var gamma = Player.id("gamma");
    final var delta = Player.id("delta");
    final var layoutCards = converter.parseCards("H7,S7,D9");
    final var answerCards = converter.parseCards("H3,S3,D3");
    final var secondAnswerCards = converter.parseCards("H2,S2,D2");
    // when
    final var afterGamma = fixture
        .play(alpha, layoutCards)
        .play(beta, answerCards)
        .play(gamma, secondAnswerCards);
    // then
    final var layoutOptional = afterGamma.currentLayout().orElseThrow();
    assertSoftly(softly -> {
      softly.assertThat(layoutOptional.finishedOnPlay()).isTrue();
      softly.assertThat(afterGamma.turn()).isEqualTo(delta);
      softly.assertThat(afterGamma.handOf(alpha).numberOfCards()).isEqualTo(4);
      softly.assertThat(afterGamma.handOf(beta).numberOfCards()).isEqualTo(4);
      softly.assertThat(afterGamma.handOf(gamma).numberOfCards()).isEqualTo(4);
      softly.assertThat(afterGamma.handOf(delta).numberOfCards()).isEqualTo(7);
    });
  }

  @Test
  void allPlayersPlayed_DeltaStartsNewLayout() {
    final var alpha = Player.id("alpha");
    final var beta = Player.id("beta");
    final var gamma = Player.id("gamma");
    final var delta = Player.id("delta");
    final var layoutCards = converter.parseCards("H7,S7,D9");
    final var answerCards = converter.parseCards("H3,S3,D3");
    final var secondAnswerCards = converter.parseCards("H2,S2,D2");
    final var finishCards = converter.parseCards("HA,SA,DA");
    // when
    final var afterGamma = fixture
        .play(alpha, layoutCards)
        .play(beta, answerCards)
        .play(gamma, secondAnswerCards)
        .play(delta, finishCards);
    // then
    final var layoutOptional = afterGamma.currentLayout();
    assertSoftly(softly -> {
      softly.assertThat(layoutOptional).isEmpty();
      softly.assertThat(afterGamma.turn()).isEqualTo(delta);
      softly.assertThat(afterGamma.handOf(alpha).numberOfCards()).isEqualTo(4);
      softly.assertThat(afterGamma.handOf(beta).numberOfCards()).isEqualTo(4);
      softly.assertThat(afterGamma.handOf(gamma).numberOfCards()).isEqualTo(4);
      softly.assertThat(afterGamma.handOf(delta).numberOfCards()).isEqualTo(4);
    });
  }

  @Test
  void alphaBetaGammaPlayed_DeltaPicksUp_DeltaHasAllCards() {
    final var alpha = Player.id("alpha");
    final var beta = Player.id("beta");
    final var gamma = Player.id("gamma");
    final var delta = Player.id("delta");
    final var layoutCards = converter.parseCards("H7,S7,D9");
    final var answerCards = converter.parseCards("H3,S3,D3");
    final var secondAnswerCards = converter.parseCards("H2,S2,D2");
    // when
    final var afterGamma = fixture
        .play(alpha, layoutCards)
        .play(beta, answerCards)
        .play(gamma, secondAnswerCards)
        .pickup(delta);
    // then
    final var layoutOptional = afterGamma.currentLayout();
    assertSoftly(softly -> {
      softly.assertThat(layoutOptional).isEmpty();
      softly.assertThat(afterGamma.turn()).isEqualTo(alpha);
      softly.assertThat(afterGamma.handOf(alpha).numberOfCards()).isEqualTo(4);
      softly.assertThat(afterGamma.handOf(beta).numberOfCards()).isEqualTo(4);
      softly.assertThat(afterGamma.handOf(gamma).numberOfCards()).isEqualTo(4);
      softly.assertThat(afterGamma.handOf(delta).numberOfCards()).isEqualTo(16);
    });
  }

  private Game fixture() {
    final var alpha = Player.id("alpha");
    final var beta = Player.id("beta");
    final var gamma = Player.id("gamma");
    final var delta = Player.id("delta");
    return Game.newBuilder()
        .id("1337")
        .playerOrder(alpha, beta, gamma, delta)
        .player(alpha).hasCards(converter.parseCards("H7,S7,D9,HT,ST,DJ,J1"))
        .player(beta).hasCards(converter.parseCards("H3,S3,D3,HK,SK,DK,J2"))
        .player(gamma).hasCards(converter.parseCards("H2,S2,D2,H8,S8,H9,S9"))
        .player(delta).hasCards(converter.parseCards("HA,SA,DA,HJ,SJ,HQ,SQ"))
        .trump(Color.CLUBS)
        .turn(alpha)
        .deck(Collections.emptyList())
        .build();
  }

}
