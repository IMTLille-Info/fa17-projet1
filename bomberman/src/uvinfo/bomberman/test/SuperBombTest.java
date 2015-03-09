package uvinfo.bomberman.test;

import static org.junit.Assert.*;

import org.junit.Test;

import uvinfo.bomberman.Avatar;
import uvinfo.bomberman.Bomb;
import uvinfo.bomberman.Monstre;
import uvinfo.bomberman.Personnage;
import uvinfo.bomberman.SuperBomb;

public class SuperBombTest {

	@Test
	public void testHurtAvatar() {
		// avatar a coté de la bombe
		Bomb bomb = new SuperBomb();
		bomb.pose(210, 305);
		bomb.setExploding(true); // bombe en train d'exploser
		Avatar av = new Avatar(); 
		bomb.hurt(av);
		
		assertEquals(6, av.getLife()); // points de vie avatar baissée de 2
	}
	
	@Test
	public void testHurtAvatarTooFar() {
		// avatar trop éloigné de la bombe ou hors de l'axe de l'explosion
		Bomb bomb = new SuperBomb();
		bomb.pose(102, 102);
		bomb.setExploding(true);
		Avatar av = new Avatar();
		bomb.hurt(av);
		
		assertEquals(10, av.getLife()); 	// points de vie du avatar intacts
	}
		
	@Test
	public void testHurtAvatarEverHurted() {
		// avatar a coté de la bombe mais déjà touché par la bombe
		Bomb bomb = new SuperBomb();
		bomb.pose(335, 305);
		bomb.setExploding(true);
		Avatar av = new Avatar();
		av.setHasBeenHurted(true);
		bomb.hurt(av);
		
		assertEquals(10, av.getLife()); // points de vie du avatar intacts
	}
	
	@Test
	public void testHurtMonstre() {
		// monstre a coté de la bombe
		Bomb bomb = new SuperBomb();
		bomb.pose(550, 305); 
		bomb.setExploding(true);
		Personnage monstre = new Monstre();
		bomb.hurt(monstre);
		
		assertEquals(6, monstre.getLife()); // points de vie monstre baissée de 2
	}
		
	@Test
	public void testHurtMonstreTooFar() {
		// monstre trop éloigné de la bombe ou hors de l'axe de l'explosion
		Bomb bomb = new SuperBomb();
		bomb.pose(102, 102);
		bomb.setExploding(true);
		Personnage monstre = new Monstre();
		bomb.hurt(monstre);
	
		assertEquals(10, monstre.getLife()); // points de vie du monstre intacts
	}
		
	@Test
	public void testHurtMonstreEverHurted() {
		// monstre a coté de la bombe mais déjà touché par la bombe
		Bomb bomb = new SuperBomb();
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
		Bomb bomb = new SuperBomb();
		bomb.pose(335, 305);
		bomb.setExploding(true);
		Personnage monstre = new Monstre();
		Avatar av = new Avatar();
		bomb.hurt(monstre);
		bomb.hurt(av);
		
		assertEquals(6, av.getLife());
		assertEquals(6, monstre.getLife());
	}

}
