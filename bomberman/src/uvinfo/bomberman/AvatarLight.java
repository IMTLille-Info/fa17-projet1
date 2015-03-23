package uvinfo.bomberman;

import org.newdawn.slick.SlickException;

public class AvatarLight implements BombermanTransmissible{
	
	public int posX = 350;
	public int posY = 300;
	public int direction = 0;
	public boolean moving;
	public String Pseudo;
	public boolean hasPutBomb;
	public int PointDeVie = 10;	
	
	public void copy(Avatar av, String pseudo){
		this.Pseudo = pseudo;
		
		this.posX = av.posX();
		this.posY = av.posY();

		this.direction = av.GetDirection();
		this.PointDeVie = av.getLife();		
		this.moving = av.isMoving();
		this.hasPutBomb = av.hasPutBomb();
	}

	public void handleReception(NetworkGame ng){
		
		Avatar newJoueur = new Avatar(this);
		
		try {
			newJoueur.initAnimation();
		} catch (SlickException e) {
			e.printStackTrace();
		}

		ng.AddJoueur(newJoueur,this.Pseudo);
			
		return;	
	}
}
