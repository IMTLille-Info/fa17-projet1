package uvinfo.bomberman;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class StateGame extends StateBasedGame {

	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new StateGame(), 704, 576, false);
		container.setShowFPS(false);//on affiche pas les FPS
		container.setTargetFrameRate(200);//on fixe le taux de rafraichissement a 200 pour ralentir le deplacement
		container.start();

	}

	public StateGame() {
		super("Projet 1 : Bomberdragon ");
		
	}

	/**
	 * Ici il suffit d'ajouter nos deux boucles de jeux. La première ajoutèe
	 * sera celle qui sera utilisée au début
	 */
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new MainScreenGameState());
		addState(new MapGameState());
	}
}