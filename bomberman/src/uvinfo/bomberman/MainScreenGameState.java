package uvinfo.bomberman;

import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

 

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import javax.swing.*; 

public class MainScreenGameState extends BasicGameState {

	public static final int ID = 1;
	private Image background;
	private StateBasedGame game;


	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
		this.background = new Image("res/accueilDragon.png");
		String Bombe = JOptionPane.showInputDialog("Combien voulez vous de bombe ? ");
		String nbBombe = JOptionPane.showInputDialog("Combien voulez vous de bombe  a? ");

	  
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