package hwr.oop.huzur.cli;

import java.io.PrintStream;
import java.util.List;

interface Command {

  void invoke(PrintStream out);
}
