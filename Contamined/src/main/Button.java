package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

//Button class is used to accelerate management of buttons in all the game
public class Button extends Body {
	
	protected GameContainer gc;
	protected Input kb;
	protected boolean isActived;
	protected String id;
	protected float lineWidth;
	protected Image selected;
	protected Image notSelected;
	
	public Button(float x, float y, float width, float height, GameContainer gc, String i, float lw) {
		this.pos_X = x;
		this.pos_Y = y;
		this.width = width;
		this.height = height;
		this.gc = gc;
		this.id = i;
		this.lineWidth = lw;
		this.kb = gc.getInput();
		this.isActived = true;
		this.color = Color.red;

	}
	
	public void setSelectedImg(Image i) {
		this.selected = i;
	}
	
	public void setNotSectedImg(Image i) {
		this.notSelected = i;
	}
	
	public boolean isActived() {
		return isActived;
	}

	public void setActived(boolean isActived) {
		this.isActived = isActived;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(float lineWidth) {
		this.lineWidth = lineWidth;
	}

	public Image getSelected() {
		return selected;
	}

	public void setSelected(Image selected) {
		this.selected = selected;
	}

	public Image getNotSelected() {
		return notSelected;
	}

	public void setNotSelected(Image notSelected) {
		this.notSelected = notSelected;
	}

	public boolean isClicked() {
		if (this.kb.getMouseX()>this.pos_X && this.kb.getMouseX()<this.pos_X + width) {
			if (this.kb.getMouseY()>this.pos_Y && this.kb.getMouseY()<this.pos_Y+this.height) {
				if (kb.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public boolean hover() {
		if (this.kb.getMouseX()>this.pos_X && this.kb.getMouseX()<this.pos_X + width) {
			if (this.kb.getMouseY()>this.pos_Y && this.kb.getMouseY()<this.pos_Y+this.height) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		//super.render(gc, g);
		
		if (this.isActived) {
			if (this.hover()) {
				this.selected.draw(this.pos_X, this.pos_Y);
			}else {
				this.notSelected.draw(this.pos_X, this.pos_Y);
			}
			
		}
		
		
	}
}
