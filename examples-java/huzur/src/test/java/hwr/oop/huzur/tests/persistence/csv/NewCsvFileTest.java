package hwr.oop.huzur.tests.persistence.csv;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import hwr.oop.huzur.application.ports.out.LoadGamePort;
import hwr.oop.huzur.application.ports.out.LoadGamePort.CouldNotLoadException;
import hwr.oop.huzur.application.ports.out.SaveGamePort;
import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.CardConverter;
import hwr.oop.huzur.persistence.CsvFilePersistenceAdapter;
import hwr.oop.huzur.tests.ErrorHandlingTag;
import hwr.oop.huzur.tests.TestSetupFailedException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NewCsvFileTest {

  private static final String FILE_NAME = "src/test/resources/tempFile.csv";

  private Path path;
  private final Player alpha = Player.id("alpha");
  private final Player beta = Player.id("beta");
  private final Player gamma = Player.id("gamma");
  private SaveGamePort saveGamePort;
  private LoadGamePort loadGamePort;
  private CardConverter converter;


  @BeforeEach
  void setUp() throws IOException {
    final var file = new File(FILE_NAME);
    file.createNewFile();
    this.path = file.toPath();
    final var adapter = new CsvFilePersistenceAdapter(path);
    this.saveGamePort = adapter;
    this.loadGamePort = adapter;
    try (var writer = Files.newBufferedWriter(path)) {
      writer.append("handId;playersAndStacks;smallBlindString;deckString;playsString");
    } catch (Exception e) {
      throw TestSetupFailedException.wrapping(e);
    }
    this.converter = new CardConverter();
  }

  @AfterEach
  void tearDown() {
    try {
      Files.delete(path);
    } catch (IOException e) {
      throw TestSetupFailedException.wrapping(e);
    }
  }

  @Test
  void saveGame_NoException() {
    final Game game = fixture();
    assertDoesNotThrow(() -> saveGamePort.save(game));
  }

  @Test
  void saveLoad_GameIsEqual() {
    final var fixture = fixture();
    saveGamePort.save(fixture);
    final var loaded = loadGamePort.loadById("1337");
    assertSoftly(softly -> {
      softly.assertThat(loaded.turn()).isEqualTo(fixture.turn());
      softly.assertThat(loaded.players()).containsAll(fixture.players().toList());
      softly.assertThat(loaded.remainingDeck()).containsAll(fixture.remainingDeck().toList());
      softly.assertThat(loaded.currentLayout()).isEmpty();
    });
  }

  @Test
  void playLayout_SaveLoad_LayoutIsEqual() {
    final var fixture = fixture();
    final var layoutCards = converter.parseCards("H7,S7,D9");
    final var afterLayout = fixture.play(alpha, layoutCards);
    saveGamePort.save(afterLayout);
    final var loaded = loadGamePort.loadById("1337");
    assertSoftly(softly -> {
      softly.assertThat(loaded.turn())
          .isEqualTo(afterLayout.turn())
          .isNotEqualTo(fixture.turn());
      softly.assertThat(loaded.currentLayout()).isPresent().get()
          .matches(l -> l.cards().toList().containsAll(layoutCards), "contains layout cards")
          .matches(l -> l.hiddenCards().toList().isEmpty(), "no hidden cards")
          .matches(l -> l.player().equals(alpha), "alpha is starting player")
          .matches(l -> l.startingPlayer().equals(alpha), "alpha is starting player")
          .matches(l -> l.numberOfCards() == 3, "3 cards");
    });
  }

  @Test
  void playLayoutAndAnswer_SaveLoad_LayoutIsEqual() {
    final var fixture = fixture();
    final var layoutCards = converter.parseCards("H7,S7,D9");
    final var answerCards = converter.parseCards("H3,S3,D3");
    final var afterAnswer = fixture.play(alpha, layoutCards).play(beta, answerCards);
    saveGamePort.save(afterAnswer);
    final var loaded = loadGamePort.loadById("1337");
    assertSoftly(softly -> {
      softly.assertThat(loaded.turn())
          .isEqualTo(afterAnswer.turn())
          .isNotEqualTo(fixture.turn());
      softly.assertThat(loaded.currentLayout())
          .isPresent().get()
          .matches(l -> l.cards().toList().containsAll(answerCards))
          .matches(l -> l.hiddenCards().toList().containsAll(layoutCards))
          .matches(l -> l.player().equals(beta))
          .matches(l -> l.startingPlayer().equals(alpha))
          .matches(l -> l.numberOfCards() == 3);
    });
  }

  @Test
  void playLayoutAnsweredAndFinished_SaveLoad_NoLayout() {
    final var fixture = fixture();
    final var layoutCards = converter.parseCards("H7,S7,D9");
    final var answerCards = converter.parseCards("H3,S3,D3");
    final var finishCards = converter.parseCards("HA,SA,DA");
    final var afterFinish = fixture
        .play(alpha, layoutCards)
        .play(beta, answerCards)
        .play(gamma, finishCards);
    saveGamePort.save(afterFinish);
    final var loaded = loadGamePort.loadById("1337");
    assertSoftly(softly -> {
      softly.assertThat(loaded.turn())
          .isEqualTo(afterFinish.turn())
          .isNotEqualTo(fixture.turn());
      softly.assertThat(loaded.currentLayout()).isEmpty();
    });
  }

  @Test
  void playLayoutAnsweredThenTaken_SaveLoad_NoLayout() {
    final var fixture = fixture();
    final var layoutCards = converter.parseCards("H7,S7,D9");
    final var answerCards = converter.parseCards("H3,S3,D3");
    final var afterFinish = fixture
        .play(alpha, layoutCards)
        .play(beta, answerCards)
        .pickup(gamma);
    saveGamePort.save(afterFinish);
    final var loaded = loadGamePort.loadById("1337");
    assertSoftly(softly -> {
      softly.assertThat(loaded.turn())
          .isEqualTo(afterFinish.turn())
          .isEqualTo(fixture.turn());
      softly.assertThat(loaded.currentLayout()).isEmpty();
    });
  }

  @Test
  void saveUpdateAndOriginal_LoadReturnsUpdate() {
    // given
    final var original = Game.fresh(Color.CLUBS).playedBy(alpha, beta, gamma);
    final var randomCardOfAlpha = original.handOf(alpha).cards().findFirst().orElseThrow();
    final var updated = original.play(alpha, List.of(randomCardOfAlpha));
    // when
    saveGamePort.save(original);
    final var firstLoad = loadGamePort.loadById(original.id().value());
    saveGamePort.save(updated);
    final var secondLoad = loadGamePort.loadById(original.id().value());
    // then
    assertSoftly(softly -> {
      softly.assertThat(firstLoad)
          .isNotNull()
          .matches(f -> f.turn().equals(alpha), "first load, alpha is turn")
          .matches(f -> f.currentLayout().isEmpty(), "first load, no layout");
      softly.assertThat(secondLoad)
          .isNotNull()
          .matches(s -> s.turn().equals(beta), "second load, beta is turn")
          .matches(s -> s.currentLayout().isPresent(), "second load, layout available");
    });
  }

  @Test
  @ErrorHandlingTag
  void loadNonExistingGame_ExceptionWithCorrectMessage() {
    assertThatThrownBy(() -> loadGamePort.loadById("1337"))
        .isInstanceOf(CouldNotLoadException.class)
        .hasMessageContainingAll("id is not available", "1337");
  }

  private Game fixture() {
    return Game.newBuilder()
        .id("1337")
        .playerOrder(alpha, beta, gamma)
        .player(alpha).hasCards(converter.parseCards("H7,S7,D9,HT,ST,DJ,J1"))
        .player(beta).hasCards(converter.parseCards("H3,S3,D3,HK,SK,DK,J2"))
        .player(gamma).hasCards(converter.parseCards("HA,SA,DA,H8,S8,H9,S9"))
        .noLayout()
        .trump(Color.CLUBS)
        .turn(alpha)
        .deck(Collections.emptyList())
        .build();
  }
}
