package Multiplayer;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import GameScenes.Boost;
import GameScenes.GameScene;
import GameScenes.JumpBoost;
import GameScenes.Map;
import GameScenes.Pickable;
import GameScenes.Player;
import GameScenes.SpeedBoost;
import animationAndTexture.AnimBackground;
import animationAndTexture.AnimChoc;
import animationAndTexture.AnimationVirus;
import animationAndTexture.TextureCat;
import animationAndTexture.TextureDog;

//This class is used to set the 2 player's game scene and interactions between the 2 players
public class TwoPlayersGameScene extends GameScene {

	protected Player p2;
	protected float lifeP2;
	
	protected ArrayList<Pickable> object;
	
	private TextureCat cat;
	private TextureDog dog;
	protected AnimationVirus virus;
	private AnimChoc choc;
	
	protected Player winner;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg){
		// TODO Auto-generated method stub
		this.gc = gc;
		this.winner = null;
		this.cat = new TextureCat();
		this.dog = new TextureDog();
		this.virus = new AnimationVirus(100, 0, (float) 1.25);
		
		this.object = new ArrayList<Pickable>();
		this.object.add(new JumpBoost(26,24));
		this.lastObjectAdd = System.currentTimeMillis();
		
		this.setLifeP1(100);
		this.lifeP2 = 100;
		
		this.choc = new AnimChoc(50, 0, (float) 1, "oneTime");

		this.kb = this.gc.getInput();
		this.stage = new Map("Old Factory");
		this.background = new AnimBackground(100, 0, 4);
		
		this.initPlayers(this.p1, this.p2);
		this.virus.updatePlayer(this.getP1(), this.p2);
	}
	
	
	public void initPlayers(Player p1, Player p2) {
		
		Random rand = new Random();
		int temprand = rand.nextInt(8) + 0;
		
		this.setP1(new Player(gc, cat, this.stage.getSpawns()[temprand][0][0], this.stage.getSpawns()[temprand][0][1]-1, this.stage.getMap(), kb.KEY_Z, kb.KEY_Q, kb.KEY_D, kb.KEY_S, Color.green, "P1", false));
		this.p2 = new Player(gc, dog, this.stage.getSpawns()[temprand][1][0], this.stage.getSpawns()[temprand][1][1]-1, this.stage.getMap(), kb.KEY_UP, kb.KEY_LEFT, kb.KEY_RIGHT, kb.KEY_DOWN, Color.yellow, "P2", false);
		
		temprand = rand.nextInt(1) + 0;
		if (temprand == 1) {
			this.p1.setContamined(true);
		}else {
			this.p2.setContamined(true);
		}
	}

	public Boost randomBoost() {
		Random rand = new Random();	
		int tempX = 36, tempY = 20;
		boolean condition = false;
		while (!condition) {
			tempX = rand.nextInt(58);
			tempY = rand.nextInt(32);
			if ((this.stage.getMap().getTileImage(tempX, tempY, this.stage.getMap().getLayerIndex("Block_parkour")) == null) && (this.stage.getMap().getTileImage(tempX, tempY+1, this.stage.getMap().getLayerIndex("Block_parkour")) != null)){
				condition = true;
			}
		}
		
		int randBoost = rand.nextInt(2)+0;
		if (randBoost == 1) {
			return new SpeedBoost(tempX,tempY);
		}else {
			return new JumpBoost(tempX,tempY);
		}
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		// TODO Auto-generated method stub
		 this.background.render(gc, g);
		 this.stage.getMap().render(0, 0, 0); //affichage block
		 //this.map.render(0, 0, 1); //affichage block
		 this.stage.getMap().render(0, 0, 2); //affichage decoration
		
		 if (!this.p1.isCanBeTouched()) {
			 if ((System.nanoTime()/100000000 - this.p1.getLastTouch()/100000000)%2 != 0) {
				 this.p1.render(gc, g);
			 }
		 }else {
			 this.p1.render(gc, g);
		 }
		 
		 if (!p2.isCanBeTouched()) {
			 if ((System.nanoTime()/100000000 - p2.getLastTouch()/100000000)%2 != 0) {
				 this.p2.render(gc, g);
			 }
		 }else {
			 this.p2.render(gc, g);
		 }
		 this.renderObject(gc, g);
		 this.choc.render(gc, g);
		 this.virus.render(gc, g);
		 this.stage.getMap().render(0, 0, 3);
	}	
	
	public void renderObject(GameContainer gc, Graphics g) {
		for (int i=0; i<this.object.size(); i++) {
			this.object.get(i).render(gc, g);
		}
	}
	
	public void updateObject(int delta) {
		for (int i=0; i<this.object.size(); i++) {
			this.object.get(i).update(delta);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta){
		// TODO Auto-generated method stub
		
		if (!this.isGameEnded()) {
			this.p1.update(delta);
			this.p2.update(delta);
			if (this.p1.isCanBeTouched() && p2.isCanBeTouched() && System.nanoTime()/100000000 - p1.getLastTouch()/100000000 > 5 && System.nanoTime()/100000000 - p2.getLastTouch()/100000000 > 5) {
				if (this.p1.updateCollide(p2)) {
					this.setOneMs(System.nanoTime());
					this.choc.updatePosition(getP1().getPos_X()-(getP1().getPos_X()-(p2.getPos_X()+p2.getWidth()))/2, getP1().getPos_Y()-(getP1().getPos_Y()-(p2.getPos_Y()+p2.getHeight()))/2);
				}
			}
			this.virus.updatePlayer(this.p1, this.p2);
			this.updateLife();
		}
		
		this.updateObject(delta);
		this.pickUpObject();
		this.addObject();
	}
	
	public void pickUpObject() {
		for (int i=0; i<this.object.size(); i++) {
			if(this.p1.updateCollide(this.object.get(i)) || this.p2.updateCollide(this.object.get(i))) {
				this.object.remove(i);
			}
			
		}
	}
	
	public void addObject() {
		if (System.currentTimeMillis() - this.lastObjectAdd > 10 * 1000) {
			this.object.add(this.randomBoost());
			this.lastObjectAdd = System.currentTimeMillis();
		}
	}
	
	public void keyReleased(int key, char c) {
		//Input kb = gc.getInput();
		
		if (key == this.p1.getKeyBoard()[0]) {
			this.p1.setAllowJump(true);
	    }
		if (key == this.p1.getKeyBoard()[1] || key == getP1().getKeyBoard()[3]) {
			this.p1.setCounterJump(0);
		}
		
		if (key == this.p2.getKeyBoard()[0]) {
	    	this.p2.setAllowJump(true);
	    }
		if (key == this.p2.getKeyBoard()[1] || key == p2.getKeyBoard()[3]) {
			this.p2.setCounterJump(0);
		}
		
	   
	}
	
	public boolean isHurted(Player p) {
		if  (p.isHurtedLeft() || p.isHurtedRight()) {
			return true;
		}else {
			return false;
		}
	}
	
	public Player isEnded() {
		if (this.lifeP1 == 0) {
			return this.p2;
		}else if (this.lifeP2 == 0) {
			return this.p1;
		}else {
			return null;
		}
	}
	
	public void updateLife() {
		if ((System.nanoTime()/100000000 - this.getOneMs()/100000000) >= 1) {
			if (this.p1.isContamined()) {
				this.p1.setLife(this.p1.getLife() - 1);
				this.lifeP1 = (float) this.getP1().getLife() * 100 / 600;
			}else if (this.p2.isContamined()) {
				this.p2.setLife(this.p2.getLife() - 1);
				this.lifeP2 = (float) this.p2.getLife() * 100 / 600;
			}
			this.setOneMs(System.nanoTime());
		}
	}

	@Override
	public boolean isGameEnded() {
		// TODO Auto-generated method stub
		if (this.lifeP1 == 0) {
			this.winner = this.p2;
			return true;
		}else if (this.lifeP2 == 0) {
			this.winner = this.p1;
			return true;
		}else {
			return false;
		}
	}
	
}
