package animationAndTexture;

import GameScenes.Follower;

public abstract class TexturePlayer implements Follower{

	protected AnimationPlayer standLeft, standRight, walkLeft, walkRight, runLeft, runRight, jumpLeft, jumpRight, fallLeft, fallRight, hurtLeft, hurtRight;

	public void updatePosition(float pos_X, float pos_Y) {
		
		this.standLeft.setPos_X(pos_X);
		this.standLeft.setPos_Y(pos_Y);
		
		this.fallLeft.setPos_X(pos_X);
		this.fallLeft.setPos_Y(pos_Y);
		
		this.fallRight.setPos_X(pos_X);
		this.fallRight.setPos_Y(pos_Y);
		
		this.standRight.setPos_X(pos_X);
		this.standRight.setPos_Y(pos_Y);
		
		this.walkLeft.setPos_X(pos_X);
		this.walkLeft.setPos_Y(pos_Y);
		
		this.walkRight.setPos_X(pos_X);
		this.walkRight.setPos_Y(pos_Y);
		
		this.runLeft.setPos_X(pos_X);
		this.runLeft.setPos_Y(pos_Y);
		
		this.runRight.setPos_X(pos_X);
		this.runRight.setPos_Y(pos_Y);
		
		this.hurtRight.setPos_X(pos_X);
		this.hurtRight.setPos_Y(pos_Y);
		
		this.hurtLeft.setPos_X(pos_X);
		this.hurtLeft.setPos_Y(pos_Y);
	}
	
	public AnimationPlayer getStandLeft() {
		return standLeft;
	}

	public void setStandLeft(AnimationPlayer standLeft) {
		this.standLeft = standLeft;
	}

	public AnimationPlayer getStandRight() {
		return standRight;
	}

	public void setStandRight(AnimationPlayer standRight) {
		this.standRight = standRight;
	}

	public AnimationPlayer getWalkLeft() {
		return walkLeft;
	}

	public void setWalkLeft(AnimationPlayer walkLeft) {
		this.walkLeft = walkLeft;
	}

	public AnimationPlayer getWalkRight() {
		return walkRight;
	}

	public void setWalkRight(AnimationPlayer walkRight) {
		this.walkRight = walkRight;
	}

	public AnimationPlayer getRunLeft() {
		return runLeft;
	}

	public void setRunLeft(AnimationPlayer runLeft) {
		this.runLeft = runLeft;
	}

	public AnimationPlayer getRunRight() {
		return runRight;
	}

	public void setRunRight(AnimationPlayer runRight) {
		this.runRight = runRight;
	}

	public AnimationPlayer getJumpLeft() {
		return jumpLeft;
	}

	public void setJumpLeft(AnimationPlayer jumpLeft) {
		this.jumpLeft = jumpLeft;
	}

	public AnimationPlayer getJumpRight() {
		return jumpRight;
	}

	public void setJumpRight(AnimationPlayer jumpRight) {
		this.jumpRight = jumpRight;
	}

	public AnimationPlayer getFallLeft() {
		return fallLeft;
	}

	public void setFallLeft(AnimationPlayer fallLeft) {
		this.fallLeft = fallLeft;
	}

	public AnimationPlayer getFallRight() {
		return fallRight;
	}

	public void setFallRight(AnimationPlayer fallRight) {
		this.fallRight = fallRight;
	}

	public AnimationPlayer getHurtLeft() {
		return hurtLeft;
	}

	public void setHurtLeft(AnimationPlayer hurtLeft) {
		this.hurtLeft = hurtLeft;
	}

	public AnimationPlayer getHurtRight() {
		return hurtRight;
	}

	public void setHurtRight(AnimationPlayer hurtRight) {
		this.hurtRight = hurtRight;
	}
	
	
	
}
