import javax.swing.*;
import java.awt.*;

public class Gui {
    private static Gui instance;
    private JFrame frame;
    private JTextField field;
    private Gui() {
        frame = new JFrame();
        field = new JTextField();

        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        field.setEditable(true);
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
        frame.setSize(image.getWidth(null), image.getHeight(null));
        frame.setVisible(true);
    }

    public void displayText(String text) {
       frame.getContentPane().removeAll();
       field.setText(text);
       frame.setSize(field.getSize().width, field.getSize().height);
    }
}
