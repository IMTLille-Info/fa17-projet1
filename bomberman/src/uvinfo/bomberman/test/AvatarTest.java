package uvinfo.bomberman.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import uvinfo.bomberman.Avatar;

public class AvatarTest {

	// @Test
	/*
	 * public void testMoveUp() throws SlickException { Avatar toto = new
	 * Avatar(); int x = toto.posX(); int y = toto.posY();
	 * 
	 * toto.moveUp();
	 * 
	 * assertEquals(y+1, toto.posY()); assertEquals(x, toto.posX());
	 * 
	 * dragTest.moveUp(); dragTest.moveUp();
	 * 
	 * assertEquals(y+3,dragTest.posY()); assertEquals(x,dragTest.posX()); }
	 */

	@Test
	public void testMoveDown() throws SlickException {
		Avatar toto = new Avatar();
		int x = toto.posX();
		int y = toto.posY();

		toto.moveDown();

		assertEquals(y - 1, toto.posY());
		assertEquals(x, toto.posX());

		toto.moveDown();
		toto.moveDown();

		assertEquals(y - 3, toto.posY());
		assertEquals(x, toto.posX());

	}

	@Test
	public void testMoveRight() throws SlickException {
		Avatar toto = new Avatar();
		int x = toto.posX();
		int y = toto.posY();

		toto.moveRight();

		assertEquals(y, toto.posY());
		assertEquals(x + 1, toto.posX());

		toto.moveRight();
		toto.moveRight();

		assertEquals(y, toto.posY());
		assertEquals(x + 3, toto.posX());
	}

	@Test
	public void testMoveLeft() throws SlickException {
		Avatar toto = new Avatar();
		int x = toto.posX();
		int y = toto.posY();

		toto.moveLeft();

		assertEquals(y, toto.posY());
		assertEquals(x - 1, toto.posX());

		toto.moveLeft();
		toto.moveLeft();

		assertEquals(y, toto.posY());
		assertEquals(x - 3, toto.posX());
	}

	@Test
	public void testIsMoving() throws SlickException {
		Avatar toto = new Avatar();
		boolean moov;
		boolean moov2=true;
		
		moov = toto.isMoving();

		assertEquals(moov, false);

		toto.SetMoving(moov2);
		moov = toto.isMoving();
		
		assertEquals(moov, true);
	}
	
	/*@Test
	public void testGetFuturX() throws SlickException {
		Avatar toto = new Avatar();
		
	}*/

}
