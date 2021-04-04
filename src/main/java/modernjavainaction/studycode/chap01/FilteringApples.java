package modernjavainaction.studycode.chap01;

import modernjavainaction.chap01.FilteringApples.Apple;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FilteringApples {
    public static void main(String[] args) {


        List<Apple> inventory = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red")
        );

//        Collections.sort(inventory, new Comparator<Apple>() {
//            @Override
//            public int compare(Apple o1, Apple o2) {
//                return o1.getWeight()-o2.getWeight();
//            }
//        });
        inventory.sort(Comparator.comparing(Apple::getWeight));

        System.out.println(inventory);
    }

}
