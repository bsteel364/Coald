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

public class CoalBarron extends GameObject {

	private BufferedImage coalBarronImage;
	private BufferedImageLoader imageLoader = new BufferedImageLoader();
	private Handler handler;
	private boolean dead;
	public static int lives;

	public CoalBarron(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		coalBarronImage = imageLoader.loadImage("CoalBarron.png");
		
		dead = false;
		this.handler = handler;
		lives = 50;
	}
	
	public boolean isDead() {
		return dead;
	}


	public void collision() {
		if (!dead) {
			for (int i = 0; i < handler.objectList.size(); i++) {
				GameObject temp = handler.objectList.get(i);

				if (temp.getID() == ID.Projectile) {
					if (temp.getX() >= x - 20 && temp.getX() < x + 300 && temp.getY() >= y && temp.getY() < y + 300) {
						lives--;
						handler.objectList.remove(temp);
						if(lives <= 0) {
							coalBarronImage = imageLoader.loadImage("Large Coal.png");
							velX = 0;
							velY = 0;
							dead = true;
						}
						
						
					}
				}
			}
		}
	}

	@Override
	public void tick() {

		collision();

		if (dead == false) {
			x += velX;
			y += velY;

			////////////////// chase player /////////////////////////////
			if (Game.playerX > x) {
				velX = 4;
			} else if (Game.playerX == x) {
				velX = 0;
			} else {
				velX = 4 * (-1);
			}
			if (Game.playerY > y + 150) {
				velY = 4;
			} else if (Game.playerY == y) {
				velY = 0;
			} else {
				velY = 4 * (-1);
			}

			//////////////////// collision detection ////////////////////////
			if (x >= Game.playerX - 50 && x <= Game.playerX + 80 && y >= Game.playerY - 50 && y <= Game.playerY + 100) {
				if (x < Game.playerX) {
					x = x - 150;
				} else {
					x = x + 150;
				}
				Game.getHUD().removeCanary();
			}

			
		}
	}

	@Override
	public void render(Graphics g) {
		// g.setColor(Color.white);
		// g.fillRect(x, y, 132, 132);
		g.drawImage(coalBarronImage, x, y, null);
	}


}
