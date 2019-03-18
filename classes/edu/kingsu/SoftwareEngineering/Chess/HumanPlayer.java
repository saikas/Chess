package edu.kingsu.SoftwareEngineering.Chess;

public class HumanPlayer extends Player {

	public HumanPlayer(boolean paramBoolean) {
		super(paramBoolean);
	}
	
	public boolean isHumanPlayer() {
		return true;
	}
	
	public void getMove(Board paramBoard) {
		Move localMove = getBestMove(getColour(), paramBoard, 4, Integer.MIN_VALUE, Integer.MAX_VALUE);
		listener.getInput(localMove);
	}

}
