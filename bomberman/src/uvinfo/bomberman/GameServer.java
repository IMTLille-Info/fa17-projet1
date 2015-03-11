package uvinfo.bomberman;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.newdawn.slick.SlickException;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.examples.chat.Network;
import com.esotericsoftware.minlog.Log;

public class GameServer {
	Server server;
	
	public GameServer() throws IOException{
		this.server = new Server();
		this.server.start();
		this.server.bind(5900);
		
		Network.register(server);
		
		server.addListener(new Listener() {
			public void received (Connection c, Object object) {
				
				// vérifer le psudo du joueur qui se connecte
				
				if(object instanceof Avatar){
					server.sendToAllTCP(object);
				}
				
				if (object instanceof Bomb) {
					
				}
				
				if (object instanceof Monstre) {
					
				}
				
				if (object instanceof MapGameState) {
					
				}
				
				if (object instanceof Map) {
					
				}
				
				if (object instanceof MainScreenGameState) {									
					
				}
				
				if (object instanceof Musique) {
					
				}
				
				if (object instanceof Personnage) {
					
				}
				
				if (object instanceof Position) {
					
				}
				
				if (object instanceof StateGame) {
					
				}
				
				if (object instanceof SuperBomb) {
					
				}
				
			}
		});
		
		
		
		
		/****************** Creation fenetre affichage *************************/
		
		// Open a window to provide an easy way to stop the server.
				JFrame frame = new JFrame("Game Server");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.addWindowListener(new WindowAdapter() {
					public void windowClosed (WindowEvent evt) {
						server.stop();
					}
				});				
				
				frame.setSize(320, 110);
				frame.setLocationRelativeTo(null);				
				Container c = frame.getContentPane();
				FlowLayout miseEnFlot = new FlowLayout();
				c.setLayout(miseEnFlot);
				FlowLayout miseEnFlot1 = new FlowLayout();
				c.setLayout(miseEnFlot1);
				FlowLayout miseEnFlot2 = new FlowLayout();
				c.setLayout(miseEnFlot2);
				InetAddress IP=InetAddress.getLocalHost();		
				
				JLabel info = new JLabel("Close to stop the game server.");
				JLabel info1 = new JLabel("IP du serveur : "+IP.toString());
				JLabel info2 = new JLabel("Port d'écoute du serveur : "+Network.port);

				c.add(info);
				c.add(info1);	
				c.add(info2);			

				frame.setVisible(true);		
		
	}
	
	public static void main (String[] args) throws IOException, SlickException {
		Log.set(Log.LEVEL_DEBUG);
		new GameServer();
	}

}
