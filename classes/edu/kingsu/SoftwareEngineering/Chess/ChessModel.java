package edu.kingsu.SoftwareEngineering.Chess;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ChessModel implements MoveListener {
	private Player white;
	private Player black;
	private static Board board;
	private ArrayList<Board> boardHistory;
	private int turnNumber;
	private GUI gui;
	private boolean isWhiteTurn;

	public ChessModel(Board paramBoard, Player paramPlayer1, Player paramPlayer2) {
		white = paramPlayer1;
		black = paramPlayer2;
		board = paramBoard;
		boardHistory = new ArrayList();
		boardHistory.add(new Board(board));
		isWhiteTurn = false;
		turnNumber = -1;
		gui = new GUI(isWhiteTurn);
		gui.addListener(this);
		gui.getGUIBoard().addListener(this);
		gui.getTextBoard().addListener(this);
		white.addListener(this);
		black.addListener(this);
		nextTurn();
	}

	public ChessModel(Player paramPlayer1, Player paramPlayer2) {
		this(new Board(), paramPlayer1, paramPlayer2);
	}

	public ChessModel(Scenario paramScenario) {
		white = paramScenario.getWhitePlayer();
		black = paramScenario.getBlackPlayer();
		board = paramScenario.getBoard();
		boardHistory = new ArrayList();
		boardHistory.add(new Board(board));
		isWhiteTurn = false;
		turnNumber = -1;
		gui = new GUI(isWhiteTurn);
		gui.addListener(this);
		gui.getGUIBoard().addListener(this);
		gui.getTextBoard().addListener(this);
		white.addListener(this);
		black.addListener(this);
		gui.update(isWhiteTurn, false);
		gui.showMessage(paramScenario.getIntroMessage(), "Scenario");
		nextTurn();
	}

	public ChessModel(PGNUtils paramPGNUtils) {
		white = paramPGNUtils.getPlayer(0);
		black = paramPGNUtils.getPlayer(1);
		board = new Board();
		boardHistory = new ArrayList();
		boardHistory.add(new Board(board));
		isWhiteTurn = false;
		turnNumber = -1;
		gui = new GUI(isWhiteTurn);
		gui.addListener(this);
		gui.getGUIBoard().addListener(this);
		gui.getTextBoard().addListener(this);
		white.addListener(this);
		black.addListener(this);

		for (int i = 0; i < paramPGNUtils.getMoves().getMoves().size(); i++) {
			board.movePiece(paramPGNUtils.getMoveArr()[i]);
			boardHistory.add(new Board(board));
			turnNumber += 1;
			isWhiteTurn = (!isWhiteTurn);
		}

		nextTurn();
	}


	public void addBoardToHistory(Board paramBoard, int paramInt) {
		if (boardHistory.size() > paramInt + 1) {
			System.out.println("Clearing boardHistory from " + (paramInt + 1) + " to " + boardHistory.size());
			boardHistory.subList(paramInt + 1, boardHistory.size()).clear();
		}

		boardHistory.add(new Board(paramBoard));
	}

	public void nextTurn() {
		System.out.println(board);
		isWhiteTurn = (!isWhiteTurn);
		turnNumber += 1;
		System.out.println("Turn number: " + turnNumber);
		System.out.println("Board history size: " + boardHistory.size());
		gui.update(isWhiteTurn, false);
		
		if (isWhiteTurn) {
			if (board.inStalemate(white)) {
				gui.askStalemate();
			}
			else if (board.inCheckmate(true)) {
				gui.askWinner(false);
			}
			else if (board.inCheck(true)) {
				gui.showMessage("White player is in check.", "Check!");
			}
		}
		else if (!isWhiteTurn) {
			if (board.inStalemate(black)) {
				gui.askStalemate();
			}
			else if (board.inCheckmate(false)) {
				gui.askWinner(true);
			}
			else if (board.inCheck(false)) {
				gui.showMessage("Black player is in check.", "Check!");
			}
		}

		MoveRunner localMoveRunner;
		Thread localThread;
		
		if ((isWhiteTurn) && (!white.isHumanPlayer())) {
			System.out.println("White AI");
			localMoveRunner = new MoveRunner(white, board);
			localThread = new Thread(localMoveRunner);
			localThread.start();
		}
		else if ((!isWhiteTurn) && (!black.isHumanPlayer())) {
			System.out.println("Black AI");
			localMoveRunner = new MoveRunner(black, board);
			localThread = new Thread(localMoveRunner);
			localThread.start();
		}
		else if ((isWhiteTurn) && (white.isHumanPlayer())) {
			System.out.println("White Human");
			gui.update(isWhiteTurn, true);
		}
		else if ((!isWhiteTurn) && (black.isHumanPlayer())) {
			System.out.println("Black Human");
			gui.update(isWhiteTurn, true);
		}
	}

	public void undo() {
		if ((turnNumber > 0) && (white.isHumanPlayer()) && (black.isHumanPlayer())) {
			board = new Board((Board)boardHistory.get(turnNumber - 1));
			turnNumber -= 2;
			nextTurn();
		}
		else if ((turnNumber > 1) && ((white.isHumanPlayer()) || (black.isHumanPlayer()))) {
			board = new Board((Board)boardHistory.get(turnNumber - 2));
			turnNumber -= 3;
			isWhiteTurn = (!isWhiteTurn);
			nextTurn();
		}
	}

	public void redo() {
		if ((boardHistory.size() > turnNumber + 1) && (white.isHumanPlayer()) && (black.isHumanPlayer())) {
			board = new Board((Board)boardHistory.get(turnNumber + 1));
			nextTurn();
		}
		else if ((boardHistory.size() > turnNumber + 2) && ((white.isHumanPlayer()) || (black.isHumanPlayer()))) {
			board = new Board((Board)boardHistory.get(turnNumber + 2));
			turnNumber += 1;
			isWhiteTurn = (!isWhiteTurn);
			nextTurn();
		}
	}

	public void undoAll() {
		board = new Board((Board)boardHistory.get(0));
		turnNumber = -1;
		isWhiteTurn = false;
		nextTurn();
	}

	public void redoAll() {
		board = (Board)boardHistory.get(boardHistory.size() - 1);
		turnNumber = (boardHistory.size() - 2);
		isWhiteTurn = false;
		if (turnNumber % 2 == 0) {
			isWhiteTurn = true;
		}
		nextTurn();
	}

	public void loadScenario() {}

	public static Board getBoard() {
		return board;
	}

	public void getInput(Move paramMove) {
		board.movePiece(paramMove);
		gui.update(isWhiteTurn, false);
		Location localLocation = board.checkPawnPromotion();
		
		if (localLocation != null) {
			ChessPiece localChessPiece = board.getPieceAtLocation(localLocation);
			if ((localChessPiece.getColour()) && (!white.isHumanPlayer())) {
				board.pawnPromotion(localLocation);
			}
			else if ((!localChessPiece.getColour()) && (!black.isHumanPlayer())) {
				board.pawnPromotion(localLocation);
			}
			else {
				for (int i = -1; i == -1; i = showPawnPromotionWindow(localChessPiece)) {}
				
				if (i == 0) {
					board.pawnPromotion(localLocation);
				}
				else if (i == 1) {
					board.pawnPromotion(new Rook(localChessPiece.getColour()), localLocation);
				}
				else if (i == 2) {
					board.pawnPromotion(new Bishop(localChessPiece.getColour()), localLocation);
				}
				else if (i == 3) {
					board.pawnPromotion(new Knight(localChessPiece.getColour()), localLocation);
				}
			}
		}
		addBoardToHistory(board, turnNumber);
		nextTurn();
	}

	public int showPawnPromotionWindow(ChessPiece paramChessPiece) {
		String str = "black";
		if (paramChessPiece.getColour()) {
			str = "white";
		}

		ImageIcon[] arrayOfImageIcon = { new ImageIcon("res/" + str + "Queen.png"), new ImageIcon("res/" + str + "Rook.png"), new ImageIcon("res/" + str + "Bishop.png"), new ImageIcon("res/" + str + "Knight.png") };
		int i = JOptionPane.showOptionDialog(null, "Choose a piece to promote the pawn to.", "You can promote your pawn!", -1, -1, null, arrayOfImageIcon, arrayOfImageIcon[0]);
		
		return i;
	}

	public void getUndo() {
		System.out.println("In undo");
		undo();
	}

	public void getRedo() {
		System.out.println("In redo");
		redo();
	}

	public void getUndoAll() {
		System.out.println("In undo all");
		undoAll();
	}

	public void getRedoAll() {
		System.out.println("In redo all");
		redoAll();
	}

	public void getHint() {
		System.out.println("In hint");
		Move localMove = white.getBestMove(isWhiteTurn, board, 4, Integer.MIN_VALUE, Integer.MAX_VALUE);
		board.setLastMove(localMove);
		gui.update(isWhiteTurn, true);
	}

	public void save() {
		System.out.println("Saving...");
	}

	public void load() {
		System.out.println("Loading...");
		PGNUtils localPGNUtils = new PGNUtils();
		localPGNUtils.loadPGN();
		white = localPGNUtils.getPlayer(0);
		black = localPGNUtils.getPlayer(1);
		board = new Board();
		boardHistory = new ArrayList();
		boardHistory.add(new Board(board));
		isWhiteTurn = false;
		turnNumber = -1;
		white.addListener(this);
		black.addListener(this);

		for (int i = 0; i < localPGNUtils.getMoves().getMoves().size(); i++) {
			board.movePiece(localPGNUtils.getMoveArr()[i]);
			boardHistory.add(new Board(board));
			turnNumber += 1;
			isWhiteTurn = (!isWhiteTurn);
		}

		nextTurn();
	}

}
