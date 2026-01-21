package com.ijse.gdse72.tictactoy.service;

import com.ijse.gdse72.tictactoy.controller.BoardUiController;

import java.util.Arrays;
import java.util.Random;

public class AiPlayer extends Player {

    Random random;
    BoardUiController controller;

    public AiPlayer(Board board , BoardUiController controller) {
        super(board);
        this.controller = controller;
    }

    @Override
    public void move(int row, int col) {
        int [] aiRowCol = findBestMove();

        System.out.println("row "+aiRowCol[0] + " | col " + aiRowCol[1]);

        if(aiRowCol[0] != -1 && aiRowCol[1] != -1) {

            board.updateMove(aiRowCol[0], aiRowCol[1], Piece.O);
            controller.update(aiRowCol[0], aiRowCol[1], false);
        }

    }

    public int[] findBestMove() {
        int bestValue = Integer.MIN_VALUE;//integer type eke thina kudam value eka laba genimata
        int[] bestMove = new int[]{-1, -1}; //best move eka sewimata array ekak hadala ekata wiya noheki numbers dekak dagannawa

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (BoardImpl.pieces[i][j] == Piece.EMPTY) {

                    BoardImpl.pieces[i][j] = Piece.O;  // Ai move

                    int moveValue = minimax(BoardImpl.pieces, 0, false);  // dentata thina pieces array eka ywayi

                    BoardImpl.pieces[i][j] = Piece.EMPTY; //kalin ai dapu move eka empty karanawa

                    if (moveValue > bestValue) {
                        bestMove = new int[]{i,j};// movevalue eka best valuve ekata wada wedi nam,Ai place karala thibba move eka best move eka lesa gani
                        bestValue = moveValue;
                    }
                }
            }
        }
        return bestMove;
    }

    public int minimax(Piece[][] currentBoard, int depth, boolean isMaximizing) {
        Winner winner = board.checkWinner();  // hema awasthawakama win awasthawa check karanawa

        if (winner != null) {
            if (winner.winningPiece == Piece.O) return 10 - depth;  // AI win karana awastha
            if (winner.winningPiece == Piece.X) return depth - 10;  // human win karana awastha
            if (winner.winningPiece == Piece.EMPTY) return 0;  // Draw wana awastha
        }

        if (isMaximizing) {  // AI's turn
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    if (currentBoard[i][j] == Piece.EMPTY) {

                        currentBoard[i][j] = Piece.O; //empty wana palamu awasthawe sita awasanaya dakwa piliwelata siyaluma awastha check karayi

//                            System.out.println("Ai Chance");
//                            System.out.println(Arrays.toString(currentBoard[i]));
//                            System.out.println();
//
                        int score = minimax(currentBoard, depth + 1, false); // recursion
                        currentBoard[i][j] = Piece.EMPTY;
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;

        } else {  // Human's turn
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    if (currentBoard[i][j] == Piece.EMPTY) {

                        currentBoard[i][j] = Piece.X; //empty wana palamu awasthawe sita awasanaya dakwa piliwelata siyaluma awastha check karayi

//                            System.out.println("Human Chance");
//                            System.out.println(Arrays.toString(currentBoard[i]));
//                            System.out.println();

                        int score = minimax(currentBoard, depth + 1, true);
                        currentBoard[i][j] = Piece.EMPTY;
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }
}
