package at.fhtw.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void constructor_shouldCreateEmptyBoard() {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                assertTrue(board.isCellEmpty(i, j));
            }
        }
    }

    @Test
    void constructor_shouldCreateNonNullBoard() {
        assertNotNull(board);
    }

    @Test
    void isCellEmpty_shouldReturnTrueForFreshCell() {
        assertTrue(board.isCellEmpty(0, 0));
    }

    @Test
    void isCellEmpty_shouldReturnFalseAfterPlacement() {
        board.place(1, 1, 'X');
        assertFalse(board.isCellEmpty(1, 1));
    }

    @Test
    void isCellEmpty_shouldReturnFalseForOutOfBoundsNegative() {
        assertFalse(board.isCellEmpty(-1, 0));
    }

    @Test
    void isCellEmpty_shouldReturnFalseForOutOfBoundsTooLarge() {
        assertFalse(board.isCellEmpty(3, 3));
    }

    @Test
    void place_shouldSetMarkerInCell() {
        board.place(0, 0, 'X');
        assertEquals('X', board.getCell(0, 0));
    }

    @Test
    void place_shouldSetDifferentMarkers() {
        board.place(0, 0, 'X');
        board.place(2, 2, 'O');
        assertEquals('X', board.getCell(0, 0));
        assertEquals('O', board.getCell(2, 2));
    }

    @Test
    void place_shouldThrowOnOutOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> board.place(5, 5, 'X'));
    }

    @Test
    void place_shouldThrowOnOccupiedCell() {
        board.place(1, 1, 'X');
        assertThrows(IllegalArgumentException.class, () -> board.place(1, 1, 'O'));
    }

    @Test
    void isFull_shouldReturnTrueWhenAllCellsFilled() {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                board.place(i, j, 'X');
            }
        }
        assertTrue(board.isFull());
    }

    @Test
    void isFull_shouldReturnFalseForEmptyBoard() {
        assertFalse(board.isFull());
    }

    @Test
    void isFull_shouldReturnFalseWithOneEmptyCell() {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (i == 2 && j == 2) continue;
                board.place(i, j, 'X');
            }
        }
        assertFalse(board.isFull());
    }

    @Test
    void clear_shouldEmptyAllCells() {
        board.place(0, 0, 'X');
        board.place(1, 1, 'O');
        board.clear();
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                assertTrue(board.isCellEmpty(i, j));
            }
        }
    }

    @Test
    void clear_shouldAllowPlacementAfterClear() {
        board.place(0, 0, 'X');
        board.clear();
        assertDoesNotThrow(() -> board.place(0, 0, 'O'));
    }

    @Test
    void clear_shouldNotKeepOldMarkers() {
        board.place(0, 0, 'X');
        board.clear();
        assertNotEquals('X', board.getCell(0, 0));
    }

    @Test
    void print_shouldNotThrowOnEmptyBoard() {
        assertDoesNotThrow(() -> board.print());
    }

    @Test
    void print_shouldNotThrowOnFilledBoard() {
        board.place(0, 0, 'X');
        board.place(1, 1, 'O');
        assertDoesNotThrow(() -> board.print());
    }
}
