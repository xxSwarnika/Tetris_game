package no.uib.inf101.tetris.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.view.TetrisView;

/**
 * The TetrisController class handles user input and game updates.
 * It listens for keyboard events to control Tetromino movements and
 * manages the game timer.
 */

public class TetrisController implements KeyListener {

    ControllableTetrisModel model;
    TetrisView view;
    Timer timer;
    TetrisSong tetrisSong = new TetrisSong();

    /**
     * Creates a TetrisController to manage user input and game updates
     * 
     * @param model The game model that controls Tetromino movements and state
     * @param view  The game view responsible for rendering the game
     */

    public TetrisController(ControllableTetrisModel model, TetrisView view) {
        this.timer = new Timer(model.getMiliSecond(), this::clockTick);
        this.model = model;
        this.view = view;
        view.setFocusable(true);
        view.addKeyListener(this);
        this.timer.start();
        this.tetrisSong.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (model.getGameState() == GameState.ACTIVE_GAME) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                model.moveTetromino(0, -1);
                // Venstre piltast ble trykket
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                model.moveTetromino(0, 1);
                // Høyre piltast ble trykket
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                model.moveTetromino(1, 0);
                timer.restart();
                // Ned piltast ble trykket
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                model.rotateTetromino();
                // Opp piltast ble trykket
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                model.dropTetromino();
                timer.restart();
                // Mellemromstasten ble trykket
            } else if (e.getKeyCode() == KeyEvent.VK_P) {
                model.setGameState(GameState.PAUSE);
            }
        } else if (model.getGameState() == GameState.WELCOME_SCREEN) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                model.setGameState(GameState.ACTIVE_GAME);
            }
        } else if (model.getGameState() == GameState.GAME_OVER) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                model.reset();
            }
        } else if (model.getGameState() == GameState.PAUSE) {
            if (e.getKeyCode() == KeyEvent.VK_P) {
                model.setGameState(GameState.ACTIVE_GAME);
            }
        }

        {

        }

        view.repaint();
        view.paintImmediately(view.getBounds());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void clockTick(ActionEvent e) {
        if (model.getGameState() == GameState.ACTIVE_GAME) {
            model.clockTick();
            getDelay();
            view.repaint();
        }

    }

    private void getDelay() {
        timer.setDelay(model.getMiliSecond());
        timer.setInitialDelay(model.getMiliSecond());
    }

}
