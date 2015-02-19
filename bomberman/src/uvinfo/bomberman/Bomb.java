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
	private Animation[] animations = new Animation[8];
	
	/******* constructeur *********/
	
	public Bomb(int posX, int posY) throws SlickException{
		this.setPosX(posX);
		this.setPosY(posY);
		Animation anim = new Animation();
		// image de la bombe
		Image image = new Image("sprites/bomb.png");
		anim.addFrame(image, 100);
		this.animations[0] = anim;
		// explosion
		SpriteSheet spriteSheet = new SpriteSheet("sprites/explosion.png", 100, 100);
		this.animations[1] = loadAnimation(spriteSheet, 0, 9, 1);
		this.animations[2] = loadAnimation(spriteSheet, 0, 9, 2);
		this.animations[3] = loadAnimation(spriteSheet, 0, 9, 3);
		this.animations[4] = loadAnimation(spriteSheet, 0, 9, 4);
		this.animations[5] = loadAnimation(spriteSheet, 0, 9, 5);
	}
	
	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
	    Animation animation = new Animation();
	    for (int x = startX; x < endX; x++) {
	        animation.addFrame(spriteSheet.getSprite(x, y), 100);
	    }
	    return animation;
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
	
	public boolean isPosed(){
		return this.isPosed;
	}
	
	public void setIsPosed(boolean pose){
		this.isPosed = pose;
	}
	
	public Animation getAnimation(int anim){
		return this.animations[anim];
	}
	
	/******** methodes  *******/
	public void exploser() {
		this.setIsPosed(false);
	}

	
	
	
}
