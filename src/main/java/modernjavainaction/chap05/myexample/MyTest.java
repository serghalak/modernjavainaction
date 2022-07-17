package modernjavainaction.chap05.myexample;

import modernjavainaction.chap04.Dish;
import modernjavainaction.chap05.Trader;
import modernjavainaction.chap05.Transaction;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyTest {
    public static void main(String[] args) {
        //getLengthOfWorlds();
        //getUniqLetters();
        //getSquareForIntArray();
        //getDecard();
        //getCountOfDishes();
        //getCountOfDishes1();
        //taskTransactionTraders();
        //getPiphagorTriple();
        //getStream();
        //getUniqueWordsFromFile();
        getFibonachi();
    }

    private static void getLengthOfWorlds() {
        Dish.menu.stream().forEach(System.out::println);
        final List<Integer> collect = Dish.menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(collect);
    }



    private static void getUniqLetters() {
        List<String> list = Arrays.asList("Hello", "World");
        final List<String> collect = list.stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);

    }

    private static void getSquareForIntArray() {
        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        final List<Integer> collect = integers.stream()
                .map(operand -> operand * operand)
                .collect(Collectors.toList());
        //.forEach(System.out::println);

        System.out.println(collect);

    }

    private static void getDecard() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        final List<int[]> collect = numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .filter(ints -> (ints[0] + ints[1]) % 3 == 0)
                .collect(Collectors.toList());
        //System.out.println(collect);
        collect.forEach(ints -> System.out.println(ints[0] + ":" + ints[1]));
    }

    private static void getCountOfDishes() {
        Integer reduce = Dish.menu.stream()
                .map(d -> 1)
                .reduce(0,(a, b) -> a +b);
        System.out.println("Total count dishes: " + reduce);
    }

    private static void getCountOfDishes1() {
        final long count = Dish.menu.stream().count();
        System.out.println("Total count dishes: " + count);
    }

    private static void taskTransactionTraders()  {
        final List<Transaction> collect = getTransaction().stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(collect);

        final List<String> collect1 = getTransaction().stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("cities: " + collect1);

        final List<Trader> cambridge = getTransaction().stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .collect(Collectors.toList());
        System.out.println(cambridge);

        final String collect2 = getTransaction().stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println(collect2);

        final boolean milan = getTransaction().stream()
                .map(transaction -> transaction.getTrader().getCity())
                .anyMatch(s -> s.equals("Milan"));
        String str = milan ? "Exist from Milan" : "Does not exist from Milan";
        System.out.println(str);

        final Optional<Integer> cambridge1 = getTransaction().stream()
                .filter(transaction -> transaction.getTrader()
                        .getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);
        System.out.println(cambridge1.orElse(0));

        final Optional<Integer> reduce = getTransaction().stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(reduce.orElse(0));

        final Optional<Integer> reduce1 = getTransaction().stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);
        System.out.println(reduce1.orElse(0));

    }

    private static List<Transaction> getTransaction() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        return transactions;
    }

    private static void getPiphagorTriple() {

//        final List<int[]> collect = IntStream.rangeClosed(1, 100).boxed()
//                .flatMap(a -> IntStream.rangeClosed(a, 100).boxed()
//                        .map(b -> new int[]{a, b}))
//                .collect(Collectors.toList());

        //collect.forEach(ints -> System.out.println(ints[0] + ":" + ints[1]));

        final List<int[]> collect = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}))
                .collect(Collectors.toList());

        //stream.forEach(ints -> System.out.println(ints[0] + ":" + ints[1] + "=" + ints[2]));
        //boxed.forEach(System.out::println);
    }

    private static void getStream() {

        int[] numbers = {2, 3, 5, 7, 11, 13};
        final IntStream stream = Arrays.stream(numbers);
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);

    }

    private static void getUniqueWordsFromFile() {
        long uniqueWords = 0;

        try(Stream<String> lines =
                    Files.lines(
                            Paths.get("c://data.txt"),
                            Charset.defaultCharset())) {
            final Set<String> collect = lines
                    .flatMap(s -> Arrays.stream(s.split(" ")))
                    .collect(Collectors.toSet());

            System.out.println(collect);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getFibonachi() {

        Stream.iterate(new int[]{0, 1}, i ->
            new int[] {i[1], (i[0] + i[1])})
                .limit(5)
                .forEach(t -> System.out.println(t[0] + ":" +t[1]));

    }


}
