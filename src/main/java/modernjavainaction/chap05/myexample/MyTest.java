package modernjavainaction.chap05.myexample;

import modernjavainaction.chap04.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyTest {
    public static void main(String[] args) {
        //getLengthOfWorlds();
        //getUniqLetters();
        //getSquareForIntArray();
        getDecard();
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
}
