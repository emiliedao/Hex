package hex;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

public class Drawing extends Canvas {

	List<Cell> cells;
	
	public Drawing(List<Cell> cells) {
		this.cells = cells;
	}
	
	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Cell c : cells) 
			c.paintComponent(g);
	}
	
}
