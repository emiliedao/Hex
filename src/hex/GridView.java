package hex;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

public class GridView extends HexView {
	private static List<Cell> cells;
	private int verticalShift; 
	private int space;
	private Point origin;
	
	private JFrame window;
	private final int X = 300;
	private final int Y = 200;
	private final int WIDTH = 800;
	private final int HEIGHT = 700;
	private final String TITLE = "Hex Game";
	
	private Drawing drawing;
	
	public GridView(HexModel model, HexController controller, int size) {
		super(model, controller);
		
		cells = new ArrayList<Cell>();
		
		// Grid filling
		verticalShift = 0;
		space = 15;
		origin = new Point(220, 220);
		
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				
				// Top border (white)
				if (i == 0)
					cells.add(new Cell(model.getGrid(), origin.x + j*(Cell.getRadius() + space + Cell.getSide()/2) - Cell.getSide()/4, origin.y - j*Cell.getHexagonHeight() - Cell.getSide()/2, new Point(i,j), Color.WHITE, true));
				
				// Bottom border (white)
				else if (i == size-1)
					cells.add(new Cell(model.getGrid(), origin.x + j*(Cell.getRadius() + space + Cell.getSide()/2) + Cell.getSide()/4, origin.y - j*Cell.getHexagonHeight() + Cell.getSide()/2, new Point(i,j), Color.WHITE, true));
				
				// Left border (black)
				if (j == 0)
					cells.add(new Cell(model.getGrid(), origin.x - Cell.getSide()/2, origin.y - j*Cell.getHexagonHeight(), new Point(i,j), Color.BLACK, true));
								
				// Right border (black)
				else if (j == size-1)
					cells.add(new Cell(model.getGrid(), origin.x + j*(Cell.getRadius() + space + Cell.getSide()/2) + Cell.getSide()/2, origin.y - j*Cell.getHexagonHeight(), new Point(i,j), Color.BLACK, true));
				
				// Grid cells
				cells.add(new Cell(model.getGrid(), origin.x + j*(Cell.getRadius() + space + Cell.getSide()/2), origin.y - j*Cell.getHexagonHeight(), new Point(i,j), Color.GRAY, false));
			
			}
				
			origin.y += Cell.getHexagonHeight()*2 + space/2;
			
		}
		
		// Window parameters
		window = new JFrame(TITLE);
		window.setBounds(X, Y, WIDTH, HEIGHT);
		window.setVisible(true);
		
		// Drawing area
		drawing = new Drawing(cells);
		drawing.setSize(WIDTH, HEIGHT);
		drawing.addMouseListener(new Click());
		window.add(drawing, "Center");
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	/** Returns the color associated to the player (int) **/
	public Color getColorPlayer(int player) {
		if (player == 0)
			return Color.BLACK;
		
		else if (player == 1)
			return Color.WHITE;
		
		return null;
	}
	
	/** Returns the cell corresponding to the coordinates i and j in the grid **/
	public static Cell getCell(int i, int j) {
		Cell c = null;
		
		for (Cell cell : cells) {
			if (cell.getGridPos().x == i && cell.getGridPos().y == j)
				c = cell;
		}
		
		return c;
	}
	
	class Click extends MouseInputAdapter {

		@Override 
		public void mouseClicked(MouseEvent e) {
			for (Cell c: cells) {
				if (c.getPolygon().contains(e.getPoint()) && !c.isPlayed() && !c.isGridBorder()) {
					GridView.this.controller.clickOnCell(c, getColorPlayer(model.getCurrentPlayer()));
				}
			}
						
			drawing.repaint();
	    }
	}
}
