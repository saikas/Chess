package edu.kingsu.SoftwareEngineering.Chess;

public class MoveRunner implements Runnable {
	
	private Player player;
	private Board board;

	public MoveRunner(Player paramPlayer, Board paramBoard) {
		player = paramPlayer;
		board = paramBoard;
	}

	public void run() {
		player.getMove(board);
	}
}
