package uvinfo.bomberman;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public class Bomb { // degager getter et setter qui ne servent à rien
	
	Musique SonBombe = new Musique();
	
	/******* attributs ******/
	private int puissance = 2;
	private int posX;
	private int posY;
	private boolean isPosed = false;
	private boolean exploding = false;
	protected Animation animPose = new Animation();
	protected Animation animExplode = new Animation();
	private long timeBegin;
	private int timePose = 2300;
	private int timeExplode = 700;
	
	private int[][] champExplosion = new int[5][2];
	private int distanceExplode = 100;
	
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
	
	public long getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(long l) {
		this.timeBegin = l;
	}
	
	public int getTimePose(){
		return this.timePose;
	}
	
	public int getTimeExplode(){
		return this.timeExplode;
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
			this.etat();
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
	
	// états de la bombe sur un cycle complet
	public void etat(){
		this.explode();
		this.finishExplode();
	}
	
	// pose d'une bombe
	public void pose(int x, int y){
		this.setCoordonnees(x, y);
		this.setPosed(true);
		this.setTimeBegin(System.currentTimeMillis());
	}
	
	// passe de l'état posé à l'état explosion
	public void explode(){
		if(System.currentTimeMillis() - this.timeBegin >= this.timePose){
			this.exploding = true;
			this.isPosed = false;
			this.animPose.restart();
		}
	}
	
	// etat explosion finie
	public void finishExplode(){
		if(System.currentTimeMillis() - this.timeBegin >= this.timeExplode + this.timePose ){
			this.exploding = false;
			this.animExplode.restart();
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
	
	// attaquer l'adversaire
	public void hurt(Monstre monstre){
		if(this.isExploding()){
			Vector2f vectorBomb = new Vector2f(this.posX, this.posY);
			Vector2f vectorMonstre = new Vector2f(monstre.posX(), monstre.posY());
			System.out.println(vectorBomb.distance(vectorMonstre));
		}
	}
}
