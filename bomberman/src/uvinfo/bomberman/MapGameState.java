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
	private TiledMap map;
	private Musique son;
	private Avatar perso;
	private Monstre monstre;
	
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
		this.map = new TiledMap("res/terrain2.tmx");

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
		// affichage
		this.map.render(0, 0);
		// faire une méthode render dans avatar et monstre
		g.drawAnimation(
				perso.GetAnimation(perso.GetDirection()
						+ (perso.isMoving() ? 4 : 0)), perso.posX() - 32,
				perso.posY() - 60);

		g.drawAnimation(
				monstre.GetAnimation(monstre.GetDirection()
						+ (monstre.isMoving() ? 4 : 0)), monstre.posX() - 32,
				monstre.posY() - 60);

		g.setColor(Color.red);
		g.drawString("Life : " + perso.getLife(), 20, 20);// affichage des
															// points de vie

		// c'est à la bombe de décider, le test doit être dans bomb...
		// faire : bomb.render(g)
		if (perso.hasPutBomb()) {
			perso.getBomb().animBomb();
		}

		// perso hasBombPosed() et dans avatar return bomb.isPosed()
		if (perso.hasPutSuperBomb()) {

			perso.getSuperBomb().animBomb();
		}

	}

	@Override
	/**update(GameContainer, int) : doit mettre à jour les élément de 
	 * la scène en fonction du delta temps qui est survenu. C’est ici que la 
	 * logique du jeux est renfermé.
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// mise à jour
		Image tilePerso = this.map.getTileImage(
				perso.getFuturX() / this.map.getTileWidth(), perso.getFuturY()
						/ this.map.getTileHeight(),
				this.map.getLayerIndex("Logic"));

		boolean collisionPerso = tilePerso != null;

		if (!collisionPerso) {
			if (perso.isMoving()) {
				perso.posX(perso.getFuturX());
				perso.posY(perso.getFuturY());
			}
		}

		// monstre.Start(perso);

		Image tilemonstre = this.map.getTileImage(monstre.getFuturX()
				/ this.map.getTileWidth(),
				monstre.getFuturY() / this.map.getTileHeight(),
				this.map.getLayerIndex("Logic"));

		boolean collisionMonstre = tilemonstre != null;

		if (!collisionMonstre) {
			if (monstre.isMoving()) {
				monstre.posX(monstre.getFuturX());
				monstre.posY(monstre.getFuturY());
			}
		}

		perso.getBomb().hurt(monstre);
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
			
			//container.exit();
			try {
				this.leave(container, game);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
		}

	}

	@Override
	public int getID() {
		return ID;
	}
}