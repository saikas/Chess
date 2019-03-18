package edu.kingsu.SoftwareEngineering.Chess;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Board {
	private ChessPiece[][] board = new ChessPiece[8][8];
	private MoveList moveList = new MoveList();
	private Move lastMove;
	private Location enPassantLocation;
	
	public Board() {
		addPiece(new King(true), new Location(4, 0));
		addPiece(new Queen(true), new Location(3, 0));
		addPiece(new Bishop(true), new Location(2, 0));
		addPiece(new Bishop(true), new Location(5, 0));
		addPiece(new Knight(true), new Location(1, 0));
		addPiece(new Knight(true), new Location(6, 0));
		addPiece(new Rook(true), new Location(0, 0));
		addPiece(new Rook(true), new Location(7, 0));
		addPiece(new King(false), new Location(4, 7));
		addPiece(new Queen(false), new Location(3, 7));
		addPiece(new Bishop(false), new Location(2, 7));
		addPiece(new Bishop(false), new Location(5, 7));
		addPiece(new Knight(false), new Location(1, 7));
		addPiece(new Knight(false), new Location(6, 7));
		addPiece(new Rook(false), new Location(0, 7));
		addPiece(new Rook(false), new Location(7, 7));
		
		for (int i = 0; i < 8; i++) {
			addPiece(new Pawn(true), new Location(i, 1));
			addPiece(new Pawn(false), new Location(i, 6));
		}
	}
	
	public Board(Board paramBoard) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Location localLocation = new Location(i, j);
				ChessPiece localChessPiece = paramBoard.getPieceAtLocation(localLocation);
				if (localChessPiece != null) {
					localChessPiece = localChessPiece.makeCopy();
				}
				addPiece(localChessPiece, localLocation);
			}
		}
		moveList = paramBoard.getMoveList().makeCopy();
		lastMove = paramBoard.getLastMove();
		enPassantLocation = paramBoard.getEnPassantLocation();
	}
	
	public Board(Board paramBoard, Move paramMove) {
		this(paramBoard);
		movePiece(paramMove);
	}
	
	public Board(ChessPiece[][] paramArrayOfChessPiece) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = paramArrayOfChessPiece[j][i];
			}
		}
	}
	
	public ChessPiece getPieceAtLocation(int paramInt1, int paramInt2) {
		return board[paramInt1][paramInt2];
	}
	
	public ChessPiece getPieceAtLocation(Location paramLocation) {
		return getPieceAtLocation(paramLocation.getX(), paramLocation.getY());
	}
	
	public MoveList getMoveList() {
		return moveList;
	}
	
	public void setMoveList(MoveList paramMoveList) {
		moveList = paramMoveList;
	}
	
	public Location getEnPassantLocation() {
		return enPassantLocation;
	}
	
	private void setEnPassantLocation(Location paramLocation) {
		enPassantLocation = paramLocation;
	}
	
	public Move getLastMove() {
		return lastMove;
	}
	
	public void setLastMove(Move paramMove) {
		lastMove = paramMove;
	}
	
	public void movePiece(Move paramMove) {
		lastMove = paramMove;
		int i = paramMove.getStartPos().getX();
		int j = paramMove.getStartPos().getY();
		int k = paramMove.getEndPos().getX();
		int m = paramMove.getEndPos().getY();
		int n = 0;

		if (board[k][m] != null) {
			n = 1;
		}

		board[k][m] = board[i][j];
		board[i][j] = null;
		
		if (paramMove.getCastling()) {
			if (k == 6) {
				board[5][j] = board[7][j];
				board[7][j] = null;
				pieceHasMoved(new Location(5, j));
			}
			else if (k == 2) {
				board[3][j] = board[0][j];
				board[0][j] = null;
				pieceHasMoved(new Location(3, j));
			}
		}

		if ((enPassantLocation != null) && (board[k][m].getSymbol() == 'P') && (enPassantLocation.getX() == k) && (enPassantLocation.getY() == m)) {
			board[k][j] = null;
		}
		
		ChessPiece localChessPiece = getPieceAtLocation(paramMove.getEndPos());
		
		if ((localChessPiece.getSymbol() == 'P') && (!localChessPiece.getHasMoved())) {
			if (Math.abs(j - m) == 2) {
				setEnPassantLocation(new Location(k, (j + m) / 2));
			}
			else {
				setEnPassantLocation(null);
			}
		}
		else {
			setEnPassantLocation(null);
		}

		pieceHasMoved(paramMove.getEndPos());
		
		if (inCheck(!localChessPiece.getColour())) {
			n += 2;
		}
		
		PGNUtils localPGNUtils = new PGNUtils(this);
		String str = localPGNUtils.moveToPGN(paramMove, localChessPiece.getSymbol(), n);
		moveList.addMove(str);
	}
	
	private void pieceHasMoved(Location paramLocation) {
		board[paramLocation.getX()][paramLocation.getY()].moved();
	}
	
	public void addPiece(ChessPiece paramChessPiece, Location paramLocation) {
		board[paramLocation.getX()][paramLocation.getY()] = paramChessPiece;
	}
	
	public void pawnPromotion(Location paramLocation) {
		pawnPromotion(new Queen(getPieceAtLocation(paramLocation).getColour()), paramLocation);
	}
	
	public void pawnPromotion(ChessPiece paramChessPiece, Location paramLocation) {
		addPiece(paramChessPiece, paramLocation);
		pieceHasMoved(paramLocation);
	}
	
	public Location checkPawnPromotion() {
		for (int i = 0; i < 8; i++) {
			ChessPiece localChessPiece1 = getPieceAtLocation(i, 7);
			ChessPiece localChessPiece2 = getPieceAtLocation(i, 0);
			
			if ((localChessPiece1 != null) && (localChessPiece1.getSymbol() == 'P')) {
				return new Location(i, 7);
			}
			
			if ((localChessPiece2 != null) && (localChessPiece2.getSymbol() == 'P')) {
				return new Location(i, 0);
			}
		}

		return null;
	}
	

	public int getBoardValue() {
		if ((inCheck(true)) && (inCheckmate(true))) {
			return Integer.MIN_VALUE;
		}

		if ((inCheck(false)) && (inCheckmate(false))) {
			return Integer.MAX_VALUE;
		}
		
		int i = 0;
		
		for (int j = 0; j < 8; j++) {
			for (int k = 0; k < 8; k++) {

				ChessPiece localChessPiece = getPieceAtLocation(j, k);
				if (localChessPiece != null) {
					if (localChessPiece.getColour()) {
						i += localChessPiece.getValue();
					}
					else {
						i -= localChessPiece.getValue();
					}
					if (localChessPiece.getSymbol() == 'P') {
						if (localChessPiece.getColour()) {
							i += k - 1;
						}
						else {
							i -= 6 - k;
						}
					}
				}//if
			}//for k
		}//for j
		return i;
	}
	

	public Location findKing(boolean paramBoolean) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				ChessPiece localChessPiece = getPieceAtLocation(i, j);
				if ((localChessPiece != null) && (localChessPiece.getColour() == paramBoolean) && (localChessPiece.getSymbol() == 'K')) {
					return new Location(i, j);
				}
			}
		}
		return new Location(-1, -1);
	}
	

	public ArrayList<Location> findPiece(boolean paramBoolean, char paramChar) {
		ArrayList localArrayList = new ArrayList();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				ChessPiece localChessPiece = getPieceAtLocation(i, j);
				if ((localChessPiece != null) && (localChessPiece.getSymbol() == paramChar) && (localChessPiece.getColour() == paramBoolean)) {
					localArrayList.add(new Location(i, j));
				}
			}
		}
		return localArrayList;
	}
	
	public Move findMove(Location paramLocation, boolean paramBoolean1, char paramChar1, boolean paramBoolean2, int paramInt, char paramChar2) {
		System.out.println("" + paramLocation + " " + paramBoolean1 + " " + paramChar1 + " " + paramBoolean2 + " " + paramInt + " " + paramChar2);
		ArrayList localArrayList1 = findPiece(paramBoolean1, paramChar1);
		System.out.println("PGN Board findMove number of locations: " + localArrayList1.size());
		Iterator localIterator = localArrayList1.iterator();

		while (localIterator.hasNext()) {
			Location localLocation = (Location)localIterator.next();
			ChessPiece localChessPiece = getPieceAtLocation(localLocation);
			ArrayList localArrayList2 = localChessPiece.getPossibleMoves(localLocation, this);
			
			for (int i = localArrayList2.size() - 1; i >= 0; i--) {
				Move localMove = (Move)localArrayList2.get(i);
				if ((localMove.getEndPos().getX() != paramLocation.getX()) || (localMove.getEndPos().getY() != paramLocation.getY())) {
					localArrayList2.remove(i);
				}
				else {
					System.out.println("Correct endLocation");
					Object localObject;
					if (paramBoolean2) {
						localObject = getPieceAtLocation(localMove.getEndPos());
						if ((localObject == null) || (((ChessPiece)localObject).getColour() == paramBoolean1)) {
							localArrayList2.remove(i);
							continue;
						}
					}

					System.out.println("Correct attack");
					
					if ((paramInt != 0) && (paramInt != localLocation.getRank())) {
						localArrayList2.remove(i);
					}
					else {
						System.out.println("Correct rank");
						localObject = "abcdefgh";
						if ((((String)localObject).indexOf("" + paramChar2) != -1) && (paramChar2 != localLocation.getFile())) {
							localArrayList2.remove(i);
						}
						else {
							System.out.println("Correct file");
						}
					}
				}
			}//for i

			if (localArrayList2.size() > 0) {
				if (localArrayList2.size() > 1) {
					System.out.println("PGN Board findMove more than one move found: " + localArrayList2.size());
				}
				System.out.println("PGN Board findMove " + localArrayList2.get(0));
				return (Move)localArrayList2.get(0);
			}
		}

		System.out.println("PGN Board findMove no move found");
		
		return null;
	}
	

	public boolean inCheck(boolean paramBoolean) {
		Location localLocation = findKing(paramBoolean);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				ChessPiece localChessPiece = getPieceAtLocation(i, j);
				if ((localChessPiece != null) && (localChessPiece.getColour() != paramBoolean)) {
					ArrayList localArrayList = localChessPiece.getListOfMoves(new Location(i, j), this);
					Iterator localIterator = localArrayList.iterator();
					
					while (localIterator.hasNext()) {
						Move localMove = (Move)localIterator.next();
						if ((localMove.getEndPos().getX() == localLocation.getX()) && (localMove.getEndPos().getY() == localLocation.getY())) {
							return true;
						}
					}//while
				}
			}//for j
		}//for i
		return false;
	}
	

	public boolean inCheckmate(boolean paramBoolean) {
		if (!inCheck(paramBoolean)) {
			return false;
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				ChessPiece localChessPiece = getPieceAtLocation(i, j);
				if ((localChessPiece != null) && (localChessPiece.getColour() == paramBoolean)) {
					ArrayList localArrayList = localChessPiece.getListOfMoves(new Location(i, j), this);
					Iterator localIterator = localArrayList.iterator();
					
					while (localIterator.hasNext()) {
						Move localMove = (Move)localIterator.next();
						Board localBoard = new Board(this);
						localBoard.movePiece(localMove);
						if (!localBoard.inCheck(paramBoolean)) {
							return false;
						}
					}//while
				}
			}//for j
		}//for i
		return true;
	}
	

	public boolean inStalemate(Player paramPlayer) {
		return (paramPlayer.getMoveList(this).size() == 0) && (!inCheckmate(paramPlayer.getColour()));
	}
	
	public String toString() {
		String str = "\n";

		for (int i = 7; i >= 0; i--) {
			str = str + "  -----------------------------------------\n";
			str = str + (i + 1);
			for (int j = 0; j < 8; j++) {
				str = str + " | ";
				if (board[j][i] != null) {
					if (board[j][i].getColour()) {
						str = str + "W";
					}
					else {
						str = str + "B";
					}
					str = str + board[j][i].getSymbol();
				}
				else {
					str = str + "  ";
				}
			}
			str = str + " |\n";
		}//for i

		str = str + "  -----------------------------------------\n";
		str = str + "    A    B    C    D    E    F    G    H   \n";
		str = str + "Board Value: " + getBoardValue();
		return str;
	}

}
