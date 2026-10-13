package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;

public interface ViewableTetrisModel {

    /**
     * The dimensions of the board, i.e. number of rows and columns
     *
     * @return an object of type GridDimension
     */
    GridDimension getDimension();

    /**
     * An object that when iterated over returns all positions
     * and corresponding values
     *
     * @return an iterable object
     */
    Iterable<GridCell> getTilesOnBoard();

    /**
     * Returns an iterable collection of the grid cells
     * occupied by the currently falling Tetromino.
     *
     * @return An iterable object containing the falling Tetromino's GridCell
     *         instances.
     */

    Iterable<GridCell> getFallingTetromino();

    /**
     * Gets the current game state, which can be one of the following: ACTIVE_GAME,
     * WELCOME_SCREEN, GAME_OVER, PAUSE.
     * 
     * @return
     */

    public GameState getGameState();

    /**
     * Returns an iterable collection representing the shadow of the falling
     * Tetromino.
     * The shadow indicates where the Tetromino will land if dropped immediately.
     *
     * @return An iterable object containing the shadow's GridCell instances.
     */

    Iterable<GridCell> getShadow();

    /**
     * Sets the current game state.
     *
     * @param gameState The new GameState to be set.
     */

    public void setGameState(GameState gameState);

    /**
     * Retrieves the current score of the player.
     *
     * @return The player's score as an Integer.
     */

    public Integer getScore();

}
