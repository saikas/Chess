package edu.kingsu.SoftwareEngineering.Chess;

import java.util.ArrayList;

public class Pawn extends ChessPiece {
	
	public Pawn(boolean paramBoolean) {
		super(paramBoolean);
	}

	public Pawn(Pawn paramPawn) {
		super(paramPawn);
	}

	public Pawn makeCopy() {
		return new Pawn(this);
	}

	public char getSymbol() {
		return 'P';
	}

	public int getValue() {
		return 10;
	}

	public ArrayList<Move> getListOfMoves(Location paramLocation, Board paramBoard) {
		ArrayList localArrayList = new ArrayList();
		int i = paramLocation.getX();
		int j = paramLocation.getY();
		int k = -1;

		if (getColour()) {
			k = 1;
		}
		
		Location localLocation = new Location(i, j + k);
		Object localObject;
		
		if ((localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation) == null)) {

			localObject = new Move(paramLocation, localLocation);
			localArrayList.add(localObject);
			
			if (!getHasMoved()) {
				localLocation = new Location(i, j + 2 * k);

				if (paramBoard.getPieceAtLocation(localLocation) == null) {
					localObject = new Move(paramLocation, localLocation);
					localArrayList.add(localObject);
				}
			}
		}

		localLocation = new Location(i - 1, j + k);
		Move localMove;
		
		if ((localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation) != null) && (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour())) {
			localObject = new Move(paramLocation, localLocation);
			localArrayList.add(localObject);
		}

		else if ((localLocation.isValid()) && (paramBoard.getEnPassantLocation() != null)) {
			localObject = paramBoard.getEnPassantLocation();
			if ((localLocation.getX() == ((Location)localObject).getX()) && (localLocation.getY() == ((Location)localObject).getY())) {
				localMove = new Move(paramLocation, localLocation);
				localArrayList.add(localMove);
			}
		}

		localLocation = new Location(i + 1, j + k);

		if ((localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation) != null) && (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour())) {
			localObject = new Move(paramLocation, localLocation);
			localArrayList.add(localObject);
		}

		else if ((localLocation.isValid()) && (paramBoard.getEnPassantLocation() != null)) {
			
			localObject = paramBoard.getEnPassantLocation();
			
			if ((localLocation.getX() == ((Location)localObject).getX()) && (localLocation.getY() == ((Location)localObject).getY())) {
				localMove = new Move(paramLocation, localLocation);
				localArrayList.add(localMove);
			}
		}

		return localArrayList;
	}

}
