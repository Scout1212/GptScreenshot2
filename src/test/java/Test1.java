import java.io.File;
import java.io.IOException;

public class Test1 {

    public static void main(String [] args){
        try {
            System.out.println(new File("").getCanonicalPath() + "/src/main/resources/tessdata");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
