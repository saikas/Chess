package edu.kingsu.SoftwareEngineering.Chess;

import java.util.ArrayList;

public class Bishop extends ChessPiece {

	public Bishop(boolean paramBoolean) {
		super(paramBoolean);
	}
	
	public Bishop(Bishop paramBishop) {
		super(paramBishop);
	}
	
	public Bishop makeCopy() {
		return new Bishop(this);
	}
	
	public char getSymbol() {
		return 'B';
	}
	
	public int getValue() {
		return 30;
	}
	
	public ArrayList<Move> getListOfMoves(Location paramLocation, Board paramBoard) {
		ArrayList localArrayList = new ArrayList();
		int i = paramLocation.getX();
		int j = paramLocation.getY();
		Move localMove;

		for (Location localLocation = new Location(i + 1, j + 1); (localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation) == null); localLocation = new Location(localLocation.getX() + 1, localLocation.getY() + 1)) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		if ((localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour())) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		for (localLocation = new Location(i - 1, j + 1); (localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation) == null); localLocation = new Location(localLocation.getX() - 1, localLocation.getY() + 1)) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		if ((localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour())) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		for (localLocation = new Location(i + 1, j - 1); (localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation) == null); localLocation = new Location(localLocation.getX() + 1, localLocation.getY() - 1)) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		if ((localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour())) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		for (localLocation = new Location(i - 1, j - 1); (localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation) == null); localLocation = new Location(localLocation.getX() - 1, localLocation.getY() - 1)) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		if ((localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour())) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		return localArrayList;
	}
}
