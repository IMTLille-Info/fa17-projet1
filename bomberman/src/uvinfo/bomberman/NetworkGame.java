package uvinfo.bomberman;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class NetworkGame extends BasicGameState {
	public static final int ID = 2;
	// déclaration des autres objets
	private GameContainer container;

	private Musique son;
	private Avatar perso;
	private AvatarLight NetPerso;
	private Barre life;
	private Map map;

	private ArrayList<String> listePseudoPersos = new ArrayList<String>();//temporaire
	private ArrayList<Personnage> listePersos = new ArrayList<Personnage>();
	private ArrayList<Bomb> listeBombes = new ArrayList<Bomb>();
	
	private Bomb bomb;
	private SuperBomb superBomb;
	
	float difficult = 1;
	
	private StateBasedGame game;

	public String pseudo;
	
	Client client;
	
	public NetworkGame() throws SlickException {
		
		/**** pour test serveur ****/
		//this.bomb = new Bomb();		
		
		client = new Client();
		client.start();

		NetPerso = new AvatarLight();
		
		
		perso = new Avatar();		
		perso.initAnimation();
		
		// For consistency, the classes to be sent over the network are
		// registered by the same method for both the client and server.
		Network.register(client);

		client.addListener(new Listener() {
			
			public void connected (Connection connection) {
				
				
				NetPerso.Pseudo = pseudo;
				
				NetPerso.posX = perso.posX();
				NetPerso.posY = perso.posY();

				NetPerso.direction = perso.GetDirection();
				NetPerso.PointDeVie = perso.getLife();				
				
				client.sendTCP(NetPerso);
				
			}

			public void received (Connection connection, Object object) {
				
				
				if (object instanceof AvatarLight) {
					
					if(listePseudoPersos.contains(((AvatarLight) object).Pseudo))
					{
						listePersos.get(listePseudoPersos.indexOf(pseudo)).posX(((AvatarLight) object).posX);//modification du personnage
						listePersos.get(listePseudoPersos.indexOf(pseudo)).posY(((AvatarLight) object).posY);
						listePersos.get(listePseudoPersos.indexOf(pseudo)).SetDirection(((AvatarLight) object).direction);
					}
					else
					{
					
					Avatar newJoueur = new Avatar();
					
					newJoueur.posX(((AvatarLight) object).posX);
					newJoueur.posY(((AvatarLight) object).posY);

					newJoueur.SetDirection(((AvatarLight)object).direction);
					newJoueur.setLife((((AvatarLight) object).PointDeVie));
					
					try {
						newJoueur.initAnimation();
					} catch (SlickException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					AddJoueur(newJoueur,((AvatarLight)object).Pseudo);
					}

					return;					
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

		// We'll do the connect on a new thread so the ChatFrame can show a progress bar.
		// Connecting to localhost is usually so fast you won't see the progress bar.
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
	
	
	@Override
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
		
		perso = new Avatar();
		perso.initAnimation();
		
		listeBombes.add(new Bomb());
		listeBombes.add(new SuperBomb());
		for(Bomb b : listeBombes) b.loadAnimations();
		
		life = new Barre(perso.getLife());
		life.init();
		
		
		NetPerso.posX = perso.posX();
		NetPerso.posY = perso.posY();		
		
		client.sendTCP(NetPerso);
		
		AddJoueur(perso, pseudo);

	}

	@Override
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

	@Override
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
		
		MAJAvatarlight();
		client.sendTCP(NetPerso);
	}
	
	public void AddJoueur(Avatar pers, String pseudo)
	{			
		if(listePseudoPersos.contains(pseudo))
		{
			listePersos.set(listePseudoPersos.indexOf(pseudo), pers);//modification du personnage
		}
		else
		{
			listePersos.add(pers);//ajout du personnage
			listePseudoPersos.add(pseudo);
		}
	}
	
	
	
	public void afficheMessage(String mess)
	{
	
		JFrame frame = new JFrame("Connexion");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
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
		
		JLabel info = new JLabel(mess);

		c.add(info);		

		frame.setVisible(true);		
		
	}
	

	@Override
	/** methode appelé à chaque relâchement de touche 
	 * 
	 * @param key 
	 * @param c
	 */
	public void keyReleased(int key, char c) {

		perso.SetMoving(false);

		MAJAvatarlight();	
		client.sendTCP(NetPerso);

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
		
		MAJAvatarlight();
		
		client.sendTCP(NetPerso);
	}

	
	public void MAJAvatarlight()
	{						
		NetPerso.posX = perso.posX();
		NetPerso.posY = perso.posY();
	
		NetPerso.direction = perso.GetDirection();
		NetPerso.moving = true;
	}
	
	@Override
	public int getID() {
		return ID;
	}
	
	public void stats(Graphics g){
		g.setColor(Color.red);
		
		int i = 1;
		
		for(String ps : listePseudoPersos)
		{
			g.drawString("pseudo" +i + " : " + ps, 450, 20*i);// affichage vitesse
			i++;
		}
	}
}
