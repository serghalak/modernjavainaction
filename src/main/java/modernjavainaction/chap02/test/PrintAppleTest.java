package modernjavainaction.chap02.test;

import java.util.Arrays;
import java.util.List;


public class PrintAppleTest {

    public static void main(String[] args) {

        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED));
        prettyPrintApple(inventory, new AppleFancyFormat());
        prettyPrintApple(inventory, new AppleSimpleFormat());
    }

    public static void prettyPrintApple(List<Apple> inventory,
                                        AppleFormater appleFormater) {
        for (Apple apple : inventory) {
            final String printApple = appleFormater.accept(apple);
            System.out.println(printApple);
        }
    }

}
