package Multiplayer;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import GameScenes.GameMode;
import GameScenes.PausedMenu;
import GameScenes.Player;
import animationAndTexture.AnimBackground;
import animationAndTexture.AnimChoc;
import animationAndTexture.Animation;
import animationAndTexture.AnimationVirus;
import animationAndTexture.TextureCat;
import animationAndTexture.TextureDog;

//DualMode class define all components for a dual game mode in contamined
public class DualMode extends GameMode {

	private TrueTypeFont yoster;
	private Font temp;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		this.playGround = new TwoPlayersGameScene();
		this.playGround.init(gc, sbg);
		this.temp = new Font("Verdana", Font.BOLD, 32);
		this.yoster = new TrueTypeFont(temp, true);
		this.menu = new PausedMenu(0, 0, 1920, 1080, gc, this, sbg);
		this.isPaused = false;
		this.isStarted = false;
		
		this.playerInterface = new TwoPlayersHud(0, 0, 1920, 150);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		this.playGround.render(gc, sbg, g);

		this.playerInterface.render(gc, g);
		if (!this.isStarted) {
			this.yoster.drawString(gc.getScreenWidth()/2-117, 75, "Click to begin", Color.red);
		}else {
			this.gamePaused();
			this.gameEnded();
		}
		if (this.isPaused) {
			this.menu.render(gc, g);
		}
		
	}	

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		
		Input kb = gc.getInput();
		if (this.isPaused) {
			this.menu.interact();
			if (kb.isKeyPressed(Input.KEY_ESCAPE)) {
				this.setUnpaused();
			}
		}
		if (!this.isStarted) {
			if (kb.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				this.isStarted = true;
				this.initStart();
			}else if (kb.isKeyPressed(Input.KEY_ESCAPE)) {
				this.isPaused = true;
			}
		}else {
			if (!this.isPaused) {
				this.countCurrent = System.nanoTime()/100000000 - this.countStart/100000000;
				if (kb.isKeyPressed(Input.KEY_ESCAPE)) {
					this.isPaused = true;
				}
				if (this.isPlaying()) {
					((TwoPlayersGameScene) this.playGround).update(gc, sbg, arg2);
					this.updateLifeDisplay(((TwoPlayersGameScene) this.playGround).getP1(), ((TwoPlayersGameScene) this.playGround).p2);
				}
			}
		}
		
	}
	
	public void reStart() {
		((TwoPlayersGameScene) this.playGround).setLifeP1(100);
		((TwoPlayersGameScene) this.playGround).lifeP2 = 100;
		((TwoPlayersGameScene) this.playGround).winner = null;
		this.isPaused = false;
		this.isStarted = false;
		this.playerInterface = new TwoPlayersHud(0, 0, 1920, 150);
		((TwoPlayersGameScene) this.playGround).initPlayers(((TwoPlayersGameScene) this.playGround).getP1(), ((TwoPlayersGameScene) this.playGround).p2);
		((TwoPlayersGameScene) this.playGround).virus.updatePlayer(((TwoPlayersGameScene) this.playGround).getP1(), ((TwoPlayersGameScene) this.playGround).p2);
	}
	
	public void setUnpaused() {
		this.isPaused = false;
		this.countStart = System.nanoTime();
	}
	
	public boolean isPlaying() {
		if ((this.count - countCurrent/10) < 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void updateLifeDisplay(Player p1, Player p2) {
		if (((TwoPlayersGameScene) this.playGround).getLifeP1() != 0) {
			this.playerInterface.getLifePlayers()[0].setLifeLoosed(100-((TwoPlayersGameScene) this.playGround).getLifeP1());
		}
		if (((TwoPlayersGameScene) this.playGround).lifeP2 != 0) {
			this.playerInterface.getLifePlayers()[1].setLifeLoosed(100-((TwoPlayersGameScene) this.playGround).lifeP2);
		}
				
	}
	
	public void gamePaused() {
		if ((this.count - countCurrent/10) > 0) {
			this.yoster.drawString(1920/2-10, 75, String.valueOf(this.count - countCurrent/10), Color.red);
		}else if ((this.count - countCurrent/10) == 0) {
			this.yoster.drawString(1920/2-50, 75, "Start !", Color.red);
			this.isPaused = false;
			((TwoPlayersGameScene) this.playGround).setOneMs(System.nanoTime());
		}else {
			if (this.isPaused) {
				this.yoster.drawString(1920/2-110, 75, "Game paused", Color.red);
			}
		}
	}
	
	public void gameEnded() {
		if (((TwoPlayersGameScene) this.playGround).winner != null) {
			this.yoster.drawString(1920/2-135, 75,((TwoPlayersGameScene) this.playGround).winner.getName() + " has won !", Color.red);
		}
	}
	
	public void keyReleased(int key, char c) {
		//Input kb = gc.getInput();
		
		if (key == ((TwoPlayersGameScene) this.playGround).getP1().getKeyBoard()[0]) {
			((TwoPlayersGameScene) this.playGround).getP1().setAllowJump(true);
	    }
		if (key == ((TwoPlayersGameScene) this.playGround).getP1().getKeyBoard()[1] || key == ((TwoPlayersGameScene) this.playGround).getP1().getKeyBoard()[3]) {
			((TwoPlayersGameScene) this.playGround).getP1().setCounterJump(0);
		}
		
		if (key == ((TwoPlayersGameScene) this.playGround).p2.getKeyBoard()[0]) {
			((TwoPlayersGameScene) this.playGround).p2.setAllowJump(true);
	    }
		if (key == ((TwoPlayersGameScene) this.playGround).p2.getKeyBoard()[1] || key == ((TwoPlayersGameScene) this.playGround).p2.getKeyBoard()[3]) {
			((TwoPlayersGameScene) this.playGround).p2.setCounterJump(0);
		}
	   
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	public void initStart() {
		this.countStart = System.nanoTime();
		this.count = 5;
		this.countCurrent = 0;
	}
	

}
