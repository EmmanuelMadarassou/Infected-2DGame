package main;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Multiplayer.DualMode;

//Loadging screen is a class used to control the first loading screen
public class loadingScreen extends BasicGameState{

	private Image bg;
	private Image fg;
	
	//Propeties used for timing the moment at wich I start loading the states of the game. 
	//This is needeed to ensure that the background is well display before we start loading states. Instead we have a black screen :(
	private boolean start;
	private long startTime, current;

	private TrueTypeFont yoster;
	private Font temp;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		this.bg = new Image("res/loading_screen.png");
		this.fg = new Image("res/KritaSaves/pleaseWait.png");
		this.start = false;
		
		this.startTime = System.currentTimeMillis();
		
		this.temp = new Font("Verdana", Font.BOLD, 40);
		this.yoster = new TrueTypeFont(temp, true);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		this.bg.draw(0, 0);
		this.fg.draw(gc.getScreenWidth()/2-325, 1080-1080/4);

		this.current = System.currentTimeMillis();
		
		//If the state has been launched and the time wich has passing by is superior than 1 sec then we initialised all needing stages
		if (!this.start && (this.current - this.startTime) > 1000 ) {
			sbg.addState(new LandingMenu());
			sbg.getState(1).init(gc, sbg);
			sbg.addState(new DualMode());
			sbg.getState(2).init(gc, sbg);
			
			//Entering in game menu 
			sbg.enterState(1);
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
