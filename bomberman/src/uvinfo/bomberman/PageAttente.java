package uvinfo.bomberman;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.newdawn.slick.SlickException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.sun.xml.internal.ws.api.pipe.ClientTubeAssemblerContext;

public class PageAttente extends JFrame {
	
	Avatar perso;
	
	Client client;
	
	private ArrayList<Personnage> listePersos = new ArrayList<Personnage>();
	
	private ArrayList<AvatarLight> listePersosLight = new ArrayList<AvatarLight>();
		
	private static final long serialVersionUID = 1L;
	
	 
	    public PageAttente() {
	        super();	        
	        
	        
	        this.setSize(200, 320);
	        this.setLocationRelativeTo(null);
	        
	        client = new Client();
			client.start();					
			perso = new Avatar();
			
			LaunchNetwork();
	        RecupInfos();	         
	        
	        setTitle("Attente de connexion des joueurs");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	                        
	        	        	 
	        JPanel boutons = new JPanel();
	 
	        boutons.add(new JButton(new AddAction()));
	 
	        getContentPane().add(boutons, BorderLayout.SOUTH);
	        
	        AffichageNewJoueur("Joueurs connectés : ");
	        
	       pack(); 
	        
	    }


		private class AddAction extends AbstractAction {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			private AddAction() {
	            super("Valider");
	        }
	 
	        public void actionPerformed(ActionEvent e) {

	        	ChangementFenetre();        	
	        }
	    }
	    
		public void ChangementFenetre()
		{
			JeuClient jeu = new JeuClient();
        	
        	try {
				jeu.launch(listePersos);
			} catch (SlickException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
        	
        	this.setVisible(false);
		}
	 
	    public static void main(String[] args) {
	        new PageAttente().setVisible(true);
	        
	    }
	    
	    
	    public void LaunchNetwork()
	    {	    	
	    	Network.register(client);

	    	client.addListener(new Listener() {
	    		
	    		public void connected (Connection connection) {
	    				    		
	    			AvatarLight avl = new AvatarLight();
	    			
	    			avl.copy(perso, perso.getPseudo());
	    			
	    			client.sendTCP(avl);// envoi du perso local	
	    			
	    			AddJoueur(avl);
	    			
	    		}

	    		public void received (Connection connection, Object object) {	    			
	    			
	    			if (object instanceof AvatarLight) {
	    				
	    				AvatarLight avl = (AvatarLight)object;	  

	    				AddJoueur(avl);
	    				
	    				/*Avatar newJoueur = new Avatar(avl);

	    				AddJoueur(newJoueur,avl.Pseudo);
	    				
	    				try 
	    				{
	    					newJoueur.initAnimation();
	    				}
	    				catch (SlickException e)
	    				{
	    					e.printStackTrace();
	    				} 				
	    					*/
	    				
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
	    	
	    }
	    
	    public void AffichageNewJoueur(String pseudo)
	    {	 

	    	Container c = this.getContentPane();

	    	JLabel info = new JLabel(pseudo);
	    	c.add(info);

	    	this.pack();
	    	
	    }
	    
	    public void AddJoueur(AvatarLight joueur)
		{		
			boolean find = false;
			
			for(AvatarLight p : listePersosLight){
				if(p.Pseudo.equals(joueur.Pseudo)){
					find = true;
					break;
				}
			}			
			
			if(!find)
			{
				listePersosLight.add(joueur);
				AffichageNewJoueur(joueur.Pseudo);
				client.sendTCP(listePersosLight.get(0));//je dis au ptit nouveau que je suis la
				javax.swing.JOptionPane.showMessageDialog(null,listePersos.get(listePersos.size()-1).getPseudo() +  " vient de se connecter");
			}			
		}
	    
	    
	    private void RecupInfos()
	    {
	    	
	    	// Request the host from the user.
			String input = (String)JOptionPane.showInputDialog(null, "Host:", "Connect to chat server", JOptionPane.QUESTION_MESSAGE,
				null, null, "localhost");
			if (input == null || input.trim().length() == 0) System.exit(1);
			final String host = input.trim();

			// Request the user's name.
			input = (String)JOptionPane.showInputDialog(null, "Name:", "Connect to chat server", JOptionPane.QUESTION_MESSAGE, null,
				null, "Pseudo");
			if (input == null || input.trim().length() == 0) System.exit(1);
			
			perso.setPseudo(input.trim());

			listePersos.add(perso);
						
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
	     
}


