package uvinfo.bomberman;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Monstre extends Personnage {

	long tempsDebut = System.currentTimeMillis();
	
	public Monstre() throws SlickException {

		CreateAnimation("sprites/monstre.png",96,48);
		
		posX(100);
		posY(100);
		SetDirection(2);
	}	

	public void Move(Avatar victime)
	{				
		int LargeurHauteurCollision = 50;
		
		if(Math.abs(this.posX()-victime.posX()) > LargeurHauteurCollision || Math.abs(this.posY()-victime.posY()) > LargeurHauteurCollision)
		{			
			
			long tempsFin = System.currentTimeMillis();//temps actuel
			
			float duree = (tempsFin - tempsDebut) / 1000F;//calcul de la durée en seconde
			
			if(duree > RandTime())//si la durée du temps aleatoire est depassée
			{
				
			Random rand = new Random();
			int random = rand.nextInt((4 - 0) + 1) + 0;// de 0 à 3
			
			SetDirection(random);//on change de direction aléatoirement
			
			}
			
			Avance(GetDirection());
		}		
	}		
	
	private float RandTime()
	{
		Random randT = new Random();
		float rand = (randT.nextInt((31 - 0) + 1) + 0);//rand de 0 a 31 exclus
		rand /= 10;//divion par 10 pour obtenir un float de maximum 3
		return rand;// de 0 à 3 avec un pas de 0.1
	}
	
	public void Avance(int direction)
	{
		tempsDebut = System.currentTimeMillis();//commence à compter le temps au moment du deplacement
		
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
