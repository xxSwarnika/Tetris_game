package no.uib.inf101.tetris;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import no.uib.inf101.tetris.controller.TetrisController;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.TetrisView;

/**
 * The Main class initializes and starts the Tetris game.
 * It sets up the game model, view, and controller, and creates the game window.
 */

public class Main {

	/**
	 * The title of the game window.
	 */

	public static final String WINDOW_TITLE = "INF101 Tetris";

	/**
	 * The main entry point for the Tetris game.
	 * This method sets up the game by initializing the board, model, view, and
	 * controller.
	 * It also creates the game window and positions it in the center of the screen.
	 *
	 * @param args Command-line arguments (not used).
	 */

	public static void main(String[] args) {

		int rows = 15;
		int cols = 10;

		TetrisBoard tetrisBoard = new TetrisBoard(rows, cols);

		TetrominoFactory tetrominoFactory = new RandomTetrominoFactory();

		TetrisModel model = new TetrisModel(tetrisBoard, tetrominoFactory);
		TetrisView view = new TetrisView(model);
		TetrisController controller = new TetrisController(model, view);

		JFrame frame = new JFrame(WINDOW_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(view);
		frame.pack();
		frame.setVisible(true);
		// Open GUI in the middle of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
	}

}
