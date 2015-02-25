package uvinfo.bomberman;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Bomb { // degager getter et setter qui ne servent à rien
	
	/******* attributs ******/
	
	private int puissance = 2;
	private int posX;
	private int posY;
	private boolean isPosed = false;
	private boolean exploding = false;
	
	private int tabExplode[];

	protected Animation animPose = new Animation();
	protected Animation animExplode = new Animation();
	
	private long timeBegin; 
	
	/******* constructeurs *********/
	public Bomb(){
		
	}

	/******** getter, setter ********/
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}

	public boolean isPosed(){
		return this.isPosed;
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
	
	/******** methodes 
	 * @throws SlickException *******/
	
	// charge les animations
	public void loadAnimations() throws SlickException{
		this.loadAnimationPose();
		this.loadAnimationExplode();
	}
	
	// charge l'animation bombe posée
	public void loadAnimationPose() throws SlickException {
		Image bomb = new Image("sprites/bomb.png");
		Image bombRouge = new Image("sprites/bomb_rouge.png");
		this.animPose.addFrame(bomb, 1000);
		this.animPose.addFrame(bombRouge, 500);
		this.animPose.addFrame(bomb, 500);
		this.animPose.addFrame(bombRouge, 100);
		this.animPose.addFrame(bomb, 100);
		this.animPose.addFrame(bombRouge, 100);
	}
	
	// charge l'animation de l'explosion de la bombe
	public void loadAnimationExplode() throws SlickException {
		SpriteSheet spriteSheet = new SpriteSheet("sprites/explosion.png", 100, 100);
		this.animExplode.addFrame(spriteSheet.getSprite(8, 0), 100);
		this.animExplode.addFrame(spriteSheet.getSprite(8, 1), 100);
		this.animExplode.addFrame(spriteSheet.getSprite(8, 2), 100);
		this.animExplode.addFrame(spriteSheet.getSprite(8, 3), 100);
		this.animExplode.addFrame(spriteSheet.getSprite(8, 4), 100);
		this.animExplode.addFrame(spriteSheet.getSprite(8, 5), 100);
		this.animExplode.addFrame(spriteSheet.getSprite(8, 6), 100);
	}
	
	public void cycleBomb(){
		this.pose();
		this.explode();
	}

	// explosion de la bombe en cours
	public void explode(){
		if(this.exploding){
			this.animExplode.draw(this.posX, this.posY);
			this.animExplode.draw(this.getPosX(), this.getPosY()-70);
			this.animExplode.draw(this.getPosX(), this.getPosY()+70);
			this.animExplode.draw(this.getPosX()-70, this.getPosY());
			this.animExplode.draw(this.getPosX()+70, this.getPosY());
		}
		if(System.currentTimeMillis() - this.timeBegin >= 3000 ){
			this.exploding = false;
			this.animExplode.restart();
		}
	}
	
	// animation de la bombe posée
	public void pose(){
		if(this.isPosed){
			this.animPose.draw(this.posX, this.posY);
		}
		if(System.currentTimeMillis() - this.timeBegin >= 2300){
			this.exploding = true;
			this.isPosed = false;
			this.animPose.restart();
		}
	}
	
	
	// met les coordonnées
	public void setCoordonnees(int x, int y){
		this.posX = x;
		this.posY = y;
	}
	
	// paramètre le temps avant que la bombe passe une 1ere fois au rouge (temps en millisecondes)
	public void setTimeBeforeExplode(int temps){
		this.animPose.setDuration(0, temps);
	}
	
	// attaquer l'adversaire
	public void hurt(Monstre monstre){
		if(this.isExploding()){
			
		}
	}
	
	// vider l'animation
	public void emptyAnim(){
		this.animPose = null;
		this.animExplode = null;
		this.animPose = new Animation();
		this.animExplode = new Animation();
	}

	public long getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(long l) {
		this.timeBegin = l;
	}
	
}
