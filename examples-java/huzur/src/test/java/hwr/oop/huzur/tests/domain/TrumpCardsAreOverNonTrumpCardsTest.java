package hwr.oop.huzur.tests.domain;


import static hwr.oop.huzur.tests.Utils.allCardsOfColor;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.CardConverter;
import hwr.oop.huzur.domain.cards.Joker;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class TrumpCardsAreOverNonTrumpCardsTest {

  @Test
  void bothJokers_AreAlwaysTrump() {
    assertSoftly(softly -> {
      softly.assertThat(Joker.first().isAlwaysTrump()).isTrue();
      softly.assertThat(Joker.second().isAlwaysTrump()).isTrue();
    });
  }

  @ParameterizedTest
  @EnumSource(Color.class)
  void allGames_BothJokers_AreAlwaysTrump(Color trumpColor) {
    final var first = Joker.first();
    final var second = Joker.second();
    final var players = List.of(new Player("alpha"), new Player("beta"));
    final var game = Game.fresh(trumpColor).playedBy(players);
    assertSoftly(softly -> {
      softly.assertThat(game.isTrump(first)).isTrue();
      softly.assertThat(game.isTrump(second)).isTrue();
    });
  }

  @ParameterizedTest
  @EnumSource(Color.class)
  void allCardsOfTrumpColor_AreTrump(Color trumpColor) {
    final var players = List.of(new Player("alpha"), new Player("beta"));
    final var game = Game.fresh(trumpColor).playedBy(players);
    assertSoftly(softly -> allCardsOfColor(trumpColor)
        .forEach(card -> softly.assertThat(game.isTrump(card)).isTrue()));
  }

  @Test
  void heartsAreTrump_DiamondsClubsAndSpadesAreNotTrump() {
    final var players = List.of(new Player("alpha"), new Player("beta"));
    final var game = Game.fresh(Color.HEARTS).playedBy(players);
    assertSoftly(softly -> Stream.of(Color.DIAMONDS, Color.CLUBS, Color.SPADES)
        .forEach(color -> allCardsOfColor(color)
            .forEach(card -> softly.assertThat(game.isTrump(card)).isFalse())
        ));

  }

  @Test
  void spadesAreTrump_TrumpCards_SpadesAreInOrder() {
    final var players = List.of(new Player("alpha"), new Player("beta"));
    final var game = Game.fresh(Color.SPADES).playedBy(players);
    final var cmp = game.strenghtComparator();
    ComparatorAssert.sut(cmp)
        .weakerThan("S7", "S8")
        .weakerThan("S8", "S9")
        .weakerThan("S9", "ST")
        .weakerThan("ST", "SJ")
        .weakerThan("SJ", "SQ")
        .weakerThan("SQ", "SK")
        .weakerThan("SK", "S3")
        .weakerThan("S3", "S2")
        .weakerThan("S2", "SA")
        .run();
  }

  @Test
  void diamondsAreTrump_ColorCards_SpadesAreInOrder() {
    final var players = List.of(new Player("alpha"), new Player("beta"));
    final var game = Game.fresh(Color.DIAMONDS).playedBy(players);
    final var cmp = game.strenghtComparator();
    ComparatorAssert.sut(cmp)
        .weakerThan("S7", "S8")
        .weakerThan("S8", "S9")
        .weakerThan("S9", "ST")
        .weakerThan("ST", "SJ")
        .weakerThan("SJ", "SQ")
        .weakerThan("SQ", "SK")
        .weakerThan("SK", "S3")
        .weakerThan("S3", "S2")
        .weakerThan("S2", "SA")
        .run();
  }

  @Test
  void clubsAreTrump_SevenOfClubs_StrongerThanAllColorAces() {
    final var players = List.of(new Player("alpha"), new Player("beta"));
    final var game = Game.fresh(Color.CLUBS).playedBy(players);
    final var comparator = game.strenghtComparator();
    ComparatorAssert.sut(comparator)
        .strongerThan("C7", "HA")
        .strongerThan("C7", "DA")
        .strongerThan("C7", "SA")
        .run();
  }

  @Test
  void clubsAreTrump_AceOfSpades_NotStrongerThanSevenOfDiamonds() {
    final var players = List.of(new Player("alpha"), new Player("beta"));
    final var game = Game.fresh(Color.CLUBS).playedBy(players);
    final var comparator = game.strenghtComparator();
    ComparatorAssert.sut(comparator)
        .notStrongerThan("SA", "D7")
        .notStrongerThan("SA", "H7")
        .strongerThan("SA", "S7")
        .run();
  }

  @Test
  void heartsAreTrump_AceOfHearts_WeakerThanJokers() {
    final var players = List.of(new Player("alpha"), new Player("beta"));
    final var game = Game.fresh(Color.HEARTS).playedBy(players);
    final var comparator = game.strenghtComparator();
    ComparatorAssert.sut(comparator)
        .weakerThan("HA", "J1")
        .weakerThan("HA", "J2")
        .strongerThan("J1", "J2")
        .run();
  }

  private static class ComparatorAssert {

    private final SoftAssertions softly;
    private final Comparator<Card> sut;
    private final CardConverter converter;

    private static ComparatorAssert sut(Comparator<Card> sut) {
      return new ComparatorAssert(sut);
    }

    private ComparatorAssert(Comparator<Card> sut) {
      Objects.requireNonNull(sut);
      this.sut = sut;
      this.softly = new SoftAssertions();
      this.converter = new CardConverter();
    }

    private ComparatorAssert strongerThan(Card bigger, Card smaller) {
      softly.assertThat(sut.compare(bigger, smaller)).isPositive().isNotZero();
      softly.assertThat(sut.compare(smaller, bigger)).isNegative().isNotZero();
      return this;
    }

    private ComparatorAssert notStrongerThan(Card first, Card second) {
      softly.assertThat(sut.compare(first, second)).isNotPositive();
      softly.assertThat(sut.compare(second, first)).isNotPositive();
      return this;
    }

    private ComparatorAssert strongerThan(String bigger, String smaller) {
      return strongerThan(converter.convert(bigger), converter.convert(smaller));
    }

    private ComparatorAssert weakerThan(String smaller, String bigger) {
      return strongerThan(bigger, smaller);
    }

    public ComparatorAssert notStrongerThan(String first, String second) {
      return notStrongerThan(converter.convert(first), converter.convert(second));
    }

    private void run() {
      softly.assertAll();
    }
  }
}
