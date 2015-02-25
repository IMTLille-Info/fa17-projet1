package uvinfo.bomberman.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

import uvinfo.bomberman.Bomb;

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
	
	
	
	

}
