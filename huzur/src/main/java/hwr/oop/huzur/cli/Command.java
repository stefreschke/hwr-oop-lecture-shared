package hwr.oop.huzur.cli;

import hwr.oop.huzur.application.LoadGamePort;
import hwr.oop.huzur.application.SaveGamePort;
import java.io.PrintStream;

interface Command {

  void invoke(PrintStream out, SaveGamePort saveGamePort, LoadGamePort loadGamePort);
}
