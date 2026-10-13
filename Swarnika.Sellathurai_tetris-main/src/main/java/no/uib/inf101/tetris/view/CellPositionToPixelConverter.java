package no.uib.inf101.tetris.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;

/**
 * Converts a cell position in a grid to its corresponding pixel position
 * in a graphical interface.
 */

public class CellPositionToPixelConverter {

    private final Rectangle2D box;
    private final GridDimension gd;
    private final double margin;

    /**
     * Creates a converter that calculates pixel coordinates for grid cells.
     *
     * @param box    The bounding box defining the area where the grid is drawn.
     * @param gd     The grid dimensions, specifying the number of rows and columns.
     * @param margin The margin space between grid cells.
     */

    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
        this.box = box;
        this.gd = gd;
        this.margin = margin;
    }

    /**
     * Calculates the pixel bounds for a specific cell in the grid.
     *
     * @param cellPosition The position of the cell in the grid.
     * @return A Rectangle2D, representing the pixel bounds of the cell.
     */

    public Rectangle2D getBoundsForCell(CellPosition cellPosition) {
        double cellW = (box.getWidth() - margin * gd.cols() - margin) / gd.cols();
        double cellH = (box.getHeight() - margin * gd.rows() - margin) / gd.rows();
        double cellX = box.getX() + margin + (cellW + margin) * cellPosition.col();
        double cellY = box.getY() + margin + (cellH + margin) * cellPosition.row();
        return new Rectangle2D.Double(cellX, cellY, cellW, cellH);
    }

}
