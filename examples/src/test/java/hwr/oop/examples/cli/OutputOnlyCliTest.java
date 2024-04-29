package hwr.oop.examples.cli;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class OutputOnlyCliTest {

  @Test
  void consoleUI_TypeThreeAndFour_OutputIsSeven() {
    // given
    final OutputStream outputStream = new ByteArrayOutputStream();
    final var consoleUI = new OutputOnlyCli(outputStream);
    // when
    consoleUI.handle(List.of("3", "4"));
    // then
    final var outputText = outputStream.toString();
    assertThat(outputText).contains("7");
  }

  @Test
  @Disabled("This is a manual tests, thus it should not be part of our test suite")
  void manualTest() {
    OutputOnlyCli ui = new OutputOnlyCli(System.out);
    ui.handle(List.of("asdf", "uiop"));
  }

}