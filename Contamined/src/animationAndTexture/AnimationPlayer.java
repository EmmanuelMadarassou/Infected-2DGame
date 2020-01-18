package animationAndTexture;



import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import GameScenes.Follower;
import main.Drawable;

public class AnimationPlayer extends Animation{

	protected float offsetX, offsetY;
	
	public AnimationPlayer(Image[] f, int tF, int fD, float c) {
		super(f, tF, fD, c, "infinite");
		// TODO Auto-generated constructor stub
		
		this.offsetX = 0;
		this.offsetY = -6;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		this.currentFrameTime = System.currentTimeMillis();
		
		if ((this.currentFrameTime-this.lastFrameTime) >= 100) {
			this.frameDisplay++;
			this.lastFrameTime = System.currentTimeMillis();
		}
				
		if (this.frameDisplay >= this.frame.length-1) {
			this.frameDisplay = 0;
		}
		
		this.frame[frameDisplay].draw(this.pos_X*32+this.offsetX,this.pos_Y*32+this.offsetY, this.coeff);
	}

}
