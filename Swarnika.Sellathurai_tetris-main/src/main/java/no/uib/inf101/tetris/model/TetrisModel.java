package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel {

    private TetrisBoard tetrisBoard;
    public TetrominoFactory tetrominoFactory;
    public Tetromino fallingTetromino;
    private GameState gameState;
    private Integer score;

    /**
     * The TetrisModel class manages the game logic, including the board state,
     * falling Tetrominoes, and game progression.
     */

    public TetrisModel(TetrisBoard tetrisBoard, TetrominoFactory factory) {
        this.tetrisBoard = tetrisBoard;

        this.tetrominoFactory = factory;
        this.fallingTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(tetrisBoard);
        this.gameState = GameState.WELCOME_SCREEN;
        this.score = 0;
    }

    @Override
    public GridDimension getDimension() {
        return tetrisBoard;

    }

    @Override
    public Iterable<GridCell> getTilesOnBoard() {
        return tetrisBoard;
    }

    @Override
    public Iterable<GridCell> getFallingTetromino() {
        return fallingTetromino;
    }

    private boolean isPositionPossible(Tetromino tetromino) {
        for (GridCell cell : tetromino) {
            CellPosition position = cell.pos();

            if (!tetrisBoard.positionIsOnGrid(position)) {
                return false;
            }
            if (tetrisBoard.get(position) != '-') {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean moveTetromino(int deltaRow, int deltaCol) {
        Tetromino copyTetromino = fallingTetromino.shiftedBy(deltaRow, deltaCol);

        if (isPositionPossible(copyTetromino)) {
            copyTetromino = fallingTetromino.shiftedBy(deltaRow, deltaCol);
            fallingTetromino = copyTetromino;
            return true;
        }

        return false;

    }

    @Override
    public boolean rotateTetromino() {
        Tetromino candidate = fallingTetromino.rotating();
        if (isPositionPossible(candidate)) {
            fallingTetromino = candidate;
            return true;
        }
        return false;

    }

    @Override
    public void dropTetromino() {
        while (moveTetromino(1, 0)) {
        }
        lockTetromino(fallingTetromino, tetrisBoard);
    }

    private void lockTetromino(Tetromino tetromino, TetrisBoard board) {
        if (gameState == GameState.ACTIVE_GAME) {
            for (GridCell cell : fallingTetromino) {
                CellPosition position = cell.pos();
                board.set(position, cell.symbol());
            }
            int removedRows = tetrisBoard.clearFilledRows();
            score += removedRows;
            fallingTetromino = generateNewFallingTetromino();
        }

    }

    private Tetromino generateNewFallingTetromino() {
        Tetromino newTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(tetrisBoard);
        if (!isPositionPossible(newTetromino)) {
            gameState = GameState.GAME_OVER;
        }
        return newTetromino;

    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void setGameState(GameState state) {
        gameState = state;
    }

    @Override
    public int getMiliSecond() {
        return 1000;
    }

    @Override
    public void clockTick() {
        Tetromino temporaryTetromino = fallingTetromino.shiftedBy(1, 0);
        if (isPositionPossible(temporaryTetromino)) {
            fallingTetromino = temporaryTetromino;
        } else {
            lockTetromino(fallingTetromino, tetrisBoard);
        }

    }

    @Override
    public Iterable<GridCell> getShadow() {
        Tetromino shadowedTetromino = fallingTetromino;
        while (true) {
            Tetromino newShadowedTetromino = shadowedTetromino.shiftedBy(1, 0);
            if (isPositionPossible(newShadowedTetromino)) {
                shadowedTetromino = newShadowedTetromino;
            } else {
                break;
            }
        }
        return shadowedTetromino;
    }

    @Override
    public void reset() {
        gameState = GameState.ACTIVE_GAME;
        tetrisBoard.clearScreen();
        score = 0;

    }

    /**
     * Returns the current score.
     *
     * @return The player's score.
     */

    public Integer getScore() {
        return score;
    }

}
