package uvinfo.bomberman;

import org.newdawn.slick.SlickException;

public interface BombermanTransmissible {

	public void handleReception(NetworkGame ng) throws SlickException;
}
