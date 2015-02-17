package uvinfo.bomberman;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class BombermanGame extends BasicGame {

	public BombermanGame(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawString("Go!", 50, 50);
	}	
	
	

	public static void main(String[] args) {

		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new BombermanGame("Projet 1 : Bomberman"));
			appgc.setDisplayMode(640, 480, false);//if true = fullscreen
			
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(BombermanGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
