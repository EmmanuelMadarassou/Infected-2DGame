package Multiplayer;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import GameScenes.Hud;
import GameScenes.LifeBar;
import GameScenes.LifeBarLeft;
import GameScenes.LifeBarRight;

public class TwoPlayersHud extends Hud {

	public TwoPlayersHud(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		this.setLifePlayers(new LifeBar[2]);
		
		try {
			this.getLifePlayers()[0] = new LifeBarLeft(this.pos_X, this.pos_Y, this.width/3, this.height, new Image("res/cat/icone.png"));
			this.getLifePlayers()[1] = new LifeBarRight(this.pos_X+(2*this.width)/3, this.pos_Y, this.width/3, this.height, new Image("res/dog/icone.png"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
