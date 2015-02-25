package uvinfo.bomberman;
import org.newdawn.slick.Music;
import org.newdawn.slick.MusicListener;
import org.newdawn.slick.SlickException;



public class Musique {

//	 Map carte = new Map();
	
	/******* attributs ******/
	String FondS1 = "sound/theme.ogg";
	String FondBombe = "sound/bongos.ogg";
	
	/******* constructeurs *********/
	public Musique()
	{
	}
	/******** methodes 
	 * @throws SlickException *******/
	public void  FondSonore() throws SlickException // gestion en fonction de la map, création de la class Map..
	{
		Music background = new Music(FondS1);
		background.loop();
	}
	public void ExplosionBombe() throws SlickException // son explosion bombe différente petite, grande
	{	
			Music Bombe = new Music(FondBombe);
			Bombe.play();
	}
}

