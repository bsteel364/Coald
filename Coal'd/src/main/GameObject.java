package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class GameObject {

	protected int x, y;
	protected ID id;
	protected int velX, velY;
	
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getVelocityX() {
		return this.velX;
	}
	
	public int getVelocityY() {
		return this.velY;
	}
	
	public void setVelocityX(int xVelocity) {
		this.velX = xVelocity;
	}
	
	public void setVelocityY(int yVelocity) {
		this.velY = yVelocity;
	}
	
	public ID getID() {
		return this.id;
	}
	
	public void setID(ID id) {
		this.id = id;
	}
	
}
