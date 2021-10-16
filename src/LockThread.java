import java.awt.*;
import java.awt.event.KeyEvent;

public class LockThread extends Thread {
    private boolean stop = false;

    @Override
    public void run() {
        while (!stop) {
            try {
//                KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
//
//                    @Override
//                    public boolean dispatchKeyEvent(KeyEvent ke) {
//                        if (ke.getID() == KeyEvent.KEY_TYPED && ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
//                            cancel();
//                        }
//                        return false;
//                    }
//                };
//                KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
                Thread.sleep(5000);
//                KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(keyEventDispatcher);
//                while (!stop) {
                    BatRunner.lock();
//                }
                cancel();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void cancel() {
        stop = true;
    }
}
