package GameScenes;

import main.Body;

public interface Collider {

	public boolean collideTop(Body b);
	public boolean collideBot(Body b);
	public boolean collideLeft(Body b);
	public boolean collideRight(Body b);
	public boolean collide(Body b);
	public boolean updateCollide(Body b);
}
