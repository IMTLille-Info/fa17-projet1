package uvinfo.bomberman.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.SlickException;

import uvinfo.bomberman.Bomb;

public class BombTest {

	@Test
	public void testSetCoordonnées() throws SlickException {
		Bomb bomb = new Bomb();
		bomb.setCoordonnees(4, 6);
		assertEquals(4, bomb.getPosX());
		assertEquals(6, bomb.getPosY());
		
	}

}
