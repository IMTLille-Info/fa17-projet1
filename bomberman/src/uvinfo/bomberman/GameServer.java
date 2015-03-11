package uvinfo.bomberman;

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

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
				
				if(object instanceof Avatar){
					server.sendToAllTCP(object);
				}
			}
		});
	}
	
	public static void main (String[] args) throws IOException, SlickException {
		Log.set(Log.LEVEL_DEBUG);
		new GameServer();
	}

}
