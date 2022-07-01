package modernjavainaction.chap02.generics;


import modernjavainaction.chap02.FilteringApples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PrintApplesWithGenerics {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED));
        //int[] arr = new int[]{1, 5, 8, 2, 4};
        List<Integer> arrInt = new ArrayList<>(Arrays.asList(1, 5, 8, 2, 4));
        final List<Apple> filtr = filtr(inventory, apple -> apple.weight > 150);
        final List<Integer> filtr1 = filtr(arrInt, num -> num > 4);
        System.out.println(filtr1);

        System.out.println(filtr);
    }

    public static <T> List<T> filtr(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }


    public static class Apple {

        private int weight;
        private Color color;

        public Apple(int weight, Color color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Apple{color=%s, weight=%d}", color, weight);
        }
    }

    enum Color {
        RED,
        GREEN
    }
}
