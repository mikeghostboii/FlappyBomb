package com.flappybird.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.flappybird.framework.GameObject;
import com.flappybird.framework.ObjectId;
import com.flappybird.framework.Texture;
import com.flappybird.window.Animation;
import com.flappybird.window.Game;
import com.flappybird.window.Handler;

public class Player extends GameObject {

	private final float MAX_SPEED = 10;

	private float width = 15, height = 15;

	private float gravity = 0.09f;

	private Handler handler;

	Texture tex = Game.getInstance();

	private Animation birdFly;

	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;

		birdFly = new Animation(1, tex.bird[0], tex.bird[1], tex.bird[2]);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		x += velX;
		y += velY;

		if (falling || jumping) {
			velY += gravity;

			if (velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}

		}

		Collision(object);
		birdFly.runAnimation();
	}

	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Block) {
				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velX = 0;
					velY = 0;
					falling = false;
					jumping = false;
				} else {
					falling = true;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		if (velX != 0) {
			//birdFly.drawAnimation(g, (int) x, (int) y, 276/3, 64);
		} else {
			//g.drawImage(tex.bird[0], (int) x, (int) y, 276/3 , 64, null);
		}
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)),
				(int) y + (int) (height / 2), (int) width / 2, (int) height / 2);
	}

	public Rectangle getBoundsTop() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)),
				(int) y, (int) width / 2, (int) height / 2);
	}

	public Rectangle getBoundsRight() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int) x + width - 5), (int) y, (int) 5,
				(int) height);
	}

	public Rectangle getBoundsLeft() {
		// TODO Auto-generated method stub
		return new Rectangle((int) x, (int) y, (int) 5, (int) height);
	}

}
