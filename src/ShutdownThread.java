public class ShutdownThread extends Thread {
    private boolean stop = false;

    @Override
    public void run() {
        while (!stop) {
            try {
                sleep(5000);
                BatRunner.shutdown();
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
