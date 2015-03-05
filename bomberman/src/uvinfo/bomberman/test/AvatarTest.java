package uvinfo.bomberman.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.SlickException;

import uvinfo.bomberman.Avatar;
import uvinfo.bomberman.Bomb;
import uvinfo.bomberman.SuperBomb;

public class AvatarTest {

	@Test
	public void testMoveUp() {
		Avatar toto = new Avatar();
		int x = toto.posX();
		int y = toto.posY();

		toto.moveUp();

		assertEquals(y + 1, toto.posY());
		assertEquals(x, toto.posX());

		toto.moveUp();
		toto.moveUp();

		assertEquals(y + 3, toto.posY());
		assertEquals(x, toto.posX());
	}

	@Test
	public void testMoveDown() {
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
	public void testMoveRight() {
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
	public void testMoveLeft() {
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
	public void testIsMoving() {
		Avatar toto = new Avatar();
		boolean moov;
		boolean moov2 = true;

		moov = toto.isMoving();

		assertEquals(moov, false);

		toto.SetMoving(moov2);
		moov = toto.isMoving();

		assertEquals(moov, true);
	}

	@Test
	public void testPutBomb(){
		Avatar av = new Avatar();
		av.putBomb(new Bomb());
		
		assertTrue(av.hasPutBomb());
		assertEquals(0, av.getTimeWaited());
	}
	
	@Test
	public void testPutSuperBomb(){
		Avatar av = new Avatar();
		av.putSuperBomb(new SuperBomb());
		
		assertTrue(av.hasPutBomb());
		assertEquals(0, av.getTimeWaited());
		assertEquals(9, av.getNbSuperBomb());
	}

}
