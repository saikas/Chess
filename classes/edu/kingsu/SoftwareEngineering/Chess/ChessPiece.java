package edu.kingsu.SoftwareEngineering.Chess;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class ChessPiece {
	private boolean isWhite;
	private boolean hasMoved;

	public ChessPiece(boolean paramBoolean) {
		isWhite = paramBoolean;
	}

	public abstract ChessPiece makeCopy();

	public ChessPiece(ChessPiece paramChessPiece) {
		isWhite = paramChessPiece.getColour();
		hasMoved = paramChessPiece.getHasMoved();
	}

	public boolean getColour() {
		return isWhite;
	}

	public abstract char getSymbol();

	public abstract int getValue();

	public abstract ArrayList<Move> getListOfMoves(Location paramLocation, Board paramBoard);

	public ArrayList<Move> getPossibleMoves(Location paramLocation, Board paramBoard) {
		ArrayList localArrayList = getListOfMoves(paramLocation, paramBoard);
		localArrayList = testMoves(localArrayList, paramBoard, getColour());
		return localArrayList;
	}

	public ArrayList<Move> testMoves(ArrayList<Move> paramArrayList, Board paramBoard, boolean paramBoolean) {
		ArrayList localArrayList = new ArrayList();
		Iterator localIterator = paramArrayList.iterator();

		while (localIterator.hasNext()) {
			Move localMove = (Move)localIterator.next();
			Board localBoard = new Board(paramBoard, localMove);
			if (!localBoard.inCheck(paramBoolean)) {
				localArrayList.add(localMove);
			}
		}

		return localArrayList;
	}

	public boolean getHasMoved() {
		return hasMoved;
	}

	public void moved() {
		hasMoved = true;
	}

}
