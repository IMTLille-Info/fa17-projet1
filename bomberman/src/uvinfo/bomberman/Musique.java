package uvinfo.bomberman;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;



public class Musique {

//	 Map carte = new Map();
	
	/******* attributs ******/
	String FondS1 = "sound/bongos.ogg";
	
	/******* constructeurs *********/
	
	public Musique()
	{
	
	}
	/******** methodes 
	 * @throws SlickException *******/
	public void  FondSonore() throws SlickException
	{
		Music background = new Music(FondS1);
		background.loop();
	}
	
	
}

