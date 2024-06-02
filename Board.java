package org.example.lld.tictoctoe;

import java.util.*;



public class Board {
    int size;
    PlayerPiece[][] board;

    public Board(int size) {
        this.size = size;
        board = new PlayerPiece[size][size];
    }

    public boolean addPiece(int row, int col, PlayerPiece piece) {
        if (board[row][col] != null) {
            return false;
        }
        board[row][col] = piece;
        return true;
    }

    public List<Pair<Integer, Integer>> getFreeCells() {
        ArrayList<Pair<Integer, Integer>> freeCells = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    freeCells.add(new Pair<>(i, j));
                }
            }
        }
        return freeCells;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    System.out.print(" "+board[i][j].pieceType.name());
                } else {
                    System.out.print("  ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    static class Pair<T, S> {
        T[] pair = (T[]) new Object[2];

        Pair(T i, T j) {
            pair[0] = i;
            pair[1] = j;
        }


    }
}
