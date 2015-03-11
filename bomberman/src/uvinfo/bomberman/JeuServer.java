package uvinfo.bomberman;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.examples.chat.Network.ChatMessage;
import com.esotericsoftware.kryonet.examples.chat.Network.RegisterName;
import com.esotericsoftware.kryonet.examples.chat.Network.UpdateNames;
import com.esotericsoftware.minlog.Log;

public class JeuServer {
	Server server;

	public JeuServer () throws IOException {
		server = new Server() {
			protected Connection newConnection () {
				// By providing our own connection implementation, we can store per
				// connection state without a connection ID to state look up.
				return new JeuConnection();
			}
		};

		// For consistency, the classes to be sent over the network are
		// registered by the same method for both the client and server.
		Network.register(server);

		server.addListener(new Listener() {
			public void received (Connection c, Object object) {
				// We know all connections for this server are actually JeuConnections.
				JeuConnection connection = (JeuConnection)c;

				if (object instanceof RegisterName) {
					// Ignore the object if a client has already registered a name. This is
					// impossible with our client, but a hacker could send messages at any time.
					if (connection.name != null) return;
					// Ignore the object if the name is invalid.
					String name = ((RegisterName)object).name;
					if (name == null) return;
					name = name.trim();
					if (name.length() == 0) return;
					// Store the name on the connection.
					connection.name = name;
					// Send a "connected" message to everyone except the new client.
					ChatMessage chatMessage = new ChatMessage();
					chatMessage.text = name + " connected.";
					server.sendToAllExceptTCP(connection.getID(), chatMessage);
					// Send everyone a new list of connection names.
					updateNames();
					return;
				}


				if (object instanceof Bomb) {
					
				}
				
				if (object instanceof Avatar) {
					
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

			public void disconnected (Connection c) {
				JeuConnection connection = (JeuConnection)c;
				if (connection.name != null) {
					// Announce to everyone that someone (with a registered name) has left.
					ChatMessage chatMessage = new ChatMessage();
					chatMessage.text = connection.name + " disconnected.";
					server.sendToAllTCP(chatMessage);
					updateNames();
				}
			}
		});
		server.bind(Network.port);
		server.start();

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

	void updateNames () {
		// Collect the names for each connection.
		Connection[] connections = server.getConnections();
		ArrayList names = new ArrayList(connections.length);
		for (int i = connections.length - 1; i >= 0; i--) {
			JeuConnection connection = (JeuConnection)connections[i];
			names.add(connection.name);
		}
		// Send the names to everyone.
		UpdateNames updateNames = new UpdateNames();
		updateNames.names = (String[])names.toArray(new String[names.size()]);
		server.sendToAllTCP(updateNames);
	}

	// This holds per connection state.
	static class JeuConnection extends Connection {
		public String name;
	}

	public static void main (String[] args) throws IOException {
		Log.set(Log.LEVEL_DEBUG);
		new JeuServer();
	}
}
