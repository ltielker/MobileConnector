import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyPressedThread extends Thread {
    @Override
    public void run() {
        try {
            KeyEventDispatcher ked = new KeyEventDispatcher() {

                @Override
                public boolean dispatchKeyEvent(KeyEvent ke) {
                    if (ke.getID() == KeyEvent.KEY_TYPED && ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
//                        cancel();
                    }
                    return false;
                }
            };
            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(ked);

            sleep(5000);
            KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(ked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
