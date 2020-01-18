package GameScenes;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SpeedBoost extends Boost {

	public SpeedBoost(int x, int y) {
		this.type = "speed";
		this.pos_X = x;
		this.pos_Y = y;
		this.width = 1;
		this.height = 1;
		this.color = Color.red;
		this.coeff = (float)0.005;
		this.type = "speed";
		this.timeEffect = 5;
		this.isPickedUp = false;
		try {
			this.texture = new Image("res/boost/boostSpeed.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.init();
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		//super.render(gc, g);
		if (this.isPickedUp) {
			this.texture.draw(this.picker.getPos_X()*32+32, this.picker.getPos_Y()*32-48, (float)1);
		}else if (!this.isDestroyed){
			this.texture.draw(pos_X*32, pos_Y*32-10, (float)1);
		}
				
	}	

}
