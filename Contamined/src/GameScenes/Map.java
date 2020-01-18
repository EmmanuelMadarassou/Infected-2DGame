package GameScenes;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {

	protected String name;
	private TiledMap map;
	private int[][][] spawns;
	
	public Map(String n) {
		this.name = n;
		if (this.name == "Old Factory") {
			try {
				this.setMap(new TiledMap("res/Map2.tmx"));
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setSpawns(new int[9][2][2]);
			
			this.getSpawns()[0][0][0] = 12; 
			this.getSpawns()[0][0][1] = 10; 
			this.getSpawns()[0][1][0] = 17; 
			this.getSpawns()[0][1][1] = 10;
			
			this.getSpawns()[1][0][0] = 25; 
			this.getSpawns()[1][0][1] = 8; 
			this.getSpawns()[1][1][0] = 31; 
			this.getSpawns()[1][1][1] = 11;
			
			this.getSpawns()[2][0][0] = 43; 
			this.getSpawns()[2][0][1] = 11; 
			this.getSpawns()[2][1][0] = 48; 
			this.getSpawns()[2][1][1] = 10;
			
			this.getSpawns()[3][0][0] = 11; 
			this.getSpawns()[3][0][1] = 19; 
			this.getSpawns()[3][1][0] = 16; 
			this.getSpawns()[3][1][1] = 18;
			
			this.getSpawns()[4][0][0] = 42; 
			this.getSpawns()[4][0][1] = 17; 
			this.getSpawns()[4][1][0] = 48; 
			this.getSpawns()[4][1][1] = 17;
			
			this.getSpawns()[5][0][0] = 23; 
			this.getSpawns()[5][0][1] = 24; 
			this.getSpawns()[5][1][0] = 27; 
			this.getSpawns()[5][1][1] = 24;
			
			this.getSpawns()[6][0][0] = 46; 
			this.getSpawns()[6][0][1] = 30; 
			this.getSpawns()[6][1][0] = 51; 
			this.getSpawns()[6][1][1] = 29;
			
			this.getSpawns()[7][0][0] = 6; 
			this.getSpawns()[7][0][1] = 30; 
			this.getSpawns()[7][1][0] = 14; 
			this.getSpawns()[7][1][1] = 30;
			
			this.getSpawns()[8][0][0] = 34; 
			this.getSpawns()[8][0][1] = 31; 
			this.getSpawns()[8][1][0] = 39; 
			this.getSpawns()[8][1][1] = 31;
		}
	}

	public int[][][] getSpawns() {
		return spawns;
	}

	public void setSpawns(int[][][] spawns) {
		this.spawns = spawns;
	}

	public TiledMap getMap() {
		return map;
	}

	public void setMap(TiledMap map) {
		this.map = map;
	}
}
