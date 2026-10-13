package no.uib.inf101.tetris.model.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

/**
 * Represents a Tetromino, which is a geometric shape used in Tetris.
 * A Tetromino consists of a symbol, a boolean grid representing its shape,
 * and a position on the grid.
 */

public class Tetromino implements Iterable<GridCell> {

    /**
     * The symbol representing the Tetromino (e.g., 'L', 'T', 'O').
     * The 2D boolean array defining the shape of the Tetromino.
     * The current position of the Tetromino on the grid.
     */

    public final char symbol;
    public final boolean[][] shape;
    public CellPosition cellPosition;

    /**
     * /**
     * Constructs a Tetromino with the given shape, symbol, and initial position.
     *
     * @param shape        The 2D boolean array representing the Tetromino's shape.
     * @param symbol       The symbol identifying the Tetromino type.
     * @param cellPosition The initial position of the Tetromino on the grid.
     */

    public Tetromino(boolean[][] shape, char symbol, CellPosition cellPosition) {
        this.shape = shape;
        this.symbol = symbol;
        this.cellPosition = cellPosition;
    }

    /**
     * Rotates the Tetromino 90 degrees clockwise and returns a new Tetromino
     * instance.
     *
     * @return A new Tetromino with the rotated shape.
     */

    public Tetromino rotating() {
        boolean[][] newShape = new boolean[shape[0].length][shape.length];
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                newShape[row][col] = shape[col][shape.length - 1 - row];
            }
        }
        return new Tetromino(newShape, symbol, cellPosition);

    }

    /**
     * Creates a new Tetromino based on the given symbol.
     * Each symbol corresponds to a specific Tetromino shape.
     *
     * @param symbol The character representing the Tetromino type.
     * @return A new Tetromino instance with the corresponding shape.
     * @throws IllegalArgumentException If the symbol is not recognized.
     */

    public static Tetromino newTetromino(char symbol) {
        boolean[][] shape;
        switch (symbol) {
            case 'S':
                shape = new boolean[][] {
                        { false, false, false },
                        { false, true, true },
                        { true, true, false }
                };

                break;

            case 'L':
                shape = new boolean[][] {
                        { false, false, false },
                        { true, true, true },
                        { true, false, false }
                };
                break;

            case 'T':
                shape = new boolean[][] {
                        { false, false, false },
                        { true, true, true },
                        { false, true, false }
                };
                break;

            case 'J':
                shape = new boolean[][] {
                        { false, false, false },
                        { true, true, true },
                        { false, false, true }
                };
                break;

            case 'Z':
                shape = new boolean[][] {
                        { false, false, false },
                        { true, true, false },
                        { false, true, true }
                };
                break;

            case 'I':
                shape = new boolean[][] {
                        { false, false, false, false },
                        { true, true, true, true },
                        { false, false, false, false },
                        { false, false, false, false }
                };

                break;

            case 'O':
                shape = new boolean[][] {
                        { false, false, false, false },
                        { false, true, true, false },
                        { false, true, true, false },
                        { false, false, false, false }
                };

                break;

            default:
                throw new IllegalArgumentException("Unknown type: " + symbol);
        }
        return new Tetromino(shape, symbol, new CellPosition(0, 0));
    }

    public Tetromino shiftedBy(int deltaRow, int deltaCol) {
        CellPosition newPosition = new CellPosition(cellPosition.row() + deltaRow, cellPosition.col() + deltaCol);
        return new Tetromino(shape, symbol, newPosition);
    }

    /**
     * Shifts the Tetromino to the top center of the grid.
     * 
     * @param grid
     * @return tetromino after ahsifting
     */

    public Tetromino shiftedToTopCenterOf(GridDimension grid) {
        int col = (grid.cols() - shape[0].length) / 2;
        return shiftedBy(-1, col);
    }

    @Override
    public Iterator<GridCell> iterator() {
        List<GridCell> cells = new ArrayList<>();

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j]) {
                    CellPosition pos = new CellPosition(cellPosition.row() + i, cellPosition.col() + j);
                    cells.add(new GridCell(pos, symbol));
                }
            }
        }

        return cells.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Tetromino toCompare = (Tetromino) obj;

        boolean hasSameSymbol = symbol == toCompare.symbol;
        boolean hasSameShape = Arrays.deepEquals(shape, toCompare.shape);
        boolean hasSamePosition = Objects.equals(cellPosition, toCompare.cellPosition);

        return (hasSameSymbol && hasSameShape && hasSamePosition);

    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, Arrays.deepHashCode(shape), cellPosition);
    }

}
