package com.flappybird.window;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Frame for Flappy Bird game
 * 
 * @author Michael Wasihun
 * @version 1.0
 */
public class Window {
	/**
	 * Constructor for objects of class Window
	 */
	public Window(int width, int height, String title, Game game) {
		game.setPreferredSize(new Dimension(width, height * 12 / 9));
		game.setMaximumSize(new Dimension(width, height * 12 / 9));
		game.setMinimumSize(new Dimension(width, height * 12 / 9));
		
		JFrame frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		game.start();
	}
}
