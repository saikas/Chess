package edu.kingsu.SoftwareEngineering.Chess;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class King extends ChessPiece {

	public King(boolean paramBoolean) {
		super(paramBoolean);
	}

	public King(King paramKing) {
		super(paramKing);
	}

	public King makeCopy() {
		return new King(this);
	}

	public char getSymbol() {
		return 'K';
	}

	public int getValue() {
		return 900;
	}

	public ArrayList<Move> getListOfMoves(Location paramLocation, Board paramBoard) {
		ArrayList localArrayList = new ArrayList();
		int i = paramLocation.getX();
		int j = paramLocation.getY();
		Location localLocation = new Location(i, j + 1);
		Object localObject;

		if ((localLocation.isValid()) && ((paramBoard.getPieceAtLocation(localLocation) == null) || (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour()))) {
			localObject = new Move(paramLocation, localLocation);
			localArrayList.add(localObject);
		}

		localLocation = new Location(i - 1, j + 1);

		if ((localLocation.isValid()) && ((paramBoard.getPieceAtLocation(localLocation) == null) || (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour()))) {
			localObject = new Move(paramLocation, localLocation);
			localArrayList.add(localObject);
		}

		localLocation = new Location(i + 1, j + 1);
		
		if ((localLocation.isValid()) && ((paramBoard.getPieceAtLocation(localLocation) == null) || (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour()))) {
			localObject = new Move(paramLocation, localLocation);
			localArrayList.add(localObject);
		}

		localLocation = new Location(i - 1, j);

		if ((localLocation.isValid()) && ((paramBoard.getPieceAtLocation(localLocation) == null) || (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour()))) {
			localObject = new Move(paramLocation, localLocation);
			localArrayList.add(localObject);
		}
		
		localLocation = new Location(i + 1, j);
		
		if ((localLocation.isValid()) && ((paramBoard.getPieceAtLocation(localLocation) == null) || (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour()))) {
			localObject = new Move(paramLocation, localLocation);
			localArrayList.add(localObject);
		}
		
		localLocation = new Location(i, j - 1);
		
		if ((localLocation.isValid()) && ((paramBoard.getPieceAtLocation(localLocation) == null) || (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour()))) {
			localObject = new Move(paramLocation, localLocation);
			localArrayList.add(localObject);
		}

		localLocation = new Location(i - 1, j - 1);
		
		if ((localLocation.isValid()) && ((paramBoard.getPieceAtLocation(localLocation) == null) || (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour()))) {
			localObject = new Move(paramLocation, localLocation);
			localArrayList.add(localObject);
		}

		localLocation = new Location(i + 1, j - 1);
		
		if ((localLocation.isValid()) && ((paramBoard.getPieceAtLocation(localLocation) == null) || (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour()))) {
			localObject = new Move(paramLocation, localLocation);
			localArrayList.add(localObject);
		}

		if (!getHasMoved()) {
			localObject = paramBoard.getPieceAtLocation(7, j);
			
			if ((localObject != null) && (!((ChessPiece)localObject).getHasMoved()) && (paramBoard.getPieceAtLocation(i + 1, j) == null) && (paramBoard.getPieceAtLocation(i + 2, j) == null)) {
				localArrayList.add(new Move(paramLocation, new Location(i + 2, j), true));
			}
			
			ChessPiece localChessPiece = paramBoard.getPieceAtLocation(0, j);
			
			if ((localChessPiece != null) && (!localChessPiece.getHasMoved()) && (paramBoard.getPieceAtLocation(i - 1, j) == null) && (paramBoard.getPieceAtLocation(i - 2, j) == null)) {
				localArrayList.add(new Move(paramLocation, new Location(i - 2, j), true));
			}
		}

		return localArrayList;
	}


	public ArrayList<Move> getPossibleMoves(Location paramLocation, Board paramBoard) {
		ArrayList localArrayList = getListOfMoves(paramLocation, paramBoard);
		localArrayList = testMoves(localArrayList, paramBoard, getColour());
		ListIterator localListIterator = localArrayList.listIterator();

		while (localListIterator.hasNext()) {
			Move localMove1 = (Move)localListIterator.next();
			if (localMove1.getCastling()) {

				if (paramBoard.inCheck(getColour())) {
					localListIterator.remove();
				}

				else {
					Move localMove2;
					Board localBoard;
					
					if (localMove1.getEndPos().getX() == 6) {
						localMove2 = new Move(paramLocation, new Location(5, paramLocation.getY()));
						localBoard = new Board(paramBoard, localMove2);
						
						if (localBoard.inCheck(getColour())) {
							localListIterator.remove();
						}
					}
					else if (localMove1.getEndPos().getX() == 2) {
						localMove2 = new Move(paramLocation, new Location(3, paramLocation.getY()));
						localBoard = new Board(paramBoard, localMove2);
						if (localBoard.inCheck(getColour())) {
							localListIterator.remove();
						}
					}
					else {
						System.out.println("Error in checking castling of king");
					}

				}//else
			}//if
		}//while

		return localArrayList;
	}

}
