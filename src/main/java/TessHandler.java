import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TessHandler extends Tesseract {


    public TessHandler() {
        String dataPath;
        System.setProperty("jna.library.path", "/opt/homebrew/Cellar/tesseract/5.4.1_1");
        try {
            dataPath = new File("").getCanonicalPath() + "/src/main/resources/Tess4J/tessdata";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setDatapath(dataPath);
        setLanguage("eng");

    }

    public String Process(Image image) {
        String output;
        try {
            output = doOCR((BufferedImage) image);
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        }

        return output;
    }
}
