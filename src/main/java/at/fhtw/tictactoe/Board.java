package at.fhtw.tictactoe;

/**
 * Repräsentiert das 3x3 Spielfeld für Tic-Tac-Toe.
 */
public class Board {

    public static final int SIZE = 3;
    public static final char EMPTY = ' ';

    private final char[][] cells;

    public Board() {
        this.cells = new char[SIZE][SIZE];
        clear();
    }

    public boolean isCellEmpty(int x, int y) {
        if (!isInBounds(x, y)) {
            return false;
        }
        return cells[x][y] == EMPTY;
    }

    /**
     * Platziert den Marker eines Spielers auf einer leeren Zelle (US-01).
     *
     * @param x      Reihe (0-2)
     * @param y      Spalte (0-2)
     * @param marker Marker des Spielers ('X' oder 'O')
     * @throws IllegalArgumentException wenn Koordinaten außerhalb des Spielfelds
     *                                  liegen oder die Zelle bereits belegt ist
     */
    public void place(int x, int y, char marker) {
        if (!isInBounds(x, y)) {
            throw new IllegalArgumentException("Coordinates out of bounds: (" + x + "," + y + ")");
        }
        if (!isCellEmpty(x, y)) {
            throw new IllegalArgumentException("Cell (" + x + "," + y + ") is already occupied.");
        }
        cells[x][y] = marker;
    }

    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (cells[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Setzt das Spielfeld in den Ausgangszustand zurück.
     *
     * Alle Zellen werden mit dem EMPTY-Marker (Leerzeichen) überschrieben,
     * sodass ein neues Spiel gestartet werden kann, ohne ein neues
     * Board-Objekt zu erzeugen.
     */
    public void clear() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = EMPTY;
            }
        }
    }

    /**
     * Gibt das aktuelle Spielfeld formatiert auf der Konsole aus (US-02).
     *
     * Belegte Zellen zeigen den jeweiligen Marker (X oder O),
     * leere Zellen werden als Leerzeichen dargestellt.
     * Reihen werden durch '|' getrennt, das Feld wird oben und unten
     * mit '-' eingerahmt.
     */
    public void print() {
        System.out.println("-------");
        for (int i = 0; i < SIZE; i++) {
            StringBuilder row = new StringBuilder("|");
            for (int j = 0; j < SIZE; j++) {
                row.append(cells[i][j]).append("|");
            }
            System.out.println(row);
        }
        System.out.println("-------");
    }

    public char getCell(int x, int y) {
        if (!isInBounds(x, y)) {
            throw new IllegalArgumentException("Coordinates out of bounds: (" + x + "," + y + ")");
        }
        return cells[x][y];
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }
}
