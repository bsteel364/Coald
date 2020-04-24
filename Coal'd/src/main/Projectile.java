/*
 *  Author - Brandon Steel
 *  April 2020
 *  bsteel364@gmail.com || bas0007@mix.wvu.edu
 */
package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Projectile extends GameObject{

	private BufferedImage coal;
	private BufferedImageLoader imageLoader = new BufferedImageLoader();
	
	public Projectile(int x, int y, ID id) {
		super(x, y, id);
		coal = imageLoader.loadImage("Large Coal.png");
		velX = 10;
	}

	@Override
	public void tick() {	
	    
		x += velX;
		
		
		////////////// Hit Detection ///////////////
		
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(Color.white);
		//g.fillRect(x, y, 132, 132);
		g.drawImage(coal, x, y, null);
	}
	
	

}
