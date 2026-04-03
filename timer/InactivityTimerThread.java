package mp230569.timer;

import java.awt.*;
import java.awt.event.*;

public class InactivityTimerThread extends Thread {

    private final int maxSeconds = 60;

    private final Frame parentFrame;
    private volatile int secondsLeft = maxSeconds;
    private volatile boolean stopped = false;
    private volatile boolean paused = false;

    private final InactivityDialog inactivityDialog;

    public InactivityTimerThread(Frame parentFrame) {
        this.parentFrame = parentFrame;
        this.inactivityDialog = new InactivityDialog();
    }

    public void resetTimer() {
        secondsLeft = maxSeconds;
        EventQueue.invokeLater(() -> inactivityDialog.setVisible(false));
    }

    public void pauseThread() {
        paused = true;
    }

    public void resumeThread() {
        if (paused) {
            paused = false;
            synchronized (this) {
                notify();
            }
        }
    }

    public void stopThread() {
        stopped = true;
        this.interrupt();
    }

    @Override
    public void run() {
        while (!stopped) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }

            synchronized (this) {
                while (paused && !stopped) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }

            if (stopped) return;

            secondsLeft--;

            if (secondsLeft <= 5 && secondsLeft > 0) {
                EventQueue.invokeLater(() -> inactivityDialog.updateMessage(secondsLeft));
            }

            if (secondsLeft <= 0) {
                EventQueue.invokeLater(() -> System.exit(0));
            }
        }
    }

    // -------------------------------
    // INNER CLASS: InactivityDialog
    // -------------------------------
    private class InactivityDialog extends Dialog {

        private final Label message;

        public InactivityDialog() {
            super(parentFrame, "Warning", false);
            setLayout(new BorderLayout());
            setSize(300, 120);

            message = new Label("", Label.CENTER);
            add(message, BorderLayout.CENTER);

            Button okButton = new Button("OK");
            okButton.addActionListener(e -> {
                setVisible(false);
                secondsLeft = maxSeconds;
            });

            Panel panel = new Panel();
            panel.add(okButton);
            add(panel, BorderLayout.SOUTH);

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    setVisible(false);
                }
            });
        }

        public void updateMessage(int secondsLeft) {
            message.setText("Program will close in " + secondsLeft + "s.");
            if (!isVisible()) {
                setLocationRelativeTo(parentFrame);
                setVisible(true);
            }
        }
    }
}
