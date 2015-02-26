package uvinfo.bomberman;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class SuperBomb extends Bomb {
	
	/******** attributs *******/
	private int puissance = 4;
	
	/******* constructeur ********/
	public SuperBomb(){
			
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
			this.animExplode.draw(this.getPosX(), this.getPosY()-100);
			this.animExplode.draw(this.getPosX(), this.getPosY()-200);
			this.animExplode.draw(this.getPosX(), this.getPosY()+100);
			this.animExplode.draw(this.getPosX(), this.getPosY()+200);
			this.animExplode.draw(this.getPosX()-100, this.getPosY());
			this.animExplode.draw(this.getPosX()-200, this.getPosY());
			this.animExplode.draw(this.getPosX()+100, this.getPosY());
			this.animExplode.draw(this.getPosX()+200, this.getPosY());
		}
	}
	
	
	
	

}
