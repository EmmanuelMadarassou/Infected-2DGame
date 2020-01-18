package animationAndTexture;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import GameScenes.Follower;

public class AnimLanding extends Animation implements Follower{

	public AnimLanding(int tF, int fD, float c, String it, String type) {
		super(tF, fD, c, it);
		// TODO Auto-generated constructor stub
					
		try {
			this.frame = new Image[11];
			for (int i=0; i< this.frame.length; i++) {
				if (type == "normal") {
					if (i<10) {
						this.frame[i] = new Image("res/jump_cloud/normal/landing/landing000"+i+".png");
					}else {
						this.frame[i] = new Image("res/jump_cloud/normal/landing/landing00"+i+".png");
					}
				}else {
					if (i<10) {
						this.frame[i] = new Image("res/jump_cloud/jumpBoost/landing/landing000"+i+".png");
					}else {
						this.frame[i] = new Image("res/jump_cloud/jumpBoost/landing/landing00"+i+".png");
					}
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
		this.pos_X = (float) (x-0.25);
		this.pos_Y = (float) (y+0.25);
	}

}
