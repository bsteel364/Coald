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

public class Blacklung extends GameObject {

	private BufferedImage blacklungImage;
	private BufferedImageLoader imageLoader = new BufferedImageLoader();
	private Handler handler;
	private boolean dead;
	private int randomVelocity;

	public Blacklung(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		blacklungImage = imageLoader.loadImage("New Piskel.png");
		randomVelocity = (int) (Math.random() * 3 + 1);
		dead = false;
		this.handler = handler;
	}

	public void collision() {
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject temp = handler.objectList.get(i);

			if (temp.getID() == ID.Projectile) {
				if (temp.getX() >= x && temp.getX() < x + 130 && temp.getY() >= y && temp.getY() < y + 130) {
					blacklungImage = imageLoader.loadImage("coal_pile.png.png");
					velX = 0;
					velY = 0;
					dead = true;
					handler.objectList.remove(temp);
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
				velX = randomVelocity;
			} else if (Game.playerX == x) {
				velX = 0;
			} else {
				velX = randomVelocity * (-1);
			}
			if (Game.playerY > y) {
				velY = randomVelocity;
			} else if (Game.playerY == y) {
				velY = 0;
			} else {
				velY = randomVelocity * (-1);
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

			///////////////// boundry detection (probably unnecessary) ///////////////
			if (x > 840 && x < 860 && y > 580) {
				y = 60;
			} else if (y >= 580) {
				y = 580;
			}
			if (x > 840 && x < 860 && y < 50) {
				y = 570;
			} else if (y <= 40) {
				y = 50;
			}
			if (x <= 140 && 270 <= y && y <= 365) {
				x = 1550;
			} else if (x <= 140) {
				x = 140;
			}
			if (x >= 1560 && 270 <= y && y <= 365) {
				x = 150;
			} else if (x >= 1560) {
				x = 1560;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		// g.setColor(Color.white);
		// g.fillRect(x, y, 132, 132);
		g.drawImage(blacklungImage, x, y, null);
	}

}
