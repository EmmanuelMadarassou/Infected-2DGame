package GameScenes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Pickable extends Mobile {

	protected String type;
	
	protected boolean isPickedUp;
	protected boolean isDestroyed;
	protected Player picker;
	protected Image texture;
	
	protected long creationTime;
	
	
	
}
