package uvinfo.bomberman;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.newdawn.slick.SlickException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class PageAttente extends JFrame {
	
	Avatar perso;
	
	Client client;
	
	private ArrayList<Personnage> listePersos = new ArrayList<Personnage>();
		
	private static final long serialVersionUID = 1L;
	
	private ModeleDynamiqueObjet  modele = new ModeleDynamiqueObjet();
	
	    private JTable tableau;
	 
	    public PageAttente() {
	        super();
	        	        
	        client = new Client();
			client.start();		
			
			perso = new Avatar();

	        RecupInfos();
	        
	        LaunchNetwork();
	        
	        
	        setTitle("Attente de connexion des joueurs");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        modele.addjoueur(listePersos.get(listePersos.size()-1).getPseudo());	                
	        	        
	        tableau = new JTable(modele);	        
		   	 
	        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
	 
	        JPanel boutons = new JPanel();
	 
	        boutons.add(new JButton(new AddAction()));
	 
	        getContentPane().add(boutons, BorderLayout.SOUTH);
	 
	        pack(); 
	        
	    }
	    
	    
	    
	    private void MajTab() {
	    	
	    	
	        
	        //modele.fireTableDataChanged();
	        			
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

	        }
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
	    			
	    			MajTab();
	    		}

	    		public void received (Connection connection, Object object) {	    			
	    			
	    			if (object instanceof AvatarLight) {
	    				
	    				AvatarLight avl = (AvatarLight)object;
	    				Avatar newJoueur = new Avatar(avl);
	    				
	    				try {
	    					newJoueur.initAnimation();
	    				} catch (SlickException e) {
	    					e.printStackTrace();
	    				}

	    				AddJoueur(newJoueur,avl.Pseudo);	    				
	    					
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
	    
	    public void AddJoueur(Avatar pers, String pseudo)
		{		
			boolean find = false;
			int index = 0;
			
			for(Personnage p : listePersos){
				if(p.getPseudo() == pseudo){
					index = listePersos.indexOf(p);
					find = true;
					break;
				}
			}			
			
			if(find){
				listePersos.set(index, pers);
			}else{
				listePersos.add(pers);
				javax.swing.JOptionPane.showMessageDialog(null,listePersos.get(listePersos.size()-1).getPseudo() +  " vient de se connecter"); 
				modele.addjoueur(listePersos.get(listePersos.size()-1).getPseudo());
				MajTab();  
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


