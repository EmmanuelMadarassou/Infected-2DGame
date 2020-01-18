package GameScenes;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import main.Button;
import main.Menu;

public class PausedMenu extends Menu {

	protected GameMode gm;
	
	public PausedMenu(float x, float y, float width, float height, GameContainer gc, GameMode gm, StateBasedGame sbg) {
		super(x, y, width, height, gc, sbg);
		// TODO Auto-generated constructor stub
		this.btn = new ArrayList<Button>();
		this.gm = gm;
		this.initButton();
		
		try {
			this.bg = new Image("res/KritaSaves/PausedMenu/PausedMenu.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initButton() {
	
		this.addButton(new Button(725, 535, 449, 65, gc,"resume", (float) 6));
		this.addButton(new Button(805, 620, 305, 62, gc,"restart", (float) 6));
		this.addButton(new Button(867, 713, 158, 62, gc,"quit", (float) 6));
		
		try {
			this.getButtonById("resume").setSelectedImg(new Image("res/KritaSaves/PausedMenu/resumeSelected.png"));
			this.getButtonById("resume").setNotSectedImg(new Image("res/KritaSaves/PausedMenu/resumeNotSelected.png"));
			
			this.getButtonById("restart").setSelectedImg(new Image("res/KritaSaves/PausedMenu/restartSelected.png"));
			this.getButtonById("restart").setNotSectedImg(new Image("res/KritaSaves/PausedMenu/restartNotSelected.png"));
			
			this.getButtonById("quit").setSelectedImg(new Image("res/KritaSaves/PausedMenu/quitSelected.png"));
			this.getButtonById("quit").setNotSectedImg(new Image("res/KritaSaves/PausedMenu/quitNotSelected.png"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void interact() {
		// TODO Auto-generated method stub
		if (this.getButtonById("resume").isActived() && this.getButtonById("resume").isClicked()){
			this.gm.isPaused = false;
			this.gm.countStart = System.nanoTime();
		}else if(this.getButtonById("restart").isActived() && this.getButtonById("restart").isClicked()) {
			this.gm.reStart();
		}else if(this.getButtonById("quit").isActived() && this.getButtonById("quit").isClicked()) {
			this.gm.reStart();
			this.sbg.enterState(1);
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		//super.render(gc, g);
		this.bg.draw(this.pos_X, this.pos_Y);
		for (int i=0; i<this.btn.size(); i++) {
			this.btn.get(i).render(gc, g);
		}
	}

}
