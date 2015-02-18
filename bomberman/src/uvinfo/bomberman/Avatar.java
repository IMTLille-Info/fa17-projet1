package uvinfo.bomberman;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Avatar {

	private int posX = 350;
	private int posY = 300;
	private int direction = 0;
	private boolean moving = false;
	
	
	private Animation[] animations = new Animation[8];
	
	public Avatar() throws SlickException
	{
		SpriteSheet spriteSheet = new SpriteSheet("sprites/drag.png", 96, 96);
		
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
	
    private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
	    Animation animation = new Animation();
	    for (int x = startX; x < endX; x++) {
	        animation.addFrame(spriteSheet.getSprite(x, y), 100);
	    }
	    return animation;
	}
    
	public int posX() {
		return this.posX;
	}

	public void moveUp() {
		this.posY += 1;
	}

	public int posY() {
		return this.posY;
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

}
