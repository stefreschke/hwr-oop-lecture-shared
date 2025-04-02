package hwr.oop.huzur.tests.persistence.csv;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import hwr.oop.huzur.application.ports.out.LoadGamePort;
import hwr.oop.huzur.application.ports.out.SaveGamePort;
import hwr.oop.huzur.domain.Game;
import hwr.oop.huzur.domain.Player;
import hwr.oop.huzur.domain.cards.Card.Color;
import hwr.oop.huzur.domain.cards.CardConverter;
import hwr.oop.huzur.persistence.CsvFilePersistenceAdapter;
import hwr.oop.huzur.IOExceptionBomb;
import hwr.oop.huzur.tests.TestSetupFailedException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IOExceptionHandlingTest {

  private static final String FILE_NAME = "src/test/resources/tempFile.csv";

  private Path path;
  private final Player alpha = Player.id("alpha");
  private final Player beta = Player.id("beta");
  private final Player gamma = Player.id("gamma");
  private final String expectedMessage = IOExceptionBomb.MESSAGE;
  private SaveGamePort saveGamePort;
  private LoadGamePort loadGamePort;
  private CardConverter converter;


  @BeforeEach
  void setUp() throws IOException {
    final var file = new File(FILE_NAME);
    file.createNewFile();
    this.path = file.toPath();
    final var adapter = new CsvFilePersistenceAdapter(path, IOExceptionBomb.DO);
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
  void ioExceptionOnLoad_CouldNotLoad_Exception() {
    final var gameId = fixture().id().value();
    assertThatThrownBy(() -> loadGamePort.loadById(gameId))
        .hasMessageContainingAll(expectedMessage);
  }

  @Test
  void ioExceptionOnSave_CouldNotSave_Exception() {
    final var game = fixture();
    assertThatThrownBy(() -> saveGamePort.save(game))
        .hasMessageContainingAll(expectedMessage);
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
