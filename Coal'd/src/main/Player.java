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

public class Player extends GameObject {

	private BufferedImage playerImage;
	private BufferedImage standingPlayerImage;
	private BufferedImageLoader imageLoader = new BufferedImageLoader();
	private long animationTimer = 0;
	private int animationCycle = 0;
    public static String playerFacing;
    private Handler handler;
	private static int HEIGHT = Game.HEIGHT;
	private static int WIDTH = Game.WIDTH;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		playerImage = imageLoader.loadImage("playerleft.png");
		standingPlayerImage = imageLoader.loadImage("playerleft.png");
		playerFacing = "left";
		this.handler = handler;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		///////////////// DEATH ///////////////////////
		if (Game.getHUD().getLives() == 0) {
			
		}

		////////////// ANIMATIONS /////////////////////

		if (System.currentTimeMillis() - animationTimer > 100) {
			animationTimer = System.currentTimeMillis();
			animationCycle++;
			// System.out.println(animationTimer);
		}

		if (velX == 0 && velY == 0) {
			playerImage = standingPlayerImage;
		} else if (velX < 0 || velX == 0) {
			playerFacing = "left";
			standingPlayerImage = imageLoader.loadImage("playerleft.png");
			if (animationCycle % 3 == 0) {
				playerImage = imageLoader.loadImage("playerleft.png");
			} else if (animationCycle % 3 == 1) {
				playerImage = imageLoader.loadImage("legdownleft.png");
			} else {
				playerImage = imageLoader.loadImage("legupleft.png");
			}
		} else if (velX > 0 || velY != 0) {
			playerFacing = "right";
			standingPlayerImage = imageLoader.loadImage("playerright.png");
			if (animationCycle % 3 == 0) {
				playerImage = imageLoader.loadImage("playerright.png");
			} else if (animationCycle % 3 == 1) {
				playerImage = imageLoader.loadImage("legdownright.png");
			} else {
				playerImage = imageLoader.loadImage("legupright.png");
			}

		}

		Game.playerX = x;
		Game.playerY = y;
		System.out.println("Player pos    x: " + x + "   y: " + y);

		///////////////// Boundry Detection /////////////////////////
		if (x <= 153) {
			if (y <= 470 && y >= 410) {
				x = WIDTH - WIDTH / 7 - 20;
			} else {
				x = WIDTH / 12;
			}

		}
		if (x >= WIDTH - WIDTH / 7) {
			if (y <= 470 && y >= 410) {
				x = WIDTH / 12;
			} else {
				x = WIDTH - WIDTH / 7;
			}

		}
		if (y <= 175) {
			if (x >= 840 && x <= 920) {
				y = 679;
			} else {
				y = 175;
			}

		}
		if (y >= 683) {
			if (x >= 840 && x <= 920) {
				y = 175;
			} else {
				y = 683;
			}

		}
	}

	@Override
	public void render(Graphics g) {
		// g.setColor(Color.white);
		// g.fillRect(x, y, 132, 132);
		g.drawImage(playerImage, x, y, null);
	}

}
