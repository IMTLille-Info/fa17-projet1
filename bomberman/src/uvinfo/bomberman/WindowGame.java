package uvinfo.bomberman;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
public class WindowGame extends BasicGame {


	private GameContainer container;
	private TiledMap map;

	private Avatar perso;
	private Monstre monstre;

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
		monstre = new Monstre();
	}

	@Override
	/**render(GameContainer, Graphics) : doit afficher le 
	 * contenu du jeux
	 */
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		this.map.render(0, 0);
		
		g.drawAnimation(perso.GetAnimation(perso.GetDirection() + (perso.isMoving() ? 4 : 0)), perso.posX()-32, perso.posY()-60);

		g.drawAnimation(monstre.GetAnimation(monstre.GetDirection() + (monstre.isMoving() ? 4 : 0)), monstre.posX()-32, monstre.posY()-60);

		
		g.setColor(Color.red); 
		g.drawString("Life : " + perso.getLife(), 20, 20);//affichage des points de vie
		
		if(perso.getBomb().isPosed() || perso.getBomb().isExploding()){
			perso.getBomb().cycleBomb();
		}
		
		if(perso.getSuperBomb().isPosed() || perso.getSuperBomb().isExploding()){
			perso.getSuperBomb().cycleBomb();
		}
		

		monstre.Start(perso);
		
		
	}

	@Override
	/**update(GameContainer, int) : doit mettre à jour les élément de 
	 * la scène en fonction du delta temps qui est survenu. C’est ici que la 
	 * logique du jeux est renfermé.
	 */
	public void update(GameContainer container, int delta)
			throws SlickException {

		Image tilePerso = this.map.getTileImage(
				perso.getFuturX() / this.map.getTileWidth(), 
				perso.getFuturY()/ this.map.getTileHeight(), 
				this.map.getLayerIndex("Logic"));			
		
		

		boolean collisionPerso = tilePerso != null;
		
		if (collisionPerso) 
		{
			//perso.SetMoving(false);//désactiv l'affichage du deplacement du personnage
		}	
		else
		{
			if (perso.isMoving()) 
			{
	            perso.posX(perso.getFuturX());
	            perso.posY(perso.getFuturY());
			}
		}		
		

		testMonstre();
		
	}
	
	public void testMonstre()
	{
		Image tilemonstre = this.map.getTileImage(
				monstre.getFuturX() / this.map.getTileWidth(), 
				monstre.getFuturY() / this.map.getTileHeight(), 
				this.map.getLayerIndex("Logic"));			

		boolean collisionMonstre = tilemonstre != null;
		
		if (collisionMonstre) 
		{
			monstre.SetMoving(false);
		}
		else 
		{
			if(monstre.isMoving())
			{
				monstre.posX(monstre.getFuturX());
				monstre.posY(monstre.getFuturY());
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
		case Input.KEY_SPACE: 
			perso.putBomb(perso.posX(), perso.posY()); 
			break;
		case Input.KEY_ENTER: 
			perso.putSuperBomb(perso.posX(), perso.posY()); break;
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
