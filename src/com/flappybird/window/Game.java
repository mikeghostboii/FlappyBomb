package com.flappybird.window;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.flappybird.framework.KeyInput;
import com.flappybird.framework.ObjectId;
import com.flappybird.framework.Texture;
import com.flappybird.objects.Player;

/**
 * Main class for Flappy Bird game
 * 
 * @author Michael Wasihun
 * @version 1.0
 */
public class Game extends Canvas implements Runnable {

	// Double buffering
	private Image dbImage;
	@SuppressWarnings("unused")
	private Graphics dbg;

	private static final long serialVersionUID = -6112428091888191314L;
	private boolean running = false;
	private Thread thread;

	public static int WIDTH, HEIGHT;

	private BufferedImage bird = null;
	
	// Object
	Handler handler;
	Camera cam;
	static Texture tex;
	
	Random rand = new Random();

	/**
	 * Constructor for objects of class Game
	 */
	public Game() {
		new Window(365, 400, "Flappy Bird", this);
	}

	public void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();

		tex = new Texture();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		bird = loader.loadImage("/bird.png");
		handler = new Handler();

		cam = new Camera(0, 0);

		handler.addObject(new Player(160, 200, handler, ObjectId.Player));
		
		handler.createLevel();
		
		this.addKeyListener(new KeyInput(handler));
	}

	public synchronized void start() {
		if (running) {
			return;
		}

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("fps: " + frames + " Tick: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void tick() {
		handler.tick();
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ObjectId.Player) {
				cam.tick(handler.object.get(i));
			}
		}
	}

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2 = (Graphics2D) g;
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		g.drawImage(dbImage, 0, 0, this);
		// ////////////////////////////////
		
		g2.translate(cam.getX(), cam.getY()); 

		// Draw here
		handler.render(g);
		
		g2.translate(-cam.getX(), -cam.getY());

		// ////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	public static Texture getInstance(){
		return tex;
	}
	public static void main(String[] args) {
		new Game();
	}
}
