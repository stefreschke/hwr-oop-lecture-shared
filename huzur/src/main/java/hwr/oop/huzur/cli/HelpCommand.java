package hwr.oop.huzur.cli;

import hwr.oop.huzur.application.ports.out.LoadGamePort;
import hwr.oop.huzur.application.ports.out.SaveGamePort;
import java.io.PrintStream;

public final class HelpCommand implements Command {

  @Override
  public void invoke(PrintStream out, SaveGamePort saveGamePort, LoadGamePort loadGamePort) {

  }
}
