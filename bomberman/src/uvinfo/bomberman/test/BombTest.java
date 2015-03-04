package uvinfo.bomberman.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.newdawn.slick.SlickException;

import uvinfo.bomberman.Avatar;
import uvinfo.bomberman.Bomb;
import uvinfo.bomberman.Monstre;
import uvinfo.bomberman.Personnage;

public class BombTest {

	@Test
	public void testSetCoordonnees() throws SlickException {
		Bomb bomb = new Bomb();
		bomb.setCoordonnees(4, 6);
		assertEquals(4, bomb.getPosX());
		assertEquals(6, bomb.getPosY());
	}
	
	@Test
	public void testSetTimeBeforeExplode() throws SlickException{
		
	}
	
	@Test
	public void testPose() throws InterruptedException{
		Bomb bomb = new Bomb();
		bomb.pose(60, 60);
		
		assertEquals(60, bomb.getPosX());
		assertEquals(60, bomb.getPosY());
		
		assertFalse(bomb.isExploding());
		assertTrue(bomb.isPosed());
	}
	
	@Test
	public void testExplode() throws InterruptedException, SlickException{
		Bomb bomb = new Bomb();
		bomb.pose(60, 60);
		
		bomb.update(new Avatar(), bomb.getTimePose());
		bomb.explode(new Avatar());
		
		assertFalse(bomb.isPosed());
		assertTrue(bomb.isExploding());		
	}
	
	@Test
	public void testFinishExplode() throws InterruptedException, SlickException{
		Bomb bomb = new Bomb();
		bomb.pose(60, 60);
		
		bomb.update(new Avatar(), bomb.getTimeExplode() + bomb.getTimePose());
		bomb.finishExplode();
		
		assertFalse(bomb.isExploding());	
	}
	
	
	@Test
	public void testUpdate() throws InterruptedException, SlickException{
		Bomb bomb = new Bomb();
		bomb.pose(60, 60);
		
		bomb.update(new Avatar(), bomb.getTimeExplode() + bomb.getTimePose());
		
		assertFalse(bomb.isPosed());
		assertFalse(bomb.isExploding());
	}
	
	@Test
	public void testHurt() throws InterruptedException, SlickException{
		// avatar a coté de la bombe
		Bomb bomb = new Bomb();
		bomb.pose(335, 305);
		Personnage perso = new Avatar();
		bomb.update(perso, bomb.getTimePose()); // update contient explode qui contient la méthode hurt
		
		assertEquals(8, perso.getLife()); // points de vie avatar baissée de 2
		
		// avatar trop éloigné de la bombe ou hors de l'axe de l'explosion
		Personnage perso2 = new Avatar();
		bomb.pose(102, 102);
		bomb.update(perso2, bomb.getTimePose());
		
		// points de vie du avatar intacts
		assertEquals(10, perso2.getLife());
		
		// monstre a coté de la bombe
		Bomb bomb2 = new Bomb();
		bomb2.pose(335, 305);
		Personnage monstre = new Monstre();
		bomb2.update(monstre, bomb2.getTimePose());
		
		assertEquals(8, monstre.getLife()); // points de vie monstre baissée de 2
		
		// avatar trop éloigné de la bombe ou hors de l'axe de l'explosion
		Personnage monstre2 = new Monstre();
		bomb2.pose(102, 102);
		bomb2.update(monstre2, bomb2.getTimePose());
	
		// points de vie du avatar intacts
		assertEquals(10, monstre2.getLife());
	}
	
	

}
