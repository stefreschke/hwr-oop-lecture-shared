package hwr.oop.examples.lambdas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class LambdaTest {

  @Test
  void runnable() {
    Runnable r = () -> System.out.println("Hello World!");
    r.run();
  }

  @Test
  void supplier() {
    Supplier<String> supplier = () -> "Hello World!";
    final String string = supplier.get();
    assertThat(string)
        .startsWith("Hello")
        .endsWith("World!");
  }

  @Test
  void consumer() {
    Consumer<String> consumer = s -> System.out.println("Hello " + s + "!");
    consumer.accept("Stefan");
    consumer.accept("Tobias");
    consumer.accept("Pizza");
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








