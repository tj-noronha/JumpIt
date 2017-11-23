package jumpIt;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {
	
	public void Background(){
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("assets/audio/airhorn.mp3").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	       // return clip;
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	        //return null;
	    }
	}

}
