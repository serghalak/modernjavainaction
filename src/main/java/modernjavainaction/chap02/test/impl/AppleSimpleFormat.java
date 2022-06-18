package modernjavainaction.chap02.test.impl;

import modernjavainaction.chap02.test.Apple;
import modernjavainaction.chap02.test.AppleFormater;

public class AppleSimpleFormat implements AppleFormater {

    @Override
    public String accept(Apple a) {
        return "An apple of " + a.getWeight() + " g";
    }
}
