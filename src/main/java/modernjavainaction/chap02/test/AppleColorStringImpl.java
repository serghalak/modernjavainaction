package modernjavainaction.chap02.test;

import modernjavainaction.chap02.FilteringApples;

public class AppleColorStringImpl implements AppleString {
    @Override
    public String getAppleString(FilteringApples.Apple apple) {
        return "This apple is " + apple.getColor();
    }
}
