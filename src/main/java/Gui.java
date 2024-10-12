import javax.swing.*;
import java.awt.*;

public class Gui {
    private static Gui instance;
    JFrame frame;
    private Gui() {
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    public static Gui getInstance() {
        if (instance == null) {
            instance = new Gui();
        }
        return instance;
    }

    public void displayImage(Image image) {
        frame.getContentPane().removeAll();
        frame.add(new JLabel(new ImageIcon(image)));
    }

    public void displayText(String text) {
        // code to display text
    }
}
