package GameScenes;

import main.Body;

abstract class Mobile extends Body implements Movable{
	
	public Mobile() {
	}
	
	public Mobile(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	protected float speedX, speedUp, speedDown, maxSpeedX, maxSpeedY;
		
	abstract void move(int delta);
	abstract boolean moveUp(int delta);
	abstract boolean moveDown(int delta);
	abstract boolean moveLeft(int delta);
	abstract boolean moveRight(int delta);
	
}
