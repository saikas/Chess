package edu.kingsu.SoftwareEngineering.Chess;

public class Location {
	private int x;
	private int y;

	public Location(int paramInt1, int paramInt2) {
		x = paramInt1;
		y = paramInt2;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRank() {
		return y + 1;
	}

	public char getFile() {
		return (char)(x + 97);
	}

	public boolean isValid() {
		return (x >= 0) && (x <= 7) && (y >= 0) && (y <= 7);
	}

	public String toString() {
		return "{" + getFile() + "," + getRank() + "}";
	}

}
