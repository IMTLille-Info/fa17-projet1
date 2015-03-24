package uvinfo.bomberman;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

public class BombLight implements BombermanTransmissible{
	public String pseudo;
	public int posX;
	public int posY;
	public boolean isPosed;
	public boolean exploding;
	public long timeDelta;
	public int[][] champExplosion; 
	
	public BombLight(){
	
	}
	
	public BombLight(Bomb b, String ps){
		this.pseudo = ps;
		this.posX = b.getPosX();
		this.posY = b.getPosY();
		this.isPosed = b.isPosed();
		this.exploding = b.isExploding();
		this.timeDelta = b.getTimeDelta();
		this.champExplosion = b.getChampExplosion();
	}
	
	public void copy(Bomb b, String ps){
		this.pseudo = ps;
		this.posX = b.getPosX();
		this.posY = b.getPosY();
		this.isPosed = b.isPosed();
		this.exploding = b.isExploding();
		this.timeDelta = b.getTimeDelta();
		this.champExplosion = b.getChampExplosion();
	}

	@Override
	public void handleReception(NetworkGame ng) throws SlickException {
		ng.addBomb(new Bomb(this), this.pseudo);
	}
	
	

}
