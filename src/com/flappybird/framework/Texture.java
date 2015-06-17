package com.flappybird.framework;

import java.awt.image.BufferedImage;

import com.flappybird.window.BufferedImageLoader;

public class Texture {

	SpriteSheet bs;
	private BufferedImage bird_sheet = null;
	
	public BufferedImage[] bird = new BufferedImage[3];
	
	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			bird_sheet = loader.loadImage("/bird.png");
		}catch(Exception e){
			
		}
		bs = new SpriteSheet(bird_sheet);
		
		getTextures();
	}

	private void getTextures() {
		// TODO Auto-generated method stub
		bird[0] = bs.grabImage(1, 1, 276/3, 64);
		bird[1] = bs.grabImage(2, 1, 276/3, 64);
		bird[2] = bs.grabImage(3, 1, 276/3, 64);
	}
	
	
}
