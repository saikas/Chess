package edu.kingsu.SoftwareEngineering.Chess;

public class Scenario_Tutorial5 extends Scenario {
	public Scenario_Tutorial5() {
		super(new Board(
			new ChessPiece[][] {
				{ null, null, null, null, new King(true), null, null, null },
				{ null, new Rook(true), null, null, null, null, new Rook(true), null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, new King(false), null, null, null }
			}),
		new HumanPlayer(true), new AIPlayer(false, 1), "Welcome to the second chess tutorial!\n\nInsert helpful message here!");
	}
}