package uvinfo.bomberman;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class WindowGame extends BasicGame {

	private GameContainer container;
	private TiledMap map;
	private Musique son;
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
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		this.map.render(0, 0);
		 
		// faire une méthode render dans avatar et monstre
		
	
		
		g.setColor(Color.red); 
		g.drawString("Life : " + perso.getLife(), 20, 20);//affichage des points de vie
		
		// c'est à la bombe de décider, le test doit être dans bomb...
		// faire : bomb.render(g) 
		
		
		perso.render();
		monstre.render();
		perso.getBomb().render();
		
		// perso hasBombPosed() et dans avatar return bomb.isPosed()
	
		perso.getSuperBomb().render();
		
		if(perso.IsAlive() == false) container.pause(); //container.exit();
		
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
		
		if (!collisionPerso) 
		{
			if (perso.isMoving()) 
			{
	            perso.posX(perso.getFuturX());
	            perso.posY(perso.getFuturY());
			}
		}		
				

		
		monstre.Move(perso);
		
		perso.getBomb().hurt(monstre);

	}

	
	public void MoveMonster()
	{

		
		
		Image tilemonstre = this.map.getTileImage(
				monstre.getFuturX() / this.map.getTileWidth(), 
				monstre.getFuturY()/ this.map.getTileHeight(), 
				this.map.getLayerIndex("Logic"));			
		
		boolean collisionmonstre = tilemonstre != null;
		
		if (!collisionmonstre) 
		{
			if (monstre.isMoving())
			{
				monstre.posX(monstre.getFuturX());
				monstre.posY(monstre.getFuturY());
			}
		}	
		
		monstre.Move(perso);
	}

	@Override
	/** methode appelé à chaque relâchement de touche 
	 * 
	 * @param key 
	 * @param c
	 */
	public void keyReleased(int key, char c) {

		perso.SetMoving(false);
		monstre.SetMoving(false);

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
		case Input.KEY_SPACE: perso.putBomb(); break;
		case Input.KEY_ENTER: perso.putSuperBomb(); break;
		}				
	}

	public static void main(String[] args) throws SlickException {
		/**GameContainer, ce container permet de configurer environnement 
		 * d’exécution de la boucle.
		 */

		WindowGame game = new WindowGame();
		AppGameContainer container = new AppGameContainer(game, 704, 576, false);// True pour faire du fullscreen
		container.setShowFPS(false);//on affiche pas les FPS
		container.setTargetFrameRate(200);//on fixe le taux de rafraichissement a 200 pour ralentir le deplacement
		container.start();
		
		game.render(container, new Graphics());
	}

}
