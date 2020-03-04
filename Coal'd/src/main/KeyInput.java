package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//System.out.println(key);  Uncomment to get key codes
		for(int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			
			if(tempObject.getID() == ID.Player) {
				
				//Player key inputs
				if(key == KeyEvent.VK_W) {
					tempObject.setVelocityY(-5);
				}
				if(key == KeyEvent.VK_A) {
					tempObject.setVelocityX(-5);
				}
				if(key == KeyEvent.VK_S) {
					tempObject.setVelocityY(5);
				}
				if(key == KeyEvent.VK_D) {
					tempObject.setVelocityX(5);
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			
			if(tempObject.getID() == ID.Player) {
				
				//Player key inputs
				if(key == KeyEvent.VK_W) {
					tempObject.setVelocityY(0);
				}
				if(key == KeyEvent.VK_A) {
					tempObject.setVelocityX(0);
				}
				if(key == KeyEvent.VK_S) {
					tempObject.setVelocityY(0);
				}
				if(key == KeyEvent.VK_D) {
					tempObject.setVelocityX(0);
				}
			}
		}
		
	}
}
