package hwr.oop.huzur.tests.persistence.csv;

import static hwr.oop.huzur.tests.Utils.resourceAsPath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import hwr.oop.huzur.application.ports.out.LoadGamePort;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.CardConverter;
import hwr.oop.huzur.persistence.CsvFilePersistenceAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoadTestDataFromCsvFileTest {

  private static final String RESOURCE_FILE = "loadExampleTest.csv";
  private CardConverter converter;
  private Player alpha;
  private Player beta;
  private Player gamma;
  private LoadGamePort sut;

  @BeforeEach
  void setUp() {
    final var path = resourceAsPath(RESOURCE_FILE);
    alpha = Player.id("alpha");
    beta = Player.id("beta");
    gamma = Player.id("gamma");
    converter = new CardConverter();
    sut = new CsvFilePersistenceAdapter(path);
  }


  @Test
  void load42_HasLayout() {
    final var layoutCards = converter.parseCards("D7,S7,S8");
    final var game = sut.loadById("42");
    final var layout = game.currentLayout().orElseThrow();
    assertSoftly(softly -> {
      softly.assertThat(layout.isFinished()).isFalse();
      softly.assertThat(layout.cards()).containsExactlyInAnyOrderElementsOf(layoutCards);
      softly.assertThat(layout.hiddenCards()).isEmpty();
      softly.assertThat(layout.startingPlayer()).isEqualTo(alpha);
    });
  }

  @Test
  void load43_InitialLayout_AnsweredOnce() {
    final var layoutCards = converter.parseCards("D7,S7,S8");
    final var answerCards = converter.parseCards("DJ,SQ,SK");
    final var game = sut.loadById("43");
    final var layout = game.currentLayout().orElseThrow();
    assertSoftly(softly -> {
      softly.assertThat(game.turn()).isEqualTo(gamma);
      softly.assertThat(layout.isFinished()).isFalse();
      softly.assertThat(layout.cards()).containsExactlyInAnyOrderElementsOf(answerCards);
      softly.assertThat(layout.hiddenCards()).containsExactlyInAnyOrderElementsOf(layoutCards);
      softly.assertThat(layout.startingPlayer()).isEqualTo(alpha);
    });
  }

  @Test
  void load44_InitialLayout_AnsweredTwice() {
    final var layoutCards = converter.parseCards("D7,S7,S8");
    final var answerCards = converter.parseCards("DJ,SQ,SK");
    final var finishCards = converter.parseCards("D3,HK,H2");
    final var game = sut.loadById("44");
    final var layout = game.currentLayout().orElseThrow();
    assertSoftly(softly -> {
      softly.assertThat(game.turn()).isEqualTo(gamma);
      softly.assertThat(layout.isFinished()).isTrue();
      softly.assertThat(layout.cards()).containsExactlyInAnyOrderElementsOf(finishCards);
      softly.assertThat(layout.hiddenCards())
          .containsAll(layoutCards)
          .containsAll(answerCards);
      softly.assertThat(layout.startingPlayer()).isEqualTo(alpha);
    });
  }

  @Test
  void load1337_CurrentlyNoLayout() {
    final var game = sut.loadById("1337");
    final var optional = game.currentLayout();
    assertThat(optional).isNotPresent();
  }

  @Test
  void load1337_DeckHasCorrectRemainingCards() {
    final var game = sut.loadById("1337");
    final var deckCards = game.remainingDeck();
    final var expectedCards = converter.parseCards("H7,H8,H9,HT");
    assertThat(deckCards).containsExactlyElementsOf(expectedCards);
  }

  @Test
  void load1337_HeartsAreTrump() {
    final var game = sut.loadById("1337");
    assertThat(game.trump()).isEqualTo(Color.HEARTS);
  }

  @Test
  void load1337_AlphaBetaGammaArePlayers() {
    final var game = sut.loadById("1337");
    assertThat(game.players()).contains(alpha, beta, gamma);
  }

  @Test
  void load1337_HasCorrectHands() {
    final var game = sut.loadById("1337");
    final var handCardsAlpha = game.handOf(alpha).cards();
    final var handCardsBeta = game.handOf(beta).cards();
    final var handCardsGamma = game.handOf(gamma).cards();
    assertSoftly(softly -> {
      softly.assertThat(handCardsAlpha).containsExactlyElementsOf(
          converter.parseCards("D7,D8,D9,S7,S8,S9,J1")
      );
      softly.assertThat(handCardsBeta).containsExactlyElementsOf(
          converter.parseCards("DJ,DQ,DK,SJ,SQ,SK,J2")
      );
      softly.assertThat(handCardsGamma).containsExactlyElementsOf(
          converter.parseCards("DA,D2,D3,HK,H2,H3,HA")
      );
    });
  }
}
