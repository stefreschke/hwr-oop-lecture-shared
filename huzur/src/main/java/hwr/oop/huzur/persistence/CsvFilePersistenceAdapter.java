package hwr.oop.huzur.persistence;

import hwr.oop.huzur.domain.Game;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class CsvFilePersistenceAdapter {

  private final Path csvFilePath;

  public CsvFilePersistenceAdapter(Path csvFilePath) {
    this.csvFilePath = csvFilePath;
  }

  public Game loadById(String id) {
    Optional<String> result;
    try (var stuff = Files.newBufferedReader(csvFilePath)) {
      result = stuff.lines()
          .filter(l -> l.startsWith(id))
          .findFirst();
    } catch (IOException e) {
      throw new IllegalStateException("idString is not available, " + id);
    }
    final var match = result;
    if (match.isPresent()) {
      final var row = match.get();
      final var csv = CsvRow.fromCsvFileLine(row);
      return csv.asGame();
    } else {
      throw new IllegalStateException("id is not available, " + id);
    }
  }

}
