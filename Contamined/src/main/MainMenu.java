package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

//MainMenu class managed all aspects of the landing menu
public class MainMenu extends Menu{
	
	//Properties for rendering player selection
	protected Image playSelected;
	protected Image playNotSelected;
	protected Image exitSelected;
	protected Image exitNotSelected;
	
	//Properties for arrow display next to player selection
	private Image arrow;
	private float arrowX, arrowY;
	
	public MainMenu(float x, float y, float width, float height, GameContainer gc, StateBasedGame sbg) {
		super(x, y, width, height, gc, sbg);
		// TODO Auto-generated constructor stub
		this.sbg = sbg;
		this.initButton();
	}

	public MainMenu(int x, int y, int width, int height, GameContainer gc, StateBasedGame sbg) {
		// TODO Auto-generated constructor stub
		super(x, y, width, height, gc, sbg);
		this.initButton();
		this.arrowX = 50;
		this.arrowY = 50;
		
	}
	
	public void initButton() {
		
		//Initialisation of main menu button
		try {
			this.arrow = new Image("res/arrow.png");
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.addButton(new Button(847, 525, 210, 95, gc,"play", (float) 6));
		this.addButton(new Button((float)750, 719, 440, 95, gc,"exit", (float)6.5));
		
		try {
			this.getButtonById("play").setNotSelected(new Image("res/KritaSaves/playNotSelected.png"));
			this.getButtonById("play").setSelected(new Image("res/KritaSaves/playSelected.png"));
			
			
			this.getButtonById("exit").setSelected(new Image("res/KritaSaves/exitSelected.png"));
			this.getButtonById("exit").setNotSelected(new Image("res/KritaSaves/exitNotSelected.png"));

		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//Method to control interactions between player and the menu
	@Override
	public void interact() {
		// TODO Auto-generated method stub
		if (this.getButtonById("play").isClicked()) {
			this.sbg.enterState(2);
		}else {
			if(this.getButtonById("exit").isClicked()) {
				System.exit (0);
			}
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		//super.render(gc, g);
		
		for (int i=0; i<this.btn.size(); i++) {
			this.btn.get(i).render(gc, g);
		}
		
		
	}

	
}
