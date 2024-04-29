package hwr.oop.examples.cli;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

// Just as a static class, to have a gist one pager.
// This is SUT. Thus, it should be under src/main!
public class InputOutputCli {

  private final PrintStream out;
  private final Scanner in;

  public InputOutputCli(OutputStream out, InputStream in) {
    this.out = new PrintStream(out);
    this.in = new Scanner(in);
  }

  public void start() {
    out.println("Enter number 1");
    int x = in.nextInt();
    out.println("Enter number 2");
    int y = in.nextInt();
    out.println("output: " + (x + y));
  }
}
