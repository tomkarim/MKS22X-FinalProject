import java.awt.event.*;

public class KeyListener extends KeyAdapter {

    public static boolean[] keyPressed = new boolean[256];

    @Override
    public void keyPressed(KeyEvent event) {
        keyPressed[event.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent event) {
        keyPressed[event.getKeyCode()] = false;
    }
}


