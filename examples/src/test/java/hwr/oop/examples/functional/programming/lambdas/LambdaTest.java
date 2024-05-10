package hwr.oop.examples.functional.programming.lambdas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Function Programming: Lambdas")
class LambdaTest {

  @Test
  void runnable() {
    final var outputStream = new ByteArrayOutputStream();
    final var out = new PrintStream(outputStream);
    Runnable r = () -> out.print("Hello World!");
    r.run();
    assertThat(outputStream.toString())
        .startsWith("Hello")
        .contains("World")
        .endsWith("!");
  }

  @Test
  void supplier() {
    Supplier<String> supplier = () -> "Hello World!";
    final String string = supplier.get();
    assertThat(string)
        .startsWith("Hello")
        .contains("World")
        .endsWith("!");
  }

  @Test
  void consumer() {
    final var outputStream = new ByteArrayOutputStream();
    final var out = new PrintStream(outputStream);
    Consumer<String> consumer = s -> out.print("Hello " + s + "!");
    consumer.accept("Stefan");
    consumer.accept("Tobias");
    consumer.accept("Pizza");
    assertThat(outputStream.toString())
        .contains("Stefan", "Tobias", "Pizza");
  }

  @Test
  void function() {
    Function<Integer, String> intToString = i -> Integer.toString(i);
    final String oneString = intToString.apply(1);
    final String twoString = intToString.apply(2);
    assertSoftly(softly -> {
      softly.assertThat(oneString).isEqualTo("1");
      softly.assertThat(twoString).isEqualTo("2");
    });
  }
}








