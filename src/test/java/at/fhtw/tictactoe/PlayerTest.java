package at.fhtw.tictactoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void constructor_shouldStoreMarkerX() {
        Player player = new Player('X');
        assertEquals('X', player.getMarker());
    }

    @Test
    void constructor_shouldStoreMarkerO() {
        Player player = new Player('O');
        assertEquals('O', player.getMarker());
    }

    @Test
    void constructor_shouldAcceptAnyChar() {
        Player player = new Player('A');
        assertNotNull(player);
    }

    @Test
    void getMarker_shouldReturnCorrectMarker() {
        Player player = new Player('X');
        assertEquals('X', player.getMarker());
    }

    @Test
    void getMarker_shouldReturnDifferentMarkers() {
        Player p1 = new Player('X');
        Player p2 = new Player('O');
        assertNotEquals(p1.getMarker(), p2.getMarker());
    }

    @Test
    void getMarker_shouldNotReturnEmptyChar() {
        Player player = new Player('X');
        assertNotEquals(' ', player.getMarker());
    }
}
