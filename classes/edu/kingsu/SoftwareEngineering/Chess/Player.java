package edu.kingsu.SoftwareEngineering.Chess;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public abstract class Player {
	private boolean isWhite;
	protected MoveListener listener;

	public void addListener(MoveListener paramMoveListener) {
		System.out.println("Added listener to player " + isWhite);
		listener = paramMoveListener;
	}

	public Player(boolean paramBoolean) {
		isWhite = paramBoolean;
	}

	public boolean getColour() {
		return isWhite;
	}

	public abstract void getMove(Board paramBoard);

	public abstract boolean isHumanPlayer();

	public ArrayList<Move> getMoveList(Board paramBoard) {
		return getMoveList(paramBoard, getColour());
	}

	public ArrayList<Move> getMoveList(Board paramBoard, boolean paramBoolean) {
		ArrayList localArrayList1 = new ArrayList();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				ChessPiece localChessPiece = paramBoard.getPieceAtLocation(i, j);
				if ((localChessPiece != null) && (localChessPiece.getColour() == paramBoolean)) {
					Location localLocation = new Location(i, j);
					ArrayList localArrayList2 = localChessPiece.getPossibleMoves(localLocation, paramBoard);
					localArrayList1.addAll(localArrayList2);
				}
			}
		}
		return localArrayList1;
	}

	public Move getBestMove(boolean paramBoolean, Board paramBoard, int paramInt1, int paramInt2, int paramInt3) {
		
		ArrayList localArrayList = getMoveList(paramBoard, paramBoolean);
		
		if (localArrayList.size() == 0) {
			return null;
		}

		Object localObject1 = (Move)localArrayList.get(new Random().nextInt(localArrayList.size()));
		Board localBoard = new Board(paramBoard);
		localBoard.movePiece((Move)localObject1);
		Location localLocation = localBoard.checkPawnPromotion();

		if (localLocation != null) {
			localBoard.pawnPromotion(localLocation);
		}
	
		int i = localBoard.getBoardValue();
		Object localObject2;
	
		if (paramInt1 > 1) {
			localObject2 = getBestMove(!paramBoolean, localBoard, paramInt1 - 1, paramInt2, paramInt3);
			if (localObject2 == null) {
				return localObject1;
			}
			localBoard.movePiece((Move)localObject2);
			localLocation = localBoard.checkPawnPromotion();

			if (localLocation != null) {
				localBoard.pawnPromotion(localLocation);
			}
			i = localBoard.getBoardValue();
		}

		if (localArrayList.size() > 1) {
			localObject2 = localArrayList.iterator();
			
			while (((Iterator)localObject2).hasNext()) {
				Move localMove1 = (Move)((Iterator)localObject2).next();
				localBoard = new Board(paramBoard);
				localBoard.movePiece(localMove1);
				localLocation = localBoard.checkPawnPromotion();
				
				if (localLocation != null) {
					localBoard.pawnPromotion(localLocation);
				}

				int j = localBoard.getBoardValue();
				
				if (paramInt1 > 1) {
					Move localMove2 = getBestMove(!paramBoolean, localBoard, paramInt1 - 1, paramInt2, paramInt3);
					if (localMove2 == null) {
						return localMove1;
					}

					localBoard.movePiece(localMove2);
					localLocation = localBoard.checkPawnPromotion();
					
					if (localLocation != null) {
						localBoard.pawnPromotion(localLocation);
					}
					j = localBoard.getBoardValue();
				}

				if (paramBoolean) {
					if (j > i) {
						i = j;
						localObject1 = localMove1;
						paramInt2 = i;
						if (i > paramInt3) {
							return localMove1;
						}
					}
				}
				else if (j < i) {
					i = j;
					localObject1 = localMove1;
					paramInt3 = i;
					if (i < paramInt2) {
						return localMove1;
					}
				}
			}//while
		}
		return localObject1;
	}
}
