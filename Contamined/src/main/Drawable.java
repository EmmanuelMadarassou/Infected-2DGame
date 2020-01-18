package main;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

//Interface to control every drawable items in the game
public interface Drawable {
	
	//render is a method used for rendering the drawable on the screen
	public void render(GameContainer gc, Graphics g);
}
