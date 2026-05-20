package at.fhtw.tictactoe;

import java.util.Scanner;

/**
 * Hauptklasse mit der Spiellogik für Tic-Tac-Toe.
 */
public class TicTacToe {

    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private final Board board;

    public TicTacToe() {
        this.player1 = new Player('X');
        this.player2 = new Player('O');
        this.currentPlayer = player1;
        this.board = new Board();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            board.clear();
            currentPlayer = player1;
            boolean gameOver = false;

            while (!gameOver) {
                System.out.println("Current Player: " + currentPlayer.getMarker());
                board.print();

                int row = readCoordinate(scanner, "row (0-2): ");
                int col = readCoordinate(scanner, "column (0-2): ");

                if (!board.isCellEmpty(row, col)) {
                    System.out.println("Cell already occupied. Try again.");
                    continue;
                }

                board.place(row, col, currentPlayer.getMarker());

                if (hasWinner()) {
                    board.print();
                    System.out.println("Player " + currentPlayer.getMarker() + " wins!");
                    gameOver = true;
                } else if (board.isFull()) {
                    board.print();
                    System.out.println("Draw! No more moves possible.");
                    gameOver = true;
                } else {
                    switchCurrentPlayer();
                }
            }

            System.out.print("Play again? (y/n): ");
            String answer = scanner.hasNextLine() ? scanner.nextLine().trim().toLowerCase() : "n";
            playAgain = answer.equals("y") || answer.equals("yes") || answer.equals("j") || answer.equals("ja");
        }

        System.out.println("Thanks for playing!");
    }

    public void switchCurrentPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public boolean hasWinner() {
        char m = currentPlayer.getMarker();

        for (int i = 0; i < Board.SIZE; i++) {
            if (board.getCell(i, 0) == m && board.getCell(i, 1) == m && board.getCell(i, 2) == m) {
                return true;
            }
            if (board.getCell(0, i) == m && board.getCell(1, i) == m && board.getCell(2, i) == m) {
                return true;
            }
        }

        if (board.getCell(0, 0) == m && board.getCell(1, 1) == m && board.getCell(2, 2) == m) {
            return true;
        }
        if (board.getCell(0, 2) == m && board.getCell(1, 1) == m && board.getCell(2, 0) == m) {
            return true;
        }

        return false;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Board getBoard() {
        return board;
    }

    private int readCoordinate(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                return -1;
            }
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= 0 && value < Board.SIZE) {
                    return value;
                }
                System.out.println("Please enter a number between 0 and " + (Board.SIZE - 1) + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
