package no.uib.inf101.tetris.controller;

import no.uib.inf101.tetris.model.GameState;

public interface ControllableTetrisModel {

    /**
     * Moves the falling Tetromino by the specified amount
     * 
     * @param deltaRow change in row position
     * 
     * @param deltaCol change in the col position
     * @return True if the Tetromino was successfully
     *         moved, false otherwise
     */

    public boolean moveTetromino(int deltaRow, int deltaCol);

    /**
     * Rotates the active Tetromino.
     * 
     * @return true: if the Tetromino was successfully rotated, false:
     *         if the rotation was not possible.
     */

    public boolean rotateTetromino();

    /**
     * Instantly drops the active Tetromino to the lowest valid position.
     */

    public void dropTetromino();

    /**
     * Retrieves the time interval in milliseconds for game updates.
     * 
     * @return The number of milliseconds between game updates.
     */

    public int getMiliSecond();

    /**
     * Advances the game state by one tick, typically called by a game timer
     */

    public void clockTick();

    /**
     * Retrieves the current state of the game
     * 
     * @return the current GameState
     */

    public GameState getGameState();

    public void setGameState(GameState gameState);

    public void reset();

}
