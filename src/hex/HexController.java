package hex;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JOptionPane;

public class HexController {
	HexModel model;
	
	public HexController(HexModel model) {
		this.model = model;
	}
	
	public void clickOnCell(Cell c, Color color) {
		c.setColor(color);
		c.setPlayed(true);
	
		Point pos = c.getGridPos();

		// Updates the grid
		int player = -1;
		
		if (c.getColor() == Color.BLACK)
			player = HexModel.BLACK;
		
		else if (c.getColor() == Color.WHITE)
			player = HexModel.WHITE;
		
		model.setGridCase(pos.x, pos.y, player);
		
		// Updates the neighbours
		c.neighbours();
		for (Cell n : c.getNeighbours())
			n.neighbours();
		
		if (model.playerWins(player)) {
			JOptionPane.showMessageDialog(null, model.playerString() + " wins! Congratulations!");
		}
			
		
		else 
			switchPlayer();
		
//		model.displayConsoleGrid();
	}
	
	public void switchPlayer() {
		if (model.getCurrentPlayer() == HexModel.BLACK)
			model.setCurrentPlayer(HexModel.WHITE);
		
		else if (model.getCurrentPlayer() == HexModel.WHITE)
			model.setCurrentPlayer(HexModel.BLACK);
	}
}
