package uvinfo.bomberman;

import org.newdawn.slick.SlickException;

public class Monstre extends Avatar {

	
	
	
	
	public Monstre() throws SlickException {

		CreateAnimation("sprites/monstre.png",96,48);
		posX(100);
		posY(100);
		SetDirection(2);		
	}

}
