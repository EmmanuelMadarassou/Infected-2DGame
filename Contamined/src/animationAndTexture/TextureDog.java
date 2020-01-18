package animationAndTexture;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TextureDog extends TexturePlayer{

	public TextureDog() {
		
		Image[] temp = new Image[10];
		
		temp = new Image[10];
		for (int i=0; i<temp.length;i++) {
			try {
				temp[i] = new Image("res/dog/right/Idle ("+(i+1)+").png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.standRight = new AnimationPlayer(temp, 100, 0, (float)0.1);
		
		temp = new Image[10];
		for (int i=0; i<temp.length;i++) {
			try {
				temp[i] = new Image("res/dog/left/Idle ("+(i+1)+").png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.standLeft = new AnimationPlayer(temp, 100, 0, (float)0.1);
		
		temp = new Image[10];
		for (int i=0; i<temp.length;i++) {
			try {
				temp[i] = new Image("res/dog/right/Walk ("+(i+1)+").png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.walkRight = new AnimationPlayer(temp, 100, 0, (float)0.1);
		
		temp = new Image[10];
		for (int i=0; i<temp.length;i++) {
			try {
				temp[i] = new Image("res/dog/left/Walk ("+(i+1)+").png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.walkLeft = new AnimationPlayer(temp, 100, 0, (float)0.1);
		
		temp = new Image[8];
		for (int i=0; i<temp.length;i++) {
			try {
				temp[i] = new Image("res/dog/right/Run ("+(i+1)+").png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.runRight = new AnimationPlayer(temp, 100, 0, (float)0.1);
		
		temp = new Image[8];
		for (int i=0; i<temp.length;i++) {
			try {
				temp[i] = new Image("res/dog/left/Run ("+(i+1)+").png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.runLeft = new AnimationPlayer(temp, 100, 0, (float)0.1);
		
		temp = new Image[8];
		for (int i=0; i<temp.length;i++) {
			try {
				temp[i] = new Image("res/dog/left/Jump ("+(i+1)+").png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.jumpLeft = new AnimationPlayer(temp, 100, 0, (float)0.1);
		
		temp = new Image[8];
		for (int i=0; i<temp.length;i++) {
			try {
				temp[i] = new Image("res/dog/right/Jump ("+(i+1)+").png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.jumpRight = new AnimationPlayer(temp, 100, 0, (float)0.1);
		
		temp = new Image[8];
		for (int i=0; i<temp.length;i++) {
			try {
				temp[i] = new Image("res/dog/left/Fall ("+(i+1)+").png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.fallLeft = new AnimationPlayer(temp, 100, 0, (float)0.1);
		
		temp = new Image[8];
		for (int i=0; i<temp.length;i++) {
			try {
				temp[i] = new Image("res/dog/right/Fall ("+(i+1)+").png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.fallRight = new AnimationPlayer(temp, 100, 0, (float)0.1);
		
		
		temp = new Image[10];
		for (int i=0; i<temp.length;i++) {
			try {
				temp[i] = new Image("res/dog/right/Hurt ("+(i+1)+").png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.hurtRight = new AnimationPlayer(temp, 100, 0, (float)0.1);
		
		temp = new Image[10];
		for (int i=0; i<temp.length;i++) {
			try {
				temp[i] = new Image("res/dog/left/Hurt ("+(i+1)+").png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.hurtLeft = new AnimationPlayer(temp, 100, 0, (float)0.1);
		
		temp = null;
	}
}
