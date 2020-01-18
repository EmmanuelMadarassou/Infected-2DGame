package GameScenes;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import animationAndTexture.AnimElectricity;
import animationAndTexture.AnimLanding;
import animationAndTexture.AnimWallSide;
import animationAndTexture.Animation;
import animationAndTexture.AnimationPlayer;
import animationAndTexture.AnimationVirus;
import animationAndTexture.FootStep;
import animationAndTexture.Norm_foot;
import animationAndTexture.TexturePlayer;
import main.Body;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;



public class Player extends Mobile implements Collider {

	protected GameContainer gc;
	protected Input kb;
	protected TiledMap map;
	protected TexturePlayer playerAnim;
	protected int[] keyBoard;
	protected Color nameColor;
	private String name;
	protected boolean isStanding, isHeadingRight;
	
	protected boolean allowJump;
	protected boolean isJumping;
	protected int counterJump;
	protected long lastJump;
	protected float nextPos, increaseJump;
	
	protected Image foot_tuileL, foot_tuileR; //tuile en-dessous du joueur
	protected Image head_tuileL, head_tuileR; // tuile au-dessus du joueur
	protected Image borderLeftT,  borderLeftB; //tuile à gauche du joueur
	protected Image borderRightT,  borderRightB; //tuile à droite du joueur
	
	protected float coeffJump, coeffGravity;
	protected FootStep stepAnim;
	protected boolean canMove;
	
	private float life;
	protected boolean isContamined;
	protected boolean canBeTouched;
	
	protected long lastContamination;
	protected long lastTouch;
	
	protected boolean isHurtedRight;
	protected boolean isHurtedLeft;
	protected boolean wallJumpingLeft;
	protected boolean wallJumpingRight;
	
	protected AnimWallSide wallCloud;
	protected AnimWallSide wallBoosted;
	protected AnimLanding landingCloud;
	protected AnimLanding landingBoosted;
	
	protected ArrayList<Boost> boost;
	protected float speedBoostEffect, jumpBoostEffect;
	protected Norm_foot normal;
	protected AnimElectricity elec;
	
	
	public Player(GameContainer gc, TexturePlayer a,int x, int y, TiledMap m, int up, int left, int right, int down, Color couleur, String name, boolean isC) {
		
		this.pos_X = x;
		this.pos_Y = y;
		this.setContamined(isC);
		this.width = (float) 0.875;
		this.height = (float) 1.15;
		this.map = m;
		this.kb = gc.getInput();
		this.playerAnim = a;
		this.setKeyBoard(new int[4]);
		this.getKeyBoard()[0] = up;
		this.getKeyBoard()[1] = right;
		this.getKeyBoard()[2] = down;
		this.getKeyBoard()[3] = left;
		this.nameColor = couleur;
		this.setName(name);
		this.isStanding = true;
		this.isJumping = false;
		this.isHeadingRight = true;
		this.coeffJump = 1;
		this.speedUp = (float)0.35;
		this.speedDown = (float)0.1;
		this.speedX = 0;
		this.maxSpeedX = (float)0.125;
		this.coeffGravity = 0;
		this.coeffJump = 1;
		this.setCounterJump(0);
		this.nextPos = this.pos_Y;
		this.canMove = true;
		this.setCanBeTouched(true);
		this.setAllowJump(true);
		this.stepAnim =  new Norm_foot(50, 0, (float) 2);
		this.landingCloud = new AnimLanding(50, 0 , (float) 1.35, "oneTime", "normal");
		this.landingBoosted = new AnimLanding(50, 0 , (float) 1.35, "oneTime", "boosted");
		this.landingBoosted.setFrameDisplay(17);
		this.wallCloud = new AnimWallSide(30, 0 , (float) 0.85, "oneTime", "normal");
		this.wallBoosted = new AnimWallSide(30, 0 , (float) 0.85, "oneTime", "boosted");
		this.wallBoosted.setFrameDisplay(17);
		this.elec = new AnimElectricity(100, 0, (float) 1.5, "oneTime");
		this.elec.setFrameDisplay(15);
		this.setLife(600);
		this.boost = new ArrayList<Boost>();
	}
	
