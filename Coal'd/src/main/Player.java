package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Player extends GameObject{

	private BufferedImage playerImage;
	private BufferedImageLoader imageLoader = new BufferedImageLoader();
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		playerImage = imageLoader.loadImage("player.png");
	}

	@Override
	public void tick() {	
		x += velX;
		y += velY;
		System.out.println("Player pos    x: " + x + "   y: " + y);

		if(x >840 && x < 860 && y > 580) {
			y = 60;
		}else if(y >= 580) {
			y = 580;
		}
		if(x >840 && x < 860 && y < 50) {
			y = 570;
		}else if(y <= 40) {
			y = 50;
		}
		if(x <= 140 && 270 <= y && y <= 365) {
			x = 1550;
		}else if(x <= 140 ) {
			x = 140;
		}
		if(x >= 1560 && 270 <= y && y <= 365) {
			x = 150;
		}else if(x >= 1560 ) {
			x = 1560;
		}
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(Color.white);
		//g.fillRect(x, y, 132, 132);
		g.drawImage(playerImage, x, y, null);
	}
	
	

}
