package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

//Every elements in the game is drawable and has a position in the screen. This class was created to control all this aspects
public abstract class Element implements Drawable {
	
	protected float pos_X, pos_Y;
	
	public Element() {
	}
	
	public Element(float x, float y) {
		this.pos_X = x;
		this.pos_Y = y;
	}
	
	public float getPos_X() {
		return pos_X;
	}

	public void setPos_X(float pos_X) {
		this.pos_X = pos_X;
	}

	public float getPos_Y() {
		return pos_Y;
	}

	public void setPos_Y(float pos_Y) {
		this.pos_Y = pos_Y;
	}

	
	
	
}
