package main;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

//Class menu is used to render every menu in game
public abstract class Menu extends Body {

	protected ArrayList<Button> btn;
	protected Button exit;
	protected GameContainer gc;
	protected Input kb;
	protected Image bg;
	protected StateBasedGame sbg;
	
	public Menu(float x, float y, float width, float height){
		super(x,y,width,height);
	}
	
	
	public Menu(float x, float y, float width, float height, GameContainer gc, StateBasedGame sbg) {
		this.pos_X = x;
		this.pos_Y = y;
		this.sbg = sbg;
		this.gc = gc;
		this.btn = new ArrayList<Button>();
		this.width = width;
		this.height = height;
		this.color = Color.red;
		this.kb = gc.getInput();
	}
	
	public void addButton(Button b) {
		this.btn.add(b);
	}
	
	public void removeButtonById(String id) {	
		for (int i=0; i<=this.btn.size(); i++) {
			if (this.btn.get(i).id == id) {
				this.btn.remove(i);
			}
		}
	}
	
	public Button getButtonById(String id) {
		for (int i=0; i<=this.btn.size(); i++) {
			if (this.btn.get(i).id == id) {
				return this.btn.get(i);
			}
		}
		return null;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		//super.render(gc, g);
		for (int i=0; i<this.btn.size(); i++) {
			this.btn.get(i).render(gc, g);
		}
	}
	
	//Abstract interaction and initialisation method because each menu has is own
	public abstract void interact();
	public abstract void initButton();
}
