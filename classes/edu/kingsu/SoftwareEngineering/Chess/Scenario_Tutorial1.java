package edu.kingsu.SoftwareEngineering.Chess;

public class Scenario_Tutorial1 extends Scenario {
	public Scenario_Tutorial1() {
		super(new Board(
			new ChessPiece[][] {
				{ null, null, null, null, new King(true), null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, new Pawn(true), null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, new Pawn(false), null, null, null, null },
				{ null, null, null, null, new King(false), null, null, null }
			}),
		new HumanPlayer(true), new AIPlayer(false, 1), "\tWelcome to the first chess tutorial!\n\n\tThe goal of chess is to protect your KING and capture the opponent's king.\nTo do this, several pieces are at your disposal. The most basic of these\nis the PAWN, which can only move forward and capture diagonally.\n\tNormally, pawns can only move a distance of one space per turn, however\non their first turn they may move two spaces forward.\n\n\tClick on the WHITE PAWN and select a space highlighted in BLUE to move it.\nIf a space is highlighted in RED, it means the piece you selected may capture\nthe highlighted piece!\n\n\tNOTE: This first scenario cannot be won, as neither pawns nor kings can\ncapture kings. Practice using the pawn, then begin a new game to move on.");
	}
}
