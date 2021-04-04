package modernjavainaction.chap01.mychap01;



import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyFilteringApples {

    public static void main(String... args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red")
        );
        sortApples(inventory);

    }

    public static void sortApples(List<Apple>apples){
//        Collections.sort(apples, new Comparator<Apple>() {
//            @Override
//            public int compare(Apple o1, Apple o2) {
//                return o1.getWeight()-o2.getWeight();
//            }
//        });
        //---------------lamdba
        apples.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(apples);
    }
}
