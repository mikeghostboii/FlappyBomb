package com.flappybird.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.flappybird.framework.GameObject;
import com.flappybird.framework.ObjectId;

public class Block extends GameObject {

	public Block(float x, float y, ObjectId id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(Color.GREEN.darker());
		g.fillRect((int) x, (int) y, 34, 32);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, 34, 32);
	}

}
