package uvinfo.bomberman;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class WindowOption extends JFrame {
	private JPanel container = new JPanel();
	// private JButton bouton = new JButton("Mon bouton");
	public JRadioButton onePlayer = new JRadioButton("1 joueur");
	public JRadioButton twoPlayer = new JRadioButton("2 joueurs");
	public ButtonGroup boutonGroupePlayer = new ButtonGroup();
	
	public JRadioButton numbersBomb3 = new JRadioButton("3 Bombes");
	public JRadioButton numbersBomb5 = new JRadioButton("5 Bombes");
	public JRadioButton numbersBomb8 = new JRadioButton("8 Bombes");
	public ButtonGroup boutonGroupeBomb = new ButtonGroup();


	public WindowOption() {
		this.setTitle("Fenétre d'option du jeu BomberDragon");
		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		JPanel panelNorth = new JPanel();
		onePlayer.addActionListener(new StateListener());
		twoPlayer.addActionListener(new StateListener());
		boutonGroupePlayer.add(onePlayer);
		boutonGroupePlayer.add(twoPlayer);
		panelNorth.add(onePlayer);
		panelNorth.add(twoPlayer);
		container.add(panelNorth, BorderLayout.NORTH);
		
		JPanel panelCenter = new JPanel();
		numbersBomb3.addActionListener(new StateListener());
		numbersBomb5.addActionListener(new StateListener());
		numbersBomb8.addActionListener(new StateListener());
		boutonGroupeBomb.add(numbersBomb3);
		boutonGroupeBomb.add(numbersBomb5);
		boutonGroupeBomb.add(numbersBomb8);
		panelCenter.add(numbersBomb3);
		panelCenter.add(numbersBomb5);
		panelCenter.add(numbersBomb8);
		container.add(panelCenter, BorderLayout.CENTER);
		
		this.setContentPane(container);
		this.setVisible(true);
	}

	public JRadioButton getOnePlayer() {
		return onePlayer;
	}

	public JRadioButton getTwoPlayer() {
		return twoPlayer;
	}

	public void numbersPlayer(Graphics g) {
		Font font = new Font("Courier", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.red);
		g.drawString("Nombres de joueurs ? ", 10, 20);
	}
	
	public static void main(String[] args){
	  	  WindowOption wo = new WindowOption();
	    }
	public class StateListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	System.out.println("source : " + onePlayer.getText() + " - état : " + onePlayer.isSelected());
	    	System.out.println("source : " + twoPlayer.getText() + " - état : " + twoPlayer.isSelected());
	    	
	    }
	    
	  }

}