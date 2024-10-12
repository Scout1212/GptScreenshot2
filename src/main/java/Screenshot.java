import java.awt.*;

public class Screenshot{
    private Robot sc;
    private static Screenshot instance;


    private Screenshot(){
        // code to take screenshot
        try {
            Robot sc = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

    }

    public static Screenshot  getInstance(){
        if(instance == null){
            instance = new Screenshot();
        }
        return instance;
    }

    public Image takeScreenshot(int x1, int y1, int x2, int y2){
        int ax1 = Math.min(x1, x2);
        int ay1 = Math.max(y1, y2);
        int ax2 = Math.max(x1, x2);
        int ay2 = Math.min(y1, y2);

        int width =  ax2 - ax1;
        int height = ay2 - ay1;

        Rectangle area = new Rectangle(ax1, ay1, ax2, ay2);
        Image image = sc.createScreenCapture(area);


        return image;
    }


}
