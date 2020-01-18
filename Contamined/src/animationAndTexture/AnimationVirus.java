package animationAndTexture;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import GameScenes.Follower;
import GameScenes.Player;

public class AnimationVirus extends Animation implements Follower{

	public AnimationVirus(int tF, int fD, float c) {
		super(tF, fD, c, "infinite");
		// TODO Auto-generated constructor stub
		
		this.frame = new Image[5];
		
		for (int i=0; i<this.frame.length;i++) {
			try {
				this.frame[i] = new Image("res/virus/toxic ("+(i+1)+").png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updatePosition(float x, float y) {
		// TODO Auto-generated method stub
		this.pos_X = (float) (x-0.15);
		this.pos_Y = (float) (y-0.2);
		
	}
	
	public void updatePlayer(Player p1, Player p2) {
		if (p1.isContamined()) {
			this.updatePosition(p1.getPos_X(), p1.getPos_Y());
		}else if (p2.isContamined()) {
			this.updatePosition(p2.getPos_X(), p2.getPos_Y());
		}
	}

	
}
