package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

	BufferedImage image;
	
	public BufferedImage loadImage(String filepath) {
		try {
			image = ImageIO.read(getClass().getResource(filepath));
			//System.out.println(filepath + " loaded successfully");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(filepath + " not loaded");
		}
		return image;
	}
	
}
