package edu.kingsu.SoftwareEngineering.Chess;

public class AIPlayer extends Player {
	private int ply;

	public AIPlayer(boolean paramBoolean, int paramInt) {
		super(paramBoolean);
		ply = paramInt;
	}
	
	public boolean isHumanPlayer() {
		return false;
	}
	
	public void getMove(Board paramBoard) {
		Move localMove = getBestMove(getColour(), paramBoard, ply, Integer.MIN_VALUE, Integer.MAX_VALUE);
		listener.getInput(localMove);
	}
	
	public int getPly() {
		return ply;
	}
}
