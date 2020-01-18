package animationAndTexture;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import GameScenes.Follower;

public class AnimWallSide extends Animation implements Follower{

	public AnimWallSide(int tF, int fD, float c, String it, String type) {
		super(tF, fD, c, it);
		// TODO Auto-generated constructor stub
		
		
		
		try {
			this.frame = new Image[9];
			for (int i=0; i< this.frame.length; i++) {
				if (type == "normal") {
					if (i<10) {
						this.frame[i] = new Image("res/jump_cloud/normal/walljump/walljump000"+i+".png");
					}else {
						this.frame[i] = new Image("res/jump_cloud/normal/walljump/walljump00"+i+".png");
					}
				}else {
					if (i<10) {
						this.frame[i] = new Image("res/jump_cloud/jumpBoost/walljump/walljump000"+i+".png");
					}else {
						this.frame[i] = new Image("res/jump_cloud/jumpBoost/walljump/walljump00"+i+".png");
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
		this.pos_X = x;
		this.pos_Y = y;
	}

}
