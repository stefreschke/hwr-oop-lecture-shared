package hwr.oop.huzur.cli;

import java.util.List;

public interface MutableCommand extends Command {
  String description();

  void parse(List<String> arguments);

  boolean isApplicable(List<String> arguments);
}
