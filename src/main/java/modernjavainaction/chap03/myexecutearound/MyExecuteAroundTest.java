package modernjavainaction.chap03.myexecutearound;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MyExecuteAroundTest {

    public static void main(String[] args) {
        System.out.println(processFile(reader -> reader.readLine()+"\n"
                +reader.readLine()+"\n"
                + reader.readLine()));
    }

    private static String processFile(MyBuffredReaderProcessor processor){
        try (BufferedReader bufferedReader=
                     new BufferedReader(
                             new FileReader(
                                     new File("./file.txt")))){
            return processor.process(bufferedReader);
        }catch (IOException ex){
            System.err.println(ex);
        }
        return null;
    }

}
