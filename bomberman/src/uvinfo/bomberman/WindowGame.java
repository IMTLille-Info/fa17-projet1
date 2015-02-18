package uvinfo.bomberman;


import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;
public class WindowGame extends BasicGame {


	private GameContainer container;
	private TiledMap map;

	private Avatar perso;

	public WindowGame() {
		super("Projet 1 : Bomberman");
	}

	@Override
	/** GameContainer methode permettant d'initialiser le contenu du 
	 * du jeu , charger les graphismes, la musique etc.. 
	 */
	public void init(GameContainer container) throws SlickException {

		this.container = container;
		this.map = new TiledMap("res/terrain2.tmx");

		perso = new Avatar();
	}



	@Override
	/**render(GameContainer, Graphics) : doit afficher le 
	 * contenu du jeux
	 */
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		this.map.render(0, 0);

		g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval(perso.posX() - 16, perso.posY() - 8, 32, 16);
		g.drawAnimation(perso.GetAnimation(perso.GetDirection() + (perso.isMoving() ? 4 : 0)), perso.posX()-32, perso.posY()-60);

	}

	@Override
	/**update(GameContainer, int) : doit mettre à jour les élément de 
	 * la scène en fonction du delta temps qui est survenu. C’est ici que la 
	 * logique du jeux est renfermé.
	 */
	public void update(GameContainer container, int delta)
			throws SlickException {

		if (perso.isMoving()) {

			switch (perso.GetDirection()) {
			case 0: perso.moveDown(); break;
			case 1: perso.moveLeft(); break;
			case 2: perso.moveUp(); break;
			case 3: perso.moveRight(); break;
			
			}

			Image tile = this.map.getTileImage(
					perso.getFuturX() / this.map.getTileWidth(), 
					perso.getFuturY() / this.map.getTileHeight(), 
					this.map.getLayerIndex("Logic"));

			boolean collision = tile != null;
			if (collision) {
				perso.SetMoving(false);

	            perso.posX(perso.getFuturX());
	            perso.posY(perso.getFuturY());
				
			}

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
			container.exit();
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_UP:    perso.SetDirection(0); perso.SetMoving(true); break;
		case Input.KEY_LEFT:  perso.SetDirection(1); perso.SetMoving(true); break;
		case Input.KEY_DOWN:  perso.SetDirection(2); perso.SetMoving(true); break;
		case Input.KEY_RIGHT: perso.SetDirection(3); perso.SetMoving(true); break;
		}
	}

	public static void main(String[] args) throws SlickException {
		/**GameContainer, ce container permet de configurer environnement 
		 * d’exécution de la boucle.
		 */

		WindowGame game = new WindowGame();
		AppGameContainer container = new AppGameContainer(game, 704, 576, false);// True pour faire du fullscreen
		container.setShowFPS(false);//on affiche pas les FPS
		container.start();

		game.render(container, new Graphics());
	}

}
