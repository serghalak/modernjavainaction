package modernjavainaction.chap02.test;

import modernjavainaction.chap02.FilteringApples;

public class AppleWeightStringImpl implements AppleString {
    @Override
    public String getAppleString(FilteringApples.Apple apple) {
        return "This apple is " + apple.getWeight() + " grams.";
    }
}
