package uvinfo.bomberman;

public class AvatarLight {
	
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

}
