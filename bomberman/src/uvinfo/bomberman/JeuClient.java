package uvinfo.bomberman;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.esotericsoftware.minlog.Log;

public class JeuClient  extends StateBasedGame {		
	
	public JeuClient () {

		super("Network Bomberman");		
	}
	
	public static void main(String[] args) throws SlickException {
		
		Log.set(Log.LEVEL_DEBUG);
		
		//PageAttente page = new PageAttente();
		
		//page.show();
		
		
		
		AppGameContainer container = new AppGameContainer(new JeuClient(), 704, 576, false);
		container.setAlwaysRender(true); 
		container.setShowFPS(false);//on affiche pas les FPS
		container.setTargetFrameRate(200);//on fixe le taux de rafraichissement a 200 pour ralentir le deplacement
		container.start();
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {

		
		
		//WaitingForPlayers();
		
		addState(new NetworkGame());
	}
	
	public void WaitingForPlayers() {
        
		boolean aJoue = false;
	    while(!aJoue) {
	    //on attend le clic sur un bouton
	    }	
	
	}
	
}
