package no.uib.inf101.tetris.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.tetromino.PatternedTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;

public class TestTetrisModel {

    @Test
    public void testMoveTetrominoSuccessful() {
        TetrisBoard board = new TetrisBoard(15, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        TetrisModel model = new TetrisModel(board, factory);

        boolean moved = model.moveTetromino(1, 0); // Move down
        assertTrue(moved, "Tetromino should be able to move down");
    }

    @Test
    public void testMoveTetrominoUpdatesPosition() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        TetrisModel model = new TetrisModel(board, factory);

        model.moveTetromino(0, 1);

        List<GridCell> tetroCells = new ArrayList<>();
        for (GridCell gc : model.getFallingTetromino()) {
            tetroCells.add(gc);
        }

        assertEquals(4, tetroCells.size());
        assertTrue(tetroCells.contains(new GridCell(new CellPosition(0, 4), 'I')));
        assertTrue(tetroCells.contains(new GridCell(new CellPosition(0, 5), 'I')));
        assertTrue(tetroCells.contains(new GridCell(new CellPosition(0, 6), 'I')));
        assertTrue(tetroCells.contains(new GridCell(new CellPosition(0, 7), 'I')));
    }

    @Test
    public void testMoveTetrominoOutOfBoundsFails() {

        TetrisBoard board = new TetrisBoard(15, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        TetrisModel model = new TetrisModel(board, factory);

        // Move Tetromino all the way to the left
        while (model.moveTetromino(0, -1))
            ;

        // Try moving further left (should fail)
        boolean moved = model.moveTetromino(0, -1);

        assertFalse(moved, "Tetromino should not be able to move out of the board");
    }

    @Test
    public void testMoveTetrominoIntoOccupiedSpaceFails() {

        TetrisBoard board = new TetrisBoard(15, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        TetrisModel model = new TetrisModel(board, factory);

        board.set(new CellPosition(0, 0), 'T');

        List<GridCell> startPos = new ArrayList<>();
        List<GridCell> endPos = new ArrayList<>();

        for (GridCell cell : model.getFallingTetromino()) {
            startPos.add(cell);
        }
        model.moveTetromino(0, -3);

        for (GridCell cell : model.getFallingTetromino()) {
            endPos.add(cell);
        }

        assertEquals(startPos, endPos);

    }

    @Test
    public void rotateOnce() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("T");
        TetrisModel model = new TetrisModel(board, factory);
        model.moveTetromino(1, 0);

        model.rotateTetromino();

        List<GridCell> tetroCells = new ArrayList<>();
        for (GridCell gc : model.getFallingTetromino()) {
            tetroCells.add(gc);
        }

        assertEquals(4, tetroCells.size());
        assertTrue(tetroCells.contains(new GridCell(new CellPosition(0, 4), 'T')));
        assertTrue(tetroCells.contains(new GridCell(new CellPosition(1, 5), 'T')));
        assertTrue(tetroCells.contains(new GridCell(new CellPosition(1, 4), 'T')));
        assertTrue(tetroCells.contains(new GridCell(new CellPosition(2, 4), 'T')));

    }

    @Test
    public void testDropTetromino() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory tetrominoFactory = new PatternedTetrominoFactory("I");
        TetrisModel tetrisModel = new TetrisModel(board, tetrominoFactory);

        tetrisModel.moveTetromino(2, 0);

        Tetromino startTetromino = tetrisModel.fallingTetromino;

        tetrisModel.dropTetromino();

        assertNotEquals(startTetromino, tetrisModel.fallingTetromino);
    }

    @Test
    public void testClockTick() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory tetrominoFactory = new PatternedTetrominoFactory("S");
        TetrisModel tetrisModel = new TetrisModel(board, tetrominoFactory);

        Tetromino temporaryTetromino = tetrisModel.fallingTetromino;
        tetrisModel.clockTick();

        assertNotEquals(tetrisModel.fallingTetromino, temporaryTetromino);

    }
}
