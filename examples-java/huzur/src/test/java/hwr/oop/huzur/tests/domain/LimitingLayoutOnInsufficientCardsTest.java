package hwr.oop.huzur.tests.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import hwr.oop.huzur.domain.FixedGameBuilder;
import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.CardConverter;
import hwr.oop.huzur.domain.cards.Deck;
import hwr.oop.huzur.domain.cards.Joker;
import hwr.oop.huzur.domain.layouts.LayoutType;
import hwr.oop.huzur.tests.ErrorHandlingTag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LimitingLayoutOnInsufficientCardsTest {

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
  @ErrorHandlingTag
  void nextPlayerHasJustSixCards_AlphaLayoutSeven_Exception() {
    final var fixture = fixture()
        .player(next).hasCards(converter.parseCards("HA,SA,DA,CA,J1,J2"))
        .build();
    final var sevenLayout = converter.parseCards("H7,S7,D9,HT,ST,C9,H8");
    assertThatThrownBy(() -> fixture.play(turn, sevenLayout))
        .hasMessageContainingAll("too many cards", "nextPlayer", "6 cards remaining", "1", "3", "5")
        .hasMessageNotContainingAny("7");
  }

  @Test
  @ErrorHandlingTag
  void nextPlayerHasJustFourCards_AlphaLayoutFive_Exception() {
    final var fixture = fixture()
        .player(next).hasCards(converter.parseCards("HA,SA,DA,J1"))
        .build();
    final var fiveLayout = converter.parseCards("H7,S7,D9,HT,ST");
    assertThatThrownBy(() -> fixture.play(turn, fiveLayout))
        .hasMessageContainingAll("too many cards", "nextPlayer", "4 cards remaining", "1", "3")
        .hasMessageNotContainingAny("5", "7");
  }

  @Test
  @ErrorHandlingTag
  void nextPlayerHasJustTwoCards_AlphaLayoutThree_Exception() {
    final var fixture = fixture()
        .player(next).hasCards(converter.parseCards("HA,J1"))
        .build();
    final var threeLayout = converter.parseCards("H7,S7,D9");
    assertThatThrownBy(() -> fixture.play(turn, threeLayout))
        .hasMessageContainingAll("too many cards", "nextPlayer", "2 cards remaining", "1")
        .hasMessageNotContainingAny("3", "5", "7");
  }

  @Test
  void layoutTypes_max4Cards_SingleAndThreePossible() {
    final var stuff = LayoutType.available(Deck.empty(), 4);
    assertThat(stuff).contains(LayoutType.SINGLE, LayoutType.THREE);
  }

  @Test
  void layoutTypes_max6Cards_SingleThreeAndFivePossible() {
    final var stuff = LayoutType.available(Deck.empty(), 6);
    assertThat(stuff).contains(LayoutType.SINGLE, LayoutType.THREE, LayoutType.FIVE);
  }

  @Test
  void layoutTypes_max8Cards_EmptyDeck_SingleThreeFiveAndSevenPossible() {
    final var stuff = LayoutType.available(Deck.empty(), 8);
    assertThat(stuff).contains(LayoutType.SINGLE, LayoutType.THREE, LayoutType.FIVE,
        LayoutType.SEVEN);
  }

  @Test
  void layoutTypes_max8Cards_NonEmptyDeck_SingleThreeAndFivePossible() {
    final var stuff = LayoutType.available(Deck.unshuffled(Joker.first()), 8);
    assertThat(stuff).contains(LayoutType.SINGLE, LayoutType.THREE, LayoutType.FIVE);
  }

  private FixedGameBuilder fixture() {
    return Game.newBuilder()
        .id("1337")
        .playerOrder(turn, next)
        .player(turn).hasCards(converter.parseCards("H7,S7,D9,HT,ST,C9,H8"))
        .trump(Color.CLUBS)
        .turn(turn)
        .deck(Deck.empty());
  }

}
