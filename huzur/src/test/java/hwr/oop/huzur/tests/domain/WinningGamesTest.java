package hwr.oop.huzur.tests.domain;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import hwr.oop.huzur.domain.FixedGameBuilder;
import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.CardConverter;
import hwr.oop.huzur.domain.cards.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WinningGamesTest {

  private Player turn;
  private Player next;
  private CardConverter converter;

  @BeforeEach
  void setUp() {
    turn = Player.id("turn");
    next = Player.id("nextPlayer");
    converter = new CardConverter();
  }

  @Test
  void nextPlayerHasSevenCards_AlphaLayoutSeven() {
    final var fixture = fixture()
        .player(next).hasCards(converter.parseCards("HA,SA,DA,C2,CA,J1,J2"))
        .build();
    final var fiveLayout = converter.parseCards("H7,S7,D9,HT,ST,C9,H8");
    final var result = fixture.play(turn, fiveLayout);
    assertSoftly(softly -> {
      softly.assertThat(result.gameIsOver()).isTrue();
      softly.assertThat(result.handOf(turn).numberOfCards()).isZero();
      softly.assertThat(result.handOf(turn).cards()).isEmpty();
      softly.assertThat(result.winner()).isPresent().get().isEqualTo(turn);
    });
  }

  private FixedGameBuilder fixture() {
    return Game.newBuilder()
        .playerOrder(turn, next)
        .player(turn).hasCards(converter.parseCards("H7,S7,D9,HT,ST,C9,H8"))
        .trump(Color.CLUBS)
        .turn(turn)
        .deck(Deck.empty());
  }

}
