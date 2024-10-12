import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TessHandler{
    //temporary variables for local data paths;
    
    private Tesseract tess;

    private String windowsDataPath = "C:\\ProgramData\\chocolatey";
    private String macDataPath = "/opt/homebrew/Cellar/tesseract/5.4.1_1";

    public TessHandler() {
        String dataPath;
        System.setProperty("jna.library.path", windowsDataPath);
        try {
            dataPath = new File("").getCanonicalPath() + "/src/main/resources/tessdata";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tess = new Tesseract();

        tess.setDatapath(dataPath);
        tess.setLanguage("eng");

    }

    public String process(Image image) {
        String output;
        try {
            output = tess.doOCR((BufferedImage) image);
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        }

        System.out.println(output);

        return output;
    }
}
