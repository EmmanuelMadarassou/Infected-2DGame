package animationAndTexture;


import GameScenes.Follower;

public class FootStep implements Follower{

	protected Animation footLeft, footRight;

	public Animation getFootLeft() {
		return footLeft;
	}

	public void setFootLeft(Animation footLeft) {
		this.footLeft = footLeft;
	}

	public Animation getFootRight() {
		return footRight;
	}

	public void setFootRight(Animation footRight) {
		this.footRight = footRight;
	}

	@Override
	public void updatePosition(float x, float y) {
		// TODO Auto-generated method stub
		this.footLeft.setPos_X(x);
		this.footLeft.setPos_Y(y);
		
		this.footRight.setPos_X(x);
		this.footRight.setPos_Y(y);
	}

}
