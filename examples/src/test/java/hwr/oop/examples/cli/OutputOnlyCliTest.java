package hwr.oop.examples.cli;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import org.junit.jupiter.api.Test;

class OutputOnlyCliTest {

  @Test
  void threeAndFour_OutputIsSeven() {
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
  void threeAndFive_OutputIsEight() {
    // given
    final OutputStream outputStream = new ByteArrayOutputStream();
    final var consoleUI = new OutputOnlyCli(outputStream);
    // when
    consoleUI.handle(List.of("3", "5"));
    // then
    final var outputText = outputStream.toString();
    assertThat(outputText).contains("8");
  }


}