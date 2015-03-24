package uvinfo.bomberman;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Avatar extends Personnage {

	private int nbSuperBomb = 10;
	private boolean hasPutBomb = false;
	private int timeForNewBomb = 6000; // temps que doit attendre l'avatar s'il veut reposer une bombe
	private int timeWaited;
	
	
	/************* constructeur *****************/
	
	public Avatar(){   	
	}
	
	public Avatar(AvatarLight avl){
		this.posX(avl.posX);
		this.posY(avl.posY);
		this.SetDirection(avl.direction);
		this.SetMoving(avl.moving);
		this.setLife(avl.PointDeVie);
		this.setHasPutBomb(avl.hasPutBomb);
	}
	
	public void copy(Avatar av){
		this.posX(av.posX());
		this.posY(av.posY());
		this.SetDirection(av.GetDirection());
		this.SetMoving(av.isMoving());
		this.setLife(av.getLife());
		this.setHasPutBomb(av.hasPutBomb);
	}

	
	public void render() throws SlickException
	{
		if(this.getHasBeenHurted()){
			GetAnimation(GetDirection()+(isMoving() ? 4 : 0)).draw(this.posX()-40, this.posY()-65, new Color(0,0,255,50));
		}else{
			GetAnimation(GetDirection()+(isMoving() ? 4 : 0)).draw(this.posX()-40, this.posY()-65);
		}
	}	
	
	/***************  methodes  
	 * @throws SlickException ***************/
	

	public void initAnimation() throws SlickException{
		CreateAnimation("sprites/drag.png", 96, 96,4);
	}
	
	public int getNbSuperBomb() {
		return nbSuperBomb;
	}

	public void setNbSuperBomb(int nbSuperBomb) {
		this.nbSuperBomb = nbSuperBomb;
	}
	
	public boolean hasPutBomb() {
		return hasPutBomb;
	}

	public void setHasPutBomb(boolean hasPutBomb) {
		this.hasPutBomb = hasPutBomb;
	}

	public int getTimeWaited() {
		return timeWaited;
	}

	public void setTimeWaited(int timeWaited) {
		this.timeWaited = timeWaited;
	}

	public void putBomb(Bomb bomb){ 	
		if(!this.hasPutBomb){
			this.hasPutBomb = true;
			this.timeWaited = 0;
			bomb.pose(this.posX()-32, this.posY()-60);
		}
	}
	
	public void putSuperBomb(Bomb bomb){
		if(!this.hasPutBomb && this.nbSuperBomb >0){
			this.hasPutBomb = true;
			this.timeWaited = 0;
			bomb.pose(this.posX()-32, this.posY()-60);
			this.nbSuperBomb -= 1;
		}
	}
	
	public void update(int delta, GameContainer container){
		if(this.hasPutBomb){
			this.timeWaited += delta;
			if(this.timeWaited >= this.timeForNewBomb){
				this.hasPutBomb = false;
			}
		}
		// perdu si perso est mort
		if(!this.IsAlive())
		{
			javax.swing.JOptionPane.showMessageDialog(null,"Game Over"); 
			container.exit();
		}
	}
	
	

}
