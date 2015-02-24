package uvinfo.bomberman;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Avatar {

	/*********** attributs ****************/
	private int posX = 350;
	private int posY = 300;
	private int direction = 0;
	private boolean moving = false;
	
	private Bomb bomb = new Bomb(); // bombe "normale", utilisation infinie
	
	private SuperBomb superBomb = new SuperBomb();
	private int nbSuperBomb = 3; 
	
	private int PointDeVie = 10;
	
	private Animation[] animations = new Animation[8];
	
	/************* constructeur *****************/
	
	protected void CreateAnimation(String img,int largeur,int hauteur) throws SlickException
	{
		SpriteSheet spriteSheet = new SpriteSheet(img, largeur, hauteur);
				
				//                                 debut case, fin case,ligne 
		this.animations[0] = loadAnimation(spriteSheet, 0, 1, 3);
		this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
		this.animations[2] = loadAnimation(spriteSheet, 0, 1, 0);
		this.animations[3] = loadAnimation(spriteSheet, 0, 1, 2);
		this.animations[4] = loadAnimation(spriteSheet, 0, 3, 3);
		this.animations[5] = loadAnimation(spriteSheet, 0, 3, 1);
		this.animations[6] = loadAnimation(spriteSheet, 0, 3, 0);
		this.animations[7] = loadAnimation(spriteSheet, 0, 3, 2);
	}
	
	public Avatar() throws SlickException
	{   
		CreateAnimation("sprites/drag.png",96,96);
		
	}
	
    private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
	    Animation animation = new Animation();
	    for (int x = startX; x < endX; x++) {
	        animation.addFrame(spriteSheet.getSprite(x, y), 100);
	    }
	    return animation;
	}
    
    /************** getter, setter ***************/
	public int posX() {
		return this.posX;
	}

	public int posY() {
		return this.posY;
	}
	
	public void posX(int x) {
		this.posX = x;
	}

	public void posY(int y) {
		this.posY = y;
	}
	
	public int GetDirection()
	{
		return this.direction;
	}
	
	public void SetDirection(int dir)
	{
		this.direction = dir;
	}
	
	public void SetMoving(boolean move)
	{
		this.moving = move;
	}
	
	public boolean isMoving()
	{
		return this.moving;
	}
	
	public Animation GetAnimation(int anim)
	{
		return this.animations[anim];
	}
	
	public Bomb getBomb() {
		return bomb;
	}
	
	public void setBomb(Bomb bomb) {
		this.bomb = bomb;
	}
	
	public int getLife()
	{
		return this.PointDeVie;
	}
	
	public void setLife(int life){
		this.PointDeVie = life;
	}
	
	public SuperBomb getSuperBomb() {
		return superBomb;
	}

	public void setSuperBomb(SuperBomb superBomb) {
		this.superBomb = superBomb;
	}
	
	public int getNbSuperBomb() {
		return nbSuperBomb;
	}

	public void setNbSuperBomb(int nbSuperBomb) {
		this.nbSuperBomb = nbSuperBomb;
	}
	
	/************** methodes *************/
	
	public void moveUp() {
		this.posY += 1;
	}
	
	
	public void moveDown() {
		this.posY -= 1;
	}
	
	public void moveRight() {
		this.posX += 1;
	}
	
	public void moveLeft() {
		this.posX -= 1;
	}
	
	public int getFuturX() {
	    int futurX = this.posX;
	    switch (this.direction) {
	    case 1: futurX = this.posX - 1; break;
	    case 3: futurX = this.posX + 1; break;
	    }
	    return futurX;
	}

	public int getFuturY() {
	    int futurY = this.posY;
	    switch (this.direction) {
	    case 0: futurY = this.posY - 1; break;
	    case 2: futurY = this.posY + 1; break;
	    }
	    return futurY;
	}
	
	public void putBomb(int x, int y){ 	
		if(!this.checkBombPosed()){
			this.bomb.setCoordonnees(x, y);
			this.bomb.setPosed(true);
		}
	}
	
	public void Hurted(int degats)
	{
		this.PointDeVie -= degats;
	}
	
	public void putSuperBomb(int x, int y){
		if(!this.checkBombPosed()){
			this.superBomb.setCoordonnees(x, y);
			this.superBomb.setPosed(true);
			this.nbSuperBomb -= 1;
		}
	}
	
	public boolean checkBombPosed(){
		if(!this.bomb.isPosed() && !this.bomb.isExploding() && !this.superBomb.isPosed() && !this.superBomb.isExploding()){
			return false;
		}else{
			return true;
		}
	}

}
