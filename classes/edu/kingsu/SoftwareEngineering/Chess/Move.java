package edu.kingsu.SoftwareEngineering.Chess;

public class Move {

	private Location startPos;
	private Location endPos;
	private boolean isCastling;

	public Move(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		startPos = new Location(paramInt1, paramInt2);
		endPos = new Location(paramInt3, paramInt4);
		isCastling = false;
	}

	public Move(Location paramLocation1, Location paramLocation2) {
		startPos = paramLocation1;
		endPos = paramLocation2;
		isCastling = false;
	}

	public Move(Location paramLocation1, Location paramLocation2, boolean paramBoolean) {
		startPos = paramLocation1;
		endPos = paramLocation2;
		isCastling = paramBoolean;
	}

	public Location getStartPos() {
		return startPos;
	}

	public Location getEndPos() {
		return endPos;
	}

	public boolean getCastling() {
		return isCastling;
	}

	public void setCastling(boolean paramBoolean) {
		isCastling = paramBoolean;
	}

	public String toString() {
		return "Move from " + startPos + " to " + endPos;
	}

}
