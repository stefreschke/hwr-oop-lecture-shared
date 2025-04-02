package hwr.oop.examples.cli;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("CLI: Input & Output (2023 and before)")
class InputOutputCliTest {

  @Test
  void consoleUI_TypeThreeAndFour_OutputIsSeven() {
    // given
    final var inputInBytes = "3\n4\n".getBytes();
    final var inputStream = (InputStream) new ByteArrayInputStream(inputInBytes);
    final var outputStream = new ByteArrayOutputStream();
    final var consoleUI = new InputOutputCli(outputStream, inputStream);
    // when
    consoleUI.start();
    // then
    final var output = retrieveResultFrom(outputStream);
    assertThat(output).isEqualTo("7");
  }

  @Test
  @SuppressWarnings("java:S2699")
  @Disabled("This is a manual tests, thus it should not be part of our test suite")
  void manualTest() {
    final var ui = new InputOutputCli(System.out, System.in);
    ui.start();
  }

  private String retrieveResultFrom(OutputStream outputStream) {
    final var outputText = outputStream.toString();
    final var key = "output:";
    return outputText.substring(outputText.indexOf(key) + key.length()).trim();
  }

}