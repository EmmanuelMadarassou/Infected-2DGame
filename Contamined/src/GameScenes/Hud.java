package GameScenes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import main.Body;

public class Hud extends Body{
	
	private LifeBar[] lifePlayers;
	
	public Hud(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		//super.render(gc, g); 
		for (int i=0; i<this.getLifePlayers().length; i++) {
			getLifePlayers()[i].render(gc, g);
		}

	}

	public LifeBar[] getLifePlayers() {
		return lifePlayers;
	}

	public void setLifePlayers(LifeBar[] lifePlayers) {
		this.lifePlayers = lifePlayers;
	}
	
	
}
