package sudoku;

import java.awt.TextField;

public class sudokuCell {
	private int col;
	private int row;
	private TextField txtfld;
	
	sudokuCell(int col, int row, TextField txtfld){
		this.col = col;
		this.row = row;
		this.txtfld = txtfld;
	}
	
	int getCol(){
		return col;
	}
	
	int getRow() {
		return row;
	}
	
	TextField getTxtfld() {
		return txtfld;
	}
	
}
