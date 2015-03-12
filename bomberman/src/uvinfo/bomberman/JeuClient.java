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

public class JeuClient {	
	
	Client client;
	String pseudo;
	
	
	private GameContainer container;

	private Musique son;
	private Avatar perso;
	private Barre life;
	private Map map;
	private ArrayList<Personnage> listePersos = new ArrayList<Personnage>();
	private ArrayList<Bomb> listeBombes = new ArrayList<Bomb>();
	
	private Bomb bomb;
	private SuperBomb superBomb;
	
	float difficult = 1;
		
	
	public JeuClient () {
		client = new Client();
		client.start();

		// For consistency, the classes to be sent over the network are
		// registered by the same method for both the client and server.
		Network.register(client);

		client.addListener(new Listener() {
			public void connected (Connection connection) {
				client.sendTCP(pseudo);
			}

			public void received (Connection connection, Object object) {
				
				if (object instanceof Position) {
					//il faut recuperer une liste de perso ici
					//pour les afficher sur la jeu de chaque client
					//listePersos
					Position pos = (Position)object;
					return;
				}
				
				if (object instanceof Personnage) {
					Personnage pos = (Personnage)object;
					return;
				}
				
			}

			public void disconnected (Connection connection) {
				EventQueue.invokeLater(new Runnable() {
					public void run () {
						
					}
				});
			}
		});

		// Request the host from the user.
		String input = (String)JOptionPane.showInputDialog(null, "Host:", "Connect to chat server", JOptionPane.QUESTION_MESSAGE,
			null, null, "localhost");
		if (input == null || input.trim().length() == 0) System.exit(1);
		final String host = input.trim();

		// Request the user's name.
		input = (String)JOptionPane.showInputDialog(null, "Name:", "Connect to chat server", JOptionPane.QUESTION_MESSAGE, null,
			null, "Test");
		if (input == null || input.trim().length() == 0) System.exit(1);
		pseudo = input.trim();

				client.sendTCP(pseudo);

		// We'll do the connect on a new thread so the ChatFrame can show a progress bar.
		// Connecting to localhost is usually so fast you won't see the progress bar.
		new Thread("Connect") {
			public void run () {
				try {
					client.connect(5000, host, Network.port);
					// Server communication after connection can go here, or in Listener#connected().
				} catch (IOException ex) {
					ex.printStackTrace();
					System.exit(1);
				}
			}
		}.start();
	}
	
	
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
			perso.putBomb(this.listeBombes.get(0));
			break;
		case Input.KEY_ENTER:
			perso.putSuperBomb(this.listeBombes.get(1));
			break;
		case Input.KEY_A:
			difficult += 0.1;
			break;
		case Input.KEY_D:
			if(difficult > 0.1) difficult -= 0.1;
			break;
		case Input.KEY_P:
			if(container.isPaused())
			{
				container.resume();
			} else container.pause();
			break;
		}
		
		client.sendTCP(perso);
	}
	
	
	
	public static void main(String[] args) throws SlickException {
		
		Log.set(Log.LEVEL_DEBUG);
		new JeuClient();
		
		AppGameContainer container = new AppGameContainer(new StateGame(), 704, 576, false);
		container.setShowFPS(false);//on affiche pas les FPS
		container.setTargetFrameRate(200);//on fixe le taux de rafraichissement a 200 pour ralentir le deplacement
		container.start();
	}

}
