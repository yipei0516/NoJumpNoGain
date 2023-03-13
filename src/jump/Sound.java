package jump;

import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	
	public Clip clip;
	
	public Sound() {}
	
	public void getSound(String sound) {
    	try {
			clip = AudioSystem.getClip();
			InputStream in = this.getClass().getClassLoader().getResourceAsStream(sound);
			AudioInputStream music = AudioSystem.getAudioInputStream(in);
			clip.open(music);
			clip.start();
			
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
    }
	public void stopSound() {
		clip.stop();
	}

}
