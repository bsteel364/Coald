package main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	
	private static final long serialVersionUID = -4810618286807932601L;

	//Constructor
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		//Window size
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setResizable(true);
		frame.setLocationRelativeTo(null); //sets frame in the middle of the screen
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}
