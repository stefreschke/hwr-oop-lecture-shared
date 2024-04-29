package hwr.oop.examples.cli;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

// Just as a static class, to have a gist one pager.
// This is SUT. Thus, it should be under src/main!
public class OutputOnlyCli {

  private final PrintStream out;

  public OutputOnlyCli(OutputStream out) {
    this.out = new PrintStream(out);
  }

  public void handle(List<String> arguments) {
    final var result = arguments.stream()
        .map(Integer::valueOf)
        .reduce(Integer::sum)
        .orElseThrow();
    out.println("result: " + result);
  }
}
