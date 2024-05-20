package hwr.oop.huzur.cli;

import java.io.PrintStream;

interface Command {

  void invoke(PrintStream out);
}
