package uvinfo.bomberman;

import java.util.Random;

import org.newdawn.slick.SlickException;

public class Monstre extends Avatar {

	
	
	public Monstre() throws SlickException {

		CreateAnimation("sprites/monstre.png",96,48);
		posX(100);
		posY(100);
		SetDirection(2);		
	}
	
	public void Start(Avatar victime)
	{
		if(this.posX() != victime.posX() && this.posY() != victime.posY() )
		{
			Random rand = new Random();
		    int random = rand.nextInt((4 - 0) + 1) + 0;
		    
		    switch(random)
		    {
		    	case 0:this.moveUp();break;
		    	case 1:this.moveDown();break;
		    	case 2:this.moveLeft();break;
		    	case 3:this.moveRight();break;
		    }
		    
		}		
	}

}
