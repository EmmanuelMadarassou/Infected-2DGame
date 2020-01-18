package GameScenes;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class JumpBoost extends Boost {
	
	public JumpBoost(int x, int y) {
		this.type = "jump";
		this.pos_X = x;
		this.pos_Y = y;
		this.width = 1;
		this.height = 1;
		this.color = Color.red;
		this.coeff = (float)0.005;
		this.timeEffect = 5;
		this.isPickedUp = false;
		try {
			this.texture = new Image("res/boost/pill.png");
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
			this.texture.draw(this.picker.getPos_X()*32+32, this.picker.getPos_Y()*32-18, (float)1);
		}else if (!this.isDestroyed){
			this.texture.draw(pos_X*32, pos_Y*32, (float)1);
		}
				
	}	
}
