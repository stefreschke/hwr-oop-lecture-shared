package hwr.oop.huzur.tests.persistence;

import static hwr.oop.huzur.tests.Utils.resourceAsPath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import hwr.oop.huzur.domain.Color;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.CardConverter;
import hwr.oop.huzur.persistence.CsvFilePersistenceAdapter;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class LoadTestDataFromCsvFileTest {

  private static final String RESOURCE_FILE = "loadExampleTest.csv";
  private CardConverter converter;
  private Player alpha;
  private Player beta;
  private Player gamma;
  private CsvFilePersistenceAdapter sut;

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
  @Disabled("Saving Games not yet implemented")
  void savingGames() {
    fail("Saving Games not yet implemented");
  }


  @Test
  @Disabled("Loading layouts not yet implemented")
  void load1337_NoLayout() {
    fail("Loading layouts not yet implemented");
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
    SoftAssertions.assertSoftly(softly -> {
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
