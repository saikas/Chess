package edu.kingsu.SoftwareEngineering.Chess;

public class Scenario_Tutorial3 extends Scenario {
	public Scenario_Tutorial3() {
		super(new Board(
			new ChessPiece[][] {
				{null, null, new Bishop(true), null, new King(true), null, null, new Rook(true) },
				{null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, new King(false), null, null, null }
			}),
		new HumanPlayer(true), new AIPlayer(false, 1), "Welcome to the third chess tutorial!\n\n\tNext we shall examine the BISHOP. Bishops can move any amount of spaces\ndiagonally. Note that bishops are always on the same colour squares, and\nthus cannot attack the opposite colour spaces!\n\nIf a king is not in direct threat of capture, but still cannot make any moves,\nthen the game ends in a STALEMATE where no one wins. Try creating this\nsituation now, keeping in mind that you can use your king too!");
	}
}
