package uvinfo.bomberman;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Bomb {
	
	/******* attributs ******/
	private int puissance = 2;
	private int posX;
	private int posY;
	private boolean isPosed = false;
	private Animation animation = new Animation();
	
	/******* constructeurs *********/
	public Bomb() throws SlickException{
		// animation de la bombe
		Image image = new Image("sprites/bomb.png");
		this.animation.addFrame(image, 3000);
		SpriteSheet spriteSheet = new SpriteSheet("sprites/explosion.png", 100, 100);
		this.animation.addFrame(spriteSheet.getSprite(8, 0), 100);
		this.animation.addFrame(spriteSheet.getSprite(8, 1), 100);
		this.animation.addFrame(spriteSheet.getSprite(8, 2), 100);
		this.animation.addFrame(spriteSheet.getSprite(8, 3), 100);
		this.animation.addFrame(spriteSheet.getSprite(8, 4), 100);
		this.animation.addFrame(spriteSheet.getSprite(8, 5), 100);
		this.animation.addFrame(spriteSheet.getSprite(8, 6), 100);
		this.animation.stopAt(8);
	}

	/******** getter, setter ********/
	public int getPuissance() {
		return puissance;
	}

	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public Animation getAnimation(){
		return this.animation;
	}

	public boolean isPosed() {
		return isPosed;
	}

	public void setPosed(boolean isPosed) {
		this.isPosed = isPosed;
	}
	
	/******** methodes *******/
	
	public void exploser(int x, int y){
		this.animation.draw(x, y);
	}

	public void setCoordonnees(int x, int y){
		this.setPosX(x);
		this.setPosY(y);
	}
	
	// paramètre le temps avant l'explosion de la bombe (temps en millisecondes)
	public void setTimeBeforeExplosion(int temps){
		this.animation.setDuration(0, temps);
	}
	
}
