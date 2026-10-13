package no.uib.inf101.tetris.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;

/**
 * The TetrisView class is responsible for rendering the Tetris game.
 * It displays the game board, falling Tetromino, and handles different game
 * states.
 */

public class TetrisView extends JPanel {

    /**
     * Outer margin around the game board.
     * 
     * Margin between individual cells
     * 
     * Preferred side length for each cell
     */

    public static final int OUTERMARGIN = 15;
    public static final int CELLMARGIN = 2;
    public static final int PREFERREDSIDESIZE = 40;

    private ViewableTetrisModel viewableTetrisModel;
    private ColorTheme colorTheme;

    /**
     * Constructs a TetrisView to render the game.
     *
     * @param viewableTetrisModel The model providing game data for rendering.
     */

    public TetrisView(ViewableTetrisModel viewableTetrisModel) {
        this.viewableTetrisModel = viewableTetrisModel;

        this.colorTheme = new ColorTheme();
        this.setBackground(colorTheme.getBackgroundColor());

        this.setFocusable(true);
        this.setPreferredSize(getDefaultSize(viewableTetrisModel.getDimension()));
    }

    @Override
    public void paintComponent(Graphics g) {
        this.setBackground(colorTheme.getBackgroundColor());
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (viewableTetrisModel.getGameState() == GameState.ACTIVE_GAME) {
            drawGame(g2);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 10));
            g2.drawString("SCORE: " + viewableTetrisModel.getScore(), 350, 10);
            g2.drawString("PRESS P TO PAUSE", 10, 10);
        } else if (viewableTetrisModel.getGameState() == GameState.WELCOME_SCREEN) {
            g2.setColor(Color.DARK_GRAY);
            g2.fillRect(0, 0, this.getWidth(), this.getHeight());
            g2.setColor(Color.ORANGE);
            g2.setFont(new Font("Arial", Font.BOLD, 48));
            int x = (this.getWidth() - g2.getFontMetrics().stringWidth("WELCOME")) / 2;
            int y = (this.getHeight() - g2.getFontMetrics().getHeight()) / 2;
            g2.drawString("WELCOME", x, y);
            g2.setFont(new Font("Arial", Font.BOLD, 35));
            int x2 = (this.getWidth() - g2.getFontMetrics().stringWidth("PRESS ENTER TO START")) / 2;
            int y2 = (this.getHeight() - g2.getFontMetrics().getHeight()) / 2 + 100;
            g2.drawString("PRESS ENTER TO START", x2, y2);
        } else if (viewableTetrisModel.getGameState() == GameState.GAME_OVER) {
            g2.setColor(Color.DARK_GRAY);
            g2.fillRect(0, 0, this.getWidth(), this.getHeight());
            g2.setColor(Color.ORANGE);
            g2.setFont(new Font("Arial", Font.BOLD, 48));
            int x = (this.getWidth() - g2.getFontMetrics().stringWidth("GAME OVER")) / 2;
            int y = (this.getHeight() - g2.getFontMetrics().getHeight()) / 2;
            g2.drawString("GAME OVER", x, y);
            g2.setFont(new Font("Arial", Font.BOLD, 30));
            int x2 = (this.getWidth() - g2.getFontMetrics().stringWidth("PRESS ENTER TO RESTART")) / 2;
            int y2 = (this.getHeight() - g2.getFontMetrics().getHeight()) / 2 + 100;
            g2.drawString("PRESS ENTER TO RESTART", x2, y2);
            g2.drawString("DIN SCORE:" + viewableTetrisModel.getScore(), x2, 500);
        } else if (viewableTetrisModel.getGameState() == GameState.PAUSE) {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, this.getWidth(), this.getHeight());
            g2.setColor(Color.ORANGE);
            g2.setFont(new Font("Arial", Font.BOLD, 48));
            int x = (this.getWidth() - g2.getFontMetrics().stringWidth("PAUSE")) / 2;
            int y = (this.getHeight() - g2.getFontMetrics().getHeight()) / 2;
            g2.drawString("PAUSE", x, y);
            g2.setFont(new Font("Arial", Font.BOLD, 30));
            int x2 = (this.getWidth() - g2.getFontMetrics().stringWidth("PRESS P TO CONTINUE")) / 2;
            int y2 = (this.getHeight() - g2.getFontMetrics().getHeight()) / 2 + 100;
            g2.drawString("PRESS P TO CONTINUE", x2, y2);

        }

    }

    private void drawGame(Graphics2D g2) {
        Rectangle2D box = new Rectangle2D.Double(
                OUTERMARGIN,
                OUTERMARGIN,
                this.getWidth() - OUTERMARGIN * 2,
                this.getHeight() - OUTERMARGIN * 2);
        g2.setColor(colorTheme.getFrameColor());
        g2.fill(box);

        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(box,
                viewableTetrisModel.getDimension(), CELLMARGIN);

        drawCells(g2, viewableTetrisModel.getTilesOnBoard(), converter, colorTheme, false);
        drawCells(g2, viewableTetrisModel.getFallingTetromino(), converter, colorTheme, false);
        drawCells(g2, viewableTetrisModel.getShadow(), converter, colorTheme, true);
    }

    private static void drawCells(Graphics2D g2, Iterable<GridCell> iterable,
            CellPositionToPixelConverter converter, ColorTheme colorTheme, Boolean shadow) {
        for (GridCell cell : iterable) {
            CellPosition pos = cell.pos();
            Character c = cell.symbol();
            Rectangle2D tile = converter.getBoundsForCell(pos);

            g2.setColor(colorTheme.getCellColor(c));
            if (shadow) {
                g2.draw(tile);
                g2.setColor(new Color(255, 255, 255, 40));
            }
            g2.fill(tile);

        }
    }

    private static Dimension getDefaultSize(GridDimension gd) {
        int width = (int) (PREFERREDSIDESIZE * gd.cols() + CELLMARGIN * (gd.cols() + 1) + 2 * OUTERMARGIN);
        int height = (int) (PREFERREDSIDESIZE * gd.rows() + CELLMARGIN * (gd.cols() + 1) + 2 * OUTERMARGIN);
        return new Dimension(width, height);
    }

}
