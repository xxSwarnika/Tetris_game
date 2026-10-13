package no.uib.inf101.tetris.model.tetromino;

/**
 * TetrominoFcatory implementation that
 * generates tetrominos based on
 * a gived pattern
 */
public class PatternedTetrominoFactory implements TetrominoFactory {

    private final String pattern;
    private int currentPosition;

    /**
     * Constructs a PatternedTetrominoFactory with
     * the specified pattern
     * 
     * @param pattern pattern used for
     *                generating tetromios
     * 
     */
    public PatternedTetrominoFactory(String pattern) {

        this.pattern = pattern;
        this.currentPosition = 0;

    }

    @Override
    public Tetromino getNext() {
        char symbol = pattern.charAt(currentPosition);
        currentPosition = (currentPosition + 1) % pattern.length();
        return Tetromino.newTetromino(symbol);
    }

}
