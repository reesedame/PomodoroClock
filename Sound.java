import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    private Clip clip;

    public Sound(String fileName) {

        try {
            File soundFile = new File(fileName);
            if (soundFile.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
                clip = AudioSystem.getClip();
                clip.open(sound);
            } else {
                throw new RuntimeException("Sound file not found");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Malformed URL");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new RuntimeException("Unsupported audio file");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Input/Output Error");
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Line unavailable");
        }
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}