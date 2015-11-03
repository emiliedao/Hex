package tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

import org.junit.Test;

import hex.Cell;
import hex.HexModel;

public class HexTests {
	
	@Test
	public void testCellContains() {
		Point origin = new Point(100, 100);
		Cell c = new Cell(null, origin.x, origin.y, null, Color.GRAY, false);
		int radius = Cell.getRadius();		
		Point p1 = new Point(origin.x, origin.y);	
		assertTrue(c.getPolygon().contains(p1));
		
	}
//	
//	@Test
//	public void testTopLeftCornerNeighbors() {
//		int size = 7;
//		HexModel model = new HexModel(size);
//		int[][] grid = model.getGrid();
//		
//		Cell topLeftCorner = new Cell(grid, 0, 0, new Point(0, 0), Color.WHITE, false);
//		model.setGridCase(0, 0, HexModel.WHITE);
//		
//		assertFalse(topLeftCorner.hasASameColorNeighbour());
//		
//		model.setGridCase(0, 1, HexModel.WHITE);
//		assertTrue(topLeftCorner.hasASameColorNeighbour());
//		
//		model.setGridCase(0, 1, HexModel.BLACK);
//		assertFalse(topLeftCorner.hasASameColorNeighbour());
//		
//		
//		model.setGridCase(1, 0, HexModel.WHITE);
//		assertTrue(topLeftCorner.hasASameColorNeighbour());
//		
//		model.setGridCase(1, 0, HexModel.BLACK);
//		assertFalse(topLeftCorner.hasASameColorNeighbour());
//		
//		model.setGridCase(1, 1, HexModel.WHITE);
//		assertTrue(topLeftCorner.hasASameColorNeighbour());
//	}
//	
//	@Test
//	public void testTopRightCornerNeighbours() {
//		int SIZE = 7;
//		HexModel model = new HexModel(SIZE);
//		int[][] grid = model.getGrid();
//		int size = grid[0].length;
//		
//		Cell topRightCorner = new Cell(grid, 0, 0, new Point(0, size-1), Color.BLACK, false);
//		model.setGridCase(0, size-1, HexModel.BLACK);
//		
//		assertFalse(topRightCorner.hasASameColorNeighbour());
//		
//		model.setGridCase(0, size-2, HexModel.BLACK);
//		assertTrue(topRightCorner.hasASameColorNeighbour());
//		
//		model.setGridCase(0, size-2, HexModel.WHITE);
//		assertFalse(topRightCorner.hasASameColorNeighbour());
//		
//		model.setGridCase(1, size-1, HexModel.BLACK);
//		assertTrue(topRightCorner.hasASameColorNeighbour());
//		
//		model.setGridCase(1, size-1, HexModel.WHITE);
//		assertFalse(topRightCorner.hasASameColorNeighbour());
//		
//		model.setGridCase(1, size-2, HexModel.BLACK);
//		assertFalse(topRightCorner.hasASameColorNeighbour());
//	}
//	 
//	@Test
//	public void testBottomleftCornerNeighbours() {
//		int SIZE = 7;
//		HexModel model = new HexModel(SIZE);
//		int[][] grid = model.getGrid();
//		int size = grid[0].length;
//		
//		Cell bottomLeftCorner = new Cell(grid, 0, 0, new Point(size-1, 0), Color.WHITE, false);
//		model.setGridCase(size-1, 0, HexModel.WHITE);
//		
//		assertFalse(bottomLeftCorner.hasASameColorNeighbour());
//		
//		model.setGridCase(size-1, 1, HexModel.WHITE);
//		assertTrue(bottomLeftCorner.hasASameColorNeighbour());
//		
//		model.setGridCase(size-1, 1, HexModel.BLACK);
//		assertFalse(bottomLeftCorner.hasASameColorNeighbour());
//		
//		model.setGridCase(size-2, 0, HexModel.WHITE);
//		assertTrue(bottomLeftCorner.hasASameColorNeighbour());
//		
//		model.setGridCase(size-2, 0, HexModel.BLACK);
//		assertFalse(bottomLeftCorner.hasASameColorNeighbour());
//		
//		model.setGridCase(size-2, 1, HexModel.WHITE);
//		assertFalse(bottomLeftCorner.hasASameColorNeighbour());
//	}
//	
//	@Test
//	public void testBottomRightCornerNeighbours() {
//		int SIZE = 7;
//		HexModel model = new HexModel(SIZE);
//		int[][] grid = model.getGrid();
//		int size = grid[0].length;
//		
//		Cell bottomRightCorner = new Cell(grid, 0, 0, new Point(size-1, size-1), Color.WHITE, false);
//		model.setGridCase(size-1, size-1, HexModel.WHITE);
//		
//		assertFalse(bottomRightCorner.hasASameColorNeighbour());
//		
//		model.setGridCase(size-1, size-2, HexModel.WHITE);
//		assertTrue(bottomRightCorner.hasASameColorNeighbour());
//		
//		model.setGridCase(size-1, size-2, HexModel.BLACK);
//		assertFalse(bottomRightCorner.hasASameColorNeighbour());
//		
//		model.setGridCase(size-2, size-1, HexModel.WHITE);
//		assertTrue(bottomRightCorner.hasASameColorNeighbour());
//		
//		model.setGridCase(size-2, size-2, HexModel.WHITE);
//		assertTrue(bottomRightCorner.hasASameColorNeighbour());
//	}
}
