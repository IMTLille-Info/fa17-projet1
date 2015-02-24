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
	private boolean exploding = false;

	private Animation animation = new Animation();
	
	/******* constructeurs *********/
	public Bomb() throws SlickException{
		// animation de la bombe
		Image bomb = new Image("sprites/bomb.png");
		Image bombRouge = new Image("sprites/bomb_rouge.png");
		this.animation.addFrame(bomb, 1000);
		this.animation.addFrame(bombRouge, 500);
		this.animation.addFrame(bomb, 500);
		this.animation.addFrame(bombRouge, 100);
		this.animation.addFrame(bomb, 100);
		this.animation.addFrame(bombRouge, 100);
		SpriteSheet spriteSheet = new SpriteSheet("sprites/explosion.png", 100, 100);
		this.animation.addFrame(spriteSheet.getSprite(8, 0), 100);
		this.animation.addFrame(spriteSheet.getSprite(8, 1), 100);
		this.animation.addFrame(spriteSheet.getSprite(8, 2), 100);
		this.animation.addFrame(spriteSheet.getSprite(8, 3), 100);
		this.animation.addFrame(spriteSheet.getSprite(8, 4), 100);
		this.animation.addFrame(spriteSheet.getSprite(8, 5), 100);
		this.animation.addFrame(spriteSheet.getSprite(8, 6), 100);
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
	
	public Animation getAnimation(){
		return this.animation;
	}

	public void setPosed(boolean isPosed) {
		this.isPosed = isPosed;
	}
	
	public boolean isExploding() {
		return exploding;
	}

	public void setExploding(boolean isExploding) {
		this.exploding = isExploding;
	}
	
	/******** methodes *******/
	
	// gère les évènements de la bombe
	public void cycleBomb(){
		this.animation.draw(this.getPosX()-32, this.getPosY()-30);
		if(this.animation.getFrame() >= 6){
			this.animation.draw(this.getPosX()-32, this.getPosY()-100);
			this.animation.draw(this.getPosX()-32, this.getPosY()+40);
			this.animation.draw(this.getPosX()-102, this.getPosY()-30);
			this.animation.draw(this.getPosX()+38, this.getPosY()-30);
		}
		this.explode();
		this.finish();	
	}
	
	// vérifie si l'animation est finie et si oui, la bombe a fini d'exploser et on remet l'animation à 0
	public void finish() {
		if(this.getAnimation().getFrame() == this.getAnimation().getFrameCount() - 1){
			this.setExploding(false);
			this.getAnimation().restart();
		}
	}

	// explosion de la bombe en cours
	public void explode(){
		if(this.getAnimation().getFrame() == 6){
			this.setExploding(true);
			this.setPosed(false);
		}
	}
	
	
	public void setCoordonnees(int x, int y){
		this.setPosX(x);
		this.setPosY(y);
	}
	
	// paramètre le temps avant que la bombe passe une 1ere fois au rouge (temps en millisecondes)
	public void setTimeBeforeExplode(int temps){
		this.animation.setDuration(0, temps);
	}
	
	// attaquer l'adversaire
	public void hurt(){
		if(this.exploding){
			// à remplir une fois qu'un ennemi sera prêt
		}
	}
	
	// vider l'animation
	public void emptyAnim(){
		this.animation = null;
		this.animation = new Animation();
	}
	
}
