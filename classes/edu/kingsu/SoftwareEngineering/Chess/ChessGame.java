package edu.kingsu.SoftwareEngineering.Chess;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class ChessGame {
	private static JComboBox<String> player1Box;
	private static JComboBox<String> player2Box;
	private static String[] playerTypes;
	private static String[] tutorialList;
	private static JFrame frame;
	private static Player player1;
	private static Player player2;
	private static PGNUtils pgn;
	private static Scenario scenario;
	
	public ChessGame() {}
	
	public static void main(String[] paramArrayOfString) {
		scenario = new Scenario_Tutorial1();
		player1 = new HumanPlayer(true);
		player2 = new HumanPlayer(false);
		frame = new JFrame("Chess v1.0");
		frame.setPreferredSize(new Dimension(400, 250));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(3);

		JPanel localJPanel = new JPanel(new GridBagLayout());
		frame.add(localJPanel);
		frame.getContentPane().add(localJPanel, "North");
		
		GridBagConstraints localGridBagConstraints = new GridBagConstraints();
		insets = new Insets(10, 10, 10, 10);
		
		JButton localJButton1 = createButton("About");
		gridx = 0;
		gridy = 0;
		localJPanel.add(localJButton1, localGridBagConstraints);
		
		JButton localJButton2 = createButton("Instructions");
		gridx = 1;
		gridy = 0;
		localJPanel.add(localJButton2, localGridBagConstraints);
		
		JLabel localJLabel1 = new JLabel("Player 1:");
		gridx = 0;
		gridy = 1;
		localJPanel.add(localJLabel1, localGridBagConstraints);
		
		JLabel localJLabel2 = new JLabel("Player 2:");
		gridx = 0;
		gridy = 2;
		localJPanel.add(localJLabel2, localGridBagConstraints);
		playerTypes = new String[] { "Human", "AI (Very Easy)", "AI (Easy)", "AI (Normal)", "AI (Hard)" };
		player1Box = new JComboBox(playerTypes);
		player1Box.setSelectedIndex(0);
		player1Box.setActionCommand("player1");
		gridx = 1;
		gridy = 1;
		localJPanel.add(player1Box, localGridBagConstraints);
		player2Box = new JComboBox(playerTypes);
		player2Box.setSelectedIndex(0);
		player2Box.setActionCommand("player2");
		gridx = 1;
		gridy = 2;
		localJPanel.add(player2Box, localGridBagConstraints);
		insets = new Insets(10, 10, 10, 10);
		
		JButton localJButton3 = createButton("Start New Game");
		gridx = 0;
		gridy = 4;
		localJPanel.add(localJButton3, localGridBagConstraints);
		
		JButton localJButton4 = createButton("Load");
		gridx = 1;
		gridy = 4;
		localJPanel.add(localJButton4, localGridBagConstraints);
		localJPanel.add(new JSeparator(0), localGridBagConstraints);
		tutorialList = new String[] { "Tutorial 1", "Tutorial 2", "Tutorial 3", "Tutorial 4", "Tutorial 5" };
		
		JComboBox localJComboBox = new JComboBox(tutorialList);
		localJComboBox.setSelectedIndex(0);
		localJComboBox.setActionCommand("tutorial");
		gridx = 0;
		gridy = 5;
		localJPanel.add(localJComboBox, localGridBagConstraints);
		
		JButton localJButton5 = createButton("Play Scenario");
		gridx = 1;
		gridy = 5;
		localJPanel.add(localJButton5, localGridBagConstraints);
		
		ChessGame.ComboBoxListener localComboBoxListener = new ChessGame.ComboBoxListener();
		player1Box.addActionListener(localComboBoxListener);
		player2Box.addActionListener(localComboBoxListener);
		localJComboBox.addActionListener(localComboBoxListener);
		frame.pack();
	}
	
	public static JButton createButton(String paramString) {
		JButton localJButton = new JButton(paramString);
		localJButton.addActionListener(new ChessGame.1());
		return localJButton;
	}
	
	public static Player getPlayer(int paramInt) {
		if (paramInt == 0) {
			return player1;
		}
		return player2;
	}
	
	public static String[] getPlayerTypes() {
		return playerTypes;
	}
	
	public static void setPlayer(int paramInt, Player paramPlayer) {
		if (paramInt == 0) {
			player1 = paramPlayer;
		}
		else {
			player2 = paramPlayer;
		}
	}
	

	public static Player assignPlayer(boolean paramBoolean, int paramInt) {
		if (paramInt == 0) {
			return new HumanPlayer(paramBoolean);
		}
		return new AIPlayer(paramBoolean, paramInt);
	}
	
	public static void setScenario(int paramInt) {
		switch (paramInt) {
		case 0: 
			scenario = new Scenario_Tutorial1();
			break;
		case 1: 
			scenario = new Scenario_Tutorial2();
			break;
		case 2: 
			scenario = new Scenario_Tutorial3();
			break;
		case 3: 
			scenario = new Scenario_Tutorial4();
			break;
		case 4: 
			scenario = new Scenario_Tutorial5();
			break;
		}
	}
	

	public static Scenario getScenario() {
		return scenario;
	}
	
	public static PGNUtils getPGN() {
		return pgn;
	}

}
