package edu.kingsu.SoftwareEngineering.Chess;

public abstract class Scenario {
	private Board board;
	private Player whitePlayer;
	private Player blackPlayer;
	private String introMessage;

	public Scenario(Board paramBoard, Player paramPlayer1, Player paramPlayer2, String paramString) {
		board = paramBoard;
		whitePlayer = paramPlayer1;
		blackPlayer = paramPlayer2;
		introMessage = paramString;
	}

	public Board getBoard() {
		return board;
	}

	public Player getWhitePlayer() {
		return whitePlayer;
	}

	public Player getBlackPlayer() {
		return blackPlayer;
	}

	public String getIntroMessage() {
		return introMessage;
	}
}
