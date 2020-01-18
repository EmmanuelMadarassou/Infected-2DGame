package main;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import animationAndTexture.AnimBackground;
import animationAndTexture.AnimationLobby;

//LandingMenu Class is used for controlling the main menu
public class LandingMenu  extends BasicGameState {
	
	//AnimationLobby and AnimationBackground are animation class with already defined frames
	private AnimationLobby fg;
	private AnimBackground bg;
	private Image ffg;
	private MainMenu main;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		this.fg = new AnimationLobby(100, 0, 1, "infinite");
		this.bg = new AnimBackground(100, 0, 4);
		this.main = new MainMenu(0,0,1920,1080, gc, sbg);
		this.ffg = new Image("res/menu screen.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		this.bg.render(gc, g);
		//this.fg.render(gc, g);
		this.main.render(gc, g);
		this.ffg.draw(0,0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		// TODO Auto-generated method stub		
		this.main.interact();
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}
