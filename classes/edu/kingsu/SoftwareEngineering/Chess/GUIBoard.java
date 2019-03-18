package edu.kingsu.SoftwareEngineering.Chess;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIBoard extends JPanel implements ActionListener {
	
	private Location startPosition = null;
	private ChessBoardButton[][] buttons;
	private BufferedImage[] images;
	private ArrayList<MoveListener> listeners = new ArrayList();
	private ArrayList<Move> validMoves;
	private boolean isWhiteTurn;
	private boolean acceptInput;

	public void addListener(MoveListener paramMoveListener) {
		System.out.println("Added listener to GUIBoard");
		listeners.add(paramMoveListener);
	}

	public GUIBoard(boolean paramBoolean) {
		super(new GridLayout(9, 9));
		
		try {
			images = new BufferedImage[] { ImageIO.read(new File("res/whiteKing.png")), ImageIO.read(new File("res/whiteQueen.png")), ImageIO.read(new File("res/whiteBishop.png")), ImageIO.read(new File("res/whiteKnight.png")), ImageIO.read(new File("res/whiteRook.png")), ImageIO.read(new File("res/whitePawn.png")), ImageIO.read(new File("res/blackKing.png")), ImageIO.read(new File("res/blackQueen.png")), ImageIO.read(new File("res/blackBishop.png")), ImageIO.read(new File("res/blackKnight.png")), ImageIO.read(new File("res/blackRook.png")), ImageIO.read(new File("res/blackPawn.png")) };
		}
		catch (Exception localException) {
			System.out.println("There was a problem loading the images!");
			localException.printStackTrace();
		}

		buttons = new ChessBoardButton[8][8];
		String[] arrayOfString = { "A", "B", "C", "D", "E", "F", "G", "H" };
		JLabel localJLabel;
		
		for (int i = 7; i >= 0; i--) {
			localJLabel = new JLabel("   " + (i + 1));
			localJLabel.setFont(new Font("Serif", 0, 40));
			add(localJLabel);

			for (int j = 0; j < 8; j++) {
				if (i % 2 == 0) {
					buttons[i][j] = new ChessBoardButton(100, 100, (j + 1) % 2 == 0, null);
				}
				else {
					buttons[i][j] = new ChessBoardButton(100, 100, j % 2 == 0, null);
				}

				buttons[i][j].addActionListener(this);
				add(buttons[i][j]);
			}//for j
		}//for i

		add(new JLabel(""));
		
		for (i = 0; i < arrayOfString.length; i++) {
			localJLabel = new JLabel("   " + arrayOfString[i]);
			localJLabel.setFont(new Font("Serif", 0, 40));
			add(localJLabel);
		}

	}


	public void update(boolean paramBoolean1, boolean paramBoolean2) {
		System.out.println("Updating GUIBoard");
		isWhiteTurn = paramBoolean1;
		acceptInput = paramBoolean2;
		Board localBoard = ChessModel.getBoard();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				ChessPiece localChessPiece = localBoard.getPieceAtLocation(j, i);
				if (localChessPiece != null) {

					if (localChessPiece.getColour()) {
						if (localChessPiece.getSymbol() == 'K') {
							buttons[i][j].setImage(images[0]);
						}
						else if (localChessPiece.getSymbol() == 'Q') {
							buttons[i][j].setImage(images[1]);
						}
						else if (localChessPiece.getSymbol() == 'B') {
							buttons[i][j].setImage(images[2]);
						}
						else if (localChessPiece.getSymbol() == 'N') {
							buttons[i][j].setImage(images[3]);
						}
						else if (localChessPiece.getSymbol() == 'R') {
							buttons[i][j].setImage(images[4]);
						}
						else if (localChessPiece.getSymbol() == 'P') {
							buttons[i][j].setImage(images[5]);
						}
					}

					else if (localChessPiece.getSymbol() == 'K') {
						buttons[i][j].setImage(images[6]);
					}

					else if (localChessPiece.getSymbol() == 'Q') {
						buttons[i][j].setImage(images[7]);
					}

					else if (localChessPiece.getSymbol() == 'B') {
						buttons[i][j].setImage(images[8]);
					}

					else if (localChessPiece.getSymbol() == 'N') {
						buttons[i][j].setImage(images[9]);
					}

					else if (localChessPiece.getSymbol() == 'R') {
						buttons[i][j].setImage(images[10]);
					}

					else if (localChessPiece.getSymbol() == 'P') {
						buttons[i][j].setImage(images[11]);
					}

				}//if
				else {
					buttons[i][j].setImage(null);
				}
			}//for j
		}//for i

		clearHighlighting();
		Move localMove = localBoard.getLastMove();
		
		if (localMove != null) {
			buttons[localMove.getStartPos().getY()][localMove.getStartPos().getX()].setHighlight(5);
			buttons[localMove.getEndPos().getY()][localMove.getEndPos().getX()].setHighlight(6);
		}

		repaint();
	}


	public void actionPerformed(ActionEvent paramActionEvent) {
		
		if (!acceptInput) {
			return;
		}
		
		Board localBoard = ChessModel.getBoard();
		ChessBoardButton localChessBoardButton = (ChessBoardButton)paramActionEvent.getSource();
		
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				
				if (localChessBoardButton == buttons[i][j]) {
					Iterator localIterator1;
					Move localMove;

					if ((localBoard.getPieceAtLocation(j, i) != null) && (localBoard.getPieceAtLocation(j, i).getColour() == isWhiteTurn)) {
						if (localBoard.getPieceAtLocation(j, i) != null) {
							startPosition = new Location(j, i);
							validMoves = localBoard.getPieceAtLocation(startPosition).getPossibleMoves(startPosition, localBoard);
							update(isWhiteTurn, true);
							localIterator1 = validMoves.iterator();

							while (localIterator1.hasNext()) {
								localMove = (Move)localIterator1.next();
								if (localBoard.getPieceAtLocation(localMove.getEndPos().getX(), localMove.getEndPos().getY()) != null) {
									buttons[localMove.getEndPos().getY()][localMove.getEndPos().getX()].setHighlight(3);
								}
								else if ((localBoard.getEnPassantLocation() != null) && (localBoard.getEnPassantLocation().getX() == localMove.getEndPos().getX()) && (localBoard.getEnPassantLocation().getY() == localMove.getEndPos().getY()) && (localBoard.getPieceAtLocation(j, i).getSymbol() == 'P')) {
									buttons[localMove.getEndPos().getY()][localMove.getEndPos().getX()].setHighlight(3);
								}
								else {
									buttons[localMove.getEndPos().getY()][localMove.getEndPos().getX()].setHighlight(2);
								}
							}//while
							localChessBoardButton.setHighlight(1);
						}

					}
					else if ((startPosition != null) && ((j != startPosition.getX()) || (i != startPosition.getY()))) {
						localIterator1 = validMoves.iterator();
						
						while (localIterator1.hasNext()) {
							localMove = (Move)localIterator1.next();
							if ((j == localMove.getEndPos().getX()) && (i == localMove.getEndPos().getY())) {
								Iterator localIterator2 = listeners.iterator();

								while (localIterator2.hasNext()) {
									MoveListener localMoveListener = (MoveListener)localIterator2.next();
									localMoveListener.getInput(localMove);
								}

								startPosition = null;
								update(isWhiteTurn, true);
							}
						}//while
					}

					repaint();
					return;
				}

			}//for j
		}//for i
	}

	private void clearHighlighting() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				buttons[i][j].setHighlight(0);
			}
		}
	}

}
