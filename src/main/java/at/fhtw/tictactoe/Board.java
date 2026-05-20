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

    public void clear() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = EMPTY;
            }
        }
    }

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
