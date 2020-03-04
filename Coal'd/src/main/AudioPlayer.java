package main;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {

	// Map<key, value>
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>(); // for sound FX
	public static Map<String, Music> musicMap = new HashMap<String, Music>(); // for music 
	
	public static void load() {
		try {
			musicMap.put("Going Steady", new Music("Going_Steady.wav"));
		} catch (SlickException e) {
			System.out.println("Music failed to load");
			e.printStackTrace();
		}
	}
	
	public static Music getMusic(String track) {
		return musicMap.get(track);
	}
	
	public static Sound getSound(String sound) {
		return soundMap.get(sound);
	}
	
	
}
