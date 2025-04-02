package hwr.oop.huzur.tests.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.CardConverter;
import hwr.oop.huzur.tests.ErrorHandlingTag;
import hwr.oop.huzur.tests.TestSetupTest;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LayoutsAndAnswersTest {

  private CardConverter converter;
  private Game fixture;

  @BeforeEach
  void setUp() {
    converter = new CardConverter();
    fixture = fixture();
  }

  @Test
  @TestSetupTest
  void fixtureTurn_Alpha() {
    final var alpha = Player.id("alpha");
    final Player turn = fixture.turn();
    assertThat(turn).isEqualTo(alpha);
  }

  @Test
  void alphaLaysThreeCard_BetaNextPlayer_AlphaFourCardsLeft() {
    final var alpha = Player.id("alpha");
    final var beta = Player.id("beta");
    final var layoutCards = converter.parseCards("H7,S7,D9");
    final var updatedGame = fixture.play(alpha, layoutCards);

    final var handOfAlpha = updatedGame.handOf(alpha);
    final var containsLayout = handOfAlpha.containsCards(layoutCards);
    assertSoftly(softly -> {
      softly.assertThat(containsLayout).isFalse();
      softly.assertThat(handOfAlpha.numberOfCards()).isEqualTo(4);
      softly.assertThat(updatedGame.turn()).isEqualTo(beta);
    });
  }

  @Test
  @ErrorHandlingTag
  void alphaLaysThreeCard_NoPairInCards_exception() {
    final var alpha = Player.id("alpha");
    final var layoutCards = converter.parseCards("HT,S7,D9");
    assertThatThrownBy(() -> fixture.play(alpha, layoutCards))
        .hasMessageContainingAll("0 pair", "TEN of HEARTS", "SEVEN of SPADES", "NINE of DIAMONDS");
  }

  @Test
  @ErrorHandlingTag
  void alphaLaysThreeCard_TwoCardsNotInHand_Exception() {
    final var alpha = Player.id("alpha");
    final var cards = converter.parseCards("HT,HA,SA");
    assertThatThrownBy(() -> fixture.play(alpha, cards))
        .hasMessageContainingAll("not in hand", "ACE of HEARTS", "ACE of SPADES");
  }

  @Test
  void alphaLaysSingleCard_betaIsNextPlayer() {
    final var alpha = Player.id("alpha");
    final var beta = Player.id("beta");
    final var card = converter.convert("H7");
    final var updatedGame = fixture.play(alpha, Collections.singletonList(card));
    final var layout = updatedGame.currentLayout().orElseThrow();
    assertSoftly(softly -> {
      softly.assertThat(updatedGame.turn()).isEqualTo(beta);
      softly.assertThat(updatedGame.handOf(alpha).containsCards(card)).isFalse();
      softly.assertThat(layout.startingPlayer()).isEqualTo(alpha);
      softly.assertThat(layout.cards()).contains(card);
      softly.assertThat(layout.hiddenCards()).isEmpty();
      softly.assertThat(layout.finishedOnPlay()).isFalse();
    });
  }

  @Test
  @ErrorHandlingTag
  void alphaLaysSingleCard_ButNotFromHisOwnHand_Exception() {
    final var alpha = Player.id("alpha");
    final var cards = List.of(converter.convert("J2"));
    assertThatThrownBy(() -> fixture.play(alpha, cards))
        .hasMessageContainingAll("not in hand", "Second Joker");
  }

  @Test
  @ErrorHandlingTag
  void alphaLaysTwoCards_Exception() {
    final var alpha = Player.id("alpha");
    final var cards = converter.parseCards("H7,S7");
    assertThatThrownBy(() -> fixture.play(alpha, cards))
        .hasMessageContainingAll("number of cards", "1", "3", "got 2");
  }

  @Test
  @ErrorHandlingTag
  void alphaLaysFourCards_Exception() {
    final var alpha = Player.id("alpha");
    final var cards = converter.parseCards("H7,S7,HT,ST");
    assertThatThrownBy(() -> fixture.play(alpha, cards))
        .hasMessageContainingAll("number of cards", "1", "3", "got 4");
  }

  @Test
  void alphaLaysFiveCards_TwoPairs_betaIsNextPlayer() {
    final var alpha = Player.id("alpha");
    final var beta = Player.id("beta");
    final var cards = converter.parseCards("H7,S7,D9,HT,ST");
    final var updatedGame = fixture.play(alpha, cards);
    assertThat(updatedGame.turn()).isEqualTo(beta);
  }

  @Test
  @ErrorHandlingTag
  void alphaLaysFiveCards_NoPairs_Exception() {
    final var alpha = Player.id("alpha");
    final var cards = converter.parseCards("H7,DJ,D9,HT,J1");
    assertThatThrownBy(() -> fixture.play(alpha, cards))
        .hasMessageContainingAll("got 0 pair", "expected", "2 pairs");
  }

  @Test
  @ErrorHandlingTag
  void alphaLaysFiveCards_OnlyOnePairs_Exception() {
    final var alpha = Player.id("alpha");
    final var cards = converter.parseCards("H7,S7,D9,HT,J1");
    assertThatThrownBy(() -> fixture.play(alpha, cards))
        .hasMessageContainingAll("got 1 pair", "expected", "2 pairs");
  }

  @Test
  @ErrorHandlingTag
  void alphaLaysFiveCards_TwoCardsNotFromHisHand_Exception() {
    final var alpha = Player.id("alpha");
    final var cards = converter.parseCards("H7,S7,D9,SK,DK");
    assertThatThrownBy(() -> fixture.play(alpha, cards))
        .hasMessageContainingAll("not in hand", "KING of SPADES", "KING of DIAMONDS");
  }

  @Test
  void alphaAndBetaPlay_GammaNextPlayer_AlphaAndBetaFourCardsLeft() {
    final var alpha = Player.id("alpha");
    final var beta = Player.id("beta");
    final var gamma = Player.id("gamma");
    final var layoutCards = converter.parseCards("H7,S7,D9");
    final var answerCards = converter.parseCards("H3,S3,D3");
    // when
    final var afterBeta = fixture
        .play(alpha, layoutCards)
        .play(beta, answerCards);
    // then
    final var layout = afterBeta.currentLayout().orElseThrow();
    final var handOfAlpha = afterBeta.handOf(alpha);
    final var handOfBeta = afterBeta.handOf(beta);
    assertSoftly(softly -> {
      softly.assertThat(afterBeta.turn()).isEqualTo(gamma);
      softly.assertThat(layout.startingPlayer()).isEqualTo(alpha);
      softly.assertThat(layout.cards()).containsExactlyInAnyOrderElementsOf(answerCards);
      softly.assertThat(layout.hiddenCards()).containsExactlyInAnyOrderElementsOf(layoutCards);
      softly.assertThat(layout.finishedOnPlay()).isTrue();
      softly.assertThat(handOfAlpha.numberOfCards()).isEqualTo(4);
      softly.assertThat(handOfAlpha.containsCards(layoutCards)).isFalse();
      softly.assertThat(handOfBeta.numberOfCards()).isEqualTo(4);
      softly.assertThat(handOfBeta.containsCards(answerCards)).isFalse();
    });
  }

  @Test
  void alphaPlays_BetaPicksUp_GammaNextPlayer_BetaHasCards() {
    final var alpha = Player.id("alpha");
    final var beta = Player.id("beta");
    final var gamma = Player.id("gamma");
    final var layoutCards = converter.parseCards("H7,S7,D9");
    // when
    final var afterBeta = fixture
        .play(alpha, layoutCards)
        .pickup(beta);
    // then
    final var layoutOptional = afterBeta.currentLayout();
    assertSoftly(softly -> {
      softly.assertThat(afterBeta.turn()).isEqualTo(gamma);
      softly.assertThat(layoutOptional).isEmpty();
      softly.assertThat(afterBeta.handOf(alpha).numberOfCards()).isEqualTo(4);
      softly.assertThat(afterBeta.handOf(alpha).containsCards(layoutCards)).isFalse();
      softly.assertThat(afterBeta.handOf(beta).numberOfCards()).isEqualTo(10);
      softly.assertThat(afterBeta.handOf(beta).containsCards(layoutCards)).isTrue();
    });
  }

  @Test
  void alphaAndBetaPlay_GammaPicksUp_AllPlayedCardsInGammasHand() {
    final var alpha = Player.id("alpha");
    final var beta = Player.id("beta");
    final var gamma = Player.id("gamma");
    final var layoutCards = converter.parseCards("H7,S7,D9");
    final var answerCards = converter.parseCards("H3,S3,D3");
    // when
    final var afterGamma = fixture
        .play(alpha, layoutCards)
        .play(beta, answerCards)
        .pickup(gamma);
    // then
    final var layoutOptional = afterGamma.currentLayout();
    assertSoftly(softly -> {
      softly.assertThat(afterGamma.turn()).isEqualTo(alpha);
      softly.assertThat(layoutOptional).isEmpty();
      softly.assertThat(afterGamma.handOf(alpha).numberOfCards()).isEqualTo(4);
      softly.assertThat(afterGamma.handOf(beta).numberOfCards()).isEqualTo(4);
      softly.assertThat(afterGamma.handOf(gamma).numberOfCards()).isEqualTo(13);
      softly.assertThat(afterGamma.handOf(gamma).containsCards(layoutCards)).isTrue();
      softly.assertThat(afterGamma.handOf(gamma).containsCards(answerCards)).isTrue();
    });
  }

  @Test
  @ErrorHandlingTag
  void alphaAndBetaPlay_BetaPlaysInsufficientlyStrongCards_Exception() {
    final var alpha = Player.id("alpha");
    final var beta = Player.id("beta");
    final var layoutCards = converter.parseCards("H7,S7,J1");
    final var answerCards = converter.parseCards("H3,S3,J2");
    final var afterAlpha = fixture.play(alpha, layoutCards);
    assertThatThrownBy(() -> afterAlpha.play(beta, answerCards))
        .hasMessageContainingAll(
            "not strong enough", "First Joker", "Second Joker");
  }

  @Test
  @ErrorHandlingTag
  void alphaPlays_GammaAnswersOutOfTurn_Exception() {
    final var alpha = Player.id("alpha");
    final var gamma = Player.id("gamma");
    final var layoutCards = List.of(converter.convert("H7"));
    final var answerCards = List.of(converter.convert("HA"));
    final var afterAlpha = fixture.play(alpha, layoutCards);
    assertThatThrownBy(() -> afterAlpha.play(gamma, answerCards))
        .hasMessageContainingAll("gamma", "out of turn", "next player", "beta");
  }

  @Test
  @ErrorHandlingTag
  void alphaPlaysThreeCards_BetaAnswersOnlyWithTwo_Exception() {
    final var alpha = Player.id("alpha");
    final var beta = Player.id("beta");
    final var layoutCards = converter.parseCards("H7,S7,D9");
    final var answerCards = converter.parseCards("H3,S3");
    final var afterAlpha = fixture.play(alpha, layoutCards);
    assertThatThrownBy(() -> afterAlpha.play(beta, answerCards))
        .hasMessageContainingAll("not enough cards", "got 2", "required 3");
  }

  @Test
  @ErrorHandlingTag
  void alphaPlaysThreeCards_BetaAnswersWithFour_Exception() {
    final var alpha = Player.id("alpha");
    final var beta = Player.id("beta");
    final var layoutCards = converter.parseCards("H7,S7,D9");
    final var answerCards = converter.parseCards("H3,S3,D3,J2");
    final var afterAlpha = fixture.play(alpha, layoutCards);
    assertThatThrownBy(() -> afterAlpha.play(beta, answerCards))
        .hasMessageContainingAll("too many cards", "got 4", "required 3");
  }

  @Test
  void alphaBetaGammaPlay_LayoutEmptied_StillGammasTurn() {
    final var alpha = Player.id("alpha");
    final var beta = Player.id("beta");
    final var gamma = Player.id("gamma");
    final var layoutCards = converter.parseCards("H7,S7,D9");
    final var answerCards = converter.parseCards("H3,S3,D3");
    final var finishCards = converter.parseCards("HA,SA,DA");
    // when
    final var afterGamma = fixture
        .play(alpha, layoutCards)
        .play(beta, answerCards)
        .play(gamma, finishCards);
    // then
    final var layoutOptional = afterGamma.currentLayout();
    assertSoftly(softly -> {
      softly.assertThat(afterGamma.turn()).isEqualTo(gamma);
      softly.assertThat(layoutOptional).isEmpty();
      softly.assertThat(afterGamma.handOf(alpha).numberOfCards()).isEqualTo(4);
      softly.assertThat(afterGamma.handOf(beta).numberOfCards()).isEqualTo(4);
      softly.assertThat(afterGamma.handOf(gamma).numberOfCards()).isEqualTo(4);
      softly.assertThat(afterGamma.handOf(gamma).containsCards(layoutCards)).isFalse();
      softly.assertThat(afterGamma.handOf(gamma).containsCards(answerCards)).isFalse();
    });
  }


  private Game fixture() {
    final var alpha = Player.id("alpha");
    final var beta = Player.id("beta");
    final var gamma = Player.id("gamma");
    return Game.newBuilder()
        .id("1337")
        .playerOrder(alpha, beta, gamma)
        .player(alpha).hasCards(converter.parseCards("H7,S7,D9,HT,ST,DJ,J1"))
        .player(beta).hasCards(converter.parseCards("H3,S3,D3,HK,SK,DK,J2"))
        .player(gamma).hasCards(converter.parseCards("HA,SA,DA,H8,S8,H9,S9"))
        .trump(Color.CLUBS)
        .turn(alpha)
        .deck(Collections.emptyList())
        .build();
  }
}
