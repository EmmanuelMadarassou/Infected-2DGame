package animationAndTexture;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

//AnimBackground is a class used to set up the background frames of the menu and the game wich are the same
public class AnimBackground extends Animation {

	public AnimBackground(int tF, int fD, float c) {
		
		super(tF, fD, c, "infinite");
			
		try {
			this.frame = new Image[63];
			for (int i=0; i< 63; i++) {
				if (i<10) {
					this.frame[i] = new Image("res/background/frame_0"+i+"_delay-0.1s.png");
				}else {
					this.frame[i] = new Image("res/background/frame_"+i+"_delay-0.1s.png");
				}
			}
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
