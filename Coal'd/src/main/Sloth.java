/*
 *  Author - Brandon Steel
 *  April 2020
 *  bsteel364@gmail.com
 */
package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Sloth extends GameObject {

	private BufferedImage slothImage;
	private BufferedImageLoader imageLoader = new BufferedImageLoader();

	public Sloth(int x, int y, ID id) {
		super(x, y, id);
		slothImage = imageLoader.loadImage("slothright.png");
		velX = 3;
	}

	@Override
	public void tick() {

		x += velX;
		y += velY;

		if (x <= Game.WIDTH / 12) {
			velX = 3;
			slothImage = imageLoader.loadImage("slothright.png");
		}
		if (x >= Game.WIDTH - Game.WIDTH / 7) {
			velX = -3;
			slothImage = imageLoader.loadImage("slothleft.png.png");
		}

		//////////////////// collision detection ////////////////////////
		if (x >= Game.playerX - 50 && x <= Game.playerX + 80 && y >= Game.playerY && y <= Game.playerY + 400) {
			this.x = x - 100;
			Game.getHUD().removeCanary();
			Game.getHUD().removeCanary();
		}

	}

	@Override
	public void render(Graphics g) {
		// g.setColor(Color.white);
		// g.fillRect(x, y, 132, 132);
		g.drawImage(slothImage, x, y, null);
	}

}
