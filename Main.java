package org.example.lld.tictoctoe;

public class Main {
    public static void main(String[] args) {
     GameManager gameManager = new GameManager();
     gameManager.initGame();
        String winner = gameManager.startGame();
        System.out.println("winner : "+winner);
    }
}
