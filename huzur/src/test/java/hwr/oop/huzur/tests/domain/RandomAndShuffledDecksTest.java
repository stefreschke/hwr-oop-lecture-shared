package hwr.oop.huzur.tests.domain;

import static hwr.oop.huzur.tests.Utils.allCardsOfColor;
import static hwr.oop.huzur.tests.Utils.allCardsOfSign;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.Card.Sign;
import hwr.oop.huzur.domain.cards.CardConverter;
import hwr.oop.huzur.domain.cards.CardFactory;
import hwr.oop.huzur.domain.cards.Deck;
import hwr.oop.huzur.domain.cards.Joker;
import hwr.oop.huzur.domain.cards.NormalCard;
import hwr.oop.huzur.tests.ErrorHandlingTag;
import java.util.Collections;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class RandomAndShuffledDecksTest {

  private CardFactory cardFactory;

  @BeforeEach
  void setUp() {
    cardFactory = new CardFactory();
  }

  @Test
  void randomDeck_ContainsAllHuzurCards() {
    final var allCards = cardFactory.createAllCards().toList();
    final var randomDeck = Deck.random();
    final var result = randomDeck.peek();
    assertSoftly(softly -> {
      softly.assertThat(result)
          .containsExactlyInAnyOrderElementsOf(allCards);
    });
  }

  @Test
  void randomDeck_IsNotEmpty() {
    final var deck = Deck.random();
    assertThat(deck.isEmpty()).isFalse();
  }

  @Test
  void randomDeck_Draw42Cards_IsEmpty() {
    final var deck = Deck.random();
    final var result = deck.draw(42);
    final var updatedDeck = result.deck();
    assertThat(updatedDeck.isEmpty()).isTrue();
  }

  @Test
  void randomDeck_Shuffles() {
    final var allCards = cardFactory.createAllCards().toList();
    final var randomDeck = Deck.random();
    final var cards = randomDeck.peek().toList();
    assertThat(cards)
        .isNotEmpty()
        .doesNotContainSequence(allCards);
  }

  @Test
  void randomDeck_ObservationDoesNotChangeState() {
    final var randomDeck = Deck.random();
    final var first = randomDeck.peek().toList();
    final var second = randomDeck.peek().toList();
    assertThat(first).containsExactlyElementsOf(second);
  }

  @Test
  void unshuffledDeck_ContainsExactlyTheProvidedCards() {
    final var converter = new CardConverter();
    final var parsedCards = converter.parseCards("J1,HA,S7,D9,CJ,J2");
    final var doubleDeck = Deck.unshuffled(parsedCards);
    final var cards = doubleDeck.peek();
    assertThat(cards).hasSize(6).containsSequence(
        Joker.first(), new NormalCard(Color.HEARTS, Sign.ACE),
        new NormalCard(Color.SPADES, Sign.SEVEN), new NormalCard(Color.DIAMONDS, Sign.NINE),
        new NormalCard(Color.CLUBS, Sign.JACK), Joker.second()
    );
  }

  @RepeatedTest(
      value = 3,
      name = "({currentRepetition}/{totalRepetitions}), repeat, because of randomization"
  )
  void randomDeck_Draw_FirstCardIsRemovedInUpdate_OldDeckImmutable() {
    // if ever flaky, replace with fixed randomization test
    final var originalDeck = Deck.random();
    final var firstCard = originalDeck.peek().findFirst().orElseThrow();
    final var deckSize = originalDeck.remainingCards();
    final var drawResult = originalDeck.draw();
    final var updatedDeck = drawResult.deck();
    final SoftAssertions soft = new SoftAssertions();
    soft.assertThat(originalDeck.peek()).hasSize(deckSize).contains(firstCard);
    soft.assertThat(updatedDeck.peek()).hasSize(deckSize - 1).doesNotContain(firstCard);
    soft.assertAll();
  }

  @Test
  void unshuffledDeckOfTwoCards_Draw_FirstCardRemoved_OldDeckImmutable() {
    final var originalDeck = Deck.unshuffled(
        new NormalCard(Color.HEARTS, Sign.SEVEN),
        new NormalCard(Color.HEARTS, Sign.EIGHT)
    );
    final var firstCard = originalDeck.peek().findFirst().orElseThrow();
    final var deckSize = originalDeck.remainingCards();
    final var drawResult = originalDeck.draw();
    final var updatedDeck = drawResult.deck();
    final var soft = new SoftAssertions();
    soft.assertThat(originalDeck.peek()).hasSize(deckSize).contains(firstCard);
    soft.assertThat(updatedDeck.peek()).hasSize(deckSize - 1).doesNotContain(firstCard);
    soft.assertAll();
  }

  @Test
  void unshuffledDeck_OneCardLeft_Draw_FirstCardRemoved_OldDeckImmutable() {
    final var originalDeck = Deck.unshuffled(
        new NormalCard(Color.HEARTS, Sign.SEVEN)
    );
    final var drawResult = originalDeck.draw();
    final var updatedDeck = drawResult.deck();
    final SoftAssertions soft = new SoftAssertions();
    soft.assertThat(updatedDeck.peek()).isEmpty();
    soft.assertThat(updatedDeck.isEmpty()).isTrue();
    soft.assertAll();
  }

  @Test
  @ErrorHandlingTag
  void unshuffledDeck_EmptyDeck_Draw_Exception() {
    final var originalDeck = Deck.unshuffled(Collections.emptyList());
    assertThatThrownBy(originalDeck::draw)
        .hasMessageContainingAll("empty", "can not draw");
  }

  @Test
  void randomDeck_ContainsAll42HuzurCards() {
    final var randomDeck = Deck.random();
    final var cards = randomDeck.peek();
    assertThat(cards)
        .hasSize(42)
        // all colors
        .containsAll(allCardsOfColor(Color.CLUBS))
        .containsAll(allCardsOfColor(Color.SPADES))
        .containsAll(allCardsOfColor(Color.HEARTS))
        .containsAll(allCardsOfColor(Color.DIAMONDS))
        // all signs
        .containsAll(allCardsOfSign(Sign.TWO))
        .containsAll(allCardsOfSign(Sign.THREE))
        .containsAll(allCardsOfSign(Sign.SEVEN))
        .containsAll(allCardsOfSign(Sign.EIGHT))
        .containsAll(allCardsOfSign(Sign.NINE))
        .containsAll(allCardsOfSign(Sign.TEN))
        .containsAll(allCardsOfSign(Sign.JACK))
        .containsAll(allCardsOfSign(Sign.QUEEN))
        .containsAll(allCardsOfSign(Sign.KING))
        .containsAll(allCardsOfSign(Sign.ACE))
        // all jokers
        .contains(Joker.first(), Joker.second());
  }

  @Test
  @ErrorHandlingTag
  void randomDeck_Draw43Cards_Exception() {
    final var deck = Deck.random();
    assertThatThrownBy(() -> deck.draw(43))
        .hasMessageContainingAll("42 cards remaining", "draw 43 cards");
  }

  @Test
  @ErrorHandlingTag
  void unshuffledDeck_ThreeCards_DrawFour_Exception() {
    final Deck deck = Deck.unshuffled(
        new NormalCard(Color.HEARTS, Sign.TWO),
        new NormalCard(Color.HEARTS, Sign.THREE),
        new NormalCard(Color.HEARTS, Sign.SEVEN)
    );
    assertThatThrownBy(() -> deck.draw(4))
        .hasMessageContainingAll("draw 4", "3 cards remaining");
  }

  @Test
  void unshuffledDeck_ThreeCards_DrawTwo() {
    final Deck deck = Deck.unshuffled(
        new NormalCard(Color.HEARTS, Sign.TWO),
        new NormalCard(Color.HEARTS, Sign.THREE),
        new NormalCard(Color.HEARTS, Sign.SEVEN)
    );
    final var result = deck.draw(2);
    final var drawnCards = result.cards();
    final var newDeckState = result.deck();
    assertSoftly(softly -> {
      softly.assertThat(drawnCards).hasSize(2).contains(
          new NormalCard(Color.HEARTS, Sign.TWO),
          new NormalCard(Color.HEARTS, Sign.THREE)
      );
      softly.assertThat(newDeckState.remainingCards()).isOne();
    });
  }
}
