package uvinfo.bomberman;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Block extends Personnage {

	/******* variables ********/
	private int max = 400;
	private int min = 0;
	private int PositionY = (int) (Math.random() * (max - min) + min);
	private int PositionX = (int) (Math.random() * (max - min) + min);

	// long tempsDebut = System.currentTimeMillis();
	@Override
	public void render() throws SlickException {
		// TODO Auto-generated method stub
		GetAnimation(GetDirection() + (isMoving() ? 4 : 0)).draw(this.posX(),
				this.posY());
	}
	/******* Constructeur *********/
	public Block() {
	}

	/****** méthodes ******/
	public void initAnimation() throws SlickException {
		CreateAnimation("sprites/Brick.png", 64, 64, 0);
		System.out.println(PositionY);
		posX(PositionX);
		posY(PositionY);
		//	SetDirection(2);
	}

	public int getPositionX() {
		return PositionX;
	}

	public void setPositionX(int PositionX) {
		this.PositionX = PositionX;
	}

	public int getPositionY() {
		return PositionY;
	}

	public void setPositionY(int PositionY) {
		this.PositionY = PositionY;
	}
}
