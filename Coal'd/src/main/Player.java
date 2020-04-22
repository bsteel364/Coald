package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Player extends GameObject{

	private BufferedImage playerImage;
	private BufferedImageLoader imageLoader = new BufferedImageLoader();
	private long animationTimer = 0;
	private int animationCycle = 0;
	
	private static int HEIGHT = Game.HEIGHT;
	private static int WIDTH = Game.WIDTH;
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		playerImage = imageLoader.loadImage("playerleft.png");
	}
	

	@Override
	public void tick() {	
		x += velX;
		y += velY;
		
		if(System.currentTimeMillis() - animationTimer > 100) {
			animationTimer = System.currentTimeMillis();
			animationCycle++;
			//System.out.println(animationTimer);
		}
		 
		if(velX == 0 && velY == 0) {
			playerImage = imageLoader.loadImage("playerleft.png");
		}else if(velX < 0) {
			
			if(animationCycle %3 == 0) {
				playerImage = imageLoader.loadImage("playerleft.png");
			}else if(animationCycle %3 == 1) {
				playerImage = imageLoader.loadImage("legdownleft.png");
			}else {
				playerImage = imageLoader.loadImage("legupleft.png");
			}			
		}
		else if(velX > 0 || velY != 0) {
			if(animationCycle %3 == 0) {
				playerImage = imageLoader.loadImage("playerright.png");
			}else if(animationCycle %3 == 1) {
				playerImage = imageLoader.loadImage("legdownright.png");
			}else {
				playerImage = imageLoader.loadImage("legupright.png");
			}
			
		}
		
		Game.playerX = x;
		Game.playerY = y;
		System.out.println("Player pos    x: " + x + "   y: " + y);

		if(x <= WIDTH/12 ) {
			x = WIDTH/12;
		}
		if(x >= WIDTH - WIDTH/7) {
			x = WIDTH - WIDTH/7;
		}
		if(y >= HEIGHT - HEIGHT/12*(5.5)) {
			y = HEIGHT - (int)(HEIGHT/12*(5.5));
		}
		if(y <= HEIGHT/8) {
			y = HEIGHT/8;
		}
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(Color.white);
		//g.fillRect(x, y, 132, 132);
		g.drawImage(playerImage, x, y, null);
	}
	
	

}
