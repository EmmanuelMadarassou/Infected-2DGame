package animationAndTexture;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import GameScenes.Follower;

public class AnimElectricity extends Animation implements Follower {

	public AnimElectricity(int tF, int fD, float c, String it) {
		super(tF, fD, c, it);
		// TODO Auto-generated constructor stub
		this.frame = new Image[10];
		
		for (int i=0; i<this.frame.length;i++) {
			try {
				this.frame[i] = new Image("res/boost/electricity/electricity000"+i+".png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updatePosition(float x, float y) {
		// TODO Auto-generated method stub
		this.pos_X = (float) (x-0.35);
		this.pos_Y = (float) (y-0.1);
	}

}
