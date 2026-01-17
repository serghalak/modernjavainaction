package modernjavainaction.chap03.myreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestMyReader {

    public String processFile(BufferedReaderProcessor bufferedReaderProcessor) throws IOException {

        try (BufferedReader br = new BufferedReader(
                new FileReader(
                        "src/main/resources/modernjavainaction/chap03/data.txt"))) {
            //return br.readLine();
            return bufferedReaderProcessor.process(br);
        }
    }

    public static void main(String[] args) {
        TestMyReader reader = new TestMyReader();
        try {
            String line = reader.processFile(r -> r.readLine() + "-" + r.readLine());
            System.out.println("First line: " + line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
