package hex;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.HashSet;

import javax.swing.JComponent;

public class Cell extends JComponent {
	
	private int grid[][];
	private Polygon polygon;
	private int centerX;
	private int centerY;
	private Point gridPos;
	private Color color;
	private boolean played;
	private boolean gridBorder; // only to draw the opposite sides of the grid
	private HashSet<Cell> neighbours;
	private boolean visited;
	
	private final static int RADIUS = 30;
	
	public Cell(int grid[][], int centerX, int centerY, Point gridPos, Color color, boolean gridBorder) {
		this.grid = grid;
		
		polygon = new Polygon();
		double arc =(Math.PI*2)/6;
		for (int i=0; i <=6; i++)
			polygon.addPoint((int)Math.round(centerX + RADIUS*Math.cos(arc*i)),
				(int)Math.round(centerY + RADIUS*Math.sin(arc*i)));
		
		this.centerX = centerX;
		this.centerY = centerY;
		this.gridPos = gridPos;
		this.color = color;
		this.played = false;
		this.gridBorder = gridBorder;
		neighbours = new HashSet<Cell>();
		visited = false;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isPlayed() {
		return played;
	}

	public void setPlayed(boolean played) {
		this.played = played;
	}

	public boolean isGridBorder() {
		return gridBorder;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public void setGridBorder(boolean gridBorder) {
		this.gridBorder = gridBorder;
	}

	public Point getGridPos() {
		return gridPos;
	}

	public void setGridPos(Point gridPos) {
		this.gridPos = gridPos;
	}

	public static int getRadius() {
		return RADIUS;
	}
	
	public static int getHexagonHeight() {
		return (int)(RADIUS*Math.sqrt(3)/2);
	}
	
	public static int getSide() {
		return RADIUS/2;
	}
	
	public HashSet<Cell> getNeighbours() {
		return neighbours;
	}
	
	public int nbNeighbours() {
		return neighbours.size();
	}

	/** Returns true if the cell has a neighbour of the same color in the grid **/
//	public boolean hasASameColorNeighbour() {
//		
//		boolean neighbour = true;
//		int value = grid[gridPos.x][gridPos.y];
//		
//		int size = grid[0].length;
//		
//		// Top left corner : 3 neighbours
//		if (gridPos.x == 0 && gridPos.y == 0) {
//			if (value != grid[0][1]
//				&& value != grid[1][0]
//				&& value != grid[1][1])
//					neighbour = false;
//		}
//
//		// Top right corner : 2 neighbours
//		else if (gridPos.x == 0 && gridPos.y == size-1) {
//			if (value != grid[0][size-2] && value != grid[1][size-1])
//				neighbour = false;
//		}
//		
//		// Bottom left corner : 2 neighbours
//		else if (gridPos.x == size-1 && gridPos.y == 0) {
//			if (value != grid[size-1][1] && value != grid[size-2][0])
//				neighbour = false;
//		}
//		
//		// Bottom right corner : 3 neighbours
//		else if (gridPos.x == size-1 && gridPos.y == size-1) {
//			if (value != grid[size-1][size-2]
//				&& value != grid[size-2][size-1]
//				&& value != grid[size-2][size-2])
//					neighbour = false;
//		}
//		
//		// Left border, except the corners : 4 neighbours
//		else if (gridPos.y == 0 && gridPos.x != 0 && gridPos.x != size-1) {
//			if (value != grid[gridPos.x-1][0]
//					&& value != grid[gridPos.x+1][0]
//					&& value != grid[gridPos.x][1]
//					&& value != grid[gridPos.x+1][1])
//						neighbour = false;
//		}
//		
//		// Right border, except the corners
//		else if (gridPos.y == size-1 && gridPos.x != 0 && gridPos.x != size-1) {
//			if (value != grid[gridPos.x-1][size-1]
//					&& value != grid[gridPos.x+1][size-1]
//					&& value != grid[gridPos.x-1][size-2]
//					&& value != grid[gridPos.x][size-2])
//						neighbour = false;	
//		}
//		
//		// Top border, except the corners : 4 neighbours
//		else if (gridPos.x == 0 && gridPos.y != 0 && gridPos.y != size-1) {
//			if (value != grid[0][gridPos.y-1]
//					&& value != grid[0][gridPos.y+1]
//					&& value != grid[1][gridPos.y]
//					&& value != grid[1][gridPos.y+1])
//						neighbour = false;
//		}
//		
//		// Bottom border, except the corners : 4 neighbours
//		else if (gridPos.x == size-1 && gridPos.y != 0 && gridPos.y != size-1) {
//			if (value != grid[size-1][gridPos.y-1]
//					&& value != grid[size-1][gridPos.y+1]
//					&& value != grid[size-2][gridPos.y-1]
//					&& value != grid[size-2][gridPos.y])
//						neighbour = false;
//		}
//		
//		// Middle of the grid : 6 neighbours
//		else {
//			if (value != grid[gridPos.x-1][gridPos.y-1]
//				&& value != grid[gridPos.x-1][gridPos.y]
//				&& value != grid[gridPos.x][gridPos.y-1]
//				&& value != grid[gridPos.x][gridPos.y+1]
//				&& value != grid[gridPos.x+1][gridPos.y]
//				&& value != grid[gridPos.x+1][gridPos.y+1])
//					neighbour = false;
//		}
//		
//		return neighbour;
//	}
	
	/** Add the neighbours of a colored cell **/
	public void neighbours() {
		int value = grid[gridPos.x][gridPos.y];
		
		int size = grid[0].length;
		
		if (value != HexModel.GRAY) {

			// Top left corner : 3 neighbours
			if (gridPos.x == 0 && gridPos.y == 0) {
				if (value == grid[0][1])
					neighbours.add(GridView.getCell(0, 1));
				
				if (value == grid[1][0])
					neighbours.add(GridView.getCell(1, 0));
				
				if (value == grid[1][1])
					neighbours.add(GridView.getCell(1, 1));
			}

			// Top right corner : 2 neighbours
			else if (gridPos.x == 0 && gridPos.y == size-1) {
				if (value ==  grid[0][size-2])
					neighbours.add(GridView.getCell(0, size-2));
				
				if (value == grid[1][size-1])
					neighbours.add(GridView.getCell(1, size-1));
			}
			
			// Bottom left corner : 2 neighbours
			else if (gridPos.x == size-1 && gridPos.y == 0) {
				if (value == grid[size-1][1])
					neighbours.add(GridView.getCell(size-1, 1));
				
				if (value == grid[size-2][0])
					neighbours.add(GridView.getCell(size-2, 0));
			}
			
			// Bottom right corner : 3 neighbours
			else if (gridPos.x == size-1 && gridPos.y == size-1) {
				if (value == grid[size-1][size-2])
					neighbours.add(GridView.getCell(size-1, size-2));
						
				if (value == grid[size-2][size-1])
					neighbours.add(GridView.getCell(size-2, size-1));
				
				if (value == grid[size-2][size-2])
					neighbours.add(GridView.getCell(size-2, size-2));	
			}
			
			// Left border, except the corners : 4 neighbours
			else if (gridPos.y == 0 && gridPos.x != 0 && gridPos.x != size-1) {
				if (value == grid[gridPos.x-1][0])
					neighbours.add(GridView.getCell(gridPos.x-1, 0));
				
				if (value == grid[gridPos.x+1][0])
					neighbours.add(GridView.getCell(gridPos.x+1, 0));
					
				if( value == grid[gridPos.x][1])
					neighbours.add(GridView.getCell(gridPos.x, 1));
					
				if (value == grid[gridPos.x+1][1])
					neighbours.add(GridView.getCell(gridPos.x+1, 1));

			}
			
			// Right border, except the corners
			else if (gridPos.y == size-1 && gridPos.x != 0 && gridPos.x != size-1) {
				if (value == grid[gridPos.x-1][size-1])
					neighbours.add(GridView.getCell(gridPos.x-1, size-1));
				
				if(value == grid[gridPos.x+1][size-1])
					neighbours.add(GridView.getCell(gridPos.x+1, size-1));
					
				if(value == grid[gridPos.x-1][size-2])
					neighbours.add(GridView.getCell(gridPos.x-1, size-2));
					
				if(value == grid[gridPos.x][size-2])
					neighbours.add(GridView.getCell(gridPos.x, size-2));
			}
			
			// Top border, except the corners : 4 neighbours
			else if (gridPos.x == 0 && gridPos.y != 0 && gridPos.y != size-1) {
				if (value == grid[0][gridPos.y-1])
					neighbours.add(GridView.getCell(0, gridPos.y-1));
				
				if (value == grid[0][gridPos.y+1])
					neighbours.add(GridView.getCell(0, gridPos.y+1));
					
				if (value == grid[1][gridPos.y])
					neighbours.add(GridView.getCell(1, gridPos.y));
					
				if (value == grid[1][gridPos.y+1])
					neighbours.add(GridView.getCell(1, gridPos.y+1));
							
			}
			
			// Bottom border, except the corners : 4 neighbours
			else if (gridPos.x == size-1 && gridPos.y != 0 && gridPos.y != size-1) {
				if (value == grid[size-1][gridPos.y-1])
					neighbours.add(GridView.getCell(size-1, gridPos.y-1));
						
				if (value == grid[size-1][gridPos.y+1])
					neighbours.add(GridView.getCell(size-1, gridPos.y+1));
				
				if (value == grid[size-2][gridPos.y-1])
					neighbours.add(GridView.getCell(size-2, gridPos.y-1));
					
				if (value == grid[size-2][gridPos.y])
					neighbours.add(GridView.getCell(size-2, gridPos.y));
						
			}
			
			// Middle of the grid : 6 neighbours
			else {
				
				if (value == grid[gridPos.x-1][gridPos.y-1])
					neighbours.add(GridView.getCell(gridPos.x-1, gridPos.y-1));
					
				if (value == grid[gridPos.x-1][gridPos.y])
					neighbours.add(GridView.getCell(gridPos.x-1, gridPos.y));
					
				if (value == grid[gridPos.x][gridPos.y-1])
					neighbours.add(GridView.getCell(gridPos.x, gridPos.y-1));
					
				if (value == grid[gridPos.x][gridPos.y+1])
					neighbours.add(GridView.getCell(gridPos.x, gridPos.y+1));
					
				if (value == grid[gridPos.x+1][gridPos.y])
					neighbours.add(GridView.getCell(gridPos.x+1, gridPos.y));
					
				if (value == grid[gridPos.x+1][gridPos.y+1])
					neighbours.add(GridView.getCell(gridPos.x+1, gridPos.y+1));			
			}	
			
//			System.out.println("Found " + neighbours.size() + " neighbours for cell " + toString());
		}	
		
	}
	
	public String toString() {
		return "(" + gridPos.x + ", " + gridPos.y + ")";
	}
	
	@Override
	public void paintComponent(Graphics g) {		
		super.paintComponent(g);
		g.setColor(color);
		g.fillPolygon(polygon);
	}
}
