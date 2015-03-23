package uvinfo.bomberman;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class JeuClient  extends BasicGameState {		
	
	private GameContainer container;

	private Musique son;
	private Avatar perso;
	private AvatarLight NetPerso;
	private Barre life;
	private Map map;

	private ArrayList<Personnage> listePersos = new ArrayList<Personnage>();
	private ArrayList<Bomb> listeBombes = new ArrayList<Bomb>();
	
	float difficult = 1;
	
	private StateBasedGame game;

	public String pseudo;
	
	Client client;
	
	public JeuClient () {

		super();
	}
	
	public void launch(ArrayList<Personnage> persos) throws SlickException {
		

		Log.set(Log.LEVEL_DEBUG);
		
		AppGameContainer container = new AppGameContainer((Game) new JeuClient(), 704, 576, false);
		container.setAlwaysRender(true); 
		container.setShowFPS(false);//on affiche pas les FPS
		container.setTargetFrameRate(200);//on fixe le taux de rafraichissement a 200 pour ralentir le deplacement
		container.start();
		
		perso = new Avatar();
		
		this.perso = (Avatar)persos.get(0);//l'indice 0 est le joueur local
		
		perso.initAnimation();
		
		for(Personnage p : persos){
			((Avatar)p).initAnimation();
			listePersos.add((Avatar) p);
		}		
	
		play();
	}

	public void play() throws SlickException
	{
		client = new Client();
		client.start();

		NetPerso = new AvatarLight();
		
		Network.register(client);

		client.addListener(new Listener() {
			
			public void connected (Connection connection) {
				
				NetPerso.copy(perso, perso.getPseudo());
				
				client.sendTCP(NetPerso);
				
				if(perso.hasPutBomb()){
					BombLight bbl = new BombLight(listeBombes.get(0));
					client.sendTCP(bbl);
				}
				
			}

			public void received (Connection connection, Object object) {
				
				
				if (object instanceof AvatarLight) {
					
					AvatarLight avl = (AvatarLight)object;
					
					MAJJoueur(avl);
					
					return;					
				}
				
				if(object instanceof BombLight){
					BombLight bbl = (BombLight)object;
					listeBombes.get(0).copyLight(bbl);
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
			null, "Pseudo");
		if (input == null || input.trim().length() == 0) System.exit(1);
		pseudo = input.trim();
		perso.setPseudo(input.trim());

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
	
	
	
	
	/** GameContainer methode permettant d'initialiser le contenu du 
	 * du jeu , charger les graphismes, la musique etc.. 
	 */
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// initialisation des objets
		this.game = game;
		this.container = container;
		
		map = new Map();
		map.init();
		
		listeBombes.add(new Bomb());
		listeBombes.add(new SuperBomb());
		for(Bomb b : listeBombes) b.loadAnimations();
		
		life = new Barre(perso.getLife());
		life.init();
		
		NetPerso.copy(perso, pseudo);
		
		client.sendTCP(NetPerso);
		
	}

	/**render(GameContainer, Graphics) : doit afficher le 
	 * contenu du jeux
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// affichage de la map fond et l'avant
		map.renderBackground();
		map.renderForeground();
	
		this.stats(g);
		
		// animations des persos de la map
		for(Personnage p : listePersos)		
		{
			p.render();
		}
		
		
		// animations des bombes
		if(perso.hasPutBomb()){
			for(Bomb b : listeBombes){
				b.render();
			}
		}
		
		life.render(g);
	}


	/**update(GameContainer, int) : doit mettre à jour les élément de 
	 * la scène en fonction du delta temps qui est survenu. C’est ici que la 
	 * logique du jeux est renfermé.
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
		throws SlickException {
		
	     // gestion des collisions
		map.isCollision(perso.getFuturX(), perso.getFuturY(), perso);
		
		container.setTargetFrameRate((int) (200*difficult));
		
		// gestion des intéractions entre personnages
		perso.update(delta, container);

		
		life.update(perso.getLife());
		
		if(perso.hasPutBomb()){
			for(Bomb b : listeBombes){
				b.update(listePersos, delta);
			}
		}
		
		for(Personnage p : listePersos){
			p.update(delta, container);
		}	
		
		NetPerso.copy(perso, pseudo);
		//if(NetPerso.moving==true) System.out.println("yes"); //ça passe !
		//if(perso.isMoving()) System.out.println("yes");  // ca passe !
		//if(listePersos.get(0).isMoving()) System.out.println("yes");  // ca passe 
		client.sendTCP(NetPerso);
		
	}
		
	
	public void MAJJoueur(AvatarLight j)
	{		
		for(Personnage p : listePersos){
			if(p.getPseudo().equals(j.Pseudo)){
				((Avatar)p).MaJAvatar(j);
				break;
			}
		}
	}	

	@Override
	/** methode appelé à chaque relâchement de touche 
	 * 
	 * @param key 
	 * @param c
	 */
	public void keyReleased(int key, char c) {

		perso.SetMoving(false);
		
		NetPerso.copy(perso, pseudo);

		if (Input.KEY_ESCAPE == key) {
			game.enterState(MainScreenGameState.ID);
		}
	}

	@Override
	/** methode appelé à chaque appui sur une touche 
	 * 
	 * @param key 
	 * @param c
	 */
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
		}
		
		
		NetPerso.copy(perso, pseudo);
		
	}
	
	public void stats(Graphics g){
		g.setColor(Color.red);
		
		int i = 1;
		
		//for(String ps : listePseudoPersos)
		/*for(Personnage ps : listePersos)
		{
			g.drawString("pseudo" +i + " : " + ps.getPseudo(), 450, 20*i);// affichage vitesse
			i++;
		}*/
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}	
	
}
