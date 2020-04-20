package main;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class AudioPlayer{
	
	
	/*public static void main (String [] args) {
		File Going_Steady = new File ("Black Lung.gif");	
		PlaySound(Going_Steady);
	}*/
	public static void playSound(File Sound){
		
		 Clip play = null;
	        try {
	     
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound);
	            play = AudioSystem.getClip();
	            play.open(audioInputStream); 
	            play.start();
	        
	            // wait for the song to finish
	            do {
	                try {
						Thread.sleep(15);
					} catch (InterruptedException e) {	
						e.printStackTrace();
					}
	            } while (play.isRunning());
	            play.drain();
	        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
	            ex.printStackTrace();
	        } finally {
	            try {
	                play.close();
	            } catch (Exception e) {
	            	e.printStackTrace();
	            }
	        }
	    }
}
