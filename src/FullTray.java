import java.awt.*;
import java.awt.event.*;

public class FullTray {

    static class ShowMessageListener implements ActionListener {
        TrayIcon trayIcon;
        String title;
        String message;
        TrayIcon.MessageType messageType;

        ShowMessageListener(
                TrayIcon trayIcon,
                String title,
                String message,
                TrayIcon.MessageType messageType) {
            this.trayIcon = trayIcon;
            this.title = title;
            this.message = message;
            this.messageType = messageType;
        }

        public void actionPerformed(ActionEvent e) {
            trayIcon.displayMessage(title, message, messageType);
        }
    }

    public static void main(String[] args) {
        Runnable runner = new Runnable() {
            public void run() {
                if (SystemTray.isSupported()) {
                    final SystemTray tray = SystemTray.getSystemTray();
                    Image image = Toolkit.getDefaultToolkit().getImage("src/power.png");
                    PopupMenu popup = new PopupMenu();
                    final TrayIcon trayIcon = new TrayIcon(image, "MobileConnector", popup);

                    MenuItem item = new MenuItem("Herunterfahren");
                    item.addActionListener(new ShowMessageListener(trayIcon,
                            "MobileConnector - Herunterfahren",
                            "Der PC wird in 5 Sekunden heruntergefahren",
                            TrayIcon.MessageType.WARNING));
                    item.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            BatRunner.shutdown();
                        }
                    });
                    popup.add(item);

                    item = new MenuItem("Ruhezustand");
                    item.addActionListener(new ShowMessageListener(trayIcon,
                            "MobileConnector - Ruhezustand",
                            "Der PC wird in 5 Sekunden in den Ruhezustand versetzt",
                            TrayIcon.MessageType.WARNING));
                    item.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            BatRunner.sleep();
                        }
                    });
                    popup.add(item);

                    item = new MenuItem("Sperren");
                    item.addActionListener(new ShowMessageListener(trayIcon,
                            "MobileConnector - Sperren",
                            "Der PC wird in 5 Sekunden gesperrt.",
                            TrayIcon.MessageType.WARNING));
                    item.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            LockThread lockThread = new LockThread();
                            lockThread.start();
                        }
                    });
                    popup.add(item);

                    item = new MenuItem("Schließen");
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            tray.remove(trayIcon);
                        }
                    });

                    popup.add(item);
                    try {
                        tray.add(trayIcon);
                    } catch (AWTException e) {
                        System.err.println("Can't add to tray");
                    }
                } else {
                    System.err.println("Tray unavailable");
                }
            }
        };
        EventQueue.invokeLater(runner);
    }


}