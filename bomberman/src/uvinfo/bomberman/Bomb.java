package uvinfo.bomberman;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public class Bomb { 
	
	Musique SonBombe = new Musique();
	
	/******* attributs ******/
	private int puissance = 2;
	private int posX;
	private int posY;
	private boolean isPosed = false;
	private boolean exploding = false;
	protected Animation animPose = new Animation();
	protected Animation animExplode = new Animation();
	private long timeDelta;
	private int timePose = 2300;
	private int timeExplode = 700;
	
	private int nbExplode = 5;
	protected int[][] champExplosion; 
	private int distanceExplode = 50;
	
	private boolean hasHurted = false;
	
	/******* constructeurs *********/
	public Bomb(){
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
	
	public int getPosY() {
		return posY;
	}
	
	public boolean isPosed(){
		return this.isPosed;
	}
	
	public boolean isExploding() {
		return exploding;
	}
	
	public int getTimePose(){
		return this.timePose;
	}
	
	public int getTimeExplode(){
		return this.timeExplode;
	}
	
	public int getNbExplode(){
		return this.nbExplode;
	}
	
	public void setNbExplode(int nb){
		this.nbExplode = nb;
	}
	
	/******** methodes *******/
	
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
		this.animPose.addFrame(bombRouge, 100); // faire looping pour setter le temps avant l'explosion
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
	
	public void render() throws SlickException{
		if(!this.isPosed || !this.exploding){
			this.animPose();
			this.animExplode();
		}
	}
	
	// explosion de la bombe en cours
	public void animExplode() throws SlickException{
		if(this.exploding){
			SonBombe.ExplosionBombe();
			this.animExplode.draw(this.posX, this.posY);
			this.animExplode.draw(this.getPosX(), this.getPosY()-70);
			this.animExplode.draw(this.getPosX(), this.getPosY()+70);
			this.animExplode.draw(this.getPosX()-70, this.getPosY());
			this.animExplode.draw(this.getPosX()+70, this.getPosY());
			
		}
	}
	
	// animation de la bombe posée
	public void animPose(){
		if(this.isPosed){
			this.animPose.draw(this.posX, this.posY);
		}
	}
	
	// prise en compte du delta de update
	public void update(Personnage perso, int delta){
		if(this.isPosed || this.exploding){
			this.timeDelta += delta ;
			this.explode(perso);
			this.finishExplode();
		}
	}
	
	// pose d'une bombe
	public void pose(int x, int y){
		this.setCoordonnees(x, y);
		this.isPosed = true;
		this.timeDelta = 0;
		this.setChampExplosion();
	}
	
	// passe de l'état posé à l'état explosion
	public void explode(Personnage perso){
		if(this.timeDelta >= this.timePose){
			this.exploding = true;
			this.isPosed = false;
			this.animPose.restart();
			this.hurt(perso);
		}
	}
	
	// etat explosion finie
	public void finishExplode(){
		if(this.timeDelta >= this.timeExplode + this.timePose){
			this.exploding = false;
			this.animExplode.restart();
			this.hasHurted = false;
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
	
	// vider l'animation
	public void emptyAnim(){
		this.animPose = null;
		this.animExplode = null;
		this.animPose = new Animation();
		this.animExplode = new Animation();
	}
	
	// rempli champExplosion avec les coordonées adjacentes
	public void setChampExplosion(){
		this.champExplosion = new int[nbExplode][2];
		// centre
		this.champExplosion[0][0] = this.posX;
		this.champExplosion[0][1] = this.posY;
		// sud
		this.champExplosion[1][0] = this.posX;
		this.champExplosion[1][1] = this.posY + 70;
		// nord
		this.champExplosion[2][0] = this.posX;
		this.champExplosion[2][1] = this.posY - 70;
		// ouest
		this.champExplosion[3][0] = this.posX + 70;
		this.champExplosion[3][1] = this.posY;
		//est
		this.champExplosion[4][0] = this.posX - 70;
		this.champExplosion[4][1] = this.posY;
		
		
	}
	
	// attaquer l'adversaire
	private void hurt(Personnage perso){
		if(this.isExploding() && !this.hasHurted){
			for(int i=0 ; i<this.nbExplode ; i++){
				Vector2f vectorBomb = new Vector2f(this.champExplosion[i][0], this.champExplosion[i][1]);
				Vector2f vectorMonstre = new Vector2f(perso.posX(), perso.posY());
				if(vectorBomb.distance(vectorMonstre) <= this.distanceExplode){
					perso.Hurted(this.puissance);
					this.hasHurted = true;
					break;
				}
			}
		}
	}
}
