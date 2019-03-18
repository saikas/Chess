package edu.kingsu.SoftwareEngineering.Chess;

import java.util.ArrayList;

public class Queen extends ChessPiece {
	public Queen(boolean paramBoolean) {
		super(paramBoolean);
	}

	public Queen(Queen paramQueen) {
		super(paramQueen);
	}

	public Queen makeCopy() {
		return new Queen(this);
	}

	public char getSymbol() {
		return 'Q';
	}

	public int getValue() {
		return 90;
	}

	public ArrayList<Move> getListOfMoves(Location paramLocation, Board paramBoard) {
		ArrayList localArrayList = new ArrayList();
		int i = paramLocation.getX();
		int j = paramLocation.getY();
		Move localMove;
		
		for (Location localLocation = new Location(i, j + 1); (localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation) == null); localLocation = new Location(localLocation.getX(), localLocation.getY() + 1)) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		if ((localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour())) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		for (localLocation = new Location(i, j - 1); (localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation) == null); localLocation = new Location(localLocation.getX(), localLocation.getY() - 1)) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}
		
		if ((localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour())) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		for (localLocation = new Location(i - 1, j); (localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation) == null); localLocation = new Location(localLocation.getX() - 1, localLocation.getY())) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}
		
		if ((localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour())) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		for (localLocation = new Location(i + 1, j); (localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation) == null); localLocation = new Location(localLocation.getX() + 1, localLocation.getY())) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		if ((localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation).getColour() != getColour())) {
			localMove = new Move(paramLocation, localLocation);
			localArrayList.add(localMove);
		}

		for (localLocation = new Location(i + 1, j + 1); (localLocation.isValid()) && (paramBoard.getPieceAtLocation(localLocation) == null); localLocation = new Location(localLocation.getX() + 1, localLocation.getY() + 1)) {
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
