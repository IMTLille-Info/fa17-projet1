package uvinfo.bomberman;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public abstract class Personnage {

	/*********** attributs ****************/
	private int posX = 350;
	private int posY = 300;
	private int direction = 0;
	private boolean moving = false;
	
	private int PointDeVie = 10;
	private boolean hasBeenHurted = false;
	
	private Animation[] animations = new Animation[8];
	
	public Personnage() throws SlickException {
		
	}
	
	
	//****************************************************** nombre d'images sur une ligne dans le sprites 
	protected void CreateAnimation(String img,int largeur,int hauteur, int nbImageSprites) throws SlickException
	{
		SpriteSheet spriteSheet = new SpriteSheet(img, largeur, hauteur);
				
				//                                 debut case, fin case,ligne 
		this.animations[0] = loadAnimation(spriteSheet, 0, 1, 3);
		this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
		this.animations[2] = loadAnimation(spriteSheet, 0, 1, 0);
		this.animations[3] = loadAnimation(spriteSheet, 0, 1, 2);
		this.animations[4] = loadAnimation(spriteSheet, 0, nbImageSprites, 3);
		this.animations[5] = loadAnimation(spriteSheet, 0, nbImageSprites, 1);
		this.animations[6] = loadAnimation(spriteSheet, 0, nbImageSprites, 0);
		this.animations[7] = loadAnimation(spriteSheet, 0, nbImageSprites, 2);
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
		
		public int getLife()
		{
			return this.PointDeVie;
		}
		
		public void setLife(int life){
			this.PointDeVie = life;
		}
		
		public boolean getHasBeenHurted(){
			return this.hasBeenHurted;
		}
		
		public void setHasBeenHurted(boolean b){
			this.hasBeenHurted = b;
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
		
		public void Hurted(int degats)
		{
			this.PointDeVie -= degats;
			this.hasBeenHurted = true;
		}
		
		public boolean IsAlive()
		{
			if(PointDeVie>0)return true;
			else return false;
		}
		
		public abstract void render() throws SlickException;
	
}
