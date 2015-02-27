package uvinfo.bomberman;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

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
		map.init();
		perso = new Avatar();
		perso.initAnimation();
		monstre = new Monstre();
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
		perso.getBomb().render();
		perso.getSuperBomb().render();

	}

	@Override
	/**update(GameContainer, int) : doit mettre à jour les élément de 
	 * la scène en fonction du delta temps qui est survenu. C’est ici que la 
	 * logique du jeux est renfermé.
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
		throws SlickException {
	     // gestion des collisions
		map.isCollision(perso.getFuturX(), perso.getFuturY());
		map.isCollision(perso.getFuturX(), perso.getFuturY());
	/* Ancien Code à voir	
		container.setTargetFrameRate((int) (200*difficult));

		Image tilePerso = this.map.getTileImage(
				perso.getFuturX() / this.map.getTileWidth(), 
				perso.getFuturY()/ this.map.getTileHeight(), 
				this.map.getLayerIndex("Logic"));			

		boolean collisionPerso = tilePerso != null;

		if (!collisionPerso) 
		{
			if (perso.isMoving()) 
			{
				perso.posX(perso.getFuturX());
				perso.posY(perso.getFuturY());
			}
		}	

		Image tilemonstre = this.map.getTileImage(
				monstre.getFuturX() / this.map.getTileWidth(), 
				monstre.getFuturY()/ this.map.getTileHeight(), 
				this.map.getLayerIndex("Logic"));			

		boolean collisionmonstre = tilemonstre != null;
		if (!collisionmonstre) 
		{
			monstre.SetMoving(true);
			monstre.Move(perso);
		}
		else 
		{
			monstre.SetMoving(true);
			monstre.OpposeDirection();
		}			*/

		perso.getBomb().hurt(monstre);
		perso.getSuperBomb().hurt(monstre);
		perso.getBomb().hurt(perso);
		perso.getSuperBomb().hurt(perso);
	
		if(!perso.IsAlive())
		{
			javax.swing.JOptionPane.showMessageDialog(null,"Game Over"); 
			container.exit();
		}
		
		if(!monstre.IsAlive())
		{
			javax.swing.JOptionPane.showMessageDialog(null,"You Win"); 
			container.exit();
		}
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
			if(difficult > 0.1) difficult -= 0.1;
			break;
		case Input.KEY_D:
			difficult += 0.1;
			break;
		}

	}

	@Override
	public int getID() {
		return ID;
	}
}
