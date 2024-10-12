import net.sourceforge.tess4j.Tesseract;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TessHandler extends Tesseract {


    public TessHandler() {
        try {
            String filePath = new File("").getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setDatapath("tessdata");
        setLanguage("eng");

    }

    public String Process(Image image) {
        return "frick";
    }
}
