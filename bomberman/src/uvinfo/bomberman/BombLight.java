package uvinfo.bomberman;

import org.newdawn.slick.Animation;

public class BombLight {
	public int posX;
	public int posY;
	public boolean isPosed;
	public boolean exploding;
	public long timeDelta;
	public int[][] champExplosion; 
	
	public BombLight(Bomb b){
		this.posX = b.getPosX();
		this.posY = b.getPosY();
		this.isPosed = b.isPosed();
		this.exploding = b.isExploding();
		this.timeDelta = b.getTimeDelta();
		this.champExplosion = b.getChampExplosion();
	}

}
