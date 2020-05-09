package sudoku;

import java.awt.TextField;
import java.util.ArrayList;
import java.util.Stack;

public class sudokuSolver {
	private Stack<sudokuMove> moves;
	
	public sudokuSolver() {
		moves = new Stack<sudokuMove>();
	}
	
	Stack<sudokuMove> getMoves(){
		return moves;
	}
	
	boolean hasFreeCell(ArrayList<sudokuCell> cells) {
		for (sudokuCell cur : cells) {
			if (cur.getTxtfld().getText().equals("")) {
				return true;
			}
		}
		return false;
	}
	
	sudokuCell nextFreeCell(ArrayList<sudokuCell> cells) {
		if (!hasFreeCell(cells))
			return null;
		for (sudokuCell cur : cells) {
			if (cur.getTxtfld().getText().equals("")) {
				return cur;
			}
		} 
		return null;
	}
	
	ArrayList<sudokuCell> getCellsInCol(ArrayList<sudokuCell> cells, int col){
		ArrayList<sudokuCell> result = new ArrayList<sudokuCell>();
		for (sudokuCell cur : cells) {
			if (cur.getCol() == col) {
				result.add(cur);
			}
		}
		return result;
	}
	
	ArrayList<sudokuCell> getCellsInRow(ArrayList<sudokuCell> cells, int row){
		ArrayList<sudokuCell> result = new ArrayList<sudokuCell>();
		for (sudokuCell cur : cells) {
			if (cur.getRow() == row) {
				result.add(cur);
			}
		}
		return result;
	}
	
	ArrayList<sudokuCell> getCellsInBox(ArrayList<sudokuCell> cells, int col, int row){
		ArrayList<sudokuCell> result = new ArrayList<sudokuCell>();
			for (sudokuCell cur : cells) {
				if ((cur.getCol() == (col/3)*3 || cur.getCol() == (col/3)*3 + 1 || cur.getCol() == (col/3)*3 + 2) && (cur.getRow() == (row/3)*3 || cur.getRow() == (row/3)*3 + 1 || cur.getRow() == (row/3)*3 + 2)) {
					result.add(cur);
				}
			}
		return result;
	}
	
	sudokuMove hasValidMove(ArrayList<sudokuCell> cells, int col, int row) {
		return hasValidMove(cells, col, row, 0);
	}
	
	sudokuMove hasValidMove(ArrayList<sudokuCell> cells, int col, int row, int val) {
		sudokuCell cell = new sudokuCell(col, row, new TextField());;
		int count = 0;
		ArrayList<sudokuCell> collist = getCellsInCol(cells, col);
		ArrayList<sudokuCell> rowlist = getCellsInRow(cells, row);
		ArrayList<sudokuCell> boxlist = getCellsInBox(cells, col, row);
		for (sudokuCell cur : cells) {
			if (cur.getCol() == col && cur.getRow() == row) {
				cell = cur;
				if (!cell.getTxtfld().getText().equals("")) {
					return null;
				}
				collist.remove(cell);
				rowlist.remove(cell);
				boxlist.remove(cell);
			}
		}
		for (int i = val + 1; i < 10; i++) {
			for (sudokuCell cur : collist) {
				if (cur.getTxtfld().getText().equals("" + i)) {
					break;
				}
				else {
					count += 1;
				}
			}
			for (sudokuCell cur : rowlist) {
				if (cur.getTxtfld().getText().equals("" + i)) {
					break;
				}
				else {
					count += 1;
				}
			}
			for (sudokuCell cur : boxlist) {
				if (cur.getTxtfld().getText().equals("" + i)) {
					break;
				}
				else {
					count += 1;
				}
			}
			if (count == 24) {
				return new sudokuMove(cell, i);
			}
			else {
				count = 0;
			}
		}
		return null;
	}
	
	void move(ArrayList<sudokuCell> cells, sudokuMove move) {
		sudokuCell cell = move.getCell();
		for (sudokuCell cur : cells) {
			if (move.getCell().equals(cur)) {
				cell = cur;
			}
		}
		cell.getTxtfld().setText("" + move.getVal());
		moves.add(move);
	}
	
	sudokuMove undoMove(ArrayList<sudokuCell> cells) {
		sudokuMove result = moves.pop();
		for (sudokuCell cur : cells) {
			if (result.getCell() == cur) {
				result.getCell().getTxtfld().setText("");
			}
		}
		//if (result.getVal() == 9)
			//undoMove(cells);
		return result;
	}
	

}
