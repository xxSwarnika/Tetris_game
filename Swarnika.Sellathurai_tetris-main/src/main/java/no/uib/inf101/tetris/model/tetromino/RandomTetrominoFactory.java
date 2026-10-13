package no.uib.inf101.tetris.model.tetromino;

import java.util.Random;

/**
 * The RandomTetrominoFactory class generates random Tetromino pieces
 * for the Tetris game.
 */

public class RandomTetrominoFactory implements TetrominoFactory {

    @Override
    public Tetromino getNext() {
        String tetrominos = "LJSZTIO";
        int randomTetromino = new Random().nextInt(tetrominos.length());
        char symbol = tetrominos.charAt(randomTetromino);
        return Tetromino.newTetromino(symbol);
    }

}
