package main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> objectList = new LinkedList<GameObject>();
	
	//Iterates through all game objects and makes each one tick()
	public void tick() {
		for(int i = 0; i < objectList.size(); i++) {
			GameObject tempObject = objectList.get(i);
			tempObject.tick();
		}
	}
	
	//Iterates through all game objects and renders each one
	public void render(Graphics g) {
		for(int i = 0; i < objectList.size(); i++) {
			GameObject tempObject = objectList.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.objectList.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.objectList.remove(object);
	}
}
