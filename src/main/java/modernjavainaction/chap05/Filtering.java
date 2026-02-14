package modernjavainaction.chap05;

import modernjavainaction.chap04.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static modernjavainaction.chap04.Dish.menu;

public class Filtering {

    public static void main(String... args) {
        // Filtering with predicate
        System.out.println("Filtering with a predicate");
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());
        vegetarianMenu.forEach(System.out::println);

        // Filtering unique elements
        System.out.println("Filtering unique elements:");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        // Slicing a stream
        // This list is sorted in ascending order of number of calories!
        List<Dish> specialMenu = Arrays.asList(
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER));

        System.out.println("-------------------Test from book page 140--------------------");
        specialMenu.stream()
                .filter(dish -> dish.getType() == Dish.Type.MEAT)
                .limit(2)
                .forEach(System.out::println);
        Stream.of("Hello", "World")
                .flatMap(word -> word.chars().boxed())
                .distinct()
                .forEach(c -> System.out.println((char) (int) c));

        int[] a = {1, 2, 3};
        int[] b = {3, 4};

        Arrays.stream(a)                // IntStream of a
                .boxed()                  // Stream<Integer>
                .flatMap(x -> Arrays.stream(b)
                        .mapToObj(y -> new int[]{x, y})) // Stream<int[]>
                .forEach(p -> System.out.printf("(%d, %d)%n", p[0], p[1]));


        //Optional<Integer> reduce =
        int sum = specialMenu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        //if (reduce.isPresent()) {
            System.out.println("Total calories: " + sum);
        //}

        System.out.println("---------------------------------------");

        System.out.println("Filtered sorted menu:");
        List<Dish> filteredMenu = specialMenu.stream()
                .filter(dish -> dish.getCalories() < 320)
                .collect(toList());
        filteredMenu.forEach(System.out::println);

        System.out.println("Sorted menu sliced with takeWhile():");
        List<Dish> slicedMenu1 = specialMenu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .collect(toList());
        slicedMenu1.forEach(System.out::println);

        System.out.println("Sorted menu sliced with dropWhile():");
        List<Dish> slicedMenu2 = specialMenu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .collect(toList());
        slicedMenu2.forEach(System.out::println);

        // Truncating a stream
        List<Dish> dishesLimit3 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList());
        System.out.println("Truncating a stream:");
        dishesLimit3.forEach(System.out::println);

        // Skipping elements
        List<Dish> dishesSkip2 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());
        System.out.println("Skipping elements:");
        dishesSkip2.forEach(System.out::println);
    }

}
