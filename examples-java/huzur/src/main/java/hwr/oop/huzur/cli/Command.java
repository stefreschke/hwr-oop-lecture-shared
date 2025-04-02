package hwr.oop.huzur.cli;

import java.io.PrintStream;
import java.util.List;

interface Command {

  static List<MutableCommand> allCommands() {
    return List.of(
        new HelpCommand(),
        new NewGameCommand(null),
        new PlayOnGameCommand(null, null),
        new GameQueryCommand(null)
    );
  }

  void invoke(PrintStream out);
}
