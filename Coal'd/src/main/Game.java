package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable{

	
	private static final long serialVersionUID = 6691247796639148462L;
	
	public static int WIDTH = 1900, HEIGHT = WIDTH / 12 * 8;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	public BufferedImageLoader imageLoader;
	public BufferedImage background;
	
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "COAL'D", this);
		imageLoader = new BufferedImageLoader();
		
		background = imageLoader.loadImage("room1.png");
		//AudioPlayer.load();
		//AudioPlayer.getMusic("Going Steady").loop();
		
		handler.addObject(new Player((WIDTH / 2) - 30 , (HEIGHT / 2) - 30, ID.Player));
	}

	public synchronized void start() {
		thread = new Thread(this);	// creates new thread that runs the Game class (main method)
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/*
	 * GAME LOOP, DO NOT EDIT!
	 */
	public void run() {
		long lastTime = System.nanoTime();
		double ticks = 60.0;
		double nanoseconds = 1000000000 /  ticks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nanoseconds;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {				
				render();
			}
			frames++;
			
			//Measures frames per second
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
		
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3); // creates 3 buffers within game
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		//g.setColor(Color.black);
		//g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(background, 0, 0, null);
		handler.render(g);
		
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Game();
	}
		
}
