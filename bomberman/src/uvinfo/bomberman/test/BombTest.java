package uvinfo.bomberman.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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
		
		// bombe pas encore en état d'explosion
		bomb.setTimeDelta(100);
		bomb.explode(new ArrayList<Personnage>());
		
		assertTrue(bomb.isPosed());
		assertFalse(bomb.isExploding());
		
		// bombe en état d'explosion
		bomb.setTimeDelta(2400);
		bomb.explode(new ArrayList<Personnage>());
		
		assertFalse(bomb.isPosed());
		assertTrue(bomb.isExploding());		
	}
	
	@Test
	public void testFinishExplode() throws InterruptedException, SlickException{
		Bomb bomb = new Bomb();
		bomb.pose(60, 60);
		bomb.setTimeDelta(2400);
		bomb.explode(new ArrayList<Personnage>());
		
		// encore en cours d'explosion
		bomb.setTimeDelta(2400);
		bomb.finishExplode(new ArrayList<Personnage>());
		
		assertTrue(bomb.isExploding());	
		
		// bombe a fini d'exploser
		bomb.setTimeDelta(3100);
		bomb.finishExplode(new ArrayList<Personnage>());
		
		assertFalse(bomb.isExploding());
	}
	
	@Test
	public void testUpdate() throws InterruptedException, SlickException{
		Bomb bomb = new Bomb();
		bomb.pose(60, 60);
		
		// bombe posée en cours
		bomb.update(new ArrayList<Personnage>(), 100);
		
		assertTrue(bomb.isPosed());
		assertFalse(bomb.isExploding());
		
		// bombe en cours d'explosion
		bomb.update(new ArrayList<Personnage>(), 2400);
		
		assertFalse(bomb.isPosed());
		assertTrue(bomb.isExploding());
		
		// bombe finie d'exploser
		bomb.update(new ArrayList<Personnage>(), 3100);
		
		assertFalse(bomb.isPosed());
		assertFalse(bomb.isExploding());
	}
	
	@Test
	public void testHurtAvatar() {
		// avatar a coté de la bombe
		Bomb bomb = new Bomb();
		bomb.pose(335, 305);
		bomb.setExploding(true); // bombe en train d'exploser
		Avatar av = new Avatar(); 
		bomb.hurt(av);
		
		assertEquals(8, av.getLife()); // points de vie avatar baissée de 2
	}
	
	@Test
	public void testHurtAvatarTooFar() {
		
		// avatar trop éloigné de la bombe ou hors de l'axe de l'explosion
		Bomb bomb = new Bomb();
		bomb.pose(102, 102);
		bomb.setExploding(true);
		Avatar av = new Avatar(); 
		bomb.hurt(av);
		
		assertEquals(10, av.getLife()); 	// points de vie du avatar intacts
		
	}
		
	@Test
	public void testHurtAvatarEverHurted() {
		// avatar a coté de la bombe mais déjà touché par la bombe
		Bomb bomb = new Bomb();
		bomb.pose(335, 305);
		bomb.setExploding(true);
		Avatar av = new Avatar();
		av.setHasBeenHurted(true);
		bomb.hurt(av);
		
		assertEquals(10, av.getLife()); // points de vie du avatar intacts
	}
	
	@Test
	public void testHurtMonstre(){
		// monstre a coté de la bombe
		Bomb bomb = new Bomb();
		bomb.pose(335, 305); 
		bomb.setExploding(true);
		Personnage monstre = new Monstre();
		bomb.hurt(monstre);
		
		assertEquals(8, monstre.getLife()); // points de vie monstre baissée de 2
	}
	
	@Test
	public void testHurtMonstreTooFar() {
		// monstre trop éloigné de la bombe ou hors de l'axe de l'explosion
		Bomb bomb = new Bomb();
		bomb.pose(102, 102);
		bomb.setExploding(true);
		Personnage monstre = new Monstre();
		bomb.hurt(monstre);
	
		assertEquals(10, monstre.getLife()); // points de vie du monstre intacts
	}
		
	@Test
	public void testHurtMonstreEverHurted() {
		// monstre a coté de la bombe mais déjà touché par la bombe
		Bomb bomb = new Bomb();
		bomb.pose(335, 305);
		bomb.setExploding(true);
		Personnage monstre = new Monstre();
		monstre.setHasBeenHurted(true);
		bomb.hurt(monstre);
		
		assertEquals(10, monstre.getLife()); // points de vie du monstre intacts
	}
	
	@Test
	public void testHurtEverybody() {
		// monstre et avatar touché en même temps
		Bomb bomb = new Bomb();
		bomb.pose(335, 305);
		bomb.setExploding(true);
		Personnage monstre = new Monstre();
		Personnage av = new Avatar();
		bomb.hurt(monstre);
		bomb.hurt(av);
		
		assertEquals(8, av.getLife());
		assertEquals(8, monstre.getLife());
	}
	


}
