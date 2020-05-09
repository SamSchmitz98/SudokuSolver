package sudoku;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import org.w3c.dom.*;

public class sudokuInterface extends Frame  implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<sudokuCell> cells;
	private Button btn;
	
	public sudokuInterface() {
		sudokuSolver solv = new sudokuSolver();
		setLayout(new FlowLayout());
		cells = new ArrayList<sudokuCell>();
		for (int i = 0; i < 9; i ++) {
			for (int c = 0; c < 9; c++) {
				cells.add(new sudokuCell(c, i, new TextField()));

			}
		}
		for (sudokuCell cur : cells) {
			cur.getTxtfld().setEditable(true);
			if (cur.getCol() % 3 == 0 && cur.getCol() != 0) {
				add(new Label(" "));
			}
			add(cur.getTxtfld());
			if (cur.getCol() == 8 && (cur.getRow()+1) % 3 == 0 && cur.getRow() != 8) {
				add(new Label("                                                                                                     "));
			}
		}
		btn = new Button("Solve");
		add(btn);
		addWindowListener(new WindowAdapter() {
	         @Override
	         public void windowClosing(WindowEvent evt) {
	            System.exit(0);  // Terminate the program
	         }
	      });
		btn.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent evt) {
	        	 for (sudokuCell cur : cells) {
	        		 if (!cur.getTxtfld().getText().equals("")) {
	        			 cur.getTxtfld().setEditable(false);
	        		 }
	        	 }
	        	 if (!solv.hasFreeCell(cells)) {
	        		 return;
	        	 }
	        	 sudokuCell cur = solv.nextFreeCell(cells);
	        	 sudokuMove curmov = solv.hasValidMove(cells, cur.getCol(), cur.getRow());
	        	 solv.move(cells, curmov);
	        	 while (solv.hasFreeCell(cells)) {
	        		 cur = solv.nextFreeCell(cells);
	        		 curmov = solv.hasValidMove(cells, cur.getCol(), cur.getRow());
	        		 while (curmov == null) {
	        			 curmov = solv.undoMove(cells);
	        			 cur = solv.nextFreeCell(cells);
	        			 curmov = solv.hasValidMove(cells, cur.getCol(), cur.getRow(), curmov.getVal());
	        		 }
	        		 solv.move(cells, curmov);
	        	 }
	         }
	      });
		setTitle("Sudoku");
		setSize(330, 390);
		setVisible(true);
	}
	public static void main(String args[]) {
		sudokuInterface app = new sudokuInterface();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
