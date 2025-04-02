package hwr.oop.huzur.tests.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import hwr.oop.huzur.domain.FixedGameBuilder;
import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.HandOfPlayer;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.CardConverter;
import hwr.oop.huzur.tests.ErrorHandlingTag;
import hwr.oop.huzur.tests.TestSetupTest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InitiallyDealingCardsTest {

  private CardConverter converter;
  private Player alpha;
  private Player beta;
  private Player gamma;

  @BeforeEach
  void setUp() {
    converter = new CardConverter();
    alpha = Player.id("alpha");
    beta = Player.id("beta");
    gamma = Player.id("gamma");
  }

  @Test
  void newGame_FourPlayer_ReturnsSetPlayers() {
    final var game = Game.fresh(Color.HEARTS).playedBy(List.of(alpha, beta, gamma));
    final var players = game.players();
    assertThat(players).contains(alpha, beta, gamma);
  }

  @Test
  @ErrorHandlingTag
  void newGame_OnlyOnePlayer_Exception() {
    assertThatThrownBy(() -> Game.fresh(Color.HEARTS).playedBy(List.of(alpha)))
        .hasMessageContainingAll("at least two players");
  }

  @Test
  void fixedGame_ThreeRemainingCardsInDeck() {
    final var game = fixture()
        .deck(converter.parseCards("HJ,SJ,CJ"))
        .build();
    assertSoftly(softly -> {
      softly.assertThat(game.remainingDeck()).hasSize(3);
      softly.assertThat(game.numberOfRemainingCards()).isEqualTo(3);
    });
  }

  @Test
  void fixedGame_Fixture_NoCardsInDeck() {
    final var fixture = fixture().build();
    assertSoftly(softly -> {
      softly.assertThat(fixture.remainingDeck()).isEmpty();
      softly.assertThat(fixture.numberOfRemainingCards()).isZero();
    });
  }

  @Test
  @ErrorHandlingTag
  void newGame_NoPlayers_Exception() {
    assertThatThrownBy(() -> Game.fresh(Color.HEARTS).playedBy(Collections.emptyList()))
        .hasMessageContainingAll("at least two players");
  }

  @Test
  @ErrorHandlingTag
  void newGame_HandOfUnknownPlayer_Exception() {
    final var game = Game.fresh(Color.HEARTS).playedBy(List.of(alpha, beta, gamma));
    assertThatThrownBy(() -> game.handOf(Player.id("delta")))
        .hasMessageContainingAll("delta", "does not play");
  }

  @Test
  void newGame_ThreePlayers_EachPlayerHasSevenCards() {
    final var game = Game.fresh(Color.HEARTS).playedBy(List.of(alpha, beta, gamma));
    assertSoftly(softly -> List.of(alpha, beta, gamma).forEach(player -> {
      final var handOfPlayer = game.handOf(player);
      final var cards = handOfPlayer.cards();
      softly.assertThat(cards).hasSize(7);
    }));
  }

  @Test
  void newGame_TwoPlayers_28CardsLeft() {
    final var deck = Game.fresh(Color.HEARTS).playedBy(List.of(alpha, beta));
    final int remainingCards = deck.numberOfRemainingCards();
    final var cards = deck.remainingDeck();
    assertSoftly(softly -> {
      softly.assertThat(remainingCards).isEqualTo(28);
      softly.assertThat(cards).hasSize(28);
    });
  }

  @Test
  void newGame_ThreePlayers_21CardsLeft() {
    final var deck = Game.fresh(Color.HEARTS).playedBy(List.of(alpha, beta, gamma));
    final int remainingCards = deck.numberOfRemainingCards();
    final var cards = deck.remainingDeck();
    assertSoftly(softly -> {
      softly.assertThat(remainingCards).isEqualTo(21);
      softly.assertThat(cards).hasSize(21);
    });
  }

  @Test
  void newGame_FourPlayers_14CardsLeft() {
    final var delta = Player.id("delta");
    final var deck = Game.fresh(Color.HEARTS).playedBy(List.of(alpha, beta, gamma, delta));
    final int remainingCards = deck.numberOfRemainingCards();
    final var cards = deck.remainingDeck();
    assertSoftly(softly -> {
      softly.assertThat(remainingCards).isEqualTo(14);
      softly.assertThat(cards).hasSize(14);
    });
  }

  @Test
  void newGame_ThreePlayers_EachPlayerHasIndividualCards() {
    final var game = Game.fresh(Color.HEARTS).playedBy(List.of(alpha, beta, gamma));
    final var allPlayersCards = Stream.of(alpha, beta, gamma).map(game::handOf)
        .flatMap(HandOfPlayer::cards).toList();
    final var distinctAllPlayerCards = allPlayersCards.stream().distinct();
    assertThat(distinctAllPlayerCards).hasSameSizeAs(allPlayersCards).hasSize(21);
  }

  @Test
  @ErrorHandlingTag
  void fixedGame_HandOfUnknownPlayer_Exception() {
    final var game = fixture().build();
    assertThatThrownBy(() -> game.handOf(Player.id("delta")))
        .hasMessageContainingAll("delta", "does not play");
  }

  @Test
  @ErrorHandlingTag
  void fixedGame_CardInPlayersHand_AndInDeck_Exception() {
    final var builder = fixture()
        .deck(converter.parseCards("J1"));
    assertThatThrownBy(builder::build)
        .hasMessageContainingAll("First Joker");
  }

  @Test
  @ErrorHandlingTag
  void fixedGame_CardTwiceInPlayersHand_Exception() {
    final var builder = fixture()
        .player(alpha).hasCards(converter.parseCards("H7,S7,D9,HK,ST,DJ,J1"))
        .player(beta).hasCards(converter.parseCards("H3,S3,D3,HK,SK,DK,J2"));
    assertThatThrownBy(builder::build)
        .hasMessageContainingAll("duplicate", "KING of HEARTS");
  }


  @Test
  @TestSetupTest
  void fixedGame_CardsDealtToPlayer() {
    final var fixture = fixture().build();
    assertSoftly(softly -> {
      softly.assertThat(fixture.players())
          .containsOnly(alpha, beta, gamma);
      softly.assertThat(fixture.trump())
          .isEqualTo(Color.CLUBS);
      softly.assertThat(fixture.handOf(alpha)
          .containsCards(converter.parseCards("H7,S7,D9,HT,ST,DJ,J1"))).isTrue();
      softly.assertThat(fixture.handOf(beta)
          .containsCards(converter.parseCards("H3,S3,D3,HK,SK,DK,J2"))).isTrue();
      softly.assertThat(fixture.handOf(gamma)
          .containsCards(converter.parseCards("HA,SA,DA,H8,S8,H9,S9"))).isTrue();
    });
  }

  private FixedGameBuilder fixture() {
    return Game.newBuilder()
        .id("1337")
        .player(alpha).hasCards(converter.parseCards("H7,S7,D9,HT,ST,DJ,J1"))
        .player(beta).hasCards(converter.parseCards("H3,S3,D3,HK,SK,DK,J2"))
        .player(gamma).hasCards(converter.parseCards("HA,SA,DA,H8,S8,H9,S9"))
        .turn(alpha)
        .trump(Color.CLUBS)
        .deck(Collections.emptyList());
  }


}
