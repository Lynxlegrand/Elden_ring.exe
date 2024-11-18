import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEngine {
    private Clip clip;

    public void playSound(String filePath) {
        try {
            // Charger le fichier audio
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Créer le clip et le préparer
            clip = AudioSystem.getClip();
            clip.open(audioStream);


            // Jouer le clip en boucle
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void closeSound() {
        if (clip != null) {
            clip.close();
        }
    }
}
