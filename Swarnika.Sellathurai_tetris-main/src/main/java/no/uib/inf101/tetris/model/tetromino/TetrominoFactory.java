package no.uib.inf101.tetris.model.tetromino;

/**
 * An interface for generating Tetromino pieces in the Tetris game.
 * Implementations of this interface define how new Tetrominos are created.
 */

public interface TetrominoFactory {
    Tetromino getNext();

}
