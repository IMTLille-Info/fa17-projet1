package uvinfo.bomberman.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

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
	public void testHurt() throws SlickException{
		// monstre a coté de la bombe
		Bomb bomb = new Bomb();
		bomb.pose(105, 105);
		Monstre monstre = new Monstre();
		bomb.hurt(monstre);
		
		// points de vie monstre baissée de 2
		assertEquals(8, monstre.getLife());
		
		// monstre trop éloigné de la bombe ou hors de l'axe de l'explosion
		bomb.setCoordonnees(500, 500);
		Monstre monstre2 = new Monstre();
		bomb.hurt(monstre2);
		
		// points de vie du monstre intacts
		assertEquals(10, monstre2.getLife());
	}
	
	

}
