package uvinfo.bomberman;

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
	private Avatar avatar;
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
		
		avatar = new Avatar();
		avatar.initAnimation();
		
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
		
		// infos sur le jeu
		g.setColor(Color.red);
		g.drawString("Life : " + avatar.getLife(), 20, 20);// affichage des points de vie du avatar
		
		g.setColor(Color.yellow);
		g.drawString("Difficulté : " + difficult, 150, 20);// affichage vitesse
		
		g.setColor(Color.white);
		g.drawString("Life monstre : " + monstre.getLife(), 300, 20);// affichage des points de vie du monstre
		
		// animation des personnages
		avatar.render();
		monstre.render();
		
		// animation des bombes
		avatar.getBomb().render();
		avatar.getSuperBomb().render();

	}

	@Override
	/**update(GameContainer, int) : doit mettre à jour les élément de 
	 * la scène en fonction du delta temps qui est survenu. C’est ici que la 
	 * logique du jeux est renfermé.
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
		throws SlickException {
	     // gestion des collisions
		map.isCollision(avatar.getFuturX(), avatar.getFuturY(), avatar);
		map.isCollision(monstre.getFuturX(), monstre.getFuturY(), monstre);
		container.setTargetFrameRate((int) (200*difficult));
	/* Ancien Code à voir	
		
 
		Image tilePerso = this.map.getTileImage(
				avatar.getFuturX() / this.map.getTileWidth(), 
				avatar.getFuturY()/ this.map.getTileHeight(), 
				this.map.getLayerIndex("Logic"));			

		boolean collisionPerso = tilePerso != null;

		if (!collisionPerso) 
		{
			if (avatar.isMoving()) 
			{
				avatar.posX(avatar.getFuturX());
				avatar.posY(avatar.getFuturY());
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
			monstre.Move(avatar);
		}
		else 
		{
			monstre.SetMoving(true);
			monstre.OpposeDirection();
		}			*/

		// gère la pose et l'explosion de la bombe
		avatar.getBomb().update(delta);
		avatar.getSuperBomb().update(delta);
		
		// gère l'attaque de la bombe
		avatar.getBomb().hurt(monstre);
		avatar.getSuperBomb().hurt(monstre);
		avatar.getBomb().hurt(avatar);
		avatar.getSuperBomb().hurt(avatar);
	
		// perdu si avatar est mort
		if(!avatar.IsAlive())
		{
			javax.swing.JOptionPane.showMessageDialog(null,"Game Over"); 
			container.exit();
		}
		
		// gagné si monstre est mort
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

		avatar.SetMoving(false);

		if (Input.KEY_ESCAPE == key) {
			game.enterState(MainScreenGameState.ID);
		}
	}

	@Override
	public void keyPressed(int key, char c) {

		switch (key) {
		case Input.KEY_UP:
			avatar.SetDirection(0);
			avatar.SetMoving(true);
			break;
		case Input.KEY_LEFT:
			avatar.SetDirection(1);
			avatar.SetMoving(true);
			break;
		case Input.KEY_DOWN:
			avatar.SetDirection(2);
			avatar.SetMoving(true);
			break;
		case Input.KEY_RIGHT:
			avatar.SetDirection(3);
			avatar.SetMoving(true);
			break;
		case Input.KEY_SPACE:
			avatar.putBomb();
			break;
		case Input.KEY_ENTER:
			avatar.putSuperBomb();
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
