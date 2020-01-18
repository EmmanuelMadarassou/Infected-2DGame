package animationAndTexture;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Norm_foot extends FootStep {

	public Norm_foot(int tF, int fD, float c) {
		
		
		Image[] temp = new Image[6];
		
		// TODO Auto-generated constructor stub
		
		for (int i=0; i<temp.length; i++) {
			try {
				if (i <6) {
					temp[i] = new Image("res/footstep/normal_foot/right/foot_norm000"+i+".png");
				}else {
					temp[i] = new Image("res/footstep/normal_foot/right/foot_norm00"+i+".png");
				}
				
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.footRight = new Animation(temp, tF, fD, c, "infinite");
		
		temp = new Image[6];
		
		// TODO Auto-generated constructor stub
		
		for (int i=0; i<temp.length; i++) {
			try {
				if (i <6) {
					temp[i] = new Image("res/footstep/normal_foot/left/foot_norm000"+i+".png");
				}else {
					temp[i] = new Image("res/footstep/normal_foot/left/foot_norm00"+i+".png");
				}
				
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.footLeft = new Animation(temp, tF, fD, c, "infinite");
	}

}
