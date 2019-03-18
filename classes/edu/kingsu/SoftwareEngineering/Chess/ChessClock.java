package edu.kingsu.SoftwareEngineering.Chess;

public class ChessClock {
	private long whiteTime;
	private long blackTime;
	
	public ChessClock(long paramLong) {
		whiteTime = paramLong;
		blackTime = paramLong;
	}
	
	public long getWhiteTime() {
		return whiteTime;
	}
	
	public long getBlackTime() {
		return blackTime;
	}
	
	public void setWhiteTime(Long paramLong) {
		whiteTime = paramLong.longValue();
	}
	
	public void setBlackTime(Long paramLong) {
		blackTime = paramLong.longValue();
	}

}
