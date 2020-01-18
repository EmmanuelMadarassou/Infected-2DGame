package GameScenes;

import org.newdawn.slick.state.BasicGameState;

//GameMode class define the game state with all his properties like the hud, is the game paused, the gameScene, etc.
public abstract class GameMode extends BasicGameState{
	
	protected Hud playerInterface;
	protected GameScene playGround;
	protected PausedMenu menu;
	
	protected int count;
	protected long countStart;
	protected long countCurrent;
	
	protected boolean isPaused;
	protected boolean isStarted;
	
	public abstract void reStart();
	
}
