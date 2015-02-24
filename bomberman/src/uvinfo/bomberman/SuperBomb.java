package uvinfo.bomberman;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class SuperBomb extends Bomb {
	
	/******** attributs *******/
	private int puissance = 4;
	//private Animation animation = new Animation();
	
	/******* constructeur 
	 * @throws SlickException ********/
	public SuperBomb() throws SlickException{
		// animation de la bombel
		Image bomb = new Image("sprites/bomb_verte.png");
		Image bombRouge = new Image("sprites/bomb_rouge.png");
		super.emptyAnim();
		this.getAnimation().addFrame(bomb, 1000);
		this.getAnimation().addFrame(bombRouge, 500);
		this.getAnimation().addFrame(bomb, 500);
		this.getAnimation().addFrame(bombRouge, 100);
		this.getAnimation().addFrame(bomb, 100);
		this.getAnimation().addFrame(bombRouge, 100);
		SpriteSheet spriteSheet = new SpriteSheet("sprites/explosion.png", 100, 100);
		this.getAnimation().addFrame(spriteSheet.getSprite(8, 0), 100);
		this.getAnimation().addFrame(spriteSheet.getSprite(8, 1), 100);
		this.getAnimation().addFrame(spriteSheet.getSprite(8, 2), 100);
		this.getAnimation().addFrame(spriteSheet.getSprite(8, 3), 100);
		this.getAnimation().addFrame(spriteSheet.getSprite(8, 4), 100);
		this.getAnimation().addFrame(spriteSheet.getSprite(8, 5), 100);
		this.getAnimation().addFrame(spriteSheet.getSprite(8, 6), 100);
			
	}
	
	// gère les évènements de la bombe
		public void cycleBomb(){
			this.getAnimation().draw(this.getPosX()-32, this.getPosY()-30);
			if(this.getAnimation().getFrame() >= 6){
				this.getAnimation().draw(this.getPosX()-32, this.getPosY()-100);
				this.getAnimation().draw(this.getPosX()-32, this.getPosY()+40);
				this.getAnimation().draw(this.getPosX()-32, this.getPosY()-170);
				this.getAnimation().draw(this.getPosX()-32, this.getPosY()+110);
				this.getAnimation().draw(this.getPosX()-102, this.getPosY()-30);
				this.getAnimation().draw(this.getPosX()+38, this.getPosY()-30);
				this.getAnimation().draw(this.getPosX()-172, this.getPosY()-30);
				this.getAnimation().draw(this.getPosX()+108, this.getPosY()-30);
			}
			this.explode();
			this.finish();	
		}
	
	

}
