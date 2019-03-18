package edu.kingsu.SoftwareEngineering.Chess;

import java.awt.BorderLayout;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.Document;

public class TextBoard extends JPanel {
	private ArrayList<MoveListener> listeners = new ArrayList();
	private String enteredMove;
	private boolean isWhiteTurn;
	private boolean acceptInput;
	private JTextArea log;
	private JTextField moveField;
	
	public void addListener(MoveListener paramMoveListener) {
		System.out.println("Added listener to TextBoard");
		listeners.add(paramMoveListener);
	}
	
	public TextBoard(boolean paramBoolean) {
		super(new BorderLayout());
		isWhiteTurn = paramBoolean;
		log = new JTextArea("", 50, 10);
		log.setEditable(false);
		log.setText(ChessModel.getBoard().getMoveList().toString());
		JScrollPane localJScrollPane = new JScrollPane(log);
		add(localJScrollPane, "North");
		moveField = new JTextField("Enter move here");
		moveField.addActionListener(new TextBoard.1(this));
		moveField.addFocusListener(new TextBoard.2(this));
		add(moveField, "South");
	}
	
	public void update(boolean paramBoolean1, boolean paramBoolean2) {
		isWhiteTurn = paramBoolean1;
		acceptInput = paramBoolean2;
		log.setText(ChessModel.getBoard().getMoveList().toString());
		log.setCaretPosition(log.getDocument().getLength());
		moveField.setText("");
	}
	
	private boolean getIsWhiteTurn() {
		return isWhiteTurn;
	}
}