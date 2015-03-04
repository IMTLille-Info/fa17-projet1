package uvinfo.bomberman;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Avatar extends Personnage {

	
	
	private Bomb bomb = new Bomb(); // bombe "normale", utilisation infinie
	
	private SuperBomb superBomb = new SuperBomb();
	private int nbSuperBomb = 10;
	
	
	
	/************* constructeur *****************/
	
	public Avatar() throws SlickException
	{   
		
	}
	
	public void render() throws SlickException
	{
		GetAnimation(GetDirection()+(isMoving() ? 4 : 0)).draw(this.posX()-40, this.posY()-65);	
		this.bomb.render();
		this.superBomb.render();
	}	
	
	/***************  methodes  
	 * @throws SlickException ***************/
	

	public void initAnimation() throws SlickException{
		CreateAnimation("sprites/drag.png", 96, 96,4);
		this.getBomb().loadAnimationPose(); 
		this.getBomb().loadAnimationExplode();
		this.getSuperBomb().loadAnimationPose();
		this.getSuperBomb().loadAnimationExplode();
	}
	
	
	
	public Bomb getBomb() {
		return bomb;
	}
	
	public void setBomb(Bomb bomb) {
		this.bomb = bomb;
	}	
	
	public SuperBomb getSuperBomb() {
		return superBomb;
	}

	public void setSuperBomb(SuperBomb superBomb) {
		this.superBomb = superBomb;
	}
	
	public int getNbSuperBomb() {
		return nbSuperBomb;
	}

	public void setNbSuperBomb(int nbSuperBomb) {
		this.nbSuperBomb = nbSuperBomb;
	}
	
	public void putBomb(){ 	
		if(!this.checkBombPosed()){
			this.bomb.pose(this.posX() - 32, this.posY() - 60);
		}
	}
	
	public void putSuperBomb(){
		if(!this.checkBombPosed() && this.nbSuperBomb >0){
			this.superBomb.pose(this.posX() - 32, this.posY() - 60);
			this.nbSuperBomb -= 1;
		}
	}
	
	public boolean checkBombPosed(){
		if(!this.bomb.isPosed() && !this.bomb.isExploding() && !this.superBomb.isPosed() && !this.superBomb.isExploding()){
			return false;
		}else{
			return true;
		}
	}
	
	public void update(int delta, GameContainer container){
		this.getBomb().update(this, delta);
		this.getSuperBomb().update(this, delta);
		// perdu si perso est mort
		if(!this.IsAlive())
		{
			javax.swing.JOptionPane.showMessageDialog(null,"Game Over"); 
			container.exit();
		}
	}
	

}
