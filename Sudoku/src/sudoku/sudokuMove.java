package sudoku;

public class sudokuMove {
	private sudokuCell cell;
	private int val;
	
	sudokuMove(sudokuCell cell, int val){
		this.cell = cell;
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	public sudokuCell getCell() {
		return cell;
	}
}
