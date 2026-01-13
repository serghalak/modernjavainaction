package modernjavainaction.chap02.test;

import modernjavainaction.chap02.FilteringApples;

public class AppleColorAndWeightStringImpl implements AppleString {
    @Override
    public String getAppleString(FilteringApples.Apple apple) {
        return "This apple is " + apple.getColor() + " and " + apple.getWeight() + " grams.";
    }
}
