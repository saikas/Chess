package edu.kingsu.SoftwareEngineering.Chess;

import java.util.ArrayList;

public class Knight extends ChessPiece {
	
	public Knight(boolean paramBoolean) {
		super(paramBoolean);
	}

	public Knight(Knight paramKnight) {
		super(paramKnight);
	}

	public Knight makeCopy() {
		return new Knight(this);
	}

	public char getSymbol() {
		return 'N';
	}

	public int getValue() {
		return 30;
	}

	public ArrayList<Move> getListOfMoves(Location paramLocation, Board paramBoard) {
		ArrayList localArrayList = new ArrayList();
		int i = paramLocation.getX();
		int j = paramLocation.getY();
		Location localLocation = new Location(i + 1, j + 2);
		Move localMove;

		if ((localLocation.isValid()) && ((paramBoard.getPieceAtLocation(localLocation) == null) || (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour()))) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		localLocation = new Location(i - 1, j + 2);
		
		if ((localLocation.isValid()) && ((paramBoard.getPieceAtLocation(localLocation) == null) || (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour()))) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		localLocation = new Location(i + 2, j + 1);
		
		if ((localLocation.isValid()) && ((paramBoard.getPieceAtLocation(localLocation) == null) || (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour()))) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		localLocation = new Location(i + 2, j - 1);

		if ((localLocation.isValid()) && ((paramBoard.getPieceAtLocation(localLocation) == null) || (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour()))) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		localLocation = new Location(i - 2, j + 1);

		if ((localLocation.isValid()) && ((paramBoard.getPieceAtLocation(localLocation) == null) || (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour()))) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		localLocation = new Location(i - 2, j - 1);

		if ((localLocation.isValid()) && ((paramBoard.getPieceAtLocation(localLocation) == null) || (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour()))) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		localLocation = new Location(i - 1, j - 2);
		
		if ((localLocation.isValid()) && ((paramBoard.getPieceAtLocation(localLocation) == null) || (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour()))) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		localLocation = new Location(i + 1, j - 2);

		if ((localLocation.isValid()) && ((paramBoard.getPieceAtLocation(localLocation) == null) || (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour()))) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		return localArrayList;
	}

}
