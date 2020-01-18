package main;
import java.io.File;

import javax.swing.JOptionPane;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
//MADARASSOU Emmanuel
//
//Contamined is a 2d players game. Feel free to download it and try it ourself.
//I used Slick2D library for the developement of this game. If you need more informations: http://www.shionn.org/tutoriels-slick-2d

//Main class is used to launch game and initialise state of the game (Loading screen, Menu and game scene)
public class Main extends StateBasedGame{

	public Main(String name) {
		super(name);
	}

	// This function is used to initialised state of the game
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		//I only initiate one state here in order to do the loading of the other states with a picture on the screen
		addState(new loadingScreen());
		enterState(0);
	}
	
	public static void main(String[] args) {
		//Initialisation of the window and execution of the screen.
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
		System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
		try {			
			AppGameContainer screen = new AppGameContainer(new Main("Infected"));
			screen.setShowFPS(true);
			screen.setTargetFrameRate(60);
			screen.setForceExit(true);
			screen.setIcon(new String ("res/KritaSaves/icone32.png"));
			screen.setDisplayMode(screen.getScreenWidth(), screen.getScreenHeight(), true);
			screen.setFullscreen(true);
			
			//All set, I launched the screen to display the state initialized before
			screen.start(); 
		} catch (SlickException exception) {
			exception.printStackTrace();
		}
	}

}
