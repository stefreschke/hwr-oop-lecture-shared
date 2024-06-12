package hwr.oop.huzur.persistence;

import hwr.oop.huzur.IOExceptionBomb;
import hwr.oop.huzur.application.ports.out.GameRepository;
import hwr.oop.huzur.domain.Game;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public final class CsvFilePersistenceAdapter implements GameRepository {

  private final Path csvFilePath;
  private final IOExceptionBomb ioExceptionBomb;

  public CsvFilePersistenceAdapter(Path csvFilePath) {
    this(csvFilePath, IOExceptionBomb.DONT);
  }

  public CsvFilePersistenceAdapter(Path csvFilePath, IOExceptionBomb ioExceptionBomb) {
    this.csvFilePath = csvFilePath;
    this.ioExceptionBomb = ioExceptionBomb;
  }

  @Override
  public Game loadById(String id) {
    Optional<String> result;
    try (var stuff = Files.newBufferedReader(csvFilePath)) {
      ioExceptionBomb.fire();  // used for testing, to simulate IOException
      result = stuff.lines()
          .filter(l -> l.startsWith(id))
          .findFirst();
    } catch (IOException e) {
      throw new CouldNotLoadException(e);
    }
    final var match = result;
    if (match.isPresent()) {
      final var row = match.get();
      final var csv = CsvRow.fromCsvFileLine(row);
      return csv.asGame();
    } else {
      throw new CouldNotLoadException("id is not available, " + id);
    }
  }

  @Override
  public void save(Game game) {
    try (final var writer = Files.newBufferedWriter(csvFilePath)) {
      ioExceptionBomb.fire();  // used for testing, to simulate IOException
      writer.append(CsvRow.fromGame(game).toString());
    } catch (IOException e) {
      throw new CouldNotSaveException(e);
    }
  }
}
