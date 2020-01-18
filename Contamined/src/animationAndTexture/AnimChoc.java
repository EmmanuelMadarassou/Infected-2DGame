package animationAndTexture;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import GameScenes.Follower;

public class AnimChoc extends Animation implements Follower{

	public AnimChoc(int tF, int fD, float c, String it) {
		super(tF, fD, c, it);
		// TODO Auto-generated constructor stub
		
		try {
			this.frame = new Image[7];
			for (int i=0; i< this.frame.length; i++) {
				if (i<10) {
					this.frame[i] = new Image("res/choc/choc000"+i+".png");
				}else {
					this.frame[i] = new Image("res/choc/choc00"+i+".png");
				}
			}
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updatePosition(float x, float y) {
		// TODO Auto-generated method stub
		this.frameDisplay = 0;
		this.pos_X = (float) (x-0.2);
		this.pos_Y = (float) (y-0.6);
	}

}
