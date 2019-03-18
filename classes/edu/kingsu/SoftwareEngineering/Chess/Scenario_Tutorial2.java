package edu.kingsu.SoftwareEngineering.Chess;

public class Scenario_Tutorial2 extends Scenario {
	public Scenario_Tutorial2() {
		super(new Board(
			new ChessPiece[][] {
				{ new Rook(true), null, null, null, new King(true), null, null, new Rook(true) },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, new King(false), null, null, null }
			}),
		new HumanPlayer(true), new AIPlayer(false, 1), "\tWelcome to the second chess tutorial!\n\n\tThis scenario involves playing with a ROOK. Rooks can move horizontally\nas well as vertically any number of spaces.\n\n\tA king cannot move into a position which would allow it to be taken. If a\nking could be taken the next turn, it is in CHECK. If a king cannot make any\nmoves, they are in CHECKMATE, and the game is done. With two rooks, you can\nmove the enemy's king into checkmate if you force it against the far wall!");
	}
}
