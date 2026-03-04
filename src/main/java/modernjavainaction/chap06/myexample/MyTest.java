package modernjavainaction.chap06.myexample;

import modernjavainaction.chap05.Trader;
import modernjavainaction.chap05.Transaction;
import modernjavainaction.chap06.Dish;
import modernjavainaction.chap06.GroupingTransactions;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.maxBy;
import static modernjavainaction.chap06.Dish.dishTags;

public class MyTest {


    public enum CaloricLevel {DIET, NORMAL, FAT};

    public static void main(String[] args) {
        //getListTransactionForCurrency();
        //getTotalCountDishes();
        //getMaxCalories();
        //getSumaCalories();
        //getSummarizing();
        //getOptional();
        //getGroupingBy();
        //getGroupingByCaloricLevel();
        //getCaloricDishesByType();
        //getCaloricDishesNamesByType();
        //dishNamesByType();
        //dishesByTypeCaloricLevel();
        //getGroupingByDishTypeCount();
        //getMostCaloricByType();
        //getMostCaloricByTypeWithoutOptional();
        //getVegetarianDishesByType();
        getMostCaloricPartitionedByVegetarian();
    }

    private static void getMostCaloricPartitionedByVegetarian() {
        Map<Boolean, Dish> collect = Dish.menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian,
                        Collectors.collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(collect);
    }

    private static void getVegetarianDishesByType() {
        Map<Boolean, Map<Dish.Type, List<Dish>>> collect = Dish.menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
        System.out.println(collect);
    }


    private static void getMostCaloricByTypeWithoutOptional() {
        Map<Dish.Type, Dish> collect = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(
                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get
                        )));
        System.out.println(collect);
    }

    private static void getMostCaloricByType() {
        Map<Dish.Type, Optional<Dish>> collect = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(collect);
    }

    private static void getGroupingByDishTypeCount() {
        Map<Dish.Type, Long> collect = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println(collect);
    }

    private static void dishesByTypeCaloricLevel() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> collect = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })));
        System.out.println(collect);
    }

    private static void dishNamesByType() {
        Map<Dish.Type, Set<String>> collect = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.flatMapping(dish -> dishTags.get(dish.getName()).stream(), Collectors.toSet())
                ));
        System.out.println(collect);
    }

    private static void getGroupingByCaloricLevel() {
        Map<CaloricLevel, List<Dish>> collect = Dish.menu.stream()
                .collect(Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));
        System.out.println(collect);
    }

    private static void getCaloricDishesByType() {
        Map<Dish.Type, List<Dish>> collect = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.filtering(o -> o.getCalories() > 500, Collectors.toList())
                ));
        System.out.println(collect);
    }

    private static void getCaloricDishesNamesByType() {
        Map<Dish.Type, List<String>> collect = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.mapping(Dish::getName, Collectors.toList())));
        System.out.println(collect);
    }

    private static void getGroupingBy() {
        Map<GroupingTransactions.Currency, List<GroupingTransactions.Transaction>> collect =
                GroupingTransactions.transactions.stream()
                        .collect(Collectors.groupingBy(GroupingTransactions.Transaction::getCurrency));
        System.out.println(collect);
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
        System.out.println("count dishes: " + count);
    }

    private static void getMaxCalories() {

        Comparator<Dish> dishCaloriesComparator =
                Comparator.comparingInt(Dish::getCalories);

        final Optional<Dish> collect = Dish.menu.stream()
                .collect(maxBy(dishCaloriesComparator));
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
