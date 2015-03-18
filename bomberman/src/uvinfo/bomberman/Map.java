package uvinfo.bomberman;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {

	/******* Constructeur ******/
	public Map() {
	}

	/******* attributs ******/
	private int Map = 2;
	private TiledMap tiledMap;
	private String ChoixMap;

	/*******
	 * initialisation de la Map
	 * 
	 * @return
	 * @return
	 ******/
	public void init() throws SlickException {
		ChangeMap();
		this.tiledMap = new TiledMap(ChoixMap);
	}

	public void ChangeMap() {
		switch (Map) {
		case 1:
			ChoixMap = "res/terrain2.tmx";
			break;
		case 2:
			ChoixMap = "res/terrain3.tmx";
			break;
		case 3:
			ChoixMap = "res/terrain.tmx";
			break;
		}
	}

	/******* Affichage fond de Map ******/
	public void renderBackground() {
		this.tiledMap.render(0, 0, 0);
		
		/*
		 * this.tiledMap.render(0, 0, 1); this.tiledMap.render(0, 0, 2);
		 */
	}

	/******* Affichage avant de map ******/
	public void renderForeground() {
		/*
		 * this.tiledMap.render(0, 0, 3); this.tiledMap.render(0, 0, 4);
		 */
		this.tiledMap.render(0, 0, 1);
	}

	/******* Gestion des collisions ******/
	public boolean isCollision(float x, float y, Personnage perso) {
		int tileW = this.tiledMap.getTileWidth();
		int tileH = this.tiledMap.getTileHeight();
		int logicLayer = this.tiledMap.getLayerIndex("Logic");
		Image tile = this.tiledMap.getTileImage((int) x / tileW, (int) y
				/ tileH, logicLayer);
		boolean collision = tile != null;

		if (collision) {
			Color color = tile.getColor((int) x % tileW, (int) y % tileH);
			collision = color.getAlpha() > 0;

			if (perso instanceof Monstre) {
				perso.SetMoving(true);
				((Monstre) perso).OpposeDirection();
			}

		} else {
			if (perso instanceof Avatar) {
				if (perso.isMoving()) {
					perso.posX(perso.getFuturX());
					perso.posY(perso.getFuturY());
				}
			}
		}
		return collision;
	}

	/*******
	 * Utilisation des Trigger (téléporteur map)
	 * 
	 * @param i
	 ******/
	public int getObjectCount(int i) {
		return this.tiledMap.getObjectCount(0);
	}

	public String getObjectType(int objectID, int objectID2) {
		return this.tiledMap.getObjectType(0, objectID);
	}

	public float getObjectX(int objectID, int objectID2) {
		return this.tiledMap.getObjectX(0, objectID);
	}

	public float getObjectY(int objectID, int objectID2) {
		return this.tiledMap.getObjectY(0, objectID);
	}

	public float getObjectWidth(int objectID, int objectID2) {
		return this.tiledMap.getObjectWidth(0, objectID);
	}

	public float getObjectHeight(int objectID, int objectID2) {
		return this.tiledMap.getObjectHeight(0, objectID);
	}

	public String getObjectProperty(int objectID, String propertyName,
			String def) {
		return this.tiledMap.getObjectProperty(0, objectID, propertyName, def);
	}

	/******* Changement de Map ******/
	public void changeMap(String file) throws SlickException {
		this.tiledMap = new TiledMap(file);
	}

	/********* GETTER SETTER ******/
	public int getMap() {
		return Map;
	}

	public void setMap(int Map) {
		this.Map = Map;

	}
}
