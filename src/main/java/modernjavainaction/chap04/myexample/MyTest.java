package modernjavainaction.chap04.myexample;

import akka.stream.TLSProtocol;
import modernjavainaction.chap04.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyTest {

    public static void main(String[] args) {
        //getLowCaloriesSortedDishesName();
        getGroupByTypeDishes();
    }

    private static void getLowCaloriesSortedDishesName() {

        final List<String> collect = Dish.menu.stream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    private static void getGroupByTypeDishes() {
        final Map<Dish.Type, List<Dish>> collect = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));
        System.out.println(collect);
    }
}
