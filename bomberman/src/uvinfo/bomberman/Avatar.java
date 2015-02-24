package uvinfo.bomberman;

import org.newdawn.slick.SlickException;

public class Avatar extends Personnage {

	
	
	private Bomb bomb = new Bomb(); // bombe "normale", utilisation infinie
	
	private SuperBomb superBomb = new SuperBomb();
	private int nbSuperBomb = 3; 
	
	
	
	/************* constructeur *****************/
	
	public Avatar() throws SlickException
	{   
		
	}
	
	/***************  methodes  
	 * @throws SlickException ***************/
	
	public void initAnimation() throws SlickException{
		CreateAnimation("sprites/drag.png", 96, 96);
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
	
	
	
	public void putBomb(int x, int y){ 	
		if(!this.checkBombPosed()){
			this.bomb.setCoordonnees(x, y);
			this.bomb.setPosed(true);
		}
	}
	
	
	
	public void putSuperBomb(int x, int y){
		if(!this.checkBombPosed()){
			this.superBomb.setCoordonnees(x, y);
			this.superBomb.setPosed(true);
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

}
