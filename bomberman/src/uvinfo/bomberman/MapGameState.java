package uvinfo.bomberman;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MapGameState extends BasicGameState {
	public static final int ID = 2;
	// déclaration des autres objets
	private GameContainer container;

	private Musique son;
	private Avatar perso;
	private Monstre monstre;
	private Map map;
	
	float difficult = 1;
	
	private StateBasedGame game;

	@Override
	/** GameContainer methode permettant d'initialiser le contenu du 
	 * du jeu , charger les graphismes, la musique etc.. 
	 */
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// initialisation des objets
		this.game = game;
		this.container = container;
		
		map = new Map();
		map.init();
		
		perso = new Avatar();
		perso.initAnimation();
		
		monstre = new Monstre();
		monstre.initAnimation();
		
		son = new Musique();
		son.FondSonore();

	}

	@Override
	/**render(GameContainer, Graphics) : doit afficher le 
	 * contenu du jeux
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// affichage de la map fond et l'avant
		map.renderBackground();
		map.renderForeground();
		// faire une méthode render dans avatar et monstre
	
		
		g.setColor(Color.red);
		g.drawString("Life : " + perso.getLife(), 20, 20);// affichage des points de vie
		
		g.setColor(Color.yellow);

		g.drawString("Difficulté : " + difficult, 150, 20);// affichage vitesse
		
		g.setColor(Color.white);
		g.drawString("Life monstre : " + monstre.getLife(), 300, 20);
		
		perso.render();
		monstre.render();

	}

	@Override
	/**update(GameContainer, int) : doit mettre à jour les élément de 
	 * la scène en fonction du delta temps qui est survenu. C’est ici que la 
	 * logique du jeux est renfermé.
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
		throws SlickException {
		
	     // gestion des collisions
		map.isCollision(perso.getFuturX(), perso.getFuturY(), perso);
		
		if(!map.isCollision(monstre.getFuturX(), monstre.getFuturY(), monstre))
			{
				monstre.SetMoving(true);
				monstre.Move(perso);
			}
		
		container.setTargetFrameRate((int) (200*difficult));
		
		// gestion des intéractions entre personnages
		perso.update(delta, container);
		monstre.update(perso, container);
	
	}

	@Override
	/** methode appelé à chaque relâchement de touche 
	 * 
	 * @param key 
	 * @param c
	 */
	public void keyReleased(int key, char c) {

		perso.SetMoving(false);

		if (Input.KEY_ESCAPE == key) {
			game.enterState(MainScreenGameState.ID);
		}
	}

	@Override
	public void keyPressed(int key, char c) {

		switch (key) {
		case Input.KEY_UP:
			perso.SetDirection(0);
			perso.SetMoving(true);
			break;
		case Input.KEY_LEFT:
			perso.SetDirection(1);
			perso.SetMoving(true);
			break;
		case Input.KEY_DOWN:
			perso.SetDirection(2);
			perso.SetMoving(true);
			break;
		case Input.KEY_RIGHT:
			perso.SetDirection(3);
			perso.SetMoving(true);
			break;
		case Input.KEY_SPACE:
			perso.putBomb();
			break;
		case Input.KEY_ENTER:
			perso.putSuperBomb();
			break;
		case Input.KEY_A:
			difficult += 0.1;
			break;
		case Input.KEY_D:
			if(difficult > 0.1) difficult -= 0.1;
			break;
		case Input.KEY_P:
			if(container.isPaused())
			{
				container.resume();
			} else container.pause();
			break;
		}
	}

	
	@Override
	public int getID() {
		return ID;
	}
}
