package uvinfo.bomberman;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Barre {
	

	private int taille;
	private static final int P_BAR_X = 10;
	private static final int P_BAR_Y = 23;
	
	private static Image coeur_plein;
	private static Image coeur_vide;
	
	
	
	private ArrayList<Image> Barre = new ArrayList<Image>();

	public Barre(int taille)
	{
		this.taille = taille;
	}
	
	  public void init() throws SlickException {
		  
		  coeur_plein = new Image("sprites/coeur.png");
		  coeur_vide = new Image("sprites/coeurN.png");
		  
		  
		  for (int i = 0 ; i<taille;i++) {
			Barre.add(i, coeur_plein);
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
		  		  
		  for(int i = Barre.size()-1;i>=taille;i--)
		  {
			  Barre.set(i,coeur_vide);
		  }
		}

}
