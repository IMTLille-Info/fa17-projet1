package uvinfo.bomberman;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class WindowGame extends BasicGame {
	private GameContainer container;
	private TiledMap map;


	public WindowGame() {
		super("Lesson 1 :: window Game");
	}

	@Override
	/** GameContainer methode permettant d'initialiser le contenu du 
	 * du jeu , charger les graphismes, la musique etc.. 
	 */
	public void init(GameContainer container) throws SlickException {
		
		this.container = container;
		this.map = new TiledMap("res/terrain.tmx");
	
	}

	@Override
	/**render(GameContainer, Graphics) : doit afficher le 
	 * contenu du jeux
	 */
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		this.map.render(0, 0);
	}

	@Override
	/**update(GameContainer, int) : doit mettre à jour les élément de 
	 * la scène en fonction du delta temps qui est survenu. C’est ici que la 
	 * logique du jeux est renfermé.
	 */
	public void update(GameContainer container, int delta)
			throws SlickException {
	}
	
	/** methode appelé à chaque relâchement de touche 
	 * 
	 * @param key 
	 * @param c
	 */
	public void KeyRealeased(int key, char c){
		 if (Input.KEY_ESCAPE == key) {
	            container.exit();
	        }
	}
	
	public static void main(String[] args) throws SlickException {
		/**GameContainer, ce container permet de configurer environnement 
		 * d’exécution de la boucle.
		 */
		
		WindowGame game = new WindowGame();
		AppGameContainer container = new AppGameContainer(game, 640, 480, false);// True pour faire du fullscreen
		container.setShowFPS(false);//on affiche pas les FPS
		container.start();
		
		game.render(container, new Graphics());
	}

}
