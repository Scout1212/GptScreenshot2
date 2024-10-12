import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class InputHandler implements NativeKeyListener, NativeMouseListener {
    private CountDownLatch latch;
    private ArrayList<Integer> combo;
    private static InputHandler instance;

    private InputHandler() {
        combo = new ArrayList<>();
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
    }

    public static InputHandler getInstance() {
        if(instance == null){
            instance = new InputHandler();
        }

        return instance;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        combo.add(e.getKeyCode());
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        combo.remove(e.getKeyCode());
    }
    private int x1, y1, x2, y2;
    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
        if(latch != null) {
            x1 = e.getX();
            y1 = e.getY();
            latch.countDown();
        }
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
        if(latch != null) {
            x2 = e.getX();
            y2 = e.getY();
            latch.countDown();
        }
    }

    public void checkInput(){
        System.out.println(combo);
        if(combo.size() == 2){
            if(combo.contains(NativeKeyEvent.VC_META) && combo.contains(NativeKeyEvent.VC_S)){
                latch = new CountDownLatch(2);
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                JOptionPane.showMessageDialog(null, "Drag a box to screenshot");
                Screenshot ss = Screenshot.getInstance();
                Image image = ss.takeScreenshot(x1, y1, x2, y2);
                Gui gui = Gui.getInstance();
                gui.displayImage(image);
                TessHandler tessHandler = new TessHandler();
                String text = tessHandler.Process(image);
                gui.displayText(text);
            }
        }
    }



}
