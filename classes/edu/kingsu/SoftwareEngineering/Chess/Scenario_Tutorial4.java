package edu.kingsu.SoftwareEngineering.Chess;

public class Scenario_Tutorial4 extends Scenario {
	public Scenario_Tutorial4() {
		super(new Board(
			new ChessPiece[][] {
				{ new Rook(true), null, null, null, new King(true), null, new Knight(true), null },
				{ null, null, null, null, null, new Pawn(true), new Pawn(true), new Pawn(true) },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, new Pawn(false), null, new Pawn(false), null, null },
				{ null, null, null, null, new King(false), null, null, null }
			}),
		new HumanPlayer(true), new AIPlayer(false, 1), "Welcome to the fourth chess tutorial!\n\n\tNow we shall cover the KNIGHT. Unlike all other pieces, knights move by\n\"jumping\" over others and so are not blocked by adjacent pieces. Knights\nalso move in a distinct \"L\" shape which makes it difficult to pin other\npieces.\n\nTry taking on this new challenge, observing the times when a knight is useful.");
	}
}