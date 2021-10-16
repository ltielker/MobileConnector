import java.io.IOException;

public class BatRunner {
    public static void run(Integer id) {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "cd src && actions.bat " + id);
            Process process = builder.start();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void lock() {
        run(400);
    }

    public static void sleep() {
        run(300);
    }

    public static void shutdown(){
        run(200);
    }
}