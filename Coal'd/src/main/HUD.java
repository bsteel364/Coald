/*
 *  Author - Brandon Steel
 *  April 2020
 *  bsteel364@gmail.com
 */
package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
 

public class HUD {

	private int lifeCount;
	private BufferedImage canary1;
	private BufferedImage canary2;
	private BufferedImage canary3;
	private BufferedImage lantern;
	private BufferedImageLoader imageLoader = new BufferedImageLoader();
	
	public HUD() {
		lifeCount = 3;
		canary1 = imageLoader.loadImage("Canary.png");
		canary2 = imageLoader.loadImage("Canary.png");
		canary3 = imageLoader.loadImage("Canary.png");
		lantern = imageLoader.loadImage("lantern1.png");
	}
	
	public void tick() {
		
	}
	
	public void removeCanary() {
		lifeCount--;
		if(lifeCount == 0) {
			//death
		}
	}
	
	public void render(Graphics g) {
		if(lifeCount >= 3) {
			g.drawImage(canary1, 300, Game.HEIGHT - 500, null);
		}
		if(lifeCount >= 2) {
			g.drawImage(canary1, 200, Game.HEIGHT - 500, null);
		}
		if(lifeCount >= 1) {
			g.drawImage(canary1, 100, Game.HEIGHT - 500, null);
		}
		
		
		
		g.drawImage(lantern, Game.WIDTH - Game.WIDTH/5, Game.HEIGHT - Game.HEIGHT/3 - 20, null);
	}
}
