import java.util.Timer;
import java.util.TimerTask;

public class Countdown {
    int delay;
    int timeRemaining;
    int minutesDisplay;
    int secondsDisplay;
    String clockDisplay;
    Timer timer = new Timer();
    TimerTask updateClockDisplay = new TimerTask() {
        @Override
        public void run() {
            if (timeRemaining >= 0) {
                minutesDisplay = timeRemaining / 60;
                secondsDisplay = timeRemaining % 60;
                clockDisplay = String.format("%02d:%02d", minutesDisplay, secondsDisplay);
                System.out.println(clockDisplay);
                timeRemaining--;
            } else {
                timer.cancel();
            }
        }
    };

    Countdown(int timeRemaining, int delay) {
        this.timeRemaining = timeRemaining;
        this.delay = delay;
    }

    public void start() {
        timer.scheduleAtFixedRate(updateClockDisplay, delay, 1000);
    }
}