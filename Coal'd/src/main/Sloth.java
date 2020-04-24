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
	private Handler handler;
	private boolean dead;
	private int lives;

	public Sloth(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		slothImage = imageLoader.loadImage("slothright.png");
		velX = 3;
		this.handler = handler;
		dead = false;
		lives = 10;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void collision() {
		if (!dead) {
			for (int i = 0; i < handler.objectList.size(); i++) {
				GameObject temp = handler.objectList.get(i);

				if (temp.getID() == ID.Projectile) {
					if (temp.getX() >= x && temp.getX() < x + 100 && temp.getY() >= y && temp.getY() < y + 220) {
						lives--;
						handler.objectList.remove(temp);
						if(lives <= 0) {
							slothImage = imageLoader.loadImage("deadsloth.png");
							y = y + 200;
							velX = 0;
							velY = 0;
							dead = true;
							Player.kills++;
						}
						
					}
				}
			}
		}
	}

	@Override
	public void tick() {
		
		collision();
		
		if(!dead) {
			x += velX;
			y += velY;
			
			System.out.println(x + " " + y);
			if (x <= Game.WIDTH / 12) {
				velX = 3;
				slothImage = imageLoader.loadImage("slothright.png");
			}
			if (x >= Game.WIDTH - Game.WIDTH / 7) {
				velX = -3;
				slothImage = imageLoader.loadImage("slothleft.png.png");
			}

			//////////////////// collision detection ////////////////////////
			if (x >= Game.playerX - 100 && x <= Game.playerX + 10 && y >= Game.playerY -130 && y <= Game.playerY + 400) {
				if(x > Game.playerX) {
					this.x = x + 100;
				}else {
					this.x = x - 100;
				}
				
				Game.getHUD().removeCanary();
				Game.getHUD().removeCanary();
			}
		}
		

	}

	@Override
	public void render(Graphics g) {
		// g.setColor(Color.white);
		// g.fillRect(x, y, 132, 132);
		g.drawImage(slothImage, x, y, null);
	}

}
