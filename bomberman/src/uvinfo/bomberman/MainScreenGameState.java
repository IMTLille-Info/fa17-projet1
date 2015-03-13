package uvinfo.bomberman;

import javax.swing.JOptionPane;
import javax.swing.JComboBox;

 





import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class MainScreenGameState extends BasicGameState {

	public static final int ID = 1;
	private Image background;
	private StateBasedGame game;
	public String bombe1;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
		this.background = new Image("res/accueilDragon.png");
		String Bombe = JOptionPane.showInputDialog("Combien voulez vous de bombe ? ");
		String nbBombe = JOptionPane.showInputDialog("Combien voulez vous de bombe  a? ");
		
		bombe1 = nbBombe;
		
		System.out.println(bombe1);
		
		
		
	}

	/**
	 * Contenons nous d'afficher l'image de fond. Le text est placé
	 * approximativement au centre.
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw(0, 0, container.getWidth(), container.getHeight());
		g.drawString("Appuyer sur espace", 280, 300);
	}

	/**
	 * Passer à l’écran de jeu à l'appui de n'importe quel touche.
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
	}

	@Override
	public void keyReleased(int key, char c) {

		switch (key) {
		case Input.KEY_SPACE:
			game.enterState(MapGameState.ID);
			break;
		}
	}

	/**
	 * L'identifiant permet d'identifier les différentes boucles. Pour passer de
	 * l'une à l'autre.
	 */
	@Override
	public int getID() {
		return ID;
	}
}