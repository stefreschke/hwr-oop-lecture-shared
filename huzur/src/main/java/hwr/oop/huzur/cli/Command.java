package hwr.oop.huzur.cli;

import hwr.oop.huzur.application.ports.out.LoadGamePort;
import hwr.oop.huzur.application.ports.out.SaveGamePort;
import java.io.PrintStream;

interface Command {


  void invoke(PrintStream out, SaveGamePort saveGamePort, LoadGamePort loadGamePort);
}
