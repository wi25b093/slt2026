package at.fhtw.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {

    private TicTacToe game;

    @BeforeEach
    void setUp() {
        game = new TicTacToe();
    }

    @Test
    void constructor_shouldInitializePlayers() {
        assertNotNull(game.getPlayer1());
        assertNotNull(game.getPlayer2());
        assertEquals('X', game.getPlayer1().getMarker());
        assertEquals('O', game.getPlayer2().getMarker());
    }

    @Test
    void constructor_shouldStartWithPlayerX() {
        assertEquals('X', game.getCurrentPlayer().getMarker());
    }

    @Test
    void constructor_shouldNotStartWithPlayerO() {
        assertNotEquals('O', game.getCurrentPlayer().getMarker());
    }

    @Test
    void switchCurrentPlayer_shouldChangeFromXtoO() {
        game.switchCurrentPlayer();
        assertEquals('O', game.getCurrentPlayer().getMarker());
    }

    @Test
    void switchCurrentPlayer_shouldChangeBackToX() {
        game.switchCurrentPlayer();
        game.switchCurrentPlayer();
        assertEquals('X', game.getCurrentPlayer().getMarker());
    }

    @Test
    void switchCurrentPlayer_shouldNotKeepSamePlayer() {
        Player before = game.getCurrentPlayer();
        game.switchCurrentPlayer();
        Player after = game.getCurrentPlayer();
        assertNotSame(before, after);
    }

    @Test
    void hasWinner_shouldDetectWinInRow() {
        Board board = game.getBoard();
        board.place(0, 0, 'X');
        board.place(0, 1, 'X');
        board.place(0, 2, 'X');
        assertTrue(game.hasWinner());
    }

    @Test
    void hasWinner_shouldDetectWinInColumn() {
        Board board = game.getBoard();
        board.place(0, 1, 'X');
        board.place(1, 1, 'X');
        board.place(2, 1, 'X');
        assertTrue(game.hasWinner());
    }

    @Test
    void hasWinner_shouldDetectWinInMainDiagonal() {
        Board board = game.getBoard();
        board.place(0, 0, 'X');
        board.place(1, 1, 'X');
        board.place(2, 2, 'X');
        assertTrue(game.hasWinner());
    }

    @Test
    void hasWinner_shouldDetectWinInAntiDiagonal() {
        Board board = game.getBoard();
        board.place(0, 2, 'X');
        board.place(1, 1, 'X');
        board.place(2, 0, 'X');
        assertTrue(game.hasWinner());
    }

    @Test
    void hasWinner_shouldReturnFalseOnEmptyBoard() {
        assertFalse(game.hasWinner());
    }

    @Test
    void hasWinner_shouldReturnFalseWhenOnlyTwoInARow() {
        Board board = game.getBoard();
        board.place(0, 0, 'X');
        board.place(0, 1, 'X');
        assertFalse(game.hasWinner());
    }

    @Test
    void hasWinner_shouldReturnFalseForOpponentWin() {
        Board board = game.getBoard();
        board.place(0, 0, 'O');
        board.place(0, 1, 'O');
        board.place(0, 2, 'O');
        assertFalse(game.hasWinner());
    }
}
