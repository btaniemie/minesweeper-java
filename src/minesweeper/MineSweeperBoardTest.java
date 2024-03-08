package minesweeper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MineSweeperBoardTest {
	MineSweeperBoard b1;
	MineSweeperBoard b2;

	@BeforeEach
	void setUp() throws Exception {
		b1 = new MineSweeperBoard();
		b2 = new MineSweeperBoard();
		
	}

	@Test
	void testConstructorsWithoutParameters() {
		assertEquals(3, b1.getRows());
		assertEquals(4, b1.getColumns());
		assertEquals(2, b1.getNumMines());
		
		assertEquals(MineSweeperBoard.MINE, b1.getCell(0, 0));
		assertEquals(MineSweeperBoard.MINE, b1.getCell(2, 1)); 
		
	} 
	
	@Test
	void testConstructorsWithParameters() {
		MineSweeperBoard l1 = new MineSweeperBoard(1);
		assertEquals(5, l1.getRows());
		assertEquals(10, l1.getColumns());
		assertEquals(3, l1.getNumMines());
		
		MineSweeperBoard l2 = new MineSweeperBoard(2);
		assertEquals(10, l2.getRows());
		assertEquals(15, l2.getColumns());
		assertEquals(15, l2.getNumMines());
		
		MineSweeperBoard l3 = new MineSweeperBoard(3);
		assertEquals(15, l3.getRows());
		assertEquals(20, l3.getColumns());
		assertEquals(45, l3.getNumMines());
		
	}
	@Test
	void testnumAdjMines() {
		assertEquals(1, b1.numAdjMines(0, 1));
		assertEquals(0, b1.numAdjMines(2, 1));
	}
	
	@Test
	void testUncoverCell() { 
		b1.uncoverCell(0, 0);
		assertEquals(MineSweeperBoard.UNCOVERED_MINE, b1.getCell(0, 0));
		
		b1.uncoverCell(1, 1);
		assertEquals(2, b1.getCell(1, 1));
		
		b1.uncoverCell(1, 1);
		assertEquals(2, b1.getCell(1, 1));
	}
	
	@Test
	void testFlagCell() { 
		b1.flagCell(0, 0);
		assertEquals(MineSweeperBoard.FLAGGED_MINE, b1.getCell(0, 0));
		
		b1.flagCell(0, 0);
		assertEquals(MineSweeperBoard.MINE, b1.getCell(0, 0)); 
		
		b1.flagCell(2, 3);
		assertEquals(MineSweeperBoard.FLAG, b1.getCell(2, 3));
		
		b1.flagCell(2, 3);
		assertEquals(MineSweeperBoard.COVERED_CELL, b1.getCell(2, 3));
		
		b1.uncoverCell(2, 0);
		assertEquals(1, b1.getCell(2, 0));
		b1.flagCell(0, 2);
		assertEquals(1, b1.getCell(2, 0));

	}
	
	@Test
	void testRevealBoard() {
		b1.revealBoard();
		assertEquals(MineSweeperBoard.UNCOVERED_MINE, b1.getCell(0, 0));
		assertEquals(MineSweeperBoard.UNCOVERED_MINE, b1.getCell(2, 1));
		
		assertEquals(1, b1.getCell(0, 1)); 
		assertEquals(1, b1.getCell(1, 2)); 
		assertEquals(1, b1.getCell(2, 0)); 
		assertEquals(1, b1.getCell(2, 2)); 
		
		assertEquals(2, b1.getCell(1, 0));
		assertEquals(2, b1.getCell(1, 1));
		
		assertEquals(0, b1.getCell(0, 2));
		assertEquals(0, b1.getCell(0, 3));
		assertEquals(0, b1.getCell(1, 3));
		assertEquals(0, b1.getCell(2, 3));
	}
	
	@Test
	void testGameLost() {
		b1.uncoverCell(0,  0);
		assertEquals(true, b1.gameLost());
		
		assertEquals(false, b2.gameLost());
	}
	
	@Test 
	void testGameWon() {
		for (int i = 0; i < b1.getRows(); i++) {
			for (int j = 0; j < b1.getColumns(); j++) {
				if (b1.getCell(i, j) != MineSweeperBoard.MINE) {
					b1.uncoverCell(i, j);
				}
				else if (b1.getCell(i, j) == MineSweeperBoard.MINE) {
					b1.flagCell(i, j);
				}
			}	
		}
		assertEquals(true, b1.gameWon()); 
		
		assertEquals(false, b2.gameWon());
		
		b2.flagCell(0, 0);
		b2.flagCell(0, 1);
		assertEquals(false, b2.gameWon());
		
		b2.flagCell(0, 1);
		b2.flagCell(0, 2);
		for (int i = 0; i < b1.getRows(); i++) {
			for (int j = 0; j < b1.getColumns(); j++) {
				if (b1.getCell(i, j) != MineSweeperBoard.FLAGGED_MINE) {
					b1.uncoverCell(i, j);
				}
				if (b1.getCell(i, j) == b1.getCell(2, 3)) {
					
				}
			}	
		}
		assertEquals(false, b2.gameWon()); 
	}

}
