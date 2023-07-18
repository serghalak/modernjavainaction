package modernjavainaction.chap06.myexample;

import org.junit.Test;

import java.time.Instant;

public class MyTestTest {

    public MyTestTest() {
    }

    @Test
     public void testInstantNow() {
        Instant instant = Instant.now();
        System.out.println("Instant.now(): " + instant);
    }
}