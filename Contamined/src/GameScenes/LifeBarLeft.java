package GameScenes;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class LifeBarLeft extends LifeBar {

	public LifeBarLeft(float x, float y, float width, float height, Image playerHead) {
		super(x, y, width, height, playerHead);
		// TODO Auto-generated constructor stub
		this.setLifeLoosed(0);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		//super.render(gc, g); 
		g.setColor(Color.red);
		g.fillRect(this.pos_X+145, this.pos_Y+(2*this.height)/4+5, this.width-((float)this.getLifeLoosed()/100)*this.width, this.height/4);
		g.setColor(Color.white);
		g.fillRect(this.pos_X+145, this.pos_Y+(2*this.height)/4+this.height/20+5, this.width-this.width/25-((float)this.getLifeLoosed()/100)*this.width, this.height/30);
		g.setColor(Color.black);
		g.setLineWidth((float)2.5);
		g.drawRect(this.pos_X+145, this.pos_Y+(2*this.height)/4+5, this.width, this.height/4);
		this.playerHead.draw(this.pos_X+5, this.pos_Y+5,(float)((this.height*1)/this.playerHead.getHeight()));

	}
}
