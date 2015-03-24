package uvinfo.bomberman;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;

public class NetworkGame extends BasicGameState {
	public static final int ID = 2;
	// déclaration des autres objets
	private GameContainer container;
	private Musique son;
	private Avatar perso;
	private AvatarLight NetPerso;
	private Barre life;
	private Map map;
	private BombLight bbl;

	private HashMap<String, Personnage> listePersos = new HashMap<String, Personnage>();
	private HashMap<String, Bomb> listeBombes = new HashMap<String, Bomb>();
	private HashMap<String, SuperBomb> listeSuperBombes = new HashMap<String, SuperBomb>();
	//private ArrayList<Bomb> listeBombes = new ArrayList<Bomb>();
	
	public HashMap<String, Bomb> getListeBombes() {
		return listeBombes;
	}

	float difficult = 1;
	
	private StateBasedGame game;

	public String pseudo;
	
	Client client;
	
	public NetworkGame() throws SlickException {	
		
		client = new Client();
		client.start();

		NetPerso = new AvatarLight();
		bbl = new BombLight();
		
		perso = new Avatar();		
		perso.initAnimation();
		
		Network.register(client);

		client.addListener(new Listener() {
			
			public void connected (Connection connection) {
				NetPerso.copy(perso, pseudo);
				client.sendTCP(NetPerso);
				
			}

			public void received (Connection connection, Object object) {
				if(object instanceof BombermanTransmissible){
					try {
						((BombermanTransmissible)object).handleReception(NetworkGame.this);
					} catch (SlickException e) {
						e.printStackTrace();
					}
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
		
		/*listeBombes.add(new Bomb());
		listeBombes.add(new SuperBomb());
		for(Bomb b : listeBombes) b.loadAnimations();*/
		
		Bomb bb = new Bomb();
		bb.loadAnimations();
		SuperBomb spb = new SuperBomb();
		spb.loadAnimations();
		
		life = new Barre(perso.getLife());
		life.init();
		
		NetPerso.copy(perso, pseudo);
		
		client.sendTCP(NetPerso);
		
		AddJoueur(perso, pseudo);
		addBomb(bb, pseudo);
		addSuperBomb(spb, pseudo);

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
		for(String p : listePersos.keySet())		
		{
			listePersos.get(p).render();
		}
		
		// animations des bombes
		/*if(perso.hasPutBomb()){
			for(Bomb b : listeBombes){
				b.render();
			}
		}*/
		
		for(String s : listeBombes.keySet()){
			listeBombes.get(s).render();
		}
		
		for(String s : listeSuperBombes.keySet()){
			listeSuperBombes.get(s).render();
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
		
		/*if(perso.hasPutBomb()){
			for(Bomb b : listeBombes){
				b.update(listePersos, delta);
			}
		}*/
		
		for(String s : listeBombes.keySet()){
			listeBombes.get(s).update(listePersos, delta);
		}
		
		for(String s : listeSuperBombes.keySet()){
			listeSuperBombes.get(s).update(listePersos, delta);
		}
		
		for(String p : listePersos.keySet()){
			listePersos.get(p).update(delta, container);
		}	
		
		NetPerso.copy(perso, pseudo); 
		client.sendTCP(NetPerso);
		
		
	}
	
	public void AddJoueur(Avatar pers, String pseudo) throws SlickException{			
		if(listePersos.containsKey(pseudo)){
			listePersos.get(pseudo).copy(pers);
		}else{
			pers.initAnimation();
			listePersos.put(pseudo, pers);	
		}
	}
	
	public void addBomb(Bomb bomb, String ps) throws SlickException{
		if(listeBombes.containsKey(ps)){
			listeBombes.get(ps).copy(bomb);
		}else{
			bomb.loadAnimations();
			listeBombes.put(ps,bomb);
		}
	}
	
	private void addSuperBomb(SuperBomb spBomb, String ps) throws SlickException {
		if(listeSuperBombes.containsKey(ps)){
			listeSuperBombes.get(ps).copy(spBomb);
		}else{
			spBomb.loadAnimations();
			listeSuperBombes.put(ps,spBomb);
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
			perso.putBomb(this.listeBombes.get(pseudo));
			this.bbl.copy(listeBombes.get(pseudo), pseudo);
			client.sendTCP(bbl);
			break;
		case Input.KEY_ENTER:
			perso.putSuperBomb(this.listeSuperBombes.get(pseudo));
			client.sendTCP(new BombLight(listeBombes.get(pseudo), pseudo));
			break;
		}
		
		
		NetPerso.copy(perso, pseudo);
		
	}

	
	@Override
	public int getID() {
		return ID;
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
}
