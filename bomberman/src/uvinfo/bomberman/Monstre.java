package uvinfo.bomberman;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Monstre extends Personnage {

	//int delta = 2;
	
	public Monstre() throws SlickException {

		CreateAnimation("sprites/monstre.png",96,48);
		
		posX(100);
		posY(100);
		SetDirection(2);
	}	
	
	
	/*@Override
	public void moveUp() {
		this.posY(posY()+delta);
	}
	
	
	public void moveDown() {
		this.posY(posY()-delta);
	}
	
	public void moveRight() {
		this.posX(posX()+delta);
	}

	public void moveLeft() {
		this.posX(posX()-delta);
	}
	 */

	public void Move(Avatar victime)
	{
		int LargeurHauteurCollision = 50;
		
		if(Math.abs(this.posX()-victime.posX()) > LargeurHauteurCollision || Math.abs(this.posY()-victime.posY()) > LargeurHauteurCollision)
		{			
			Random rand = new Random();
			int random = rand.nextInt((4 - 0) + 1) + 0;// de 0 à 3

			SetDirection(random);
			
			Avance(GetDirection());
		}		
	}		
	
	public void Avance(int direction)
	{
		this.SetMoving(true);
		
		switch (GetDirection()) {

	    case 0: moveUp(); break;
	    case 1: moveLeft(); break;
	    case 2: moveDown(); break;
	    case 3: moveRight(); break;
	    }		
		
		this.SetMoving(false);
	}
	
	
	public void render()
	{
		GetAnimation(GetDirection()+(isMoving() ? 4 : 0)).draw(this.posX()-40, this.posY()-50);	
	}	
}
