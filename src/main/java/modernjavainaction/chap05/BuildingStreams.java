package modernjavainaction.chap05;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStreams {

  public static void main(String... args) throws Exception {

    System.out.println("-------------start-------------");
//    String homeValue = System.getenv("path");
//    Stream<String> homeValuesStream = homeValue == null ? Stream.empty() : Stream.of(homeValue);
//    homeValuesStream.flatMap(s -> Arrays.stream(s.split(";"))).forEach(System.out::println);
    Stream<String> values =
            Stream.of("config", "user.home", "user")
                    .flatMap(key -> {
                      String prop = System.getProperty(key);
                      // skip null or empty properties, split non-null values into stream, trim and ignore empty parts
                      return (prop == null || prop.isEmpty())
                              ? Stream.empty()
                              : Arrays.stream(prop.split("\\\\"))
                                      .map(String::trim)
                                      .filter(s -> !s.isEmpty());
                    });
    values.forEach(System.out::println);

//    Stream.ofNullable(Arrays.stream(System.getProperties()))
//        .forEach(System.out::println);

//    Properties properties = System.getProperties();
//    properties.stringPropertyNames().stream().forEach(System.out::println);

    System.out.println("-------------end-------------");

    // Stream.of
    Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
    stream.map(String::toUpperCase).forEach(System.out::println);

    // Arrays.stream
    int[] numbers = { 2, 3, 5, 7, 11, 13 };
    System.out.println(Arrays.stream(numbers).sum());

    // Stream.iterate
    Stream.iterate(0, n -> n + 2)
        .limit(10)
        .forEach(System.out::println);

    // Fibonacci with iterate
    Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[1], t[0] + t[1] })
        .limit(10)
        .forEach(t -> System.out.printf("(%d, %d)", t[0], t[1]));

    Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[1], t[0] + t[1] })
        .limit(10)
        .map(t -> t[0])
        .forEach(System.out::println);

    // random stream of doubles with Stream.generate
    Stream.generate(Math::random)
        .limit(10)
        .forEach(System.out::println);

    // stream of 1s with Stream.generate
    IntStream.generate(() -> 1)
        .limit(5)
        .forEach(System.out::println);

    // replaced anonymous IntSupplier with a simple lambda
    IntStream.generate(() -> 2).limit(5).forEach(System.out::println);

    IntSupplier fib = new IntSupplier() {

      private int previous = 0;
      private int current = 1;

      @Override
      public int getAsInt() {
        int nextValue = previous + current;
        previous = current;
        current = nextValue;
        return previous;
      }

    };
    IntStream.generate(fib)
        .limit(10)
        .forEach(System.out::println);


    var resource = BuildingStreams.class.getClassLoader()
            .getResource("modernjavainaction/chap05/data.txt");

    if (resource == null) {
      throw new RuntimeException("File not founded в resources!");
    }
    long uniqueWords;
    try (Stream<String> lines = Files.lines(Paths.get(resource.toURI()), Charset.defaultCharset())) {
      uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
          .distinct()
          .count();
    }

    System.out.println("There are " + uniqueWords + " unique words in data.txt");
  }

}
