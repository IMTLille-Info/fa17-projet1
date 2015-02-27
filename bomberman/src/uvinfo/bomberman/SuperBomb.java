package uvinfo.bomberman;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class SuperBomb extends Bomb {
	
	/******* constructeur ********/
	public SuperBomb(){
		this.setPuissance(4);
		this.setNbExplode(9);
	}
	
	/*********** méthodes ***********/
	
	@Override
	public void loadAnimations() throws SlickException{
		this.emptyAnim();
		this.loadAnimationPose();
		this.loadAnimationExplode();
	}
	
	@Override
	public void loadAnimationPose() throws SlickException {
		Image bomb = new Image("sprites/bomb_verte.png");
		Image bombRouge = new Image("sprites/bomb_rouge.png");
		super.emptyAnim();
		this.animPose.addFrame(bomb, 1000);
		this.animPose.addFrame(bombRouge, 500);
		this.animPose.addFrame(bomb, 500);
		this.animPose.addFrame(bombRouge, 100);
		this.animPose.addFrame(bomb, 100);
		this.animPose.addFrame(bombRouge, 100);
	}
	
	@Override
	public void loadAnimationExplode() throws SlickException{
				
		SpriteSheet spriteSheet = new SpriteSheet("sprites/explosion.png", 100, 100);
		this.animExplode.addFrame(spriteSheet.getSprite(8, 0), 100);
		this.animExplode.addFrame(spriteSheet.getSprite(8, 1), 100);
		this.animExplode.addFrame(spriteSheet.getSprite(8, 2), 100);
		this.animExplode.addFrame(spriteSheet.getSprite(8, 3), 100);
		this.animExplode.addFrame(spriteSheet.getSprite(8, 4), 100);
		this.animExplode.addFrame(spriteSheet.getSprite(8, 5), 100);
		this.animExplode.addFrame(spriteSheet.getSprite(8, 6), 100);
	}
	
	@Override
	public void animExplode() throws SlickException{
		if(this.isExploding()){
			
			SonBombe.ExplosionSuperBombe();
			this.animExplode.draw(this.getPosX(), this.getPosY());
			this.animExplode.draw(this.getPosX(), this.getPosY()-70);
			this.animExplode.draw(this.getPosX(), this.getPosY()-140);
			this.animExplode.draw(this.getPosX(), this.getPosY()+70);
			this.animExplode.draw(this.getPosX(), this.getPosY()+140);
			this.animExplode.draw(this.getPosX()-70, this.getPosY());
			this.animExplode.draw(this.getPosX()-140, this.getPosY());
			this.animExplode.draw(this.getPosX()+70, this.getPosY());
			this.animExplode.draw(this.getPosX()+140, this.getPosY());
		}
	}
	
	@Override
	// rempli champExplosion avec les coordonées adjacentes
	public void setChampExplosion(){
		this.champExplosion = new int[this.getNbExplode()][2];
		// centre
		this.champExplosion[0][0] = this.getPosX();
		this.champExplosion[0][1] = this.getPosY();
		// sud
		this.champExplosion[1][0] = this.getPosX();
		this.champExplosion[1][1] = this.getPosY() + 70;
		this.champExplosion[2][0] = this.getPosX();
		this.champExplosion[2][1] = this.getPosY() + 140;
		// nord
		this.champExplosion[3][0] = this.getPosX();
		this.champExplosion[3][1] = this.getPosY() - 70;
		this.champExplosion[4][0] = this.getPosX();
		this.champExplosion[4][1] = this.getPosY() - 140;
		// ouest
		this.champExplosion[5][0] = this.getPosX() + 70;
		this.champExplosion[5][1] = this.getPosY();
		this.champExplosion[6][0] = this.getPosX() + 140;
		this.champExplosion[6][1] = this.getPosY();
		//est
		this.champExplosion[7][0] = this.getPosX() - 70;
		this.champExplosion[7][1] = this.getPosY();
		this.champExplosion[8][0] = this.getPosX() - 140;
		this.champExplosion[8][1] = this.getPosY();
		
	}
	
	
	
	

}
