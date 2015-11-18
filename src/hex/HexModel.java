package hex;

import java.util.Iterator;
import java.util.Observable;

public class HexModel extends Observable {
	
	private int grid[][];
	private int size;
	private int nbColoredCells;
	
	public final static int GRAY = -1;
	public final static int BLACK = 0;
	public final static int WHITE = 1;
	
	private int currentPlayer;
	private boolean wins;
	
	public HexModel(int size) {
		this.size = size;
		grid = new int[size][size];
		nbColoredCells = 0;
		
		// Grid initialisation
		for (int i=0; i<size; i++) 
			for (int j=0; j<size; j++)
				grid[i][j] = GRAY;
		
		// First player is white
		currentPlayer = WHITE;
		
		wins = false;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}	
	
	public String playerString() {
		String str = "";
		
		if (currentPlayer == WHITE)
			str = "WHITE";
		
		else if (currentPlayer == BLACK)
			str = "BLACK";
		
		return str;
	}
	
	public int[][] getGrid() {
		return grid;
	}
	
	public int getGridCase(int i, int j) {
		return grid[i][j];
	}
	
	public void setGridCase(int i, int j, int player) {
		grid[i][j] = player;
	}
	
	/** Returns true is the grid is completed (= game is over) **/
	public boolean gridCompleted() {
		if (nbColoredCells == size*size)
			return true;
		return false;
	}
	
	public void reinitGame() {
//		System.out.println("reinitGame");
		
		// Grid reinitialisation
		for (int i=0; i<size; i++) 
			for (int j=0; j<size; j++)
				grid[i][j] = GRAY;
		
		currentPlayer = WHITE;
		wins = false;
	}
	
	private void searchOppositeSide(Cell onBorder, Cell c) {
		c.setVisited(true);
//		System.out.println("Visiting " + c.toString() + ", " + c.nbNeighbours() + " neighbours");
		
		Iterator i = c.getNeighbours().iterator();
		
		while (i.hasNext() && !wins) {
			Cell n = (Cell) i.next();
			// If the opposite side is reached
			if (Math.abs(onBorder.getGridPos().x - n.getGridPos().x) == size-1
				|| Math.abs(onBorder.getGridPos().y - n.getGridPos().y) == size-1) {
//				System.out.println("reached");
				wins = true;
			}
						
			else
				if (!n.isVisited()) 
					searchOppositeSide(onBorder, n);
		}	
	}
	
	public boolean playerWins(int player) {
		
//		System.out.println("player : " + player);
		
		// Initializing the attribute visited
		for (Cell c : GridView.getCells())
				c.setVisited(false);
		
		int i = 0;
		int j;
		
		while (i<size && !wins) {
			j = 0;
			while (j<size && !wins) {
				if (player == WHITE) {
					if (i == 0 && grid[i][j] == WHITE) {
//						System.out.println("White cell found on (" + i + "," + j + ")");
						searchOppositeSide(GridView.getCell(i, j), GridView.getCell(i, j));	
					}
				}
				
				else if (player == BLACK) {
					if (j == 0 && grid[i][j] == BLACK) {
//						System.out.println("Black cell found on (" + i + "," + j + ")");	
						searchOppositeSide(GridView.getCell(i, j), GridView.getCell(i, j));	
					}
				}
			
				j++;
			}
			i++;
		}
		
//		System.out.println("______");
		
		return wins;
	}
	
	public void displayConsoleGrid() {
		System.out.println("Hex grid :");
		
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				System.out.print(" | " + grid[i][j]);
			}
			System.out.print(" |");
			System.out.println("");
		}
	}
}
