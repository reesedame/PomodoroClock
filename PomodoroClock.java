import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PomodoroClock implements ActionListener {

    JFrame frame = new JFrame();
    JButton startAndPauseButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    JButton customizeButton = new JButton("CUSTOMIZE");
    JLabel sessionLabel = new JLabel();
    JLabel timeLabel = new JLabel();
    ImageIcon pomodoroImage = new ImageIcon(getClass().getResource("media/pomodoroImg.jpeg"));
    JLabel imgLabel1 = new JLabel(pomodoroImage);
    JLabel imgLabel2 = new JLabel(pomodoroImage);
    JLabel workLengthLabel = new JLabel();
    JLabel workLength = new JLabel();
    ImageIcon upArrow = new ImageIcon(getClass().getResource("media/upArrow.jpg"));
    ImageIcon downArrow = new ImageIcon(getClass().getResource("media/downArrow.jpg"));
    JButton increaseWorkLengthButton = new JButton(upArrow);
    JButton decreaseWorkLengthButton = new JButton(downArrow);
    JLabel breakLengthLabel = new JLabel();
    JLabel breakLength = new JLabel();
    JButton increaseBreakLengthButton = new JButton(upArrow);
    JButton decreaseBreakLengthButton = new JButton(downArrow);
    boolean started = false;
    int workLengthValue = 25;
    int breakLengthValue = 5;
    int timeRemaining = workLengthValue * 60;
    int minutesDisplay = timeRemaining / 60;
    int secondsDisplay = timeRemaining % 60;
    String clockDisplay = String.format("%02d:%02d", minutesDisplay, secondsDisplay);
    String sessionType = "Ready?";
    Sound alarmSound = new Sound("media/sound.wav");

    Timer workTimer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            sessionType = "Work Session";
            sessionLabel.setText(sessionType);

            minutesDisplay = timeRemaining / 60;
            secondsDisplay = timeRemaining % 60;
            clockDisplay = String.format("%02d:%02d", minutesDisplay, secondsDisplay);
            timeLabel.setText(clockDisplay);
            timeRemaining = timeRemaining - 1;

            if (timeRemaining < 0) {
                workTimer.stop();

                alarmSound.play();

                minutesDisplay = timeRemaining / 60;
                secondsDisplay = timeRemaining % 60;
                clockDisplay = String.format("%02d:%02d", minutesDisplay, secondsDisplay);
                timeRemaining = breakLengthValue * 60;

                breakTimer.setInitialDelay(5000);
                breakTimer.start();
            }
        }
    });

    Timer breakTimer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            sessionType = "Break Time!";
            sessionLabel.setText(sessionType);

            minutesDisplay = timeRemaining / 60;
            secondsDisplay = timeRemaining % 60;
            clockDisplay = String.format("%02d:%02d", minutesDisplay, secondsDisplay);
            timeLabel.setText(clockDisplay);
            timeRemaining = timeRemaining - 1;

            if (timeRemaining < 0) {
                breakTimer.stop();

                alarmSound.play();

                minutesDisplay = timeRemaining / 60;
                secondsDisplay = timeRemaining % 60;
                clockDisplay = String.format("%02d:%02d", minutesDisplay, secondsDisplay);

                started = false;
                timeRemaining = workLengthValue * 60;
                startAndPauseButton.setText("RESTART");
            }
        }
    });

    public PomodoroClock() {
        sessionLabel.setText(sessionType);
        sessionLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        sessionLabel.setBounds(200, 50, 400, 100);
        sessionLabel.setHorizontalAlignment(JTextField.CENTER);
        sessionLabel.setVerticalAlignment(JTextField.CENTER);

        imgLabel1.setBounds(50, 50, 100, 100);

        imgLabel2.setBounds(650, 50, 100, 100);

        timeLabel.setText(clockDisplay);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 90));
        timeLabel.setBounds(150, 200, 500, 100);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        timeLabel.setVerticalAlignment(JTextField.CENTER);

        startAndPauseButton.setBounds(250, 325, 125, 75);
        startAndPauseButton.addActionListener(this);

        resetButton.setBounds(425, 325, 125, 75);
        resetButton.addActionListener(this);

        workLengthLabel.setText("Session Length");
        workLengthLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        workLengthLabel.setBounds(150, 475, 200, 50);
        workLengthLabel.setHorizontalAlignment(JTextField.CENTER);
        workLengthLabel.setVerticalAlignment(JTextField.CENTER);

        workLength.setText(String.valueOf(workLengthValue));
        workLength.setFont(new Font("Arial", Font.PLAIN, 25));
        workLength.setBounds(200, 480, 100, 150);
        workLength.setHorizontalAlignment(JTextField.CENTER);
        workLength.setVerticalAlignment(JTextField.CENTER);

        increaseWorkLengthButton.setBounds(150, 530, 50, 50);
        increaseWorkLengthButton.addActionListener(this);

        decreaseWorkLengthButton.setBounds(300, 530, 50, 50);
        decreaseWorkLengthButton.addActionListener(this);

        breakLengthLabel.setText("Break Length");
        breakLengthLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        breakLengthLabel.setBounds(450, 475, 200, 50);
        breakLengthLabel.setHorizontalAlignment(JTextField.CENTER);
        breakLengthLabel.setVerticalAlignment(JTextField.CENTER);

        breakLength.setText(String.valueOf(breakLengthValue));
        breakLength.setFont(new Font("Arial", Font.PLAIN, 25));
        breakLength.setBounds(500, 480, 100, 150);
        breakLength.setHorizontalAlignment(JTextField.CENTER);
        breakLength.setVerticalAlignment(JTextField.CENTER);

        increaseBreakLengthButton.setBounds(450, 530, 50, 50);
        increaseBreakLengthButton.addActionListener(this);

        decreaseBreakLengthButton.setBounds(600, 530, 50, 50);
        decreaseBreakLengthButton.addActionListener(this);

        frame.add(sessionLabel);
        frame.add(imgLabel1);
        frame.add(imgLabel2);
        frame.add(timeLabel);
        frame.add(startAndPauseButton);
        frame.add(resetButton);
        frame.add(workLengthLabel);
        frame.add(workLength);
        frame.add(increaseWorkLengthButton);
        frame.add(decreaseWorkLengthButton);
        frame.add(breakLengthLabel);
        frame.add(breakLength);
        frame.add(increaseBreakLengthButton);
        frame.add(decreaseBreakLengthButton);

        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 650);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startAndPauseButton) {
            if (started == false) {
                started = true;
                startAndPauseButton.setText("PAUSE");
                start();
            } else {
                started = false;
                startAndPauseButton.setText("START");
                pause();
            }
        }

        if (e.getSource() == resetButton) {
            reset();
        }

        if (e.getSource() == increaseWorkLengthButton) {
            increaseWorkLength();
        }

        if (e.getSource() == decreaseWorkLengthButton) {
            decreaseWorkLength();
        }

        if (e.getSource() == increaseBreakLengthButton) {
            increaseBreakLength();
        }

        if (e.getSource() == decreaseBreakLengthButton) {
            decreaseBreakLength();
        }
    }

    void start() {
        workTimer.start();
    }

    void pause() {
        workTimer.stop();
        breakTimer.stop();
    }

    void reset() {
        workTimer.stop();
        breakTimer.stop();
        workLengthValue = 25;
        breakLengthValue = 5;
        timeRemaining = workLengthValue * 60;
        minutesDisplay = 0;
        secondsDisplay = 0;
        clockDisplay = String.format("%02d:%02d", minutesDisplay, secondsDisplay);
        timeLabel.setText(clockDisplay);
        started = false;
        startAndPauseButton.setText("START");
        sessionType = "Ready?";
        sessionLabel.setText(sessionType);
    }

    void increaseWorkLength() {
        if (started == false) {
            workLengthValue++;
            workLength.setText(String.valueOf(workLengthValue));
            timeRemaining = workLengthValue * 60;
            minutesDisplay = timeRemaining / 60;
            secondsDisplay = timeRemaining % 60;
            clockDisplay = String.format("%02d:%02d", minutesDisplay, secondsDisplay);
            timeLabel.setText(clockDisplay);
        }
    }

    void decreaseWorkLength() {
        if (started == false && workLengthValue > 1) {
            workLengthValue--;
            workLength.setText(String.valueOf(workLengthValue));
            timeRemaining = workLengthValue * 60;
            minutesDisplay = timeRemaining / 60;
            secondsDisplay = timeRemaining % 60;
            clockDisplay = String.format("%02d:%02d", minutesDisplay, secondsDisplay);
            timeLabel.setText(clockDisplay);
        }
    }

    void increaseBreakLength() {
        if (started == false) {
            breakLengthValue++;
            breakLength.setText(String.valueOf(breakLengthValue));
        }
    }

    void decreaseBreakLength() {
        if (started == false && breakLengthValue > 1) {
            breakLengthValue--;
            breakLength.setText(String.valueOf(breakLengthValue));
        }
    }
}
