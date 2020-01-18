package GameScenes;

public abstract class Boost extends Pickable {
	
	protected long timeEffect;
	protected long startEffect, currentEffect;
	protected float coeff;
	protected float supp_Y, inf_Y;
	protected boolean isGoingDown, isGoingUp;
	
	public void updateDirection() {
		if (this.pos_Y*32 >= this.inf_Y) {
			this.isGoingUp = true;
			this.isGoingDown = false;
		}else if (this.pos_Y*32 <= this.supp_Y) {
			this.isGoingUp = false;
			this.isGoingDown = true;
		}
	}
	
	@Override
	public void move(int delta){
		this.updateDirection();
		if (this.isGoingUp) {
			this.moveUp(delta);
		}else if (this.isGoingDown) {
			this.moveDown(delta);
		}
	}
	
	public void init() {
		this.supp_Y = (float) (this.pos_Y*32 - 3);
		this.inf_Y = (float) (this.pos_Y*32 + 3);
		this.speedDown = (float)0.004;
		this.speedUp = (float)0.004;
		this.isGoingUp = true;
		this.isGoingDown = false;
	}
	
	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
		if (this.isPickedUp) {
			this.pos_X = this.picker.getPos_X();
			this.pos_Y = this.picker.getPos_Y();
		}else {
			this.move(delta);
		}
	}
	
	@Override
	boolean moveUp(int delta) {
		// TODO Auto-generated method stub
		this.pos_Y = this.pos_Y - this.speedUp*delta/17;
		return true;
	}

	@Override
	boolean moveDown(int delta) {
		// TODO Auto-generated method stub
		this.pos_Y = this.pos_Y + this.speedDown*delta/17;
		return true;
	}
	@Override
	boolean moveLeft(int delta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean moveRight(int delta) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
