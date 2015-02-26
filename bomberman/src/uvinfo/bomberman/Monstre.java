package uvinfo.bomberman;

import java.util.Random;

import org.newdawn.slick.SlickException;

public class Monstre extends Personnage {

	int delta = 2;
	
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
	
	public void Start(Avatar victime)
	{
		this.SetMoving(true);
		
		while(this.posX() != victime.posX() && this.posY() != victime.posY())
		{
			Random rand = new Random();
			int random = rand.nextInt((4 - 0) + 1) + 0;// de 0 à 3
			
			switch (random)
			{
				case 0: this.moveDown();break;
				case 1: this.moveUp();break;
				case 2: this.moveLeft();break;
				case 3: this.moveRight();break;
			}
		}
		
		this.SetMoving(false);
	}		
}
