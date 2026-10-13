package no.uib.inf101.tetris.view;

import java.awt.Color;

/**
 * Defines the color theme for the Tetris game, including Tetromino colors,
 * frame color, and background color.
 */

public class ColorTheme {

    /**
     * Returns the color associated with a specific Tetromino symbol.
     *
     * @param c The character representing the Tetromino type or empty cell ('-').
     * @return The corresponding color for the given character.
     * @throws IllegalArgumentException If the character does not have an associated
     *                                  color.
     */

    public Color getCellColor(Character c) {
        Color color = switch (c) {
            case 'O' -> Color.RED;
            case 'L' -> Color.GREEN;
            case 'S' -> Color.YELLOW;
            case 'Z' -> Color.BLUE;
            case 'J' -> Color.WHITE;
            case 'T' -> Color.MAGENTA;
            case 'I' -> Color.CYAN;
            case '-' -> Color.BLACK;

            default -> throw new IllegalArgumentException(
                    "No available color for '" + c + "'");
        };
        return color;
    }

    /**
     * Returns the color of the frame surrounding the game board.
     *
     * @return The frame color.
     */

    public Color getFrameColor() {
        return Color.DARK_GRAY;
    }

    /**
     * Returns the background color of the game.
     *
     * @return The background color.
     */

    public Color getBackgroundColor() {
        return Color.GRAY;
    }

}
