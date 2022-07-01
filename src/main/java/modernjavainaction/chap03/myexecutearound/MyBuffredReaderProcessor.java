package modernjavainaction.chap03.myexecutearound;

import java.io.BufferedReader;
import java.io.IOException;

public interface MyBuffredReaderProcessor {

    String process(BufferedReader reader) throws IOException;


}
