package no.uib.inf101.tetris.model.tetromino;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.TetrisBoard;

public class TestTetromino {
    @Test
    public void testHashCode() {

        Tetromino t1 = Tetromino.newTetromino('T');
        Tetromino t2 = Tetromino.newTetromino('T');
        Tetromino t3 = Tetromino.newTetromino('T').shiftedBy(1, 0);
        Tetromino s1 = Tetromino.newTetromino('S');
        Tetromino s2 = Tetromino.newTetromino('S').shiftedBy(0, 0);

        assertEquals(t1.hashCode(), t2.hashCode(), "Same tetrominoes have same hashcode");
        assertEquals(s1.hashCode(), s2.hashCode(), "Same tetrominoes have same hashcode");

        assertNotEquals(t1.hashCode(), t3.hashCode(),
                "Different tetrominoes with different position have another hascode");
        assertNotEquals(t1.hashCode(), s1.hashCode(),
                "Different tetrominoes with different symbols have another hascode");
    }

    @Test
    public void testIteratorT() {
        Tetromino tetro = Tetromino.newTetromino('T');

        tetro = tetro.shiftedToTopCenterOf(new TetrisBoard(15, 10));
        // Collect which objects are iterated through
        List<GridCell> objs = new ArrayList<>();
        for (GridCell gc : tetro) {
            objs.add(gc);
        }

        System.err.println(objs);

        // Check that we got the expected GridCell objects
        assertEquals(4, objs.size());
        assertTrue(objs.contains(new GridCell(new CellPosition(0, 3), 'T')));
        assertTrue(objs.contains(new GridCell(new CellPosition(0, 4), 'T')));
        assertTrue(objs.contains(new GridCell(new CellPosition(0, 5), 'T')));
        assertTrue(objs.contains(new GridCell(new CellPosition(1, 4), 'T')));
    }

    @Test
    public void testIteratorS() {
        // Create a standard 'S' tetromino placed at (10, 100) to test
        Tetromino tetro = Tetromino.newTetromino('S');
        tetro = tetro.shiftedBy(10, 100);

        // Collect which objects are iterated through
        List<GridCell> objs = new ArrayList<>();
        for (GridCell gc : tetro) {
            objs.add(gc);
        }

        // Check that we got the expected GridCell objects
        assertEquals(4, objs.size());
        assertTrue(objs.contains(new GridCell(new CellPosition(11, 102), 'S')));
        assertTrue(objs.contains(new GridCell(new CellPosition(11, 101), 'S')));
        assertTrue(objs.contains(new GridCell(new CellPosition(12, 100), 'S')));
        assertTrue(objs.contains(new GridCell(new CellPosition(12, 101), 'S')));
    }

    @Test
    public void testEquals() {

        Tetromino tetromino1 = Tetromino.newTetromino('S');

        Tetromino tetromino2 = Tetromino.newTetromino('S');

        assertTrue(tetromino1.equals(tetromino2));

    }

    @Test
    public void testShifting() {
        Tetromino original = Tetromino.newTetromino('O');
        Tetromino shifted = original.shiftedBy(2, 3);

        // Sjekker at posisjonen har endret seg riktig
        assertEquals(new CellPosition(2, 3), shifted.cellPosition, "Tetromino skal flytte seg korrekt");

        // Sjekker at shape og symbol er uendret
        assertArrayEquals(original.shape, shifted.shape, "Shape skal forbli uendret");
        assertEquals(original.symbol, shifted.symbol, "Symbol skal forbli uendret");

        // Sjekker at det er et nytt objekt (immutability)
        assertNotSame(original, shifted, "shiftedBy() skal returnere et nytt Tetromino-objekt");
    }

    @Test
    public void doubbleMovement() {
        Tetromino oTetromino = Tetromino.newTetromino('O');
        oTetromino.shiftedBy(10, 20);
        Tetromino firsTetromino = oTetromino.shiftedBy(1, 2);
        Tetromino secondTetromino = oTetromino.shiftedBy(2, 4);

        assertEquals(firsTetromino.shiftedBy(1, 2), secondTetromino);
    }

    @Test
    public void testRotateTetrominoT() {

        Tetromino tetromino = Tetromino.newTetromino('T');
        tetromino = tetromino.rotating();

        List<GridCell> objs = new ArrayList<>();
        for (GridCell gc : tetromino) {
            objs.add(gc);
        }

        System.err.println(objs);
        assertEquals(4, objs.size());
        assertTrue(objs.contains(new GridCell(new CellPosition(0, 1), 'T')));
        assertTrue(objs.contains(new GridCell(new CellPosition(1, 1), 'T')));
        assertTrue(objs.contains(new GridCell(new CellPosition(1, 2), 'T')));
        assertTrue(objs.contains(new GridCell(new CellPosition(2, 1), 'T')));
    }

}
