package edu.kingsu.SoftwareEngineering.Chess;

import java.util.ArrayList;
import java.util.Iterator;

public class MoveList {

	ArrayList<String> moves = new ArrayList();
	int currentMoveNum = 0;

	public MoveList() {}

	public int getCurrentMoveNum() {
		return currentMoveNum;
	}

	public void setMoveNumber(int paramInt) {
		currentMoveNum = paramInt;
	}

	public ArrayList<String> getMoves() {
		return moves;
	}

	public void addMove(String paramString) {
		moves.add(paramString);
		currentMoveNum += 1;
	}

	public MoveList makeCopy() {
		MoveList localMoveList = new MoveList();
		localMoveList.setMoveNumber(currentMoveNum);
		Iterator localIterator = moves.iterator();
		
		while (localIterator.hasNext()) {
			String str = (String)localIterator.next();
			localMoveList.addMove(str);
		}
		
		return localMoveList;
	}

	public String toString() {
		String str1 = "";
		int i = 0;
		int j = 1;
		Iterator localIterator = moves.iterator();
		
		while (localIterator.hasNext()) {
			String str2 = (String)localIterator.next();
			
			if (j != 0) {
				i++;
				str1 = str1 + "\n" + i + ".";
			}

			str1 = str1 + " " + str2;
			j = j == 0 ? 1 : 0;
		}

		return str1;
	}

}
