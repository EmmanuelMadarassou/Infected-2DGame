package animationAndTexture;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AnimationLobby extends Animation{

	public AnimationLobby(int tF, int fD, float c, String it) {
		super(tF, fD, c, it);
		// TODO Auto-generated constructor stub
		this.pos_X = 0;
		this.pos_Y = 0;
		try {
			this.frame = new Image[10];
			for (int i=0; i< this.frame.length; i++) {
				if (i<10) {
					this.frame[i] = new Image("res/principalMenu/lobby000"+i+".png");
				}else {
					this.frame[i] = new Image("res/principalMenu/lobby00"+i+".png");
				}
			}
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
