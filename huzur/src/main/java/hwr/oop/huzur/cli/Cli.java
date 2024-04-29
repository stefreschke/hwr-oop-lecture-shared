package hwr.oop.huzur.cli;

import hwr.oop.huzur.application.ports.out.LoadGamePort;
import hwr.oop.huzur.application.ports.out.SaveGamePort;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Cli {

  private final PrintStream out;
  private final SaveGamePort saveGamePort;
  private final LoadGamePort loadGamePort;
  private final ArgumentParser argumentParser;

  public Cli(OutputStream out, SaveGamePort saveGamePort, LoadGamePort loadGamePort) {
    this.out = new PrintStream(out);
    this.saveGamePort = saveGamePort;
    this.loadGamePort = loadGamePort;
    this.argumentParser = new ArgumentParser();
  }

  public void main(List<String> arguments) {
    final List<String> mutable = new ArrayList<>(arguments);
    if (mutable.contains("--debug")) {
      out.println("Welcome to Huzur, the funky mongolian card game!");
      out.println(" Arguments were: " + arguments);
      mutable.remove("--debug");
    }
    final var reduced = Collections.unmodifiableList(mutable);
    final var command = argumentParser.parse(reduced);
    command.invoke(out, saveGamePort, loadGamePort);
  }
}
