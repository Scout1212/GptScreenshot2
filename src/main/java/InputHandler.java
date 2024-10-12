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
            GlobalScreen.addNativeKeyListener(this);
            GlobalScreen.addNativeMouseListener(this);
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

    public void nativeKeyPressed(NativeKeyEvent e) {
        if(!combo.contains(e.getKeyCode())) {
            combo.add(e.getKeyCode());
        }
    }


    public void nativeKeyReleased(NativeKeyEvent e) {
        combo.remove(Integer.valueOf(e.getKeyCode()));
    }
    private int x1, y1, x2, y2;

    public void nativeMousePressed(NativeMouseEvent e) {
        if(latch != null) {
            x1 = e.getX();
            y1 = e.getY();
            latch.countDown();
        }
    }


    public void nativeMouseReleased(NativeMouseEvent e) {
        if(latch != null) {
            x2 = e.getX();
            y2 = e.getY();
            latch.countDown();
        }
    }

    public void checkInput(){
        if(combo.contains(NativeKeyEvent.VC_CONTROL) && combo.contains(NativeKeyEvent.VC_S)) {
            System.out.println("sucess");
            combo.clear();
            JOptionPane.showMessageDialog(null, "Drag a box to screenshot");
            latch = new CountDownLatch(2);
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


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
