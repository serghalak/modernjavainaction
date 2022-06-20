package modernjavainaction.chap02.test;

public class AppleFancyFormat implements AppleFormater {

    @Override
    public String accept(Apple a) {
        String formatString = a.getWeight() > 150 ? "heavy" : "light";
        return "A " + formatString + " " + a.getColor() + " apple";
    }
}
