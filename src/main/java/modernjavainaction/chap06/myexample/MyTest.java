package modernjavainaction.chap06.myexample;

import modernjavainaction.chap05.Trader;
import modernjavainaction.chap05.Transaction;
import modernjavainaction.chap06.Dish;

import java.util.*;
import java.util.stream.Collectors;

public class MyTest {

    public static void main(String[] args) {
        //getListTransactionForCurrency();
        //getTotalCountDishes();
        //getMaxCalories();
        //getSumaCalories();
        //getSummarizing();
        getOptional();
    }

    private static void getOptional() {
        List<Optional<String>> listOfOptionals = Arrays.asList(
                Optional.empty(),
                //Optional.of("foo"),
                Optional.empty()
                //Optional.of("bar")
                );
        List<String> filteredList = listOfOptionals.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        System.out.println(filteredList);
    }

    private static void getSummarizing() {

        final IntSummaryStatistics collect = Dish.menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println("Summarizing Info: " + collect);
    }

    private static void getSumaCalories() {
        final Integer collect = Dish.menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
        System.out.println("Total calories: " + collect);
    }

    private static void getListTransactionForCurrency() {

        final Map<Integer, List<Transaction>> collect = getTransaction().stream()
                .collect(Collectors.groupingBy(Transaction::getYear));
        System.out.println(collect);
    }

    private static void getTotalCountDishes() {
        final Long collect = Dish.menu.stream()
                .collect(Collectors.counting());

        final long count = Dish.menu.stream().count();

        System.out.println("count dishes: " + collect);
        System.out.println("count dishes: "  + count);
    }

    private static void getMaxCalories() {

        Comparator<Dish> dishCaloriesComparator =
                Comparator.comparingInt(Dish::getCalories);

        final Optional<Dish> collect = Dish.menu.stream()
                .collect(Collectors.maxBy(dishCaloriesComparator));
        final Dish dish = collect.orElse(null);
        System.out.println(dish.getCalories());
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
}
