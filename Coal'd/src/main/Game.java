/*
 *  Author - Brandon Steel
 *  April 2020
 *  bsteel364@gmail.com
 */
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
	
	public static HUD hud;
	public Handler handler;
	public BufferedImageLoader imageLoader;
	public BufferedImage background;
	public static int playerX = 0;
	public static int playerY = 0;
	private boolean bossSpawn;
	
	public Game() {
		handler = new Handler();
		hud = new HUD();
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "COAL'D", this);
		imageLoader = new BufferedImageLoader();
		
		
		background = imageLoader.loadImage("room1.png");
		File music = new File ("Coal'd FINAL DRAFT.wav");
		AudioPlayer.playSound(music);
		bossSpawn = false;
		
		handler.addObject(new Blacklung(155, 175, ID.Blacklung, handler));
		handler.addObject(new Blacklung(170, 650, ID.Blacklung, handler));
		handler.addObject(new Blacklung(1600, 653, ID.Blacklung, handler));
		handler.addObject(new Blacklung(1600, 170, ID.Blacklung, handler));
		handler.addObject(new Sloth(1200, 200, ID.Sloth, handler));
		handler.addObject(new Player((WIDTH / 2) - 30 , (HEIGHT / 2) - 30, ID.Player, this.handler));
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
		this.requestFocus();	// so that you dont have to click o the window
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
		hud.tick();
		
		if(Player.kills >= 5 && !bossSpawn) {
			handler.addObject(new CoalBarron(200, 200, ID.CoalBarron, this.handler));
			bossSpawn = true;
		}
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
		hud.render(g);
		handler.render(g);
		
		
		g.dispose();
		bs.show();
	}
	
	public static HUD getHUD() {
		return hud;
	}
	


	public static void main(String[] args) {
		new Game();
	}
	
	
		
}
