package modernjavainaction.chap02.test;

import scala.App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class PrintAppleTest {

    public static void main(String[] args) {

        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED));
        prettyPrintApple(inventory, new AppleFancyFormat());
        prettyPrintApple(inventory, new AppleSimpleFormat());
        System.out.println("---------------Lambda---------");
        prettyPrintAppleWithLamda(inventory,apple -> Color.GREEN.equals(apple.getColor()));
        System.out.println("----------stream-------");
        final List<Apple> appleStream =
                inventory.stream()
                    .filter(apple -> apple.getWeight() > 150)
                        .collect(Collectors.toList());

        System.out.println(appleStream);
    }

    public static void prettyPrintApple(List<Apple> inventory,
                                        AppleFormater appleFormater) {
        for (Apple apple : inventory) {
            final String printApple = appleFormater.accept(apple);
            System.out.println(printApple);
        }
    }

    public static void prettyPrintAppleWithLamda(
            List<Apple> inventory, Predicate<Apple>p) {

        List<Apple>result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple))
                result.add(apple);
        }
        System.out.println(result);
    }

}
