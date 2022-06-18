package modernjavainaction.chap02.test.impl;

import modernjavainaction.chap02.test.Apple;
import modernjavainaction.chap02.test.AppleFormater;

public class AppleFancyFormat implements AppleFormater {

    @Override
    public String accept(Apple a) {
        String formatString = a.getWeight() > 150 ? "heavy" : "light";
        return "A " + formatString + " " + a.getColor() + " apple";
    }
}
