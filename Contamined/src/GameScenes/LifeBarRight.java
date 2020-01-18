package GameScenes;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class LifeBarRight extends LifeBar {

	public LifeBarRight(float x, float y, float width, float height, Image i) {
		super(x, y, width, height, i);
		// TODO Auto-generated constructor stub
		this.setLifeLoosed(0);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		//super.render(gc, g); 
		g.setColor(Color.red);
		g.fillRect(this.pos_X-140+((float)this.getLifeLoosed()/100)*this.width, this.pos_Y+(2*this.height)/4+5, this.width-((float)this.getLifeLoosed()/100)*this.width, this.height/4);
		g.setColor(Color.white);
		g.fillRect(this.pos_X+this.width/25-140+((float)this.getLifeLoosed()/100)*this.width, this.pos_Y+(2*this.height)/4+this.height/20+5, this.width-((float)this.getLifeLoosed()/100)*this.width-this.width/25, this.height/30);
		g.setColor(Color.black);
		g.setLineWidth((float)2.5);
		g.drawRect(this.pos_X+-140, this.pos_Y+(2*this.height)/4+5, this.width, this.height/4);
		this.playerHead.draw(this.pos_X+this.width+135-this.playerHead.getWidth(), this.pos_Y+5,(float)((this.height*1)/this.playerHead.getHeight()));
	}

}
