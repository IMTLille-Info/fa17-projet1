package uvinfo.bomberman;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Barre {
	

	private int taille;
	private static final int P_BAR_X = 470;
	private static final int P_BAR_Y = 23;
	
	private ArrayList<Image> Barre = new ArrayList<Image>();

	public Barre(int taille)
	{
		this.taille = taille;
	}
	
	  public void init() throws SlickException {
		  
		  for (int i = 0 ; i<taille;i++) {
			Barre.add(i, new Image("sprites/coeur.png"));
		}
	  }	    

	  public void render(Graphics g) {

		  int i = 0;		  
		  for (Image image : Barre) {
			  g.drawImage(image, P_BAR_X + (image.getWidth()+1)*i , P_BAR_Y);		
			  i++;
		}	    
	  }
	  
	  public void update(int taille){
		  
		  while(taille<Barre.size() && taille > 0)
		  {
			  int i = 1;
			  Barre.remove(Barre.size()-i);
			  i++;
		  }
		}

}
