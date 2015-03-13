package uvinfo.bomberman;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network {
	static public final int port = 59665;

	// This registers objects that are going to be sent over the network.
	static public void register (EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(Bomb.class);
		kryo.register(Avatar.class);
		kryo.register(AvatarLight.class);		
		kryo.register(Monstre.class);
		kryo.register(MapGameState.class);
		kryo.register(Map.class);
		kryo.register(MainScreenGameState.class);
		kryo.register(Musique.class);
		kryo.register(Personnage.class);
		kryo.register(Position.class);
		kryo.register(StateGame.class);
		kryo.register(SuperBomb.class);
		kryo.register(org.newdawn.slick.Animation.class);
		kryo.register(java.util.ArrayList.class);
		kryo.register(int[][].class);
		kryo.register(org.newdawn.slick.Animation[].class);		
	}
	
}
