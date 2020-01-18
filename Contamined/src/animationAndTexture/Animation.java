package animationAndTexture;
import main.Drawable;
import main.Element;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

//this class is used to control every game's animation frame by frame
public class Animation extends Element{
	
	//Animation properties
	//Every animation as a list of image, a display time and a possible iteration
	protected Image[] frame;
	protected int timeFrame;
	protected int frameDisplay;
	protected long lastFrameTime, currentFrameTime;
	protected float coeff;
	protected String iteration;
	
	
	
	public Animation(int tF, int fD, float c, String it) {
		this.frameDisplay = fD;
		this.timeFrame = tF;
		this.coeff = c;
		this.lastFrameTime = System.currentTimeMillis();
		this.currentFrameTime = System.currentTimeMillis();
		this.iteration = it;
	}
	
	public Animation(Image[] f, int tF, int fD, float c, String it) {
		this.frame = new Image[f.length];
		this.frame = f;
		this.timeFrame = tF;
		this.iteration = it;
		for (int i=0; i<f.length; i++) {
			try {
				
				this.frame[i] = new Image(f[i].getResourceReference());
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.frameDisplay = fD;
		this.coeff = c;
		this.lastFrameTime = System.currentTimeMillis();
		this.currentFrameTime = System.currentTimeMillis();
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		this.currentFrameTime = System.currentTimeMillis();
		
		//Checking if the current frame has spend to much time on screen. If so we can display the next frame, if not it can stay under the light
		if ((this.currentFrameTime-this.lastFrameTime) >= this.timeFrame) {
			this.frameDisplay++;
			this.lastFrameTime = System.currentTimeMillis();
		}
		
		//If we have displayed the last frame and the iteration is infinite, then we start over with drawing the first frame.
		//If we didn't displayed the last frame, we can draw the current one of course
		if (this.frameDisplay >= this.frame.length-1) {
			if (this.iteration == "infinite") {
				this.frameDisplay = 0;
				this.frame[frameDisplay].draw(this.pos_X*32,this.pos_Y*32, this.coeff);
			}
		}else {
			this.frame[frameDisplay].draw(this.pos_X*32,this.pos_Y*32, this.coeff);
		}
		
		
		
	}

	public Image[] getFrame() {
		return frame;
	}
	
	public Image getFrame(int i) {
		return frame[i];
	}

	public void setFrame(Image[] frame) {
		this.frame = frame;
	}

	public int getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(int timeFrame) {
		this.timeFrame = timeFrame;
	}

	public int getFrameDisplay() {
		return frameDisplay;
	}

	public void setFrameDisplay(int frameDisplay) {
		this.frameDisplay = frameDisplay;
	}

	public long getLastFrameTime() {
		return lastFrameTime;
	}

	public void setLastFrameTime(long lastFrameTime) {
		this.lastFrameTime = lastFrameTime;
	}

	public long getCurrentFrameTime() {
		return currentFrameTime;
	}

	public void setCurrentFrameTime(long currentFrameTime) {
		this.currentFrameTime = currentFrameTime;
	}

	public float getCoeff() {
		return coeff;
	}

	public void setCoeff(float coeff) {
		this.coeff = coeff;
	}

	public String getIteration() {
		return iteration;
	}

	public void setIteration(String iteration) {
		this.iteration = iteration;
	}
	
	
	
}
