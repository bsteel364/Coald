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
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(Color.white);
		//g.fillRect(x, y, 132, 132);
		g.drawImage(playerImage, x, y, null);
	}
	
	

}
