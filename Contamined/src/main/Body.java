package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

//A body is an element with a width, height and sometimes a color but not always
public abstract class Body extends Element{
	
	protected float width, height;
	protected Color color;
	
	public Body() {
	}
	
	public Body(float x, float y, float width, float height) {
		super(x,y);
		this.width = width; 
		this.height = height;
	}
	
	public void render(GameContainer gc, Graphics g) {
		g.setColor(this.color);
		g.drawRect(this.pos_X*32, this.pos_Y*32, this.width*32, this.height*32);
	}

	
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}



}
