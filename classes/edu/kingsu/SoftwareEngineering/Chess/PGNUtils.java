package edu.kingsu.SoftwareEngineering.Chess;

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class PGNUtils {
	private Player player1 = new HumanPlayer(true);
	private Player player2 = new HumanPlayer(false);
	private MoveList moves = new MoveList();
	private Move[] moveArr = null;
	private Board board;

	public PGNUtils(Board paramBoard) {
		board = paramBoard;
	}

	public PGNUtils() {
		this(new Board());
	}

	public boolean savePGN(String paramString1, String paramString2, ArrayList<String> paramArrayList) {
		String str = "" + System.getProperty("user.dir");
		return writeToFile(paramString1, paramString2, paramArrayList, str);
	}

	public void loadPGN() {
		MoveList localMoveList = new MoveList();
		ArrayList localArrayList = processPGN(readPGN(choosePGN()));
		Move[] arrayOfMove = new Move[localArrayList.size()];
		
		for (int i = 0; i < localArrayList.size(); i++) {
			Move localMove = PGNToMove((String)localArrayList.get(i), i % 2 == 0);
			if (localMove != null) {
				arrayOfMove[i] = localMove;
				localMoveList.addMove((String)localArrayList.get(i));
			}
		}
		
		setMoveList(localMoveList);
		setMoves(arrayOfMove);
	}

	public Move PGNToMove(String paramString, boolean paramBoolean) {
		System.out.print("" + paramString + " ");
		paramString = paramString.trim();

		Location localLocation1 = null;
		Location localLocation2 = null;
		boolean bool = false;
		Move localMove = null;
		String str1 = "abcdefgh";
		String str2 = "12345678";

		int i = -1;
		int j = -1;
		int k = paramString.length();

		if (paramString.charAt(paramString.length() - 1) == '+') {
			k--;
		}

		if (paramString.charAt(paramString.length() - 1) == '#') {
			k--;
		}

		if (paramString.indexOf("e.p.") != -1) {
			k -= 4;
		}

		if (Character.isLowerCase(paramString.charAt(0))) {
			if (k == 2) {
				j = str1.indexOf("" + paramString.charAt(0));
				i = str2.indexOf("" + paramString.charAt(1));
				localLocation2 = new Location(j, i);
				localMove = getBoard().findMove(localLocation2, paramBoolean, 'P', false, 0, 'x');
			}
			else if ((k == 4) && (paramString.indexOf("x") != -1)) {
				j = str1.indexOf("" + paramString.charAt(2));
				i = str2.indexOf("" + paramString.charAt(3));
				localLocation2 = new Location(j, i);
				localMove = getBoard().findMove(localLocation2, paramBoolean, 'P', true, 0, 'x');
			}
		}
		else if (Character.isUpperCase(paramString.charAt(0))) {
			if (paramString.charAt(0) == 'O') {
				bool = true;
			}
			else {
				j = str1.indexOf("" + paramString.charAt(paramString.length() - 2));
				i = str2.indexOf("" + paramString.charAt(paramString.length() - 1));
				localLocation2 = new Location(j, i);

				if (paramString.indexOf("x") == -1) {
					if (k == 3) {
						localMove = getBoard().findMove(localLocation2, paramBoolean, paramString.charAt(0), false, 0, 'x');
					}
					else if (k == 4) {
						if (Character.isDigit(paramString.charAt(1))) {
							localMove = getBoard().findMove(localLocation2, paramBoolean, paramString.charAt(0), false, Integer.parseInt("" + paramString.charAt(1)), 'x');
						}
						else {
							localMove = getBoard().findMove(localLocation2, paramBoolean, paramString.charAt(0), false, 0, paramString.charAt(1));
						}
					}
				}

				else if ((k == 4) && (paramString.indexOf("x") >= 0)) {
					paramString = paramString.replaceAll("x", "");
					localMove = getBoard().findMove(localLocation2, paramBoolean, paramString.charAt(0), true, 0, 'x');
				}

				else if ((k > 4) && (paramString.indexOf("x") >= 0)) {
					paramString = paramString.replaceAll("x", "");

					if (Character.isDigit(paramString.charAt(1))) {
						localMove = getBoard().findMove(localLocation2, paramBoolean, paramString.charAt(0), true, Integer.parseInt("" + paramString.charAt(1)), 'x');
					}
					else {
						localMove = getBoard().findMove(localLocation2, paramBoolean, paramString.charAt(0), true, 0, paramString.charAt(1));
					}
				}
			}//else
		}

		if (bool) {
			if (paramBoolean) {
				localLocation1 = new Location(4, 0);
				if (k == 3) {
					localLocation2 = new Location(6, 0);
				}
				else if (k == 5) {
					localLocation2 = new Location(2, 0);
				}
			}
			else {
				localLocation1 = new Location(4, 7);
				
				if (k == 3) {
					localLocation2 = new Location(6, 7);
				}
				else if (k == 5) {
					localLocation2 = new Location(2, 7);
				}
			}
			localMove = new Move(localLocation1, localLocation2, bool);
		}

		if (localMove != null) {
			getBoard().movePiece(localMove);
		}
		
		return localMove;
	}

	public String moveToPGN(Move paramMove, char paramChar) {
		return moveToPGN(paramMove, paramChar, 0);
	}

	public String moveToPGN(Move paramMove, char paramChar, int paramInt) {
		int[] arrayOfInt = { 0, 1, 2, 3, 4 };
		String str1 = "";
		Location localLocation1 = new Location(paramMove.getStartPos().getX(), paramMove.getStartPos().getY());
		Location localLocation2 = new Location(paramMove.getEndPos().getX(), paramMove.getEndPos().getY());
		
		String str2 = "abcdefgh";
		String str3 = "12345678";
		String str4 = "OQKRNB";
		
		char[] arrayOfChar1 = str2.toCharArray();
		char[] arrayOfChar2 = str3.toCharArray();

		if (paramMove.getCastling()) {
			if (paramChar == 'K') {
				if (((localLocation2.getY() == 0) || (localLocation2.getY() == 7)) && (localLocation2.getX() == 6)) {
					str1 = str1 + "O-O";
				}
				else if (((localLocation2.getY() == 0) || (localLocation2.getY() == 7)) && (localLocation2.getX() == 2)) {
					str1 = str1 + "O-O-O";
				}
			}
			return str1;
		}

		if (paramChar != 'P') {
			str1 = str1 + "" + paramChar;
		}

		if ((paramChar != 'R') || ((paramInt == arrayOfInt[1]) || (paramInt == arrayOfInt[3]))) {
			str1 = str1 + "x";
		}
		
		str1 = str1 + "" + localLocation2.getFile() + localLocation2.getRank();
		
		if ((paramInt == arrayOfInt[2]) || (paramInt == arrayOfInt[3])) {
			str1 = str1 + "+";
		}
		else if (paramInt == arrayOfInt[4]) {
			str1 = str1 + "#";
		}

		return str1;
	}


	public String readPGN(String paramString) {
		
		ArrayList localArrayList = new ArrayList();
		String str2 = "";
		
		try {
			File localFile = new File(paramString);
			Scanner localScanner = new Scanner(localFile);
			
			while (localScanner.hasNextLine()) {
				String str1 = localScanner.nextLine();
				
				if (str1.contains("Player 1:")) {
					if (str1.contains("Human")) {
						setPlayer(0, new HumanPlayer(true));
					}
					else if (str1.contains("Very Easy")) {
						setPlayer(0, new AIPlayer(true, 1));
					}
					else if (str1.contains("(Easy)")) {
						setPlayer(0, new AIPlayer(true, 2));
					}
					else if (str1.contains("Normal")) {
						setPlayer(0, new AIPlayer(true, 3));
					}
					else if (str1.contains("Hard")) {
						setPlayer(0, new AIPlayer(true, 4));
					}
				}

				else if (str1.contains("Player 2:")) {
					if (str1.contains("Human")) {
						setPlayer(1, new HumanPlayer(false));
					}
					else if (str1.contains("Very Easy")) {
						setPlayer(1, new AIPlayer(false, 1));
					}
					else if (str1.contains("(Easy)")) {
						setPlayer(1, new AIPlayer(false, 2));
					}
					else if (str1.contains("Normal")) {
						setPlayer(1, new AIPlayer(false, 3));
					}else if (str1.contains("Hard")) {
						setPlayer(1, new AIPlayer(false, 4));
					}
				}
				else if ((!str1.contains("[")) && (!str1.trim().isEmpty())) {
					str2 = str2 + str1;
				}
			}

			localScanner.close();
		}
		catch (Exception localException) {
			JOptionPane.showMessageDialog(null, "Couldn't read PGN:\n" + localException, "Error", 1);
		}

		return str2;
	}

	public static boolean pgnValidator(String paramString) {
		String str1 = "12345678";
		String str2 = "abcdefgh";
		String str3 = "ORNBQK";

		if ((Character.isDigit(paramString.charAt(0))) || ((str3.indexOf(paramString.charAt(0)) == -1) && (str2.indexOf(paramString.charAt(0)) == -1))) {
			return false;
		}

		if (paramString.charAt(0) == 'O') {
			if (paramString.length() == 3) {
				if (paramString.charAt(2) != 'O') {
					return false;
				}

				if (paramString.charAt(1) != '-') {
					return false;
				}
			}
			else if (paramString.length() == 5) {
				if ((paramString.charAt(2) != 'O') || (paramString.charAt(4) != 'O')) {
					return false;
				}

				if ((paramString.charAt(1) != '-') || (paramString.charAt(3) != '-')) {
					return false;
				}
			}

			else {
				return false;
			}

			return true;
		}

		for (int i = 1; i < paramString.length(); i++) {
			if (i != paramString.length() - 1) {
				if ((Character.isDigit(paramString.charAt(i))) && (str1.indexOf(paramString.charAt(i)) == -1)) {
					return false;
				}

				if ((Character.isLetter(paramString.charAt(i))) && (str2.indexOf(paramString.charAt(i)) == -1)) {
					return false;
				}

				if ((paramString.charAt(i) == '+') || (paramString.charAt(i) == '#') || (paramString.charAt(i) == '*')) {
					return false;
				}

				if ((paramString.charAt(i) == 'x') && ((i != 1) || (i != 2))) {
					return false;
				}
			}
			else if ((paramString.charAt(i) != '+') && (paramString.charAt(i) != '#') && (paramString.charAt(i) != '*') && (str1.indexOf(paramString.charAt(i)) == -1)) {
				return false;
			}
		}

		return true;
	}


	public ArrayList<String> processPGN(String paramString) {
		ArrayList localArrayList = new ArrayList();
		String[] arrayOfString = null;
		try {
			paramString = paramString.replaceAll("\\{.*?\\}", "");
			paramString = paramString.replaceAll("\\[.*?\\]", "");
			paramString = paramString.replaceAll("1-0", "");
			paramString = paramString.replaceAll("0-1", "");
			paramString = paramString.replaceAll("1/2-1/2", "");
			paramString = paramString.replaceAll("\\*", "");
			paramString = paramString.replaceAll("\\+", "");
			paramString = paramString.replaceAll("\\#", "");
			paramString = paramString.replaceAll("\\.\\s{1,}", ".");
			arrayOfString = paramString.split("[^_]\\w*+\\.| (?=[^\\d ])| \\d\\-\\d");
		}
		catch (Exception localException) {
			JOptionPane.showMessageDialog(null, "Couldn't read PGN:\n" + localException, "Error", 1);
		}

		for (int i = 0; i < arrayOfString.length; i++) {
			if (!arrayOfString[i].trim().isEmpty()) {
				localArrayList.add(arrayOfString[i]);
			}
		}
		return localArrayList;
	}

	public String choosePGN() {
		
		String str1 = "" + System.getProperty("user.dir");
		JFileChooser localJFileChooser = new JFileChooser(new File(str1));
		int i = localJFileChooser.showOpenDialog(null);
		
		if (i == 0) {
			String str2 = localJFileChooser.getSelectedFile().getAbsolutePath();
			if (str2.substring(str2.length() - 3).toLowerCase().equals("pgn")) {
				return str2;
			}
		}
		
		JOptionPane.showMessageDialog(null, "Please choose a file with a \".pgn\" extension", "Error", 1);
		System.exit(-1);
		
		return "";
	}


	public boolean writeToFile(String paramString1, String paramString2, ArrayList<String> paramArrayList, String paramString3) {

		PrintWriter localPrintWriter = null;
		Date localDate = new Date();
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String str1 = localSimpleDateFormat.format(localDate);
		String str2 = paramString3 + "/" + "Chess_Save[" + str1 + "].pgn";

		try {
			localPrintWriter = new PrintWriter(str2, "UTF-8");
		}
		catch (Exception localException) {
			System.out.println("Error: Coulddn't create a file\n" + localException);
			return false;
		}

		int i = 1;
		localPrintWriter.println("[Player 1: " + paramString1 + "]");
		localPrintWriter.println("[Player 2: " + paramString2 + "]");
		localPrintWriter.println("");

		for (int j = 0; j < paramArrayList.size(); j++) {
			if (j % 2 == 0) {
				if (j == 0) {
					localPrintWriter.print("" + i + ".");
				} else {
					localPrintWriter.print(" " + i + ".");
				}
				i++;
			}
			localPrintWriter.print(" " + (String)paramArrayList.get(j));
		}

		localPrintWriter.println("");
		localPrintWriter.close();

		return true;
	}

	public void setPlayer(int paramInt, Player paramPlayer) {
		if (paramInt == 0) {
			player1 = paramPlayer;
		}
		else {
			player2 = paramPlayer;
		}
	}

	public Player getPlayer(int paramInt) {
		if (paramInt == 0) {
			return player1;
		}
		return player2;
	}

	public Board getBoard() {
		return board;
	}

	public void setMoveList(MoveList paramMoveList) {
		moves = paramMoveList;
	}

	public void setMoves(Move[] paramArrayOfMove) {
		moveArr = paramArrayOfMove;
	}

	public MoveList getMoves() {
		return moves;
	}

	public Move[] getMoveArr() {
		return moveArr;
	}

	private void movePiece(Move paramMove) {
		board.movePiece(paramMove);
	}

}
