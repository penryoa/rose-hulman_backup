/**
 * Greetings, earthlings.
 * @author Many people, including YOUR-NAME-HERE.
 */

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class HelloPrinter {

	/**
	 * It all begins here.
	 *
	 * @param args Command-line arguments, ignored here.
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		  try {
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("D:/MusicPlayer/fml.mp3").getAbsoluteFile());
		        Clip clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		    }
	}
}
