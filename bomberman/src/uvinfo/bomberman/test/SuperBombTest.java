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
	public void testHurt() {
		// avatar a coté de la bombe
		Bomb bomb = new SuperBomb();
		bomb.pose(210, 305);
		bomb.setExploding(true); // bombe en train d'exploser
		Avatar av = new Avatar(); 
		bomb.hurt(av);
		
		assertEquals(6, av.getLife()); // points de vie avatar baissée de 2
		
		// avatar trop éloigné de la bombe ou hors de l'axe de l'explosion
		bomb.pose(102, 102);
		bomb.setExploding(true);
		bomb.hurt(av);
		
		assertEquals(6, av.getLife()); 	// points de vie du avatar intacts
		
		// avatar a coté de la bombe mais déjà touché par la bombe
		bomb.pose(335, 305);
		bomb.setExploding(true);
		av.setHasBeenHurted(true);
		bomb.hurt(av);
		
		assertEquals(6, av.getLife()); // points de vie du avatar intacts
		
		// monstre a coté de la bombe
		bomb.pose(550, 305); 
		bomb.setExploding(true);
		Personnage monstre = new Monstre();
		bomb.hurt(monstre);
		
		assertEquals(6, monstre.getLife()); // points de vie monstre baissée de 2
		
		// monstre trop éloigné de la bombe ou hors de l'axe de l'explosion
		bomb.pose(102, 102);
		bomb.setExploding(true);
		bomb.hurt(monstre);
	
		assertEquals(6, monstre.getLife()); // points de vie du monstre intacts
		
		// monstre a coté de la bombe mais déjà toucgé par la bombe
		bomb.pose(335, 305);
		bomb.setExploding(true);
		monstre.setHasBeenHurted(true);
		bomb.hurt(monstre);
		
		assertEquals(6, monstre.getLife()); // points de vie du monstre intacts
		
		// monstre et avatar touché en même temps
		bomb.pose(335, 305);
		bomb.setExploding(true);
		monstre.setHasBeenHurted(false);
		av.setHasBeenHurted(false);
		bomb.hurt(monstre);
		bomb.hurt(av);
		
		assertEquals(2, av.getLife());
		assertEquals(2, monstre.getLife());
	}

}
