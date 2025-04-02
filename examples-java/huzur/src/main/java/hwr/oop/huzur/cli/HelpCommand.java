package hwr.oop.huzur.cli;

import java.io.PrintStream;
import java.util.List;

public final class HelpCommand implements MutableCommand {

  @Override
  public String description() {
    return "help - display help message";
  }

  @Override
  public void parse(List<String> arguments) {
    // nothing to do
  }

  @Override
  public boolean isApplicable(List<String> arguments) {
    return arguments.getFirst().equals("help");
  }

  @Override
  public void invoke(PrintStream out) {
    final var commands = Command.allCommands();
    commands.forEach(command -> out.println(command.description()));
  }
}
