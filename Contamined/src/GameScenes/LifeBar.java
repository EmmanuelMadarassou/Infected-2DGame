package GameScenes;

import org.newdawn.slick.Image;

import main.Body;

public class LifeBar extends Body{

	protected Image playerHead;
	private float lifeLoosed;
	
	public LifeBar(float x, float y, float width, float height, Image i) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		this.playerHead = i;
	}

	public float getLifeLoosed() {
		return lifeLoosed;
	}

	public void setLifeLoosed(float lifeLoosed) {
		this.lifeLoosed = lifeLoosed;
	}

}
