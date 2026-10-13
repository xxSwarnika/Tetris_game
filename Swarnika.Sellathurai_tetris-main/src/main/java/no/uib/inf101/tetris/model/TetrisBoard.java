package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;

/**
 * Represents the Tetris game board, which extends the Grid class.
 * The board keeps track of placed Tetrominos and handles row clearing.
 */

public class TetrisBoard extends Grid {

    /**
     * Creates a new TetrisBoard with the given dimensions.
     * The board is initially filled with the character '-'.
     *
     * @param rows The number of rows in the board.
     * @param cols The number of columns in the board.
     */

    public TetrisBoard(int rows, int cols) {

        super(rows, cols, '-');
    }

    /**
     * A string representation of the board in a readable format.
     * For testing purposes.
     *
     * @return a string representation of the board
     */

    public String prettyString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < this.rows(); i++) {
            for (int j = 0; j < this.cols(); j++) {
                result.append(get(new CellPosition(i, j)));
            }
            result.append(System.lineSeparator());
        }
        return result.toString().substring(0, result.length() - 1);

    }

    /**
     * Clears all filled rows from the board and shifts the remaining rows down.
     *
     * @return The number of rows that were cleared.
     */

    public int clearFilledRows() {
        int removedRows = 0;

        for (int row = this.rows() - 1; row >= 0; row--) {
            if (isRowFilled(row)) {
                removeRow(row);
                removedRows++;
                row++;
            }
        }
        return removedRows;
    }

    private boolean isRowFilled(int row) {
        for (int col = 0; col < this.cols(); col++) {
            if (get(new CellPosition(row, col)) == '-') {
                return false;
            }
        }
        return true;
    }

    private void removeRow(int row) {
        for (int i = row; i > 0; i--) {
            copyRowTo(i - 1, i);
        }
        fillTopRowWithEmpty(0);
    }

    private void copyRowTo(int originalRow, int targetRow) {
        for (int col = 0; col < this.cols(); col++) {
            set(new CellPosition(targetRow, col), get(new CellPosition(originalRow, col)));
        }
    }

    private void fillTopRowWithEmpty(int row) {
        for (int col = 0; col < this.cols(); col++) {
            set(new CellPosition(row, col), '-');
        }

    }

    /**
     * Clears the entire board by filling all positions with '-'.
     */

    public void clearScreen() {
        for (GridCell cell : this) {
            set(cell.pos(), '-');
        }
    }

}