	public void updateTuile(int delta) {
		
		this.head_tuileL = this.map.getTileImage( (int) (this.pos_X+this.width-this.width*0.65), (int) (this.pos_Y-0.2825), this.map.getLayerIndex("Block_parkour"));
		this.head_tuileR = this.map.getTileImage( (int) (this.pos_X+width*0.75), (int) (this.pos_Y-0.2825), this.map.getLayerIndex("Block_parkour"));

		this.foot_tuileL = this.map.getTileImage( (int) (this.pos_X+this.width-this.width*0.65), (int) (this.pos_Y+this.height+(this.speedDown*(this.coeffGravity/2))*delta/17), this.map.getLayerIndex("Block_parkour"));
		this.foot_tuileR = this.map.getTileImage( (int) (this.pos_X+this.width*0.65), (int) (this.pos_Y+this.height+(this.speedDown*this.coeffGravity)*delta/17), this.map.getLayerIndex("Block_parkour"));
		
		this.borderLeftT = this.map.getTileImage( (int) (this.pos_X-this.speedX*delta/17), (int) (this.pos_Y), this.map.getLayerIndex("Block_parkour"));
		this.borderLeftB = this.map.getTileImage( (int) (this.pos_X-this.speedX*delta/17), (int) (this.pos_Y+this.height-0.2825), this.map.getLayerIndex("Block_parkour"));
				
		this.borderRightT = this.map.getTileImage( (int) (this.pos_X+this.width*0.9+this.speedX*delta/17), (int) (this.pos_Y), this.map.getLayerIndex("Block_parkour"));
		this.borderRightB = this.map.getTileImage( (int) (this.pos_X+this.width*0.9+this.speedX*delta/17), (int) (this.pos_Y+this.height-0.2825), this.map.getLayerIndex("Block_parkour"));
	}
	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		//super.render(gc, g); 
		
		//g.drawString(String.valueOf(this.counterJump), this.pos_X*32 + 16, this.pos_Y*32 - 32 ); //affichage du nom au-dessus du joueur 1
		
		this.playerAnim.updatePosition(this.pos_X, this.pos_Y);
		this.elec.updatePosition(this.pos_X, this.pos_Y);
		
		this.renderBoostEffect(gc, g);
		this.renderFootStep(gc, g);
		this.renderPlayer(gc, g);
	
		this.elec.render(gc, g);
		
		if (this.landingCloud.getFrameDisplay() < this.landingCloud.getFrame().length) {
			this.landingCloud.getFrame(0).draw(this.getPos_X(), this.getPos_Y());
		}
		
