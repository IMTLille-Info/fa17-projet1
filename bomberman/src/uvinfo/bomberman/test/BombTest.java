package uvinfo.bomberman.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

import uvinfo.bomberman.Avatar;
import uvinfo.bomberman.Bomb;
import uvinfo.bomberman.Monstre;

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
		
		assertEquals(System.currentTimeMillis(), bomb.getTimeBegin());
	}
	
	@Test
	public void testExplode() throws InterruptedException{
		Bomb bomb = new Bomb();
		bomb.pose(60, 60);
		
		Thread.sleep(bomb.getTimePose());
		bomb.explode();
		
		assertFalse(bomb.isPosed());
		assertTrue(bomb.isExploding());		
	}
	
	@Test
	public void testFinishExplode() throws InterruptedException{
		Bomb bomb = new Bomb();
		bomb.pose(60, 60);
		
		Thread.sleep(bomb.getTimeExplode() + bomb.getTimePose());
		bomb.finishExplode();
		
		assertFalse(bomb.isExploding());	
	}
	
	@Test
	public void testEtat() throws InterruptedException{
		Bomb bomb = new Bomb();
		bomb.pose(60, 60);
		
		Thread.sleep(bomb.getTimeExplode() + bomb.getTimePose());
		bomb.etat();
		
		assertFalse(bomb.isPosed());
		assertFalse(bomb.isExploding());
	}
	
	@Test
	public void testHurt() throws InterruptedException, SlickException{
		/********** Test sur Avatar *********/
		// avatar a coté de la bombe
		Bomb bomb = new Bomb();
		bomb.pose(355, 305);
		Avatar av = new Avatar();
		Thread.sleep(bomb.getTimePose());
		bomb.explode();
		bomb.hurt(av);
		
		assertEquals(8, av.getLife()); // points de vie avatar baissée de 2
		
		// avatar trop éloigné de la bombe ou hors de l'axe de l'explosion
		bomb.setCoordonnees(500, 500);
		Avatar av2 = new Avatar();
		bomb.pose(102, 102);
		Thread.sleep(bomb.getTimePose());
		bomb.hurt(av2);
		
		// points de vie du avatar intacts
		assertEquals(10, av2.getLife());
		
		/************* Test sur Monstre **********/
		// monstre à coté de la bombe
		Monstre monstre = new Monstre();
		Thread.sleep(bomb.getTimePose());
		bomb.explode();
		bomb.hurt(monstre);
		
		// points de vie avatar baissée de 2
		assertEquals(8, monstre.getLife());
		
		// avatar trop éloigné de la bombe ou hors de l'axe de l'explosion
		bomb.setCoordonnees(500, 500);
		Monstre monstre2 = new Monstre();
		bomb.pose(102, 102);
		Thread.sleep(bomb.getTimePose());
		bomb.hurt(monstre2);
		
		// points de vie du avatar intacts
		assertEquals(10, monstre2.getLife());
		
		
	}
	
	

}
