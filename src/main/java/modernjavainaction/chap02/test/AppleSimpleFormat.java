package modernjavainaction.chap02.test;

public class AppleSimpleFormat implements AppleFormater {

    @Override
    public String accept(Apple a) {
        return "An apple of " + a.getWeight() + " g";
    }
}
