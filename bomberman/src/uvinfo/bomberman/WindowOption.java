package uvinfo.bomberman; 

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class WindowOption extends JFrame{
  private JPanel container = new JPanel();
  //private JButton bouton = new JButton("Mon bouton");
  private JRadioButton onePlayer = new JRadioButton("1 joueur");
  private JRadioButton twoPlayer = new JRadioButton("2 joueurs");
  private ButtonGroup boutonGroupe = new ButtonGroup();
  
  public WindowOption(){
    this.setTitle("Fenétre d'option du jeu BomberDragon");
    this.setSize(300, 150);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());
    JPanel panel = new JPanel();
    onePlayer.addActionListener(new StateListener());
    twoPlayer.addActionListener(new StateListener());
    boutonGroupe.add(onePlayer);
    boutonGroupe.add(twoPlayer);
    panel.add(onePlayer);
    panel.add(twoPlayer);
    container.add(panel, BorderLayout.NORTH);
    this.setContentPane(container);
    this.setVisible(true); 
  }  
  
  class StateListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	System.out.println("source : " + onePlayer.getText() + " - état : " + onePlayer.isSelected());
	    	System.out.println("source : " + twoPlayer.getText() + " - état : " + twoPlayer.isSelected());
	    }
	  }
  public static void main(String[] args){
	  WindowOption wo = new WindowOption();
  }
}