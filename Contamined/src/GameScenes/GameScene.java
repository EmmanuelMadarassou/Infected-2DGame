package GameScenes;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import animationAndTexture.AnimBackground;
import animationAndTexture.AnimChoc;
import animationAndTexture.Animation;
import animationAndTexture.AnimationVirus;
import animationAndTexture.TextureCat;
import animationAndTexture.TextureDog;

//GameScene class define all the components of a basic game scene wich for examples include a background, a map, at least one player
public abstract class GameScene {

	protected Map stage;
	protected Animation background;
	protected Input kb;
	protected Player p1;
	protected GameContainer gc;
	
	protected long oneMs;
	protected long lastObjectAdd;
	
	
	protected float lifeP1;
	

	//Every game scene will need this method to run normally
	//Init initialise all game scenes components
	//render to display the game scene and its components
	//update to update components state in the game scene
	//Keyreleased to interact with the player
	//IsGameEnded to implement conditions to stop de game when it's finished
	public abstract void init(GameContainer gc, StateBasedGame sbg);
	public abstract void render(GameContainer gc, StateBasedGame sbg, Graphics g);
	protected abstract void update(GameContainer gc2, StateBasedGame sbg, int arg2);
	public abstract void keyReleased(int key, char c);
	public abstract boolean isGameEnded();
	public abstract void updateLife();
	
	
	public Player getP1() {
		return p1;
	}
	public void setP1(Player p1) {
		this.p1 = p1;
	}
	public float getLifeP1() {
		return lifeP1;
	}
	public void setLifeP1(float lifeP1) {
		this.lifeP1 = lifeP1;
	}
	public long getOneMs() {
		return oneMs;
	}
	public void setOneMs(long oneMs) {
		this.oneMs = oneMs;
	}
	
	
}
