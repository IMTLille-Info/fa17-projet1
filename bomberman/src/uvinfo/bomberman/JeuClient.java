package uvinfo.bomberman;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class JeuClient  extends StateBasedGame {		
	
	public JeuClient () {

		super("Network Bomberman");		
	}
	
	public static void main(String[] args) throws SlickException {
		
		Log.set(Log.LEVEL_DEBUG);
		
		AppGameContainer container = new AppGameContainer(new JeuClient(), 704, 576, false);
		container.setAlwaysRender(true); 
		container.setShowFPS(false);//on affiche pas les FPS
		container.setTargetFrameRate(200);//on fixe le taux de rafraichissement a 200 pour ralentir le deplacement
		container.start();
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {

		addState(new NetworkGame());
	}

}
