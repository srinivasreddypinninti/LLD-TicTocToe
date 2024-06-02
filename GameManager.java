package org.example.lld.tictoctoe;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GameManager {
    Board gameBoard;
    Deque<Player> players;

    public void initGame() {
        this.players = new LinkedList<>();
        PlayerPieceX pieceX = new PlayerPieceX();
        Player p1 = new Player("X", pieceX);
        players.add(p1);

        PlayerPieceO pieceO = new PlayerPieceO();
        Player p2 = new Player("O", pieceO);
        players.add(p2);

        gameBoard = new Board(3);
    }

    public String startGame() {

        boolean noWinner = true;
        while (noWinner) {

            Player playerTurn = players.removeFirst();
            gameBoard.printBoard();
            List<Board.Pair<Integer, Integer>> freeCells = gameBoard.getFreeCells();
            if (freeCells.isEmpty()) {
                noWinner = false;
                continue;
            }

            System.out.println("Player : " + playerTurn.name + " Enter row,col: ");
            Scanner inputScanner = new Scanner(System.in);
            String input = inputScanner.nextLine();
            String[] rc = input.split(",");
            int row = Integer.parseInt(rc[0]);
            int col = Integer.parseInt(rc[1]);

//            System.out.println(row + ", " + col);

            boolean addedSuccessfully = gameBoard.addPiece(row, col, playerTurn.piece);
            if (!addedSuccessfully) {
                System.out.println("Incorrect position chosen, try again.");
                players.addFirst(playerTurn);
                continue;
            }
            players.add(playerTurn);
            boolean isWinner = isThereWinner(row, col, playerTurn.piece.pieceType);
            if (isWinner) {
                return playerTurn.name;
            }
        }
        return "tie";

    }

    private boolean isThereWinner(int row, int col, PieceType pieceType) {

        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diagonalMatch1 = true;
        boolean diagonalMatch2 = true;
        //row check
        for (int i = 0; i < gameBoard.size; i++) {
            if (gameBoard.board[row][i] == null || gameBoard.board[row][i].pieceType != pieceType) {
                rowMatch =  false;
            }
        }
        // column check
        for (int i = 0; i < gameBoard.size; i++) {
            if (gameBoard.board[i][col] == null ||
            gameBoard.board[i][col].pieceType != pieceType) {
                colMatch = false;
            }
        }
        
        // diagonal 1 - (0,0) (1,1) (2,2)
        for (int i = 0, j = 0; i < gameBoard.size; i++, j++) {
            if (gameBoard.board[i][j] == null ||
            gameBoard.board[i][j].pieceType != pieceType) {
                diagonalMatch1 =  false;
            }
        }

        // diagonal 2 - (0,0) (1,1) (2,2)
        for (int i = 0, j = 2; i < gameBoard.size; i++, j--) {
            if (gameBoard.board[i][j] == null ||
                    gameBoard.board[i][j].pieceType != pieceType) {
                diagonalMatch2 = false;
            }
        }
        
        return rowMatch || colMatch || diagonalMatch1 || diagonalMatch2;
    }
}
