package uvinfo.bomberman;

import org.newdawn.slick.SlickException;

public class SuperBomb extends Bomb {
	
	/******** attributs *******/
	private int puissance = 4;
	
	/******* constructeur 
	 * @throws SlickException ********/
	public SuperBomb(int posX, int posY) throws SlickException{
		super(posX, posY);
	}

}
