package uvinfo.bomberman.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import uvinfo.bomberman.Avatar;

public class AvatarTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMoveUp() throws SlickException {
		Avatar toto = new Avatar();
		int x = toto.posX();
		int y = toto.posY();

		toto.moveUp();
		
		assertEquals(y+1, toto.posY());
		assertEquals(x, toto.posX());
	}

}
