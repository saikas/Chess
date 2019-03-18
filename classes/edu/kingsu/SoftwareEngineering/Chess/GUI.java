package edu.kingsu.SoftwareEngineering.Chess;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI {
	private GUIBoard guiBoard;
	private TextBoard textBoard;
	private Move enteredMove;
	private ArrayList<MoveListener> listeners = new ArrayList();
	private JLabel turnLabel;
	
	public void addListener(MoveListener paramMoveListener) {
		System.out.println("Added listener to GUI");
		listeners.add(paramMoveListener);
	}
	
	public GUI(boolean paramBoolean) {
		JFrame localJFrame = new JFrame("ChessGame");
		localJFrame.setDefaultCloseOperation(0);
		localJFrame.addWindowListener(new GUI.1(this));
		JPanel localJPanel = new JPanel(new BorderLayout());
		localJFrame.add(localJPanel);
		guiBoard = new GUIBoard(paramBoolean);
		textBoard = new TextBoard(paramBoolean);
		localJPanel.add(guiBoard, "West");
		JMenuBar localJMenuBar = new JMenuBar();
		JMenu localJMenu1 = new JMenu("File");
		JMenu localJMenu2 = new JMenu("Edit");
		JMenu localJMenu3 = new JMenu("Help");
		
		localJMenu1.setMnemonic(70);
		localJMenu2.setMnemonic(69);
		localJMenu3.setMnemonic(72);
		localJMenuBar.add(localJMenu1);
		localJMenuBar.add(localJMenu2);
		localJMenuBar.add(localJMenu3);
		
		JMenuItem localJMenuItem1 = new JMenuItem("New Game", 78);
		localJMenuItem1.addActionListener(new GUI.2(this));
		localJMenu1.add(localJMenuItem1);
		
		JMenuItem localJMenuItem2 = new JMenuItem("Load", 76);
		localJMenuItem2.addActionListener(new GUI.3(this));
		localJMenu1.add(localJMenuItem2);
		
		JMenuItem localJMenuItem3 = new JMenuItem("Save", 83);
		localJMenuItem3.addActionListener(new GUI.4(this));
		localJMenu1.add(localJMenuItem3);
		
		JMenuItem localJMenuItem4 = new JMenuItem("Quit", 81);
		localJMenuItem4.addActionListener(new GUI.5(this));
		localJMenu1.add(localJMenuItem4);
		
		JMenuItem localJMenuItem5 = new JMenuItem("Undo All", 78);
		localJMenuItem5.addActionListener(new GUI.6(this));
		localJMenu2.add(localJMenuItem5);
		
		JMenuItem localJMenuItem6 = new JMenuItem("Undo", 85);
		localJMenuItem6.addActionListener(new GUI.7(this));
		localJMenu2.add(localJMenuItem6);
		
		JMenuItem localJMenuItem7 = new JMenuItem("Redo", 82);
		localJMenuItem7.addActionListener(new GUI.8(this));
		localJMenu2.add(localJMenuItem7);
		
		JMenuItem localJMenuItem8 = new JMenuItem("Redo All", 68);
		localJMenuItem8.addActionListener(new GUI.9(this));
		localJMenu2.add(localJMenuItem8);
		
		JMenuItem localJMenuItem9 = new JMenuItem("About", 65);
		localJMenuItem9.addActionListener(new GUI.10(this));
		localJMenu3.add(localJMenuItem9);
		localJFrame.setJMenuBar(localJMenuBar);
		
		Box localBox1 = Box.createVerticalBox();
		localJPanel.add(localBox1, "East");
		localBox1.add(textBoard);
		JLabel localJLabel1 = new JLabel(" ");
		localBox1.add(localJLabel1);
		turnLabel = new JLabel("");
		
		if (paramBoolean) {
			turnLabel.setText("WHITE's turn.");
		}
		else {
			turnLabel.setText("BLACK's turn.");
		}
		
		localBox1.add(turnLabel);
		JLabel localJLabel2 = new JLabel(" ");
		localBox1.add(localJLabel2);
		
		Box localBox2 = Box.createHorizontalBox();
		localBox1.add(localBox2);
		
		JButton localJButton1 = new JButton("<<");
		localJButton1.addActionListener(new GUI.11(this));
		localBox2.add(localJButton1);
		
		JButton localJButton2 = new JButton("<");
		localJButton2.addActionListener(new GUI.12(this));
		localBox2.add(localJButton2);
		
		JButton localJButton3 = new JButton(">");
		localJButton3.addActionListener(new GUI.13(this));
		localBox2.add(localJButton3);
		
		JButton localJButton4 = new JButton(">>");
		localJButton4.addActionListener(new GUI.14(this));
		localBox2.add(localJButton4);
		
		Box localBox3 = Box.createHorizontalBox();
		localBox1.add(localBox3);
		
		JButton localJButton5 = new JButton("Hint!");
		localJButton5.setPreferredSize(new Dimension(100, 50));
		localJButton5.addActionListener(new GUI.15(this));
		localBox3.add(localJButton5);
		localJFrame.pack();
		localJFrame.setResizable(false);
		localJFrame.setVisible(true);
	}
	
	public void update(boolean paramBoolean1, boolean paramBoolean2) {
		guiBoard.update(paramBoolean1, paramBoolean2);
		textBoard.update(paramBoolean1, paramBoolean2);
		if (paramBoolean1) {
			turnLabel.setText("WHITE's turn.");
		}
		else {
			turnLabel.setText("BLACK's turn.");
		}
	}
	
	public void showMessage(String paramString1, String paramString2) {
		JOptionPane.showMessageDialog(null, paramString1, paramString2, -1);
	}
	
	public void askWinner(boolean paramBoolean) {
		String str = "white";

		if (!paramBoolean) {
			str = "black";
		}
		if (JOptionPane.showConfirmDialog(null, "Congratulations " + str + "!\n\nDo you want to play again?", "YAY!", 0) == 0) {
			relaunchGame();
		}
		else {
			System.exit(0);
		}
	}
	
	public void askStalemate() {
		if (JOptionPane.showConfirmDialog(null, "Game is in stalemate!\n\nDo you want to play again?", "Stalemate!", 0) == 0) {
			relaunchGame();
		}
		else {
			System.exit(0);
		}
	}
	
	public GUIBoard getGUIBoard() {
		return guiBoard;
	}
	
	public TextBoard getTextBoard() {
		return textBoard;
	}
	
	private void relaunchGame() {
		try {
			String str = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
			File localFile = new File(ChessGame.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			
			if (!localFile.getName().endsWith(".jar")) {
				return;
			}
			
			ArrayList localArrayList = new ArrayList();
			localArrayList.add(str);
			localArrayList.add("-jar");
			localArrayList.add(localFile.getPath());
			
			ProcessBuilder localProcessBuilder = new ProcessBuilder(localArrayList);
			localProcessBuilder.start();
			System.exit(0);
		}
		catch (Exception localException) {
			System.out.println("Error when restarting program!");
			localException.printStackTrace();
			System.exit(-1);
		}
	}

}
