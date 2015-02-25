package uvinfo.bomberman.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

import uvinfo.bomberman.Bomb;

public class BombTest {

	@Test
	public void testSetCoordonnees() throws SlickException {
		Bomb bomb = new Bomb(8);
		bomb.setCoordonnees(4, 6);
		assertEquals(4, bomb.getPosX());
		assertEquals(6, bomb.getPosY());
	}
	
	@Test
	public void testSetTimeBeforeExplode() throws SlickException{
		Bomb bomb = new Bomb(8);
		bomb.getAnimation().addFrame(new Image("sprites/bomb.png"), 100);
		bomb.setTimeBeforeExplode(50);
		assertEquals(50, bomb.getAnimation().getDuration(1));
	}
	
	

}
