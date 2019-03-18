package edu.kingsu.SoftwareEngineering.Chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import javax.swing.JButton;

class ChessBoardButton extends JButton implements MouseListener {
	public static final int NO_HIGHLIGHT = 0;
	public static final int SELECTED_HIGHLIGHT = 1;
	public static final int VALID_MOVE_HIGHLIGHT = 2;
	public static final int KILL_MOVE_HIGHLIGHT = 3;
	public static final int HOVER_HIGHLIGHT = 4;
	public static final int LAST_MOVED_START_HIGHLIGHT = 5;
	public static final int LAST_MOVED_END_HIGHLIGHT = 6;
	private boolean blackSquare;
	private int highlight;
	private int trueHighlight;
	private BufferedImage img;

	public ChessBoardButton(int paramInt1, int paramInt2, boolean paramBoolean, BufferedImage paramBufferedImage) {
		setPreferredSize(new Dimension(paramInt1, paramInt2));
		addMouseListener(this);
		blackSquare = paramBoolean;
		highlight = 0;
		trueHighlight = 0;
		img = paramBufferedImage;
	}

	public void setImage(BufferedImage paramBufferedImage) {
		img = paramBufferedImage;
	}

	public void setHighlight(int paramInt) {
		highlight = paramInt;
		trueHighlight = paramInt;
	}

	public void paint(Graphics paramGraphics) {
		
		super.paint(paramGraphics);
		Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
		Rectangle2D.Double localDouble = new Rectangle2D.Double(0.0D, 0.0D, getWidth(), getHeight());
		
		if (blackSquare) {
			localGraphics2D.setColor(new Color(186, 156, 67));
		}
		else {
			localGraphics2D.setColor(new Color(255, 213, 91));
		}

		localGraphics2D.fill(localDouble);
		
		if (highlight == 1)
		{
			localGraphics2D.setColor(new Color(0, 255, 216, 100));
			localGraphics2D.fill(localDouble);
		}
		else if (highlight == 2) {
			localGraphics2D.setColor(new Color(0, 97, 255, 100));
			localGraphics2D.fill(localDouble);
		}
		else if (highlight == 3) {
			localGraphics2D.setColor(new Color(255, 0, 0, 100));
			localGraphics2D.fill(localDouble);
		}
		else if (highlight == 4) {
			localGraphics2D.setColor(new Color(100, 100, 100, 200));
			localGraphics2D.fill(localDouble);
		}
		else if (highlight == 5) {
			localGraphics2D.setColor(new Color(0, 255, 216, 50));
			localGraphics2D.fill(localDouble);
		}
		else if (highlight == 6) {
			localGraphics2D.setColor(new Color(0, 97, 255, 50));
			localGraphics2D.fill(localDouble);
		}
		if (img != null) {
			paramGraphics.drawImage(img, 0, 0, getWidth(), getHeight(), null, null);
		}
	}

	public void mouseEntered(MouseEvent paramMouseEvent) {
		highlight = 4;
	}

	public void mouseExited(MouseEvent paramMouseEvent) {
		highlight = trueHighlight;
	}

	public void mouseReleased(MouseEvent paramMouseEvent) {}

	public void mousePressed(MouseEvent paramMouseEvent) {}

	public void mouseClicked(MouseEvent paramMouseEvent) {}

}