		//this.playerAnim.getRunLeft().render(gc, g);

	}
	
	public void renderBoostEffect(GameContainer gc, Graphics g) {
		if (this.hasJumpBoost()) {
			this.landingBoosted.render(gc, g);
			this.wallBoosted.render(gc, g);
		}else {
			this.landingCloud.render(gc, g);
			this.wallCloud.render(gc, g);
		}
		
	}
	
	public void updateBoost() {
		for (int i=0; i<this.boost.size(); i++) {
			this.boost.get(i).currentEffect = System.currentTimeMillis()/1000 - this.boost.get(i).startEffect;
		}
		this.removeOldBoost();
		this.updateBoostEffect();
	}
	
	public void removeOldBoost() {
		for (int i=0; i<this.boost.size(); i++) {
			if (this.boost.get(i).currentEffect > this.boost.get(i).timeEffect) {
				this.boost.remove(i);
			}
		}
	}
	
	
	public void updateBoostEffect() {
		if (this.hasSpeedBoost()) {
			if (this.elec.getFrameDisplay() >= this.elec.getFrame().length) {
				this.elec.setFrameDisplay(0);
			}
			this.speedBoostEffect = 1;
		}else {
			this.speedBoostEffect = 0;
		}
		if (this.hasJumpBoost()) {	
			this.jumpBoostEffect = 1;
		}else {
			this.jumpBoostEffect = 0;
		}
	}
	
	public boolean hasSpeedBoost() {
		for (int i=0; i<this.boost.size(); i++) {
			if (this.boost.get(i).type == "speed") {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasJumpBoost() {
		for (int i=0; i< this.boost.size(); i++) {
			if (this.boost.get(i).type == "jump") {
				return true;
			}
		}
		return false;
	}
	
	public void renderFootStep(GameContainer gc, Graphics g) {
		if (this.speedX > 0.09 && this.isOnFloor()) {
			if (this.isHeadingRight) {
				this.stepAnim.getFootRight().setPos_X((float) (this.pos_X-this.width/2-1.2));
				this.stepAnim.getFootRight().setPos_Y((float) (this.pos_Y-0.55));
				this.stepAnim.getFootRight().render(gc, g);
			}else {
				this.stepAnim.getFootLeft().setPos_X((float) (this.pos_X-this.width/2+0.25));
				this.stepAnim.getFootLeft().setPos_Y((float) (this.pos_Y-0.55));
				this.stepAnim.getFootLeft().render(gc, g);
			}
			
		}
	}
	
	public void renderPlayer(GameContainer gc, Graphics g) {
		
		g.setColor(this.nameColor);
		g.drawString(this.getName(), (float)(this.pos_X+0.25)*32, (float)(this.pos_Y-1)*32);
		this.playerAnim.updatePosition(this.pos_X, this.pos_Y);
		
		if (this.isHurtedLeft()) {
			this.playerAnim.getHurtRight().render(gc, g);
		}else if (this.isHurtedRight()) {
			this.playerAnim.getHurtLeft().render(gc, g);
		}else if (this.isHeadingRight) {
			this.animPlayerRight().render(gc, g);
		}else {
			this.animPlayerLeft().render(gc, g);
		}
	}
	
	public AnimationPlayer animPlayerRight() {
		if (this.isOnFloor()) {
			if (this.kb.isKeyDown(this.getKeyBoard()[1])) {
				if (this.speedX > 0.12) {
					return this.playerAnim.getRunRight();
				}else {
					return this.playerAnim.getWalkRight();
				}
			}else {
				return this.playerAnim.getStandRight();
			}
		}else if (this.wallJumpingRight) {
			return this.playerAnim.getFallLeft();
		}else if (this.isJumping) {
			return this.playerAnim.getRunRight();
			
		}else {
			return this.playerAnim.getFallRight();
		}
	}
	public AnimationPlayer animPlayerLeft() {
		if (this.isOnFloor()) {
			if (this.kb.isKeyDown(this.getKeyBoard()[3])) {
				if (this.speedX > 0.12) {
					return this.playerAnim.getRunLeft();
				}else {
					return this.playerAnim.getWalkLeft();
				}
			}else {
				return this.playerAnim.getStandLeft();
			}
		}else if (this.wallJumpingLeft) {
			return this.playerAnim.getFallRight();
		}else if (this.isJumping) {
			return this.playerAnim.getRunLeft();
		}else {
			return this.playerAnim.getFallLeft();
		}
	}
	
	public boolean isContamined() {
		return this.isContamined;
	}
	public boolean isOnFloor() {
		if (this.foot_tuileL != null || this.foot_tuileR != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isNearWall() {
		if (this.borderLeftB != null || this.borderRightB != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public void reInitWallCloud() {
		if (this.wallCloud.getFrameDisplay() > this.wallCloud.getFrame().length-1) {
			this.wallCloud.setFrameDisplay(0);
		}
		if (this.isHeadingRight) {
			this.wallCloud.updatePosition(this.pos_X-(float)0.5+this.width, this.pos_Y-(float)0.25);
		}else {
			this.wallCloud.updatePosition(this.pos_X-(float)0.3, this.pos_Y-(float)0.25);
		}
		if (this.hasJumpBoost()) {
			if (this.wallBoosted.getFrameDisplay() > this.wallBoosted.getFrame().length-1) {
				this.wallBoosted.setFrameDisplay(0);
				this.wallBoosted.updatePosition(0,0);
			}
			
			if (this.isHeadingRight) {
				this.wallBoosted.updatePosition(this.pos_X-(float)0.5+this.width, this.pos_Y-(float)0.25);
			}else {
				this.wallBoosted.updatePosition(this.pos_X-(float)0.3, this.pos_Y-(float)0.25);
			}
		}
	}
	
	public void addGravity(int delta) {
		if (!this.isOnFloor()) {
			if (this.coeffGravity > 2.5) {
				this.coeffGravity = (float) 2.5;
			}else {
				if (!this.isJumping) {
					if (this.isNearWall() && (this.kb.isKeyDown(this.getKeyBoard()[1]) || this.kb.isKeyDown(this.getKeyBoard()[3]))){
						
						this.reInitWallCloud();
						
						if (this.coeffGravity > 0.75) {
							this.coeffGravity = (float) 0.75;
						}else {
							this.coeffGravity = (float) (this.coeffGravity + 0.025*delta/17);
						}
						
						
					}else {
						this.wallCloud.setFrameDisplay(20);
						this.coeffGravity = (float) (this.coeffGravity + 0.1*delta/17);
						if (this.coeffGravity > 2.5) {
							this.coeffGravity = (float) 2.5;
						}
					}
					
				}
				
			}
			this.moveDown(delta);
		}else {
			//this.allowJump = false;
			this.coeffGravity = 0;
		}
	}
	
	public void jump() {
		if (this.isAllowJump()) {
			if (kb.isKeyDown(this.getKeyBoard()[1]) || kb.isKeyDown(this.getKeyBoard()[3])) {
				if (this.getCounterJump() == 0) {
					if (System.nanoTime()/100000000 - this.lastJump/100000000 <= 6 ) {
						this.setCounterJump(this.getCounterJump()+1);
					}else {
						this.setCounterJump(0);
					}
				}else if (this.getCounterJump() == 1) {
					
					if (System.nanoTime()/100000000 - this.lastJump/100000000 <= 9 ) {
						this.setCounterJump(this.getCounterJump()+1);
					}else {
						this.setCounterJump(0);
					}
				}else if (this.getCounterJump() == 2) {
					
					this.setCounterJump(0);
				}
			}else {
				this.setCounterJump(0);
			}
			
			if (this.getCounterJump() == 0) {
				this.nextPos = (float) (this.pos_Y - this.width*2.5);
				this.increaseJump = (float) 0.07;
			}else if (this.getCounterJump() == 1) {
				this.nextPos = (float) (this.pos_Y - this.width*3.5);
				this.increaseJump = (float) 0.03;
			}else {
				this.nextPos = (float) (this.pos_Y - this.width*5);
				this.increaseJump = (float) 0.009;
				this.setCounterJump(0);
			}
			
			this.setAllowJump(false);
			this.isJumping = true;
			this.coeffJump = 1;
			this.coeffGravity = 0;
			this.lastJump = System.nanoTime();
	
		}
	}

	public boolean wallJumpLeft() {
		if (this.borderLeftT != null && this.borderLeftB != null  && kb.isKeyDown(this.getKeyBoard()[0]) && kb.isKeyDown(this.getKeyBoard()[3]) && this.isAllowJump()){
			//System.out.println("WallJumprLeft");
			this.reInitWallCloud();
			this.wallJumpingRight = false;
			this.wallJumpingLeft = true;
			return true;
		}else {
			return false;
		}
	}
	
	public boolean wallJumpRight() {
		if (this.borderRightT != null && this.borderRightB != null  && kb.isKeyDown(this.getKeyBoard()[0]) && kb.isKeyDown(this.getKeyBoard()[1]) && this.isAllowJump()){
			//System.out.println("WallJumprRight");
			this.reInitWallCloud();
			this.wallJumpingRight = true;
			this.wallJumpingLeft = false;
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public boolean moveUp(int delta) {
		// TODO Auto-generated method stub
		this.updateTuile(delta);
		if (this.head_tuileL != null || this.head_tuileR != null) {
			//System.out.println("Collision top");
			return false;
		}else {
			this.pos_Y = this.pos_Y - (this.speedUp/(float)this.coeffJump)*delta/17;
			return true;
		}
		
	}

	@Override
	public boolean moveDown(int delta) {
		// TODO Auto-generated method stub
		this.updateTuile(delta);
		if (this.foot_tuileL != null || this.foot_tuileR != null) {
			//System.out.println("Collision bottom");
			this.landingCloud.updatePosition(this.pos_X, this.pos_Y);
			if (this.hasJumpBoost()) {
				this.landingBoosted.updatePosition(this.pos_X, this.pos_Y);
			}
			this.pos_Y = (float) ((int) (this.pos_Y+this.height+(this.speedDown*this.coeffGravity)*delta/17) - this.height);
			return false;
		}else {
			if (this.coeffGravity != 0) {
				this.pos_Y = this.pos_Y + (this.speedDown*this.coeffGravity)*delta/17;
			}else {
				this.pos_Y = this.pos_Y + this.speedDown*delta/17;
			}
			return true;
		}
	}

	@Override
	public boolean moveLeft(int delta) {
		// TODO Auto-generated method stub
		this.updateTuile(delta);
		if (this.borderLeftB != null || this.borderLeftT != null) {
			//System.out.println("Collision left");
			//this.pos_X = (float) ((int) (this.pos_X-this.speedX*delta/17)+this.width*0.85);
			this.speedX = (float) (this.speedX-0.0035);
			this.setCounterJump(0);
			return false;
		}else {
			this.pos_X = this.pos_X - this.speedX*delta/17;
			return true;
		}
		
	}

	@Override
	public boolean moveRight(int delta) {
		// TODO Auto-generated method stub
		this.updateTuile(delta);
		if (this.borderRightB != null || this.borderRightT != null) {
			//System.out.println("Collision right");
			//this.pos_X = (float) ((int) (this.pos_X+this.speedX*delta/17)+(1-this.width*0.8));
			this.speedX = (float) (this.speedX-0.0035);
			this.setCounterJump(0);
			return false;
		}else {
			this.pos_X = this.pos_X + this.speedX*delta/17;
			return true;
		}
	}
	

	@Override
	public void move(int delta) {
		// TODO Auto-generated method stub
		
		if (this.kb.isKeyDown(this.getKeyBoard()[3]) || this.kb.isKeyDown(this.getKeyBoard()[1])) {
			if (this.speedX >= this.maxSpeedX) {
				this.speedX = this.maxSpeedX;
			}else {
				this.speedX = (float) (this.speedX + 0.0025*delta/17);
			}
			if (this.kb.isKeyDown(this.getKeyBoard()[1])){
				if(!this.moveRight(delta)) {
					//this.speedX = 0;
				}
				this.isHeadingRight = true;
			}else {
				if(!this.moveLeft(delta)) {
					//this.speedX = 0;
				}	
				this.isHeadingRight = false;
			}
					
		}else {
			if (this.speedX < 0 ) {
				this.speedX = 0;
			}else {
				this.speedX = (float) (this.speedX - 0.005*delta/17);
			}
		}
		
	
		
	}

	public void updateJumpAndGravity(int delta) {
		
		if (!this.isJumping) {
			if (this.kb.isKeyDown(this.getKeyBoard()[0]) && this.isOnFloor() && this.canMove) {
				this.jump();
				this.landingCloud.updatePosition(this.pos_X, this.pos_Y);
				if (this.hasJumpBoost()) {
					this.landingBoosted.updatePosition(this.pos_X, this.pos_Y);
				}
			}
			
		}else if (this.pos_Y > this.nextPos){
			if (!this.moveUp(delta)) {
				this.isJumping = false;
				this.nextPos = this.pos_Y;
			}else {
				this.coeffJump = this.coeffJump + this.increaseJump*delta/17;
			}
		}else {
			this.nextPos = this.pos_Y;
			this.isJumping = false;
		}
		
		if (System.nanoTime()/100000000 - this.lastJump/100000000 > 9 ) {
			this.setCounterJump(0);
		}
		
		
		if ((this.wallJumpLeft() || this.wallJumpRight()) &&  !this.isOnFloor()) {
			//System.out.println("wallJump");
			this.nextPos = (float) (this.pos_Y-2.5);
			this.increaseJump = (float) 0.07;
			this.setAllowJump(false);
			this.isJumping = true;
			this.coeffJump = 1;
			this.coeffGravity = 0;
			this.setCounterJump(0);
			this.lastJump = System.nanoTime();
			this.canMove = false;
			this.speedX = this.maxSpeedX;
		
		}
		
	}
	
	public void update(int delta) {
		this.updateBoost();
		this.updateJumpAndGravity(delta);
		this.addGravity(delta);
		if (!(System.nanoTime()/100000000 - this.getLastTouch()/100000000 < 17)) {
			this.setCanBeTouched(true);
			this.setHurtedLeft(false);
			this.setHurtedRight(false);
		}
		
		if (this.canMove) {
			this.move(delta);
		}else {

			if (this.wallJumpingLeft) {
				if (System.nanoTime()/100000000 - this.lastJump/100000000 < 5) {
					this.moveRight(delta);
					this.canMove = false;
				}else {
					this.canMove = true;
					this.isJumping = false;
					this.wallJumpingLeft = false;
				}
				
			}else if (this.wallJumpingRight) {
				if (System.nanoTime()/100000000 - this.lastJump/100000000 < 5) {
					this.moveLeft(delta);
					this.canMove = false;
				}else {
					this.canMove = true;
					this.isJumping = false;
					this.wallJumpingRight = false;
				}
			}else if (this.isHurtedLeft()) {
				if (System.nanoTime()/100000000 - this.getLastTouch()/100000000 < 5) {
					this.speedX = this.maxSpeedX/2;
					this.moveRight(delta);
					this.canMove = false;
				}else {
					this.setCanBeTouched(false);
					this.speedX = 0;
					this.canMove = true;
					this.setHurtedLeft(false);
				}
			}else if (this.isHurtedRight()) {
				if (System.nanoTime()/100000000 - this.getLastTouch()/100000000 < 5) {
					this.speedX = this.maxSpeedX/2;
					this.moveLeft(delta);
					this.canMove = false;
				}else {
					this.speedX = 0;
					this.setCanBeTouched(false);
					this.canMove = true;
					this.setHurtedRight(false);
				}
			}
			
			if (this.isHurtedLeft() || this.isHurtedRight() || this.wallJumpingLeft || this.wallJumpingRight) {
				this.canMove = false;
			}else {
				this.canMove = true;
			}

			if (System.nanoTime()/100000000 - this.getLastTouch()/100000000 > 5) {
				this.setHurtedLeft(false);
				this.setHurtedRight(false);
			}
		}
	}

	@Override
	public boolean collideTop(Body b) {
		// TODO Auto-generated method stub
		if ((b.getPos_X()+b.getWidth()*0.65 >= this.pos_X && b.getPos_X() + b.getWidth()*0.65 <= this.pos_X+this.width) || (b.getPos_X() + b.getWidth()-b.getWidth()*0.65 >= this.pos_X && b.getPos_X()+b.getWidth() -b.getWidth()*0.65 <= this.pos_X+this.width))  {
			if (b.getPos_Y()+b.getHeight() >= this.pos_Y && b.getPos_Y()+b.getHeight() <= this.pos_Y+this.height/5) {
				//System.out.println("Collision top");
				return true;
			}else {
				return false;
			}
			
		}else {
			return false;
		}
		
	}

	@Override
	public boolean collideBot(Body b) {
		// TODO Auto-generated method stub
		if ((this.pos_X+this.width*0.55 >= b.getPos_X() && this.pos_X + this.width*0.55 <= b.getPos_X()+b.getWidth()) || (this.pos_X + this.width*0.5>= b.getPos_X() && this.pos_X+this.width*0.5 <= b.getPos_X()+b.getWidth()))  {
			if (this.pos_Y+this.height >= b.getPos_Y() && this.pos_Y+this.height <= b.getPos_Y()+b.getHeight()/5) {
				//System.out.println("Collision bot");
				return true;
			}else {
				return false;
			}
			
		}else {
			return false;
		}
	}

	@Override
	public boolean collideLeft(Body b) {
		// TODO Auto-generated method stub
		
		if (this.pos_X <= b.getPos_X()+b.getWidth() && this.pos_X > b.getPos_X()) {
			if ((this.pos_Y+this.height >= b.getPos_Y()+2*b.getHeight()/3 && this.pos_Y+this.height <= b.getPos_Y() + b.getHeight()) || (b.getPos_Y()+b.getHeight() >= this.pos_Y+2*this.height/3 && b.getPos_Y()+b.getHeight() <= this.pos_Y + this.height)){
				//System.out.println("Collision left");
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}

	}

	@Override
	public boolean collideRight(Body b) {
		// TODO Auto-generated method stub
		
		if (this.pos_X+this.width >= b.getPos_X() && this.pos_X+this.width <= b.getPos_X()+b.getWidth()) {
			
			if ((this.pos_Y+this.height >= b.getPos_Y()+2*b.getHeight()/3 && this.pos_Y+this.height <= b.getPos_Y() + b.getHeight()) || (this.pos_Y+this.height >= b.getPos_Y()+b.getHeight() && this.pos_Y <= b.getPos_Y()) || (b.getPos_Y()+b.getHeight() >= this.pos_Y+2*this.height/3 && b.getPos_Y()+b.getHeight() <= this.pos_Y + this.height)){
				//System.out.println("Collision right");
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	
	@Override
	public boolean collide(Body b) {
		// TODO Auto-generated method stub
		
		if (this.collideLeft(b) || this.collideRight(b) || this.collideTop(b) || this.collideBot(b)) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public boolean updateCollide(Body b) {
		// TODO Auto-generated method stub

		if (this.collide(b)) {
			if (b instanceof Player) {
				Player p2 = (Player) b;
		
				p2.setLastTouch(System.nanoTime());
				this.setLastTouch(System.nanoTime());
				
				p2.canMove = false;
				this.canMove = false;
				
				boolean temp = p2.isContamined;
				p2.isContamined = this.isContamined;
				this.isContamined = temp;

				
				if (this.collideTop(b)) {
					p2.canMove = true;
					
					this.setContamined(true);
					p2.setContamined(false);
					
					p2.setAllowJump(true);
					p2.counterJump = 0;
					p2.jump();
					
					if (p2.isHeadingRight) {
						if (p2.getPos_X()+p2.getWidth()/2 > this.getPos_X()+this.getWidth()/2) {
							this.setHurtedRight(true);
						}else {
							this.setHurtedLeft(true);
						}
					}else {
						if (p2.getPos_X()+p2.getWidth()/2 < this.getPos_X()+this.getWidth()/2) {
							this.setHurtedLeft(true);
						}else {
							this.setHurtedRight(true);
						}
					}	
					p2.setAllowJump(true);
				}else if (this.collideBot(b)) {
					this.canMove = true;
					
					p2.setContamined(true);
					this.setContamined(false);
					
					this.setAllowJump(true);
					this.counterJump = 0;
					this.jump();
					
					if (this.isHeadingRight) {
						if (this.getPos_X()+this.getWidth()/2 > p2.getPos_X()+p2.getWidth()/2) {
							p2.setHurtedRight(true);
						}else {
							p2.setHurtedLeft(true);
						}
					}else {
						if (this.getPos_X()+this.getWidth()/2 < p2.getPos_X()+p2.getWidth()/2) {
							p2.setHurtedLeft(true);
						}else {
							p2.setHurtedRight(true);
						}
					}	
					this.setAllowJump(true);
				}else if (this.collideLeft(p2)) {
					this.setHurtedLeft(true);
					p2.setHurtedRight(true);					
				}else if (this.collideRight(p2)) {
					p2.setHurtedLeft(true);
					this.setHurtedRight(true);						
				}	
				return true;
				
			}else if(b instanceof Boost) {
				Boost nb = (Boost) b;
				nb.currentEffect = 0;
				nb.startEffect = System.currentTimeMillis()/1000;
				this.boost.add(nb);
				
				return true;
			}else {
				return false;
			}
			
		}else {
			return false;
		}
			
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getKeyBoard() {
		return keyBoard;
	}

	public void setKeyBoard(int[] keyBoard) {
		this.keyBoard = keyBoard;
	}

	public boolean isAllowJump() {
		return allowJump;
	}

	public void setAllowJump(boolean allowJump) {
		this.allowJump = allowJump;
	}

	public int getCounterJump() {
		return counterJump;
	}

	public void setCounterJump(int counterJump) {
		this.counterJump = counterJump;
	}

	public void setContamined(boolean isContamined) {
		this.isContamined = isContamined;
	}

	public boolean isCanBeTouched() {
		return canBeTouched;
	}

	public void setCanBeTouched(boolean canBeTouched) {
		this.canBeTouched = canBeTouched;
	}

	public long getLastTouch() {
		return lastTouch;
	}

	public void setLastTouch(long lastTouch) {
		this.lastTouch = lastTouch;
	}

	public boolean isHurtedLeft() {
		return isHurtedLeft;
	}

	public void setHurtedLeft(boolean isHurtedLeft) {
		this.isHurtedLeft = isHurtedLeft;
	}

	public boolean isHurtedRight() {
		return isHurtedRight;
	}

	public void setHurtedRight(boolean isHurtedRight) {
		this.isHurtedRight = isHurtedRight;
	}

	public float getLife() {
		return life;
	}

	public void setLife(float life) {
		this.life = life;
	}
	
}